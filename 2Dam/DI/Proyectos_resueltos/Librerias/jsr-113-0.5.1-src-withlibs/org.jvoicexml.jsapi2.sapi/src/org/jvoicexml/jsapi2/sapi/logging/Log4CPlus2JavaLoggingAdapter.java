/*
 * File:    $HeadURL: https://svn.sourceforge.net/svnroot/jvoicexml/trunk/src/org/jvoicexml/Application.java$
 * Version: $LastChangedRevision: 68 $
 * Date:    $LastChangedDate $
 * Author:  $LastChangedBy: schnelle $
 *
 * JSAPI - An independent reference implementation of JSR 113.
 *
 * Copyright (C) 2010-2012 JVoiceXML group - http://jvoicexml.sourceforge.net
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package org.jvoicexml.jsapi2.sapi.logging;

import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * A simple wrapper for log4cplus or similar native logging frameworks.
 * This thread retrieves the next {@link LogRecord} and forwards it to
 * the Java logging framework.
 * @author Dirk Schnelle-Walka
 *
 */
public final class Log4CPlus2JavaLoggingAdapter extends Thread {
    /** Logger for this class. */
    private static final Logger LOGGER =
        Logger.getLogger(Log4CPlus2JavaLoggingAdapter.class.getName());

    /** Handle for the native logging adapter. */
    private long handle;

    /** Semaphore to notify about new logging events. */
    private final Object lock;

    /**
     * Constructs a new object.
     */
    public Log4CPlus2JavaLoggingAdapter() {
        setDaemon(true);
        lock = new Object();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        handle = initLogging();
        synchronized (lock) {
            lock.notifyAll();
        }
        while (!isInterrupted()) {
            final LogRecord record = getNextLogRecord(handle);
            if (record != null) {
                Logger logger = Logger.getAnonymousLogger();
                logger.log(record);
            }
        }
    }

    /**
     * Wait until the thread has started.
     */
    public void waitStarted() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                LOGGER.warning(e.getMessage());
            }
        }
    }

    /**
     * Native method call to initialize the logging.
     * @return logging handle
     */
    public native long initLogging();

    /**
     * Retrieves the next log record.
     * @param loggingHandle the native handle
     * @return the next log record.
     */
    public native LogRecord getNextLogRecord(final long loggingHandle);
}
