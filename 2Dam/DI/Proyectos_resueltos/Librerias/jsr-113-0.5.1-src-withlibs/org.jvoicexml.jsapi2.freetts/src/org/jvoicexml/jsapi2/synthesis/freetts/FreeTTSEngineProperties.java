/*
 * File:    $HeadURL: https://jsapi.svn.sourceforge.net/svnroot/jsapi/trunk/org.jvoicexml.jsapi2.jse/src/org/jvoicexml/jsapi2/jse/synthesis/freetts/FreeTTSEngineProperties.java $
 * Version: $LastChangedRevision: 292 $
 * Date:    $LastChangedDate $
 * Author:  $LastChangedBy: schnelle $
 *
 * JSAPI - An base implementation for JSR 113.
 *
 * Copyright (C) 2009 JVoiceXML group - http://jvoicexml.sourceforge.net
 *
 */

package org.jvoicexml.jsapi2.synthesis.freetts;

import javax.speech.synthesis.Voice;

import org.jvoicexml.jsapi2.synthesis.BaseSynthesizer;
import org.jvoicexml.jsapi2.synthesis.BaseSynthesizerProperties;

/**
 * Engine properties for FreeTTS.
 *
 * @author Renato Cassaca
 * @version 1.0
 */
public class FreeTTSEngineProperties extends BaseSynthesizerProperties {
    /**
     * Constructs a new object.
     * @param synthesizer the associated synthesizer
     */
    public FreeTTSEngineProperties(final BaseSynthesizer synthesizer) {
        super(synthesizer);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void setVoice(final Voice voice) {
        if (voice instanceof FreeTTSVoice) {
            FreeTTSVoice freettsVoice = (FreeTTSVoice) voice;
            final FreeTTSSynthesizer engine = (FreeTTSSynthesizer) getEngine();
            boolean ok = engine.setCurrentVoice(freettsVoice);
            if (!ok) {
                return;
            }
        }

        super.setVoice(voice);
    }
}
