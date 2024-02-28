#pragma once

#include "stdafx.h"
#include "JavaInputStream.h"

log4cplus::Logger JavaInputStream::logger =
    log4cplus::Logger::getInstance(_T("org.jvoicexml.sapi.cpp.JavaInputStream"));

// ---------------------------------------------------------------------------
// %%Deconstructor: CJavaInputStream::~CJavaInputStream()
// ---------------------------------------------------------------------------
JavaInputStream::~JavaInputStream()
{
	if (this->env != NULL)
    {
		env->DeleteWeakGlobalRef(inputStream);
		jvm->DetachCurrentThread();
	}
}

// ---------------------------------------------------------------------------
// %%Function: CJavaInputStream::SetJavaInputStream
// ---------------------------------------------------------------------------
 STDMETHODIMP
JavaInputStream::SetJavaInputStream(JNIEnv *env, jobject object)
 {
	/* the java environment */
	this->env = env;

	/* our InputStream on the javaside */
	inputStream = env->NewWeakGlobalRef(object);

	// prefetch and cache some MethodIDs for better performance
	jclass clazz = env->GetObjectClass(inputStream);

	//method header of "int InputStream.read(byte[] b, int off, int len)"
	// => "([BII)I"
	jReadByteArray = env->GetMethodID(clazz, "read", "([BII)I");
	if (jReadByteArray == NULL)
    {
        LOG4CPLUS_ERROR(logger, "Method 'int read(byte[], int, int)' not found!");
		return S_FALSE;
	}

	//method header of "int InputStream.read()"
	// => "()I"
	jReadByte = env->GetMethodID(clazz, "read", "()I");
	if (jReadByte == NULL)
    {
		LOG4CPLUS_ERROR(logger,  "Method 'int read()' not found!");
		return S_FALSE;
	}
	
	jAvailable = env->GetMethodID(clazz, "available", "()I");
	if (jAvailable == NULL)
    {
		LOG4CPLUS_ERROR(logger,  "Method 'int available()' not found!");
		return S_FALSE;
	}

	return S_OK;
} // CJavaInputStream::setJavaInputStream


// ---------------------------------------------------------------------------
// %%Function: CJavaInputStream::QueryInterface
// ---------------------------------------------------------------------------
 STDMETHODIMP
JavaInputStream::QueryInterface(REFIID riid, void** ppv)
{
    if (ppv == NULL)
    {
        return E_INVALIDARG;
    }
	if (riid == IID_IUnknown)
    {
		*ppv = static_cast<IUnknown*> (this);
        AddRef();
        return S_OK;
    }

	if (riid == IID_IStream)
    {
		*ppv = static_cast<IStream*> (this);
		AddRef();
		return S_OK;
	}

	if (riid == IID_IJavaInputStream)
    {
		*ppv = static_cast<InputStream*> (this);
		AddRef();
		return S_OK;
	}

    *ppv = NULL;
    return E_NOINTERFACE;
}  // CJavaInputStream::QueryInterface


// ---------------------------------------------------------------------------
// %%Function: CJavaInputStream::Read
// ---------------------------------------------------------------------------
 STDMETHODIMP
JavaInputStream::Read(void *pv, ULONG cb, ULONG *pcbRead)
{
	//std::clog << "IStream:Read" << std::endl;

	// if we have no connection to the jvm, return immediately
	if (jvm == NULL)
    {
		if (pcbRead)
        {
			*pcbRead = 0;
		}
		return S_FALSE;
	}

	// Attach the current thread to the running JVM
	// - this is needed, because thread(s) started by the recognizer are not connected to the JVM
	//		and the precached "env"-variable is only valid in the thread it was created
	int res = jvm->AttachCurrentThreadAsDaemon((void**)&env, NULL);

	//if the java environment has not been set, return immediately
	if (env == NULL)
    {
		if (pcbRead)
        {
			*pcbRead = 0;
		}
		return S_FALSE;
	}
	
	// look if enough bytes are available
	//	if not, wait some time for more data
	ULONG available = env->CallIntMethod(inputStream, jAvailable);
	{
		// the above code may raise an exception in JVM
		jthrowable exc;
		exc = env->ExceptionOccurred();
		if (exc)
        {
            LOG4CPLUS_ERROR(logger, "Exception in JavaInputStream::read(). available()");
			env->ExceptionDescribe();
			//Clear the Exception in the JVM
			env->ExceptionClear();
			//Close this IStream
			if (pcbRead)
            {
				*pcbRead = 0;
			}
			return S_FALSE;
		}
	}

	int readTimeOut = 10; //50ms * 10 = 500ms timeout
	while (available < cb && readTimeOut > 0)
    {
		//std::clog << "Available: " << available << ";requested: " << cb << std::endl;
		//std::cout << "Not enough data! Sleeping...";
		Sleep(50); //sleep 50ms
		//std::cout << "and awake!" << std::endl;
		readTimeOut--;
		available = env->CallIntMethod(inputStream, jAvailable);
		{
			// the above code may raise an exception in JVM
			jthrowable exc;
			exc = env->ExceptionOccurred();
			if (exc)
            {
                LOG4CPLUS_ERROR(logger, "Exception in JavaInputStream::read(). available()");
				env->ExceptionDescribe();
				//Clear the Exception in the JVM
				env->ExceptionClear();
				//Close this IStream
				if (pcbRead)
                {
					*pcbRead = 0;
				}
				return S_FALSE;
			}
		}
	}

	
	// prepare internal buffer to get the requested bytes
	jbyteArray arr = env->NewByteArray(cb);
	jbyte* buf = (jbyte*)malloc(cb * sizeof(jbyte));

	// call InputStream.read(byte[], int offset, int len)
	//jvalue args[3];
	//args[0] = (jvalue)arr;
	//args[1] = (jvalue)0;
	//args[2] = (jvalue)cb;
	//jint bytesRead = env->CallIntMethodA(inputStream, jReadByteArray, args);
	//jint cbJInt = (jint)cb;
	jint bytesRead = env->CallIntMethod(inputStream, jReadByteArray, arr, 0, (jint)cb);
	{
		// the above code may raise an exception in JVM
		jthrowable exc;
		exc = env->ExceptionOccurred();
		if (exc) {
            LOG4CPLUS_ERROR(logger, "Exception in JavaInputStream::read(). read()");
			env->ExceptionDescribe();
			//Clear the Exception in the JVM
			env->ExceptionClear();
			//Close this IStream
			if (pcbRead) {
				*pcbRead = 0;
			}
			return S_FALSE;
		}
	}

	// check if we have bytes in our puffer
	if (bytesRead == -1)
    {
		// InputStream's EOF
		//	 ref: http://msdn.microsoft.com/en-us/library/aa380011%28v=VS.85%29.aspx
        LOG4CPLUS_DEBUG(logger, "discovered EOF!");
		env->ReleaseByteArrayElements(arr, NULL, JNI_ABORT);
		if (pcbRead) {
			*pcbRead = 0;
		}
		return S_FALSE;
	}

	// read the elt's in our cpp-compliant array "buf"
	env->GetByteArrayRegion(arr, 0, bytesRead, buf);

	// give the elt's back to the callee
	memcpy(pv, buf, bytesRead);
	env->ReleaseByteArrayElements(arr, NULL, JNI_ABORT);
	
	if (pcbRead) {
		*pcbRead = bytesRead;
	}
	if (bytesRead == cb) {
		return S_OK;
	} else {
		return S_FALSE;
	}
}  // CJavaInputStream::Read

