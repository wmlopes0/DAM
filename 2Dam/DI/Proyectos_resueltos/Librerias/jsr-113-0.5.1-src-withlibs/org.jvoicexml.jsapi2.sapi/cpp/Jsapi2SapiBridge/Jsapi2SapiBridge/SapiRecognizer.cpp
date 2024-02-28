#include "stdafx.h"
#include "org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer.h"
#include "Recognizer.h"
#include "JNIUtils.h"
#include <log4cplus/loggingmacros.h>

// wraps a java InputStream in an IStream
#include "JavaInputStream.h"

//static initializations
static log4cplus::Logger logger =
    log4cplus::Logger::getInstance(_T("org.jvoicexml.sapi.cpp.SapiRecognizer"));

/*
 * Class:     org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer
 * Method:    nativGetBuildInGrammars
 * Signature: (J)Ljava/util/Vector;
 */
JNIEXPORT jobject JNICALL Java_org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer_sapiGetBuildInGrammars
  (JNIEnv *env, jobject object, jlong recognizerHandle)
{
	return false;
}

/*
 * Class:     org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer
 * Method:    nativeHandleAllocate
 * Signature: (J)V
 */
JNIEXPORT jlong JNICALL Java_org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer_sapiAllocate
  (JNIEnv *env, jobject object)
{
    /* create new Recognizer class */
    Recognizer* recognizer = new Recognizer(hWnd, env, object);
    /* check if Handle valid */
    if (FAILED(recognizer->hr))
    {      
        char buffer[1024];
        GetErrorMessage(buffer, sizeof(buffer), "Allocation of recognizer failed",
            recognizer->hr);
        ThrowJavaException(env, "javax/speech/EngineException", buffer);
        return 0;
    }
    return (jlong) recognizer;
}

/*
 * Class:     org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer
 * Method:    sapiSetRecognizerInputStream
 * Signature: (JLjava/io/InputStream;FIIZZLjava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer_sapiSetRecognizerInputStream
  (JNIEnv *env, jobject caller, jlong recognizerHandle, jobject inputStream, 
	jfloat sampleRate, jint bitsPerSample, jint channels, jboolean endian, jboolean signedData, jstring encoding) 
{
    Recognizer* recognizer = (Recognizer*)recognizerHandle;
    HRESULT hr;

    // create instance of JInputStream
    CComPtr<IStream> stream;
    hr = stream.CoCreateInstance(CLSID_JavaInputStream);

    // query Setter-Interface
    CComPtr<InputStream> jStreamSetter;
    if (SUCCEEDED(hr)) 
	{
        hr = stream->QueryInterface(IID_IJavaInputStream, (void**) &jStreamSetter);
    }
    // setup our IStream with the given Java InputStream as it's source
    if (SUCCEEDED(hr)) 
	{
        hr = jStreamSetter->SetJavaInputStream(env, inputStream);
    }

    // create ISpStream for the recognizer
    CComPtr<ISpStream> cpSpStream;
    if (SUCCEEDED(hr)) 
	{
        hr = cpSpStream.CoCreateInstance(CLSID_SpStream);
    }

    /* set WAVEFORMATEX accordingly */
    // see http://msdn.microsoft.com/en-us/library/ms720517%28v=VS.85%29.aspx
    WAVEFORMATEX *format;
    format = (WAVEFORMATEX*)malloc(sizeof(WAVEFORMATEX));
    format->wFormatTag = WAVE_FORMAT_PCM;	//constant
    format->nChannels = channels;			//variable
    format->nSamplesPerSec = (DWORD)sampleRate;	//variable
    format->wBitsPerSample = bitsPerSample;	//variable
    format->nBlockAlign = (format->wBitsPerSample * format->nChannels) / 8;	//constant
    format->nAvgBytesPerSec = format->nSamplesPerSec * format->nBlockAlign;	//constant
    format->cbSize = 0;	//constant

    // set the Java IStream and it's format as the source for the SpeechStream
    if (SUCCEEDED(hr))
    {
        hr = cpSpStream->SetBaseStream(stream, SPDFID_WaveFormatEx, format);
    }

    /* set the constructed SpeechStream as the new recognizerInput */
    /* NOTE:
    *	The RecognizerContext must be paused before the InputStream-switch.
    *	This responsibility lies on the Java-side!
    */
    if (SUCCEEDED(hr)) 
    {
        hr = recognizer->SetRecognizerInputStream(cpSpStream);
    }

    if (SUCCEEDED(hr))
    {
        return JNI_TRUE;
    }
    else
    {
        //insert ERROR-Logging here
        //LOG4CPLUS_ERROR(logger, "CPP: Error setting a new InputStream! ErrorCode: 0x" << std::hex << std::uppercase << hr);
		
		char buffer[1024];
        GetErrorMessage(buffer, sizeof(buffer), "Error setting a new InputStream!",
            hr);
        ThrowJavaException(env, "javax/speech/EngineException", buffer);
        
		return JNI_FALSE;
    }
}
 
/*
 * Class:     org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer
 * Method:    nativHandleDeallocate
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer_sapiDeallocate
  (JNIEnv *env, jobject object, jlong recognizerHandle) {

	Recognizer* recognizer = (Recognizer*)recognizerHandle;
	delete recognizer;
}

/*
 * Class:     org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer
 * Method:    nativHandlePause
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer_sapiPause__J
  (JNIEnv *env, jobject object, jlong recognizerHandle){
		
	Recognizer* recognizer = (Recognizer*)recognizerHandle;
	HRESULT hr = recognizer->Pause();
	
	if (FAILED(hr))
    {
		LOG4CPLUS_ERROR(logger, "Could not pause the recognizer!");
        char buffer[1024];
        GetErrorMessage(buffer, sizeof(buffer), "Pause recognizer failed",
            recognizer->hr);
        ThrowJavaException(env, "javax/speech/EngineException", buffer);
    }
}

/*
 * Class:     org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer
 * Method:    nativHandlePause
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer_sapiPause__JI
  (JNIEnv *env, jobject object, jlong recognizerHandle, jint flags){

}

/*
 * Class:     org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer
 * Method:    sapiResume
 * Signature: (J[Ljava/lang/String;[Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer_sapiResume
(JNIEnv *env, jobject object, jlong handle, jobjectArray grammars, jobjectArray references)
{
	Recognizer* recognizer = (Recognizer*)handle;
    jsize size = env->GetArrayLength(grammars);
    if (size == 0)
    {
        return JNI_TRUE;
    }
    for (jsize i=0; i<size; i++)
    {
        jstring grammar = (jstring) env->GetObjectArrayElement(grammars, i);
		const wchar_t* gram = (const wchar_t*)env->GetStringChars(grammar, NULL);	
        
		jstring reference = (jstring) env->GetObjectArrayElement(references, i);
		const wchar_t* ref = (const wchar_t*)env->GetStringChars(reference, NULL);	

		HRESULT hr= recognizer->LoadGrammar(gram, ref);
        if ( FAILED(hr) )
        {
            char buffer[1024];
			GetErrorMessage(buffer, sizeof(buffer),
                "Resume Recognizer: load Grammar failed",
                hr);
            ThrowJavaException(env, "javax/speech/EngineStateException", buffer);
        }
    }
	
	return recognizer->Resume();
}

/*
 * Class:     org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer
 * Method:    sapiSetGrammar
 * Signature: (JLjava/lang/String;Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer_sapiSetGrammar
(JNIEnv *env, jobject object, jlong recognizerHandle, jstring grammarPath, jstring reference){
		
	Recognizer* recognizer = (Recognizer*)recognizerHandle;
    const wchar_t* gram = (const wchar_t*)env->GetStringChars(grammarPath, NULL);
	const wchar_t* ref = (const wchar_t*)env->GetStringChars(reference, NULL);	
	
	HRESULT hr = recognizer->LoadGrammarFile(gram, ref);
	if (SUCCEEDED(hr))
    {
        return JNI_TRUE;
	}
	else
    {
		return JNI_FALSE;
	}
}

/*
 * Class:     org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer
 * Method:    sapiSetGrammarContent
 * Signature: (JLjava/lang/String;Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer_sapiSetGrammarContent
(JNIEnv *env, jobject object, jlong recognizerHandle, jstring grammarContent, jstring reference)
{		
	Recognizer* recognizer = (Recognizer*)recognizerHandle;
    const wchar_t* grammar = (const wchar_t*)env->GetStringChars(grammarContent, NULL);
	const wchar_t* ref = (const wchar_t*)env->GetStringChars(reference, NULL);	
	
	HRESULT hr = recognizer->LoadGrammar(grammar, ref);
	if (SUCCEEDED(hr))
    {
        return JNI_TRUE;
	}
	else
    {
		return JNI_FALSE;
	}
}

/*
 * Class:     org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer
 * Method:    getChangeRequestListener
 * Signature: ()Lorg/jvoicexml/jsapi2/EnginePropertyChangeRequestListener;
 */
JNIEXPORT jobject JNICALL Java_org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer_getChangeRequestListener
 (JNIEnv *env, jobject object)
{
	return NULL;
}

/*
 * Class:     org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer
 * Method:    sapiRecognize
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jint JNICALL Java_org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer_sapiRecognize
  (JNIEnv *env, jobject object, jlong handle, jobjectArray returnResult)
{
	const jint EVENT_NOMATCH = -1;

    Recognizer* recognizer = (Recognizer*) handle;
	WCHAR* result[2];
	result[0] = NULL; result[1] = NULL;
	HRESULT hr = recognizer->StartRecognition(result);
	if (FAILED(hr))
    {
        char buffer[1024];
        GetErrorMessage(buffer, sizeof(buffer),
            "Abort Recognition failed",
            hr);
        ThrowJavaException(env, "javax/speech/EngineException", buffer);
		return hr;
	}

	// if the recognizer successfully matched something, update the returnResult
	if (hr == S_OK)
	{
		jstring ruleName = env->NewString((jchar*)result[0], wcslen(result[0]));
		jstring utterance = env->NewString((jchar*)result[1], wcslen(result[1]));
		env->SetObjectArrayElement(returnResult, 0, ruleName); //ruleName
		env->SetObjectArrayElement(returnResult, 1, utterance); //utterance
	}

    return hr;
}


/*
 * Class:     org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer
 * Method:    sapiAbortRecognition
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer_sapiAbortRecognition
(JNIEnv *env, jobject object, jlong handle)
{
	Recognizer* recognizer = (Recognizer*) handle;
	
	HRESULT hr = recognizer->AbortRecognition();
    if ( FAILED(hr) )
    {
        char buffer[1024];
        GetErrorMessage(buffer, sizeof(buffer),
            "Abort Recognition failed",
            hr);
        ThrowJavaException(env, "javax/speech/EngineStateException", buffer);
    }
}

JNIEXPORT jobject JNICALL Java_org_jvoicexml_jsapi2_sapi_recognition_SapiRecognizer_sapiGetAudioFormat
  (JNIEnv *env, jobject object, jlong handle)
{
    WAVEFORMATEX format;
	HRESULT hr = Recognizer::GetAudioFormat(format);
    if (FAILED(hr))
    {
        char buffer[1024];
        GetErrorMessage(buffer, sizeof(buffer), "GetAudioFormat failed", hr);
        ThrowJavaException(env, "javax/speech/synthesis/SpeakableException",
            buffer);
		return NULL;
    }
    jclass clazz = env->FindClass("javax/sound/sampled/AudioFormat");
    if (clazz == NULL)
    {
        ThrowJavaException(env, "javax/sound/sampled/AudioFormat",
            "Unable to create javax/sound/sampled/AudioFormat!");
        return NULL;
    }
    jmethodID constructor = env->GetMethodID(clazz, "<init>", "(FIIZZ)V");
    if (constructor == NULL)
    {
        ThrowJavaException(env, "java/lang/NullPointerException",
            "Constructor for javax/sound/sampled/AudioFormat not found!");
        return NULL;
    }

    return env->NewObject(clazz, constructor, (float)format.nSamplesPerSec,
        (int)format.wBitsPerSample, (int)format.nChannels, JNI_TRUE, JNI_FALSE);
}
