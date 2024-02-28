#include "stdafx.h"
#include <log4cplus/helpers/stringhelper.h>
#include "JavaLoggingAppender.h"
#include "JNIUtils.h"


JavaLoggingAppender::JavaLoggingAppender(JNIEnv* environment)
    :env(environment)
{
    ::InitializeCriticalSection(&queueAccess);
    eventsPresent = CreateSemaphore(NULL, 0, 1, NULL);
}


JavaLoggingAppender::~JavaLoggingAppender(void)
{
    ::DeleteCriticalSection(&queueAccess);
}

void JavaLoggingAppender::close(void)
{
}

void JavaLoggingAppender::append(const log4cplus::spi::InternalLoggingEvent& event)
{
    EnterCriticalSection(&queueAccess);
    loggingEvents.push(event);
    LeaveCriticalSection(&queueAccess);
    ReleaseSemaphore(eventsPresent, 1, NULL);
}


jobject JavaLoggingAppender::GetNextLogRecord(void)
{
    WaitForSingleObject(eventsPresent, INFINITE);
    EnterCriticalSection(&queueAccess);
    log4cplus::spi::InternalLoggingEvent event = loggingEvents.front();
    loggingEvents.pop();
    LeaveCriticalSection(&queueAccess);

    jobject level = NULL;
    if (event.getLogLevel() == log4cplus::TRACE_LOG_LEVEL)
    {
        GetStaticObjectField(env, "java/util/logging/Level", "FINER", "Ljava/util/logging/Level;", level);
    }
    else if (event.getLogLevel() == log4cplus::DEBUG_LOG_LEVEL)
    {
        GetStaticObjectField(env, "java/util/logging/Level", "FINE", "Ljava/util/logging/Level;", level);
    }
    else if (event.getLogLevel() == log4cplus::INFO_LOG_LEVEL)
    {
        GetStaticObjectField(env, "java/util/logging/Level", "INFO", "Ljava/util/logging/Level;", level);
    }
    else if (event.getLogLevel() == log4cplus::WARN_LOG_LEVEL)
    {
        GetStaticObjectField(env, "java/util/logging/Level", "WARNING", "Ljava/util/logging/Level;", level);
    }
    else if (event.getLogLevel() == log4cplus::ERROR_LOG_LEVEL)
    {
        GetStaticObjectField(env, "java/util/logging/Level", "SEVERE", "Ljava/util/logging/Level;", level);
    }
    else if (event.getLogLevel() == log4cplus::FATAL_LOG_LEVEL)
    {
        GetStaticObjectField(env, "java/util/logging/Level", "SEVERE", "Ljava/util/logging/Level;", level);
    }
    else
    {
        GetStaticObjectField(env, "java/util/logging/Level", "INFO", "Ljava/util/logging/Level;", level);
    }

    jclass logrecordClazz = NULL;
    jmethodID newLogRecord = NULL;
    if (!GetMethodId(env, "java/util/logging/LogRecord", "<init>",
            "(Ljava/util/logging/Level;Ljava/lang/String;)V", logrecordClazz, newLogRecord))
    {
        return NULL;
    }
    const wchar_t* wmessage = event.getMessage().c_str();
    char* message = new char[wcslen(wmessage) + 1];
    wcstombs(message, wmessage, wcslen(wmessage) + 1);
    jstring msg = env->NewStringUTF(message);
    delete[] message;
    jobject record = env->NewObject(logrecordClazz, newLogRecord, level, msg);

    jmethodID setLoggerName = NULL;
    if (!GetMethodId(env, "java/util/logging/LogRecord", "setLoggerName",
            "(Ljava/lang/String;)V", logrecordClazz, setLoggerName))
    {
        return NULL;
    }
    jmethodID setSourceClassName = NULL;
    if (!GetMethodId(env, "java/util/logging/LogRecord", "setSourceClassName",
            "(Ljava/lang/String;)V", logrecordClazz, setSourceClassName))
    {
        return NULL;
    }
    const wchar_t* wname = event.getLoggerName().c_str();
    char* name = new char[wcslen(wname) + 1];
    wcstombs(name, wname, wcslen(wname) + 1);
    jstring loggerName = env->NewStringUTF(name);
    env->CallVoidMethod(record, setLoggerName, loggerName);
    env->CallVoidMethod(record, setSourceClassName, loggerName);
    delete[] name;
    return record;
}

