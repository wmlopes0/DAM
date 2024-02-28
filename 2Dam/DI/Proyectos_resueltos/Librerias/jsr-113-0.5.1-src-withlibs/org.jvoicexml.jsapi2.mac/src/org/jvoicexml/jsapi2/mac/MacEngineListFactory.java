package org.jvoicexml.jsapi2.mac;

import java.util.Arrays;
import java.util.Vector;

import javax.speech.EngineList;
import javax.speech.EngineMode;
import javax.speech.spi.EngineListFactory;
import javax.speech.synthesis.SynthesizerMode;
import javax.speech.synthesis.Voice;

import org.jvoicexml.jsapi2.mac.synthesis.MacSynthesizerMode;

/**
 * Factory for the Mac Speech engine.
 * 
 * @author Stefan Radomski
 * 
 */
public class MacEngineListFactory implements EngineListFactory {
	static {
		System.loadLibrary("Jsapi2MacBridge");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EngineList createEngineList(final EngineMode require) {
		if (require instanceof SynthesizerMode) {
			final SynthesizerMode mode = (SynthesizerMode) require;
			final Voice[] allVoices = macGetVoices();
			Vector<Voice> voices = new Vector<Voice>();
			if (mode.getVoices() == null) {
				voices.addAll(Arrays.asList(allVoices));
			} else {
				for (Voice availableVoice : allVoices) {
					for (Voice requiredVoice : mode.getVoices()) {
						if (availableVoice.match(requiredVoice)) {
							voices.add(availableVoice);
						}
					}
				}
			}
			final SynthesizerMode[] features = new SynthesizerMode[] { new MacSynthesizerMode(null, mode
					.getEngineName(), mode.getRunning(), mode.getSupportsLetterToSound(), mode.getMarkupSupport(),
					voices.toArray(new Voice[0])) };
			return new EngineList(features);
		}
		// Mac Recognizer unusable as it is
//		if (require instanceof RecognizerMode) {
//			final RecognizerMode[] features = new RecognizerMode[] { new MacRecognizerMode() };
//			return new EngineList(features);
//		}

		return null;
	}

	/**
	 * Retrieves all voices.
	 * 
	 * @return all voices
	 */
	private native Voice[] macGetVoices();

}
