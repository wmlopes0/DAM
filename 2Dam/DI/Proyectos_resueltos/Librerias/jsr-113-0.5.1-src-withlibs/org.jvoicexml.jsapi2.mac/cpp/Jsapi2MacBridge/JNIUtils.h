#pragma once

#include <jni.h>
#include <iostream>

void ThrowJavaException(JNIEnv* env, char* exceptionClassName, char* message);

bool GetMethodId(JNIEnv* env, const char* className, const char* methodName,
                 const char* sig, jclass& clazz, jmethodID& methodId);
char* GetStringNativeChars(JNIEnv *env, jstring jstr);
