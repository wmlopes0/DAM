#include "stdafx.h"
#include "org_jvoicexml_jsapi2_sapi_SapiEngineListFactory.h"
#include "Synthesizer.h"
#include "JNIUtils.h"

JNIEXPORT jobjectArray JNICALL Java_org_jvoicexml_jsapi2_sapi_SapiEngineListFactory_sapiGetVoices
  (JNIEnv *env, jobject obj)
{
    Voice* voices = NULL;
    ULONG num;
    Synthesizer::ListVoices(voices, num);

    jclass clazz;
    jmethodID constructor;
    BOOL rc = GetMethodId(env, "javax/speech/synthesis/Voice",
        "<init>", "(Ljavax/speech/SpeechLocale;Ljava/lang/String;III)V",
        clazz, constructor);
    if (!rc)
    {
        return NULL;
    }
    jobjectArray jvoices = env->NewObjectArray(num, clazz, NULL);
    if (jvoices == NULL)
    {
        char* msg = "Error creating the voices array!";
        ThrowJavaException(env, "java/lang/NullPointerException", msg);
        return NULL;
    }
    for (ULONG i=0; i<num; i++)
    {
        wchar_t* voiceName = voices[i].GetName();
        jstring name = env->NewString((jchar*)voiceName, wcslen(voiceName));
        jobject voice = env->NewObject(clazz, constructor, NULL, name, -1, -1, -1);
        env->SetObjectArrayElement(jvoices, i, voice);
    }

    if (voices != NULL)
    {
        delete[] voices;
    }
    return jvoices;
}

