#include "stdafx.h"
#include "org_jvoicexml_jsapi2_sapi_logging_Log4CPlus2JavaLoggingAdapter.h"
#include "JNIUtils.h"
#include <log4cplus/logger.h>
#include <log4cplus/loggingmacros.h>
#include "JavaLoggingAppender.h"

static log4cplus::Logger logger =
    log4cplus::Logger::getInstance(_T("org.jvoicexml.sapi.cpp.logging.Log4CPlus2JavaLoggingAdapter"));

/*
 * Class:     org_jvoicexml_jsapi2_sapi_logging_Log4CPlus2JavaLoggingAdapter
 * Method:    initLogging
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jvoicexml_jsapi2_sapi_logging_Log4CPlus2JavaLoggingAdapter_initLogging
  (JNIEnv *env, jobject object)
{
    JavaLoggingAppender* appender = new JavaLoggingAppender(env);
    log4cplus::SharedAppenderPtr javaLogging(appender);
    log4cplus::Logger::getRoot().addAppender(javaLogging);
    LOG4CPLUS_INFO(logger, _T("Jsapi2SapiBridge successfully loaded"));
    return (jlong) appender;
}


/*
 * Class:     org_jvoicexml_jsapi2_sapi_logging_Log4CPlus2JavaLoggingAdapter
 * Method:    getNextLogRecord
 * Signature: (J)Ljava/util/logging/LogRecord;
 */
JNIEXPORT jobject JNICALL Java_org_jvoicexml_jsapi2_sapi_logging_Log4CPlus2JavaLoggingAdapter_getNextLogRecord
  (JNIEnv *env, jobject object, jlong handle)
{
    JavaLoggingAppender* appender = (JavaLoggingAppender*) handle;
    return appender->GetNextLogRecord();
}
