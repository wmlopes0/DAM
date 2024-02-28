#include "include/org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer.h"
#include "JNIUtils.h"
#include <Carbon/Carbon.h>
#include <ApplicationServices/ApplicationServices.h>
#include <AudioToolbox/AudioToolbox.h>
#include <CoreAudio/CoreAudio.h>
#include <iostream>
#include "CAXException.h"
#include "CAStreamBasicDescription.h"

/*
 * Class:     org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer
 * Method:    macHandleAllocate
 * Signature: (Ljavax/speech/synthesis/Voice;)J
 */
JNIEXPORT jlong JNICALL Java_org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer_macHandleAllocate
(JNIEnv* env, jobject object, jobject jvoice) {
//  std::cout << "macHandleAllocate" << std::endl;
  
  // NULL will cause GetVoiceDescription to return the system default
  VoiceSpec	voiceSpec;
  OSErr theErr = noErr;
  
  if (jvoice != NULL) {
    // determine name of voice
    jclass voiceClass = env->GetObjectClass(jvoice);
    if (voiceClass == NULL) {
      ThrowJavaException(env, "java/lang/NullPointerException", "Unable to create javax/speech/synthesis/Voice!");
      return 0;
    }
    jmethodID voiceNameMethod = env->GetMethodID(voiceClass, "getName", "()Ljava/lang/String;");
    if (voiceNameMethod == NULL) {
      ThrowJavaException(env, "java/lang/NullPointerException", "Unable to get the getName method of javax/speech/synthesis/Voice!");
      return 0;
    }
    jstring name = (jstring) env->CallObjectMethod(jvoice, voiceNameMethod);
    
    // Carbon specific part
    if (name != NULL) {
      char* voiceName = GetStringNativeChars(env, name);
      
//      std::cout << "Given Voice: " << voiceName << std::endl;

      short numOfVoices;
      theErr = CountVoices(&numOfVoices);
      
      u_int voiceIndex;
      for (voiceIndex = 1; voiceIndex <= numOfVoices; voiceIndex++) {
        VoiceDescription tmpVDesc;
        VoiceSpec	tmpVSpec;
        
        theErr = GetIndVoice(voiceIndex, &tmpVSpec);
        theErr = GetVoiceDescription(&tmpVSpec, &tmpVDesc, sizeof(tmpVDesc));
        
        char* currName = new char[64];
        snprintf(currName, 64, "%s", &(tmpVDesc.name[1]));
//        std::cout << "Found voice " << currName << std::endl;
        
        if (strcmp(currName, voiceName) == 0) {
//          std::cout << "Set " << currName << std::endl;
          voiceSpec = tmpVSpec;
        }
        free(currName);
      }
      free(voiceName);
    }
  }
  
  // create a new speech channel
  SpeechChannel* chan = (SpeechChannel*)malloc(sizeof(SpeechChannel));
  theErr = NewSpeechChannel(&voiceSpec, chan);
  return (jlong) chan;
}

/*
 * Class:     org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer
 * Method:    macHandleCancel
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer_macHandleCancel__J
(JNIEnv* env, jobject object, jlong handle) {
//  std::cout << "macHandleCancel" << std::endl;
}


/*
 * Class:     org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer
 * Method:    macHandleCancel
 * Signature: (JI)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer_macHandleCancel__JI
(JNIEnv* env, jobject object, jlong handle, jint id) {
//  std::cout << "macHandleCancel" << std::endl;
}


/*
 * Class:     org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer
 * Method:    macHandleCancelAll
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer_macHandleCancelAll
(JNIEnv* env, jobject object, jlong handle) {
//  std::cout << "macHandleCancelAll" << std::endl;
}


/*
 * Class:     org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer
 * Method:    macHandlDeallocate
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer_macHandlDeallocate
(JNIEnv* env, jobject object, jlong handle) {
//  std::cout << "macHandlDeallocate" << std::endl;
}


/*
 * Class:     org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer
 * Method:    macHandlePause
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer_macHandlePause
(JNIEnv* env, jobject object, jlong handle) {
//  std::cout << "macHandlePause" << std::endl;
}


/*
 * Class:     org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer
 * Method:    macHandlResume
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer_macHandlResume
(JNIEnv* env, jobject object, jlong handle) {
//  std::cout << "macHandlResume" << std::endl;
}


/*
 * Class:     org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer
 * Method:    macHandleSpeak
 * Signature: (JILjava/lang/String;)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer_macHandleSpeak
(JNIEnv* env, jobject object, jlong handle, jint id, jstring item) {
//  std::cout << "macHandleSpeak" << std::endl;
  
  SpeechChannel* chan = (SpeechChannel*) handle;
  OSErr ok = noErr;
  OSStatus stat;
  
  const char* file = std::tmpnam(NULL);
  char* textBuf = GetStringNativeChars(env, item);
  long size = strlen(textBuf);
  
  CFURLRef url = CFURLCreateWithFileSystemPath(
                                               kCFAllocatorDefault,
                                               CFStringCreateWithCString(NULL, file, NULL),
                                               kCFURLPOSIXPathStyle,
                                               false ); //false == not a directory
  
  ok = SetSpeechInfo(*chan, soOutputToFileWithCFURL, url);
  if (ok != noErr) {
    std::cerr << "SetSpeechInfo failed: " << ok << std::endl;    
  }
  
  // Write to dedicated audio file with conversion - not needed for now
  
//  AudioStreamBasicDescription asbd = AudioStreamBasicDescription();
//  bzero(&asbd, sizeof(asbd));
//  asbd.mSampleRate = 22050.0;
//  asbd.mFormatID = kAudioFormatLinearPCM;
//  asbd.mBytesPerPacket = 2;
//  asbd.mFramesPerPacket = 1;
//  asbd.mBytesPerFrame = 2;
//  asbd.mChannelsPerFrame = 1;
//  asbd.mBitsPerChannel = 16;
//  asbd.mFormatFlags = kAudioFormatFlagsAreAllClear;
//  asbd.mFormatFlags |= kAudioFormatFlagIsSignedInteger;
//  asbd.mFormatFlags |= kAudioFormatFlagIsBigEndian;
//
//  AudioChannelLayout acl = AudioChannelLayout();
//  bzero(&acl, sizeof(acl));
//  
//  ExtAudioFileRef af;
//  stat = ExtAudioFileCreateWithURL (url, kAudioFileAIFFType, &asbd, &acl, kAudioFileFlags_EraseFile, &af);
//  if (stat != noErr) {
//    std::cout << "ExtAudioFileCreateWithURL not ok! " << stat << std::endl;    
//  }  
//  
//  ok = SetSpeechInfo(*chan, soOutputToExtAudioFile, af);
//  if (ok != noErr) {
//    std::cout << "SetSpeechInfo not ok! " << ok << std::endl;    
//  }
  
//  std::cout << "filename " << file << std::endl;
  
  // speak text into file
  ok = SpeakText((*chan), textBuf, strlen(textBuf));
  if (ok != noErr) {
    std::cerr << "SpeakText failed: " << ok << std::endl;    
  }
  
  // wait for speech to finish!
  while (SpeechBusy()) {
    usleep(200);
  }
  
  // close the audio file
//  ExtAudioFileDispose(af);
  
  AudioFileID afId;
  XThrowIfError(AudioFileOpenURL(url, kAudioFileReadPermission, kAudioFileAIFFType, &afId), "can't open file");

  UInt32 propertySize;
  
  /*
  AudioStreamBasicDescription srcFmt;
  UInt32 propertySize = sizeof(srcFmt);
  stat = AudioFileGetProperty(afId, kAudioFilePropertyDataFormat, &propertySize, &srcFmt);
  
  std::cout << "kAudioFilePropertyDataFormat: \n" 
    << "\tmFormatID " << (char*)&srcFmt.mFormatID << "\n"
    << "\tmFormatFlags " << srcFmt.mFormatFlags << "\n"
    << "\tmSampleRate " << srcFmt.mSampleRate << "\n"
    << "\tmBitsPerChannel " << srcFmt.mBitsPerChannel << "\n"
    << "\tmChannelsPerFrame " << srcFmt.mChannelsPerFrame << "\n"
    << "\tmBytesPerPacket " << srcFmt.mBytesPerPacket << "\n"
    << "\tmFramesPerPacket " << srcFmt.mFramesPerPacket << "\n"
    << "\tmBytesPerFrame " << srcFmt.mBytesPerFrame << "\n"
    << std::endl;
  */
  
  UInt64 numBytes;
  propertySize = sizeof(numBytes);
  XThrowIfError(AudioFileGetProperty(afId, kAudioFilePropertyAudioDataByteCount, &propertySize, &numBytes), "get byte count of audio file failed");

/*  std::cout << "kAudioFilePropertyDataFormat: \n" 
    << "\tnumBytes " << numBytes << "\n"
    << std::endl;
*/
  
  UInt64 numPackets;
  propertySize = sizeof(numPackets);
  XThrowIfError(AudioFileGetProperty(afId, kAudioFilePropertyAudioDataPacketCount, &propertySize, &numPackets), "get packet count of audio file failed");
  
/*  std::cout << "kAudioFilePropertyAudioDataPacketCount: \n" 
  << "\tnumPackets " << numPackets << "\n"
  << std::endl;
*/
  
  UInt32 numBytes32 = numBytes;
  UInt32 numPackets32 = numPackets;
  void* buffer = malloc(numBytes);
    
	XThrowIfError(AudioFileReadPackets(afId, false, &numBytes32, NULL, 0, &numPackets32, buffer), "reading packets from audio file failed.");

/*  std::cout << "AudioFileReadPackets: \n" 
  << "\tnumPackets " << numPackets32 << "\n"
  << "\tnumBytes " << numBytes32 << "\n"
  << std::endl;
*/

  // tidy up
  CFRelease(url);
  AudioFileClose(afId);
  remove(file);
  free(textBuf);
  
  // byte swap
  SInt16* buf = (SInt16*)buffer;
  UInt32 cnt = 0;
  while (cnt < (numBytes32 / 2)) {
    buf[cnt] = ((buf[cnt] & 0xff) << 8) | ((buf[cnt] & 0xff00) >> 8);
    cnt++;
  }
  
  jbyteArray jb = env->NewByteArray(numBytes32);
  env->SetByteArrayRegion(jb, 0, numBytes32, (jbyte*)buffer);
  free(buffer);
//  std::cout << "Returning buffer of size " << numBytes32 << std::endl;

  return jb;
}


/*
 * Class:     org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer
 * Method:    macHandleSpeakSsml
 * Signature: (JILjava/lang/String;)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer_macHandleSpeakSsml
(JNIEnv* env, jobject object, jlong handle, jint id, jstring ssml) {
//  std::cout << "macHandleSpeakSsml" << std::endl;
}


/*
 * Class:     org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer
 * Method:    macGetAudioFormat
 * Signature: (J)Ljavax/sound/sampled/AudioFormat;
 */
JNIEXPORT jobject JNICALL Java_org_jvoicexml_jsapi2_mac_synthesis_MacSynthesizer_macGetAudioFormat
(JNIEnv* env, jobject object, jlong handle) {
//  std::cout << "macGetAudioFormat" << std::endl;
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
  //return env->NewObject(clazz, method, format.nSamplesPerSec,
  //    format.wBitsPerSample, format.nChannels, JNI_TRUE, JNI_TRUE);
  return env->NewObject(clazz, constructor, 22050.0,
                        16, 1, JNI_TRUE, JNI_FALSE);
  
}


