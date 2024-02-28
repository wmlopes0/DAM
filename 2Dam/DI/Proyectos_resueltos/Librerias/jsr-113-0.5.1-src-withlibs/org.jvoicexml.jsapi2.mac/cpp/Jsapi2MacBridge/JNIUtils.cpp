#include "JNIUtils.h"

void ThrowJavaException(JNIEnv* env, char* exceptionClassName, char* message)
{
  jclass exception = env->FindClass(exceptionClassName);
  if (exception == 0) /* Unable to find the new exception class, give up. */
  {
    std::cerr << message << std::endl;
    return;
  }
  env->ThrowNew(exception, message);
}

bool GetMethodId(JNIEnv* env, const char* className, const char* methodName,
                 const char* sig, jclass& clazz, jmethodID& methodId)
{
  clazz = env->FindClass(className);
  if (clazz == NULL)
  {
    std::cerr << "Did not find class" << std::endl;
    char msg[512];
    snprintf(msg, sizeof(msg), "Unable to %s!", className);
    ThrowJavaException(env, "java/lang/NullPointerException", msg);
    return NULL;
  }
  
  methodId = env->GetMethodID(clazz, methodName, sig);
  if (methodId == NULL)
  {
    std::cerr << "Did not find method" << std::endl;
    char msg[1024];
    snprintf(msg, sizeof(msg), "Unable to find method '%s(%s)'!", methodName, sig);
    ThrowJavaException(env, "java/lang/NullPointerException", msg);
    return NULL;
  }
  return 1;
}

char* GetStringNativeChars(JNIEnv* env, jstring jstr)
{
  jbyteArray bytes = 0;
  jthrowable exc;
  char *result = 0;
  if (env->EnsureLocalCapacity(2) < 0) {
    return 0; /* out of memory error */
  }
  // get the getBytes method id
  jmethodID getBytesMID = env->GetMethodID(env->GetObjectClass(jstr), "getBytes", "()[B");
  
  bytes = (jbyteArray)env->CallObjectMethod(jstr, getBytesMID);
  exc = env->ExceptionOccurred();
  if (!exc) {
    jint len = env->GetArrayLength(bytes);
    result = (char *)malloc(len + 1);
    if (result == 0) {
      ThrowJavaException(env, "java/lang/OutOfMemoryError",
                      0);
      env->DeleteLocalRef(bytes);
      return 0;
    }
    env->GetByteArrayRegion(bytes, 0, len,
                               (jbyte *)result);
    result[len] = 0; /* NULL-terminate */
  } else {
    env->DeleteLocalRef(exc);
  }
  env->DeleteLocalRef(bytes);
  return result;
}
