package org.jvoicexml.jsapi2.mac.recognition;

import javax.speech.Engine;
import javax.speech.EngineException;
import javax.speech.SpeechLocale;
import javax.speech.recognition.RecognizerMode;
import javax.speech.spi.EngineFactory;

public final class MacRecognizerMode extends RecognizerMode
    implements EngineFactory {

    /**
     * Constructs a new object.
     */
    public MacRecognizerMode() {
        super("Apple MacOSX", "CoreSpeech", Boolean.FALSE, Boolean.TRUE,
                Boolean.TRUE, RecognizerMode.MEDIUM_SIZE, null, null);
    }

    /**
     * Constructs a new object.
     * @param locale  the locale associated with this mode
     */
    public MacRecognizerMode(final SpeechLocale locale) {
        super(locale);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Engine createEngine() throws IllegalArgumentException,
            EngineException {
        return new MacRecognizer(this);
    }

}
