#include "include/org_jvoicexml_jsapi2_mac_recognition_MacRecognizer.h"
#include <iostream>

/*
 * Class:     org_jvoicexml_jsapi2_mac_recognition_MacRecognizer
 * Method:    sapiGetBuildInGrammars
 * Signature: (J)Ljava/util/Vector;
 */
JNIEXPORT jobject JNICALL Java_org_jvoicexml_jsapi2_mac_recognition_MacRecognizer_macGetBuildInGrammars
(JNIEnv* env, jobject object, jlong handle) {
  std::cout << "macGetBuildInGrammars" << std::endl;
  return NULL;
}

/*
 * Class:     org_jvoicexml_jsapi2_mac_recognition_MacRecognizer
 * Method:    sapiAllocate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jvoicexml_jsapi2_mac_recognition_MacRecognizer_macAllocate
(JNIEnv* env, jobject object) {
  std::cout << "macAllocate" << std::endl;
}

/*
 * Class:     org_jvoicexml_jsapi2_mac_recognition_MacRecognizer
 * Method:    sapiDeallocate
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_jvoicexml_jsapi2_mac_recognition_MacRecognizer_macDeallocate
(JNIEnv* env, jobject object, jlong) {
  std::cout << "macDeallocate" << std::endl;
}

/*
 * Class:     org_jvoicexml_jsapi2_mac_recognition_MacRecognizer
 * Method:    sapiPause
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_jvoicexml_jsapi2_mac_recognition_MacRecognizer_macPause__J
(JNIEnv* env, jobject object, jlong handle) {
  std::cout << "macPause" << std::endl;
}

/*
 * Class:     org_jvoicexml_jsapi2_mac_recognition_MacRecognizer
 * Method:    sapiPause
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_org_jvoicexml_jsapi2_mac_recognition_MacRecognizer_macPause__JI
(JNIEnv* env, jobject object, jlong handle, jint flags) {
  std::cout << "macPause" << std::endl;
}

/*
 * Class:     org_jvoicexml_jsapi2_mac_recognition_MacRecognizer
 * Method:    sapiResume
 * Signature: (J[Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jvoicexml_jsapi2_mac_recognition_MacRecognizer_macResume
(JNIEnv* env, jobject object, jlong handle, jobjectArray grammars) {
  std::cout << "macResume" << std::endl;
}

/*
 * Class:     org_jvoicexml_jsapi2_mac_recognition_MacRecognizer
 * Method:    sapiSetGrammar
 * Signature: (JLjava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jvoicexml_jsapi2_mac_recognition_MacRecognizer_macSetGrammar
(JNIEnv* env, jobject object, jlong handle, jstring grammarPath) {
  std::cout << "macSetGrammar" << std::endl;
}

/*
 * Class:     org_jvoicexml_jsapi2_mac_recognition_MacRecognizer
 * Method:    start
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_jvoicexml_jsapi2_mac_recognition_MacRecognizer_start
(JNIEnv* env, jobject object, jlong handle) {
  std::cout << "start" << std::endl;
}

