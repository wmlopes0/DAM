#include "stdafx.h"
#include <org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer.h>
#include "Synthesizer.h"
#include <sperror.h>
#include "JNIUtils.h"

/*
 * Class:     org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer
 * Method:    getSpeakable
 * Signature: (Ljava/lang/String;)Ljavax/speech/synthesis/Speakable;
 */
JNIEXPORT jobject JNICALL Java_org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer_getSpeakable
(JNIEnv *, jobject, jstring)
{
	return NULL;
}

/*
 * Class:     org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer
 * Method:    sapiHandleAllocate
 * Signature: (Ljavax/speech/synthesis/Voice;)J
 */
JNIEXPORT jlong JNICALL Java_org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer_sapiHandleAllocate
  (JNIEnv *env, jobject obj, jobject jvoice)
{
    const wchar_t* engineName;
    if (jvoice == NULL)
    {
        engineName = NULL;
    }
    else 
    {
        jclass voiceClass = env->GetObjectClass(jvoice);
        if (voiceClass == NULL)
        {
            ThrowJavaException(env, "java/lang/NullPointerException",
                "Unable to create javax/speech/synthesis/Voice!");
            return 0;
        }
        jmethodID voiceNameMethod = env->GetMethodID(voiceClass, "getName",
            "()Ljava/lang/String;");
        if (voiceNameMethod == NULL)
        {
            ThrowJavaException(env, "java/lang/NullPointerException",
                "Unable to get the getName method of javax/speech/synthesis/Voice!");
            return 0;
        }
        jstring name = (jstring) env->CallObjectMethod(jvoice, voiceNameMethod);
        if (name == NULL)
        {
            engineName = NULL;
        }
        else
        {
            engineName = (const wchar_t*)env->GetStringChars(name, NULL);
        }
    }

	/* create new Synthesizer class */
	Synthesizer* synth = new Synthesizer(engineName);
    if (FAILED(synth->GetLastHResult()))
    {
        char buffer[1024];
        GetErrorMessage(buffer, sizeof(buffer), "Allocation of synthesizer failed",
            synth->GetLastHResult());
        ThrowJavaException(env, "javax/speech/EngineException", buffer);
        return 0;
    }
    return (jlong) synth;
}

/*
 * Class:     org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer
 * Method:    sapiHandleCancel
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer_sapiHandleCancel__J
  (JNIEnv *env, jobject obj, jlong handle)
{
	Synthesizer* synth = (Synthesizer*) handle;
	boolean success = synth->Cancel();
    return success ? JNI_TRUE : JNI_FALSE;
}

/*
 * Class:     org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer
 * Method:    sapiHandleCancel
 * Signature: (JI)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer_sapiHandleCancel__JI
  (JNIEnv * env, jobject obj, jlong handle, jint id)
{
	return NULL;
}

/*
 * Class:     org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer
 * Method:    sapiHandleCancelAll
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer_sapiHandleCancelAll
  (JNIEnv * env, jobject obj, jlong handle)
{
	return NULL;
}

/*
 * Class:     org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer
 * Method:    sapiHandlDeallocate
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer_sapiHandlDeallocate
  (JNIEnv * env, jobject obj, jlong handle)
{
	Synthesizer* synthesizer = (Synthesizer*) handle;
	delete synthesizer;
}

/*
 * Class:     org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer
 * Method:    sapiHandlPause
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer_sapiHandlPause
  (JNIEnv *env, jobject obj, jlong handle)
{
	Synthesizer* synth = (Synthesizer*) handle;	
	
	synth->Pause();
    if (FAILED(synth->GetLastHResult()))
    {
        char buffer[1024];
        GetErrorMessage(buffer, sizeof(buffer), "Pause recognizer failed",
            synth->GetLastHResult());
        ThrowJavaException(env, "javax/speech/EngineException", buffer);
    }
}

/*
 * Class:     org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer
 * Method:    sapiHandlResume
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer_sapiHandlResume
  (JNIEnv *env, jobject obj, jlong handle)
{	
	Synthesizer* synth = (Synthesizer*) handle;	
	boolean success = synth->Resume();
    return success ? JNI_TRUE : JNI_FALSE;
}

/*
 * Class:     org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer
 * Method:    sapiHandleSpeak
 * Signature: (JILjava/lang/String;)V
 */
JNIEXPORT jbyteArray JNICALL Java_org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer_sapiHandleSpeak
  (JNIEnv *env, jobject obj, jlong handle, jint id, jstring item)
{
	Synthesizer* synth = (Synthesizer*) handle;

	/* get string and cast as const wchar_t* */
    const wchar_t* utterance = (const wchar_t*)env->GetStringChars(item, NULL);
		
    long size;
    byte* buffer = NULL;

	std::vector<std::wstring> words;
	std::vector<float> wordTimes;
	std::vector<std::pair<std::wstring, int>> phoneInfos;


	HRESULT hr = synth->Speak(utterance, false, size, buffer, words, wordTimes, phoneInfos);
	if (FAILED(hr))
    {
        if (buffer != NULL)
        {
            delete[] buffer;
        }
        char msg[1024];
        GetErrorMessage(msg, sizeof(msg), "Speak failed", hr);
        ThrowJavaException(env, "javax/speech/synthesis/SpeakableException", msg);
        return NULL;
    }

	//Send extra result information Java side
	{
		//Get Class of Synthesizer object
		jclass synthesizerClass = env->GetObjectClass(obj);

		//Send words to Java side
		if (words.size() > 0)
		{			
			//Get ID of method to call
			jmethodID setWordsMID = env->GetMethodID(synthesizerClass, "setWords", "(I[Ljava/lang/String;)V");

			//Allocate a Java array to store words
			jobjectArray wordsArray = env->NewObjectArray(words.size(), 
														  env->FindClass("java/lang/String"),
														  env->NewString((jchar*)"", 0));

			//Populate Java array
			for (size_t i = 0; i < words.size(); i++)
			{
				env->SetObjectArrayElement(wordsArray, i, env->NewString((jchar*)words.at(i).c_str(), words.at(i).size()));
			}

			//Put array in synthesizer queue
			env->CallVoidMethod(obj, setWordsMID, id, wordsArray);
		}


		if (wordTimes.size() > 0)
		{
			//Get ID of method to call
			jmethodID setWordsTimesMID = env->GetMethodID(synthesizerClass, "setWordsStartTimes", "(I[F)V");

			jfloatArray timesArray = env->NewFloatArray(wordTimes.size());

			//Copy values to Java array (creating a static array....)
			float* times = new float[wordTimes.size()];
			memcpy(times, &wordTimes[0], sizeof(float) * wordTimes.size()); //Because std::vector uses contiguous memory
			
			env->SetFloatArrayRegion(timesArray, 0, wordTimes.size(), times);

			//And send Java array
			env->CallVoidMethod(obj, setWordsTimesMID, id, timesArray);

			//Now delete the native array
			delete[] times;
		}

		if (phoneInfos.size() > 0)
		{
			//Get the ID of the method to call
			jmethodID setPhonesMID = env->GetMethodID(synthesizerClass, "setPhonesInfo", "(I[Ljavax/speech/synthesis/PhoneInfo;)V");
			
			//Allocate a Java array to store phone infos
			jobjectArray phonesArray = env->NewObjectArray(phoneInfos.size(), 
														   env->FindClass("javax/speech/synthesis/PhoneInfo"),
														   NULL);
			
			//Prepare the mass creation of PhoneInfo Java objects
			jclass clazz = env->FindClass("javax/speech/synthesis/PhoneInfo");
			jmethodID constructor = env->GetMethodID(clazz, "<init>", "(Ljava/lang/String;I)V");

			//Populate Java array
			for (size_t i = 0; i < phoneInfos.size(); i++)
			{
				jstring jPhone = env->NewString((jchar*)phoneInfos.at(i).first.c_str(), phoneInfos.at(i).first.size());
				jint duration = phoneInfos.at(i).second;
				env->SetObjectArrayElement(phonesArray, i, env->NewObject(clazz, constructor, jPhone, duration));
			}

			//Put array in synthesizer queue
			env->CallVoidMethod(obj, setPhonesMID, id, phonesArray);
		}

	}

	//Prepare return value
    jbyteArray jb = env->NewByteArray(size);
    env->SetByteArrayRegion(jb, 0, size, (jbyte *)buffer);
    delete[] buffer;

    return jb;
}


/*
 * Class:     org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer
 * Method:    sapiHandleSpeakSsml
 * Signature: (JILjava/lang/String;)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer_sapiHandleSpeakSsml
  (JNIEnv *env, jobject obj, jlong handle, jint id, jstring markup)
{
	Synthesizer* synth = (Synthesizer*) handle;
	
	/* get string and cast as const wchar_t* */
    const wchar_t* utterance = (const wchar_t*)env->GetStringChars(markup, NULL);
    long size;
    byte* buffer = NULL;

	std::vector<std::wstring> words;
	std::vector<float> wordTimes;
	std::vector<std::pair<std::wstring, int>> phoneInfos;

	HRESULT hr = synth->Speak(utterance, true, size, buffer, words, wordTimes, phoneInfos);
    if (FAILED(hr))
    {
        if (buffer != NULL)
        {
            delete[] buffer;
        }
        char msg[1024];
        GetErrorMessage(msg, sizeof(msg), "Speak SSML failed", hr);
        ThrowJavaException(env, "javax/speech/synthesis/SpeakableException", msg);
        return NULL;
    }


	//Prepare return value
    jbyteArray jb = env->NewByteArray(size);
    env->SetByteArrayRegion(jb, 0, size, (jbyte *)buffer);
    delete[] buffer;

    return jb;
}

/*
 * Class:     org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer
 * Method:    sapiGetAudioFormat
 * Signature: (J)Ljavax/sound/sampled/AudioFormat;
 */
JNIEXPORT jobject JNICALL Java_org_jvoicexml_jsapi2_sapi_synthesis_SapiSynthesizer_sapiGetAudioFormat
  (JNIEnv *env, jobject object, jlong handle)
{
    WAVEFORMATEX format;
	HRESULT hr = Synthesizer::GetAudioFormat(format);
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

	//Store bytes-per-second in synthesizer to compute word times
	Synthesizer::setBytesPerSecond((float)format.nAvgBytesPerSec);

    return env->NewObject(clazz, constructor, (float)format.nSamplesPerSec,
        (int)format.wBitsPerSample, (int)format.nChannels, JNI_TRUE, JNI_FALSE);
}

