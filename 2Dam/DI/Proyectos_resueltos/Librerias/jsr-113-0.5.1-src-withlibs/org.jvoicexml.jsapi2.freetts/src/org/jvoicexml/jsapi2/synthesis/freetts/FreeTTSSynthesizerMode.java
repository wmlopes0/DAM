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

package org.jvoicexml.jsapi2.synthesis.freetts;

import java.util.Locale;
import javax.speech.Engine;
import javax.speech.EngineException;
import javax.speech.spi.EngineFactory;
import javax.speech.synthesis.SynthesizerMode;


/**
 * Represents a SynthesizerModeDesc for the
 * FreeTTSSynthesizer. A FreeTTSSynthesizerModeDesc adds
 * an audio player to the standard mode items.
 */
public class FreeTTSSynthesizerMode extends SynthesizerMode implements
        EngineFactory {
    /**
     * Constructs a new object.
     */
    public FreeTTSSynthesizerMode() {
    }

    /**
     * Creates a fully-specified descriptor.
     * Any of the features may be <code>null</code>.
     *
     * @param engineName  the name of the engine
     * @param modeName   the name of the mode
     * @param locale  the locale associated with this mode
     */
    public FreeTTSSynthesizerMode(String engineName, String modeName,
                                  Locale locale, FreeTTSVoice[] voices) {
        super(engineName, modeName, null, null, null, voices);
    }


    /**
     * Constructs a FreeTTSSynthesizer with the properties of this mode
     * descriptor.
     *
     * @return a synthesizer that matches the mode
     *
     * @throws IllegalArgumentException  if the properties of this
     * 		descriptor do not match any known engine or mode
     * @throws EngineException if the engine could not be created
     * @throws SecurityException if the caller does not have
     * 		permission to use the speech engine
     */
    @Override
    public Engine createEngine() throws IllegalArgumentException,
            EngineException, SecurityException {
        return new FreeTTSSynthesizer(this);
    }
}
