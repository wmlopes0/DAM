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

package org.jvoicexml.jsapi2.sapi.recognition;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.speech.EngineException;

/**
 * Perform the recognition in an own java process.
 * @author Josua Arndt
 * @author Dirk Schnelle-Walka
 * @author Markus Baumgart
 *
 */
final class SapiRecognitionThread extends Thread {
    /** Recognition was successful <code>S_OK</code>. */
    private static final int RECOGNITION_SUCCESSFULL = 0;
    /**
     * Recognition did not match a grammar
     * <code>SPEVENTENUM::SPEI_FALSE_RECOGNITION</code>.
     */
    private static final int RECOGNITION_NOMATCH = 43;
    /**
     * Recognition process was aborted because of an error <code>S_FALSE</code>.
     */
    private static final int RECOGNITION_ABORTED = 1;

    /** Logger for this class. */
    private static final Logger LOGGER =
        Logger.getLogger(SapiRecognitionThread.class.getName());

    /** The calling SapiRecognizer.  **/
    private SapiRecognizer recognizer;
   
    /**
     * Constructs a new object.
     * @param rec the calling recognizer
     */
    public SapiRecognitionThread(final SapiRecognizer rec) {
        recognizer = rec;
        setDaemon(true);
    }
    
    /**
     * {@inheritDoc}
     */
    public void run() {
        final long handle = recognizer.getRecognizerHandle();
        //start recognition and get the recognition result
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine("Starting recognition process");
        }
        final String[] tmp = {null, null};
        int returnValue = -1;
        try {
            returnValue = recognizer.sapiRecognize(handle, tmp);
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.fine("Recognitionprocess ended");
            }
        } catch (EngineException e) {
            LOGGER.log(Level.WARNING, "Error in recognition process: {0}",
                    e.getMessage());
            recognizer.postEngineException(e);
            return;
        }

        switch (returnValue) {
        case RECOGNITION_NOMATCH:
            recognizer.reportResultRejected();
            break;
        case RECOGNITION_SUCCESSFULL:
            final String ruleName = tmp[0];
            final String utterance = tmp[1];
            recognizer.reportResult(ruleName, utterance);
            break;
        case RECOGNITION_ABORTED:
            break;
        default:
            LOGGER.log(Level.WARNING, "Unknown return value: {0}",
                   Integer.toHexString(returnValue));
            break;
        }
    }

    /**
     * Stops the recognition process.
     */
    public void stopRecognition() {
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine("Issuing Interrupt to Recognition-Thread...");
        }
        interrupt();
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine("...issued Interrupt to Recognition-Thread");
        }
        final long handle = recognizer.getRecognizerHandle();
        recognizer.sapiAbortRecognition(handle);
    }
}
