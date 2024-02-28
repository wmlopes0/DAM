#include "stdafx.h"
#include "JNIUtils.h"
#include <log4cplus/logger.h>
#include <log4cplus/loggingmacros.h>
#include "log4cplus/consoleappender.h"
#include "JavaLoggingAppender.h"
#include "JavaInputStream.h" 
#include "ErrorLog.h"
#include "COMClassFactory.h"

static log4cplus::Logger logger =
    log4cplus::Logger::getInstance(_T("org.jvoicexml.sapi.cpp.JNI"));


COMClassFactory   classFactory; // Factory for JInputStream etc.
DWORD dwRegister; // Token to unregister JInputStream
JavaVM *jvm; //Handle to the Java Virtual Machine

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *jvm_init, void *reserved)
{
	// cache the jvm-handle
	jvm = jvm_init;

	// inilialize COM
	HRESULT hr = ::CoInitializeEx(NULL, COINIT_MULTITHREADED);
    if (FAILED(hr))
    {
        char buffer[1024];
        GetErrorMessage(buffer, sizeof(buffer), "Initializing COM failed!",
            hr);
        LOG4CPLUS_FATAL(logger, buffer);
        return JNI_ERR;
    }

	// register own component IJavaInputStream etc.
	hr = ::CoRegisterClassObject(CLSID_JavaInputStream, &classFactory, 
		CLSCTX_SERVER, REGCLS_MULTIPLEUSE, &dwRegister);
	if (FAILED(hr)) {
        char buffer[1024];
        GetErrorMessage(buffer, sizeof(buffer), "Registering component \"JavaInputStream\" failed!",
            hr);
        LOG4CPLUS_FATAL(logger, buffer);
        return JNI_ERR;
	}

	hr = ::CoRegisterClassObject(CLSID_ErrorLog, &classFactory, 
		CLSCTX_SERVER, REGCLS_MULTIPLEUSE, &dwRegister);
	if (FAILED(hr)) {
        char buffer[1024];
        GetErrorMessage(buffer, sizeof(buffer), "Registering component \"ErrorLog\" failed!",
            hr);
        LOG4CPLUS_FATAL(logger, buffer);
        return JNI_ERR;
	}

    return JNI_VERSION_1_6;
}

JNIEXPORT void JNICALL JNI_OnUnload(JavaVM *jvm, void *reserved)
{
	::CoUninitialize();
}


void ThrowJavaException(JNIEnv* env, char* exceptionClassName, char* message)
{
    jclass exception = env->FindClass(exceptionClassName);
    if (exception == 0) /* Unable to find the new exception class, give up. */
    {
        LOG4CPLUS_ERROR(logger, message);
        return;
    }
    env->ThrowNew(exception, message);
}

BOOL GetMethodId(JNIEnv* env, const char* className, const char* methodName,
                 const char* sig, jclass& clazz, jmethodID& methodId)
{
    clazz = env->FindClass(className);
    if (clazz == NULL)
    {
        char msg[512];
        _snprintf(msg, sizeof(msg), "Unable to find class %s!", className);
        ThrowJavaException(env, "java/lang/NullPointerException", msg);
        return FALSE;
    }
    methodId = env->GetMethodID(clazz, methodName, sig);
    if (methodId == NULL)
    {
        char msg[1024];
        _snprintf(msg, sizeof(msg), "Unable to find method '%s(%s)'!", methodName, sig);
        ThrowJavaException(env, "java/lang/NullPointerException", msg);
        return FALSE;
    }
    return TRUE;
}

BOOL GetStaticMethodId(JNIEnv* env, const char* className, const char* methodName,
                 const char* sig, jclass& clazz, jmethodID& methodId)
{
    clazz = env->FindClass(className);
    if (clazz == NULL)
    {
        char msg[512];
        _snprintf(msg, sizeof(msg), "Unable to %s!", className);
        ThrowJavaException(env, "java/lang/NullPointerException", msg);
        return FALSE;
    }
    methodId = env->GetStaticMethodID(clazz, methodName, sig);
    if (methodId == NULL)
    {
        char msg[1024];
        _snprintf(msg, sizeof(msg), "Unable to find method '%s(%s)'!", methodName, sig);
        ThrowJavaException(env, "java/lang/NullPointerException", msg);
        return FALSE;
    }
    return TRUE;
}

BOOL GetStaticObjectField(JNIEnv* env, const char* className, const char* fieldName,
                 const char* sig, jobject& object)
{
    jclass clazz = env->FindClass(className);
    if (clazz == NULL)
    {
        char msg[512];
        _snprintf(msg, sizeof(msg), "Unable to %s!", className);
        ThrowJavaException(env, "java/lang/NullPointerException", msg);
        return FALSE;
    }
    jfieldID fieldId = env->GetStaticFieldID(clazz, fieldName, sig);
    if (fieldId == NULL)
    {
        char msg[1024];
        _snprintf(msg, sizeof(msg), "Unable to find field '%s(%s)'!", fieldName, sig);
        ThrowJavaException(env, "java/lang/NullPointerException", msg);
        return FALSE;
    }
    object = env->GetStaticObjectField(clazz, fieldId);
    return TRUE;
}

