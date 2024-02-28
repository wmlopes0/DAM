#include "include/org_jvoicexml_jsapi2_mac_MacEngineListFactory.h"
#include "JNIUtils.h"
#include <wchar.h>
#import <Carbon/Carbon.h>
#import <ApplicationServices/ApplicationServices.h>

JNIEXPORT jobjectArray JNICALL Java_org_jvoicexml_jsapi2_mac_MacEngineListFactory_macGetVoices(JNIEnv* env, jobject) {

//  std::cout << "macGetVoices" << std::endl;

  OSErr		theErr = noErr;
  short		numOfVoices;
  
  theErr = CountVoices(&numOfVoices);

  if (theErr != noErr) {
    ThrowJavaException(env, "javax/speech/EngineException", "No voices found");
    return NULL;
  }

//  std::cout << "Nr. of Voices " << numOfVoices << std::endl;

  jclass clazz;
  jmethodID constructor;
  bool rc = GetMethodId(env, "javax/speech/synthesis/Voice",
                        "<init>", "(Ljavax/speech/SpeechLocale;Ljava/lang/String;III)V",
                        clazz, constructor);
  if (!rc) {
    std::cerr << "No Voice Constructor found!" << std::endl;
    return NULL;
  }

  jobjectArray jvoices = env->NewObjectArray(numOfVoices, clazz, NULL);
  
  if (jvoices == NULL) {;
    ThrowJavaException(env, "java/lang/NullPointerException", "Error creating the voices array!");
    return NULL;
  }
  
  // iterate all voices - they really do start at index 1
  u_int voiceIndex;
  for (voiceIndex = 1; voiceIndex <= numOfVoices; voiceIndex++) {
    VoiceDescription voiceDesc;
    VoiceSpec	theVoiceSpec;
    
    theErr = GetIndVoice(voiceIndex, &theVoiceSpec);
    if (theErr != noErr)
      ThrowJavaException(env, "javax/speech/EngineException", "Cannot get voice");
    
    theErr = GetVoiceDescription(&theVoiceSpec, &voiceDesc, sizeof(voiceDesc));
    if (theErr != noErr)
      ThrowJavaException(env, "javax/speech/EngineException", "Cannot get voice description");
    
    char* voiceName = new char[64];
    snprintf(voiceName, 64, "%s", &(voiceDesc.name[1]));
    
    jstring name = env->NewStringUTF(voiceName);
    jobject voice = env->NewObject(clazz, constructor, NULL, name, -1, -1, -1);
    env->SetObjectArrayElement(jvoices, voiceIndex-1, voice);
  }
  return jvoices;
}