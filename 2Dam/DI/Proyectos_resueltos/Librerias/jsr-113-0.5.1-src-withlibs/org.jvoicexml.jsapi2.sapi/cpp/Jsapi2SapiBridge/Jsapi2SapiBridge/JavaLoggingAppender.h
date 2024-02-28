#pragma once

#include <log4cplus/appender.h>
#include <log4cplus/spi/loggingevent.h>
#include <jni.h>
#include <list>
#include <queue>
#include <deque>

class JavaLoggingAppender : public log4cplus::Appender
{
public:
    JavaLoggingAppender(JNIEnv* env);
    ~JavaLoggingAppender(void);

    // Methods
    jobject GetNextLogRecord(void);
    virtual void close();

protected:
    virtual void append(const log4cplus::spi::InternalLoggingEvent& event);

private:
    JNIEnv* env;
    std::queue<log4cplus::spi::InternalLoggingEvent> loggingEvents;
    CRITICAL_SECTION queueAccess;
    HANDLE eventsPresent;
};

