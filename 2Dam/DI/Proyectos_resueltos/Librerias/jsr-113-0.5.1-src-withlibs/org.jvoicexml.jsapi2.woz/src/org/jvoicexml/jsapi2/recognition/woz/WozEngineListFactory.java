package org.jvoicexml.jsapi2.recognition.woz;

import javax.speech.EngineList;
import javax.speech.EngineMode;
import javax.speech.recognition.RecognizerMode;
import javax.speech.spi.EngineListFactory;


public class WozEngineListFactory implements EngineListFactory {

    private final EngineMode[] engineModes;

    public WozEngineListFactory() {
        engineModes = new EngineMode[] {new WozRecognizerMode()};
    }

	@Override
	public EngineList createEngineList(EngineMode require) {
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
