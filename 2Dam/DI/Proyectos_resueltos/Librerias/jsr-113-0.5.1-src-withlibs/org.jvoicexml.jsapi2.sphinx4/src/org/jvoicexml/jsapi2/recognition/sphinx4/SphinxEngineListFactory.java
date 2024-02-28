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

package org.jvoicexml.jsapi2.recognition.sphinx4;

import javax.speech.EngineList;
import javax.speech.EngineMode;
import javax.speech.recognition.RecognizerMode;
import javax.speech.spi.EngineListFactory;


/**
 * The engine list factory for sphinx.
 *
 * @author Renato Cassaca
 * @author Dirk Schnelle-Walka
 */
public class SphinxEngineListFactory implements EngineListFactory {
    /** Available engined modes. */
    private final EngineMode[] engineModes;

    /**
     * Constructs a new object.
     */
    public SphinxEngineListFactory() {
        engineModes = new EngineMode[] {new SphinxRecognizerMode()};
    }

    /**
     * {@inheritDoc}
     */
    public EngineList createEngineList(final EngineMode require) {
        // Must be a recognizer.
        if (!(require instanceof RecognizerMode)) {
            return null;
        }
        EngineList engineList = new EngineList(engineModes);

        if (require != null) {
            //Removes unwanted modes
            engineList.requireMatch(require);
        }

        return (engineList.size() > 0 ? engineList : null);
    }
}
