/*
 * File:    $HeadURL: https://svn.sourceforge.net/svnroot/jvoicexml/trunk/src/org/jvoicexml/Application.java$
 * Version: $LastChangedRevision: 68 $
 * Date:    $LastChangedDate $
 * Author:  $LastChangedBy: schnelle $
 *
 * JSAPI - An independent reference implementation of JSR 113.
 *
 * Copyright (C) 2007-2012 JVoiceXML group - http://jvoicexml.sourceforge.net
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

package org.jvoicexml.jsapi2.recognition;

import javax.speech.recognition.Result;
import javax.speech.recognition.ResultToken;
import javax.speech.recognition.RecognizerProperties;

/**
 * Basic implementation of a {@link ResultToken}.
 *
 * @author Renato Cassaca
 * @author Dirk Schnelle-Walka
 * @version 1.0
 */
public class BaseResultToken implements ResultToken {

    private int confidenceLevel;
    private long startTime;
    private long endTime;
    private final Result result;
    private final String token;

    /**
     * Constructs a new object
     * @param res the result
     * @param tok the token
     */
    public BaseResultToken(final Result res, final String tok) {
        result = res;
        confidenceLevel = RecognizerProperties.NORM_CONFIDENCE;
        startTime = -1;
        endTime = -1;
        token = tok;
    }

    /**
     * Retrieves the confidence level if the result was accepted. 
     *
     * @return the confidence level,
     *          <code>RecognizerProperties.UNKNOWN_CONFIDENCE</code> if the
     *          result was not accepted.
     */
    public int getConfidenceLevel() {
        if (result.getResultState() == Result.ACCEPTED) {
            return confidenceLevel;
        }

        return RecognizerProperties.UNKNOWN_CONFIDENCE;
    }

    /**
     * Sets the confidence level.
     * @param the confidence level
     */
    public void setConfidenceLevel(final int level) {
        if (level < RecognizerProperties.MIN_CONFIDENCE
                || level > RecognizerProperties.MAX_CONFIDENCE) {
            throw new IllegalArgumentException("Confidence must be between" +
                    " MIN_CONFIDENCE and MAX_CONFIDENCE");
        }
        confidenceLevel = level;
    }

    /**
     * Sets the start time.
     * @param time the start time
     */
    public void setStartTime(final long time) {
        startTime = time;
    }

    /**
     * Retrieves the start time.
     *
     * @return the start time
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Sets the end time.
     * @param time the end time
     */
    public void setEndTime(final long time) {
        endTime = time;
    }

    /**
     * Retrieves the end time.
     *
     * @return the end time.
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * Retrieves the result.
     *
     * @return the result.
     */
    public Result getResult() {
        return result;
    }

    /**
     * Retrieves the result token.
     *
     * @return the token
     */
    public String getText() {
        return token;
    }
}
