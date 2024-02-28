package org.jvoicexml.jsapi2.recognition.woz;

import javax.speech.Engine;
import javax.speech.EngineException;
import javax.speech.SpeechLocale;
import javax.speech.recognition.RecognizerMode;
import javax.speech.spi.EngineFactory;

public class WozRecognizerMode extends RecognizerMode implements EngineFactory {

    public WozRecognizerMode() {
    	// TODO: match anything
        super("woz", null, true, false, true, 10000,
                new SpeechLocale[] {new SpeechLocale("en_US")}, null);
    }

	@Override
	public Engine createEngine() throws EngineException {
		return new WozRecognizer(this);
	}
}
