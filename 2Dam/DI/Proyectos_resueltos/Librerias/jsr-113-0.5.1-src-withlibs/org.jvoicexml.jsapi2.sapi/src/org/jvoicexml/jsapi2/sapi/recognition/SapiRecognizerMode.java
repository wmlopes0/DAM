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

import javax.speech.Engine;
import javax.speech.EngineException;
import javax.speech.SpeechLocale;
import javax.speech.recognition.RecognizerMode;
import javax.speech.spi.EngineFactory;

/**
 * Recognizer mode for the SAPI recognizer.
 * @author Dirk Schnelle-Walka
 *
 */
public final class SapiRecognizerMode extends RecognizerMode
    implements EngineFactory {

    /**
     * Constructs a new object.
     */
    public SapiRecognizerMode() {
        super("Microsoft SAPI", "SAPI", Boolean.FALSE, Boolean.TRUE,
                Boolean.TRUE, RecognizerMode.MEDIUM_SIZE, null, null);
    }

    /**
     * Constructs a new object.
     * @param locale  the locale associated with this mode
     */
    public SapiRecognizerMode(final SpeechLocale locale) {
        super(locale);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Engine createEngine() throws IllegalArgumentException,
            EngineException {
        return new SapiRecognizer(this);
    }
}
