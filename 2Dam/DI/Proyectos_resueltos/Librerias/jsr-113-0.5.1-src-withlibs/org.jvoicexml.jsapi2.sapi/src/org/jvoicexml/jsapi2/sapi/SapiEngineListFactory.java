package org.jvoicexml.jsapi2.sapi;

import javax.speech.EngineList;
import javax.speech.EngineMode;
import javax.speech.recognition.RecognizerMode;
import javax.speech.spi.EngineListFactory;
import javax.speech.synthesis.SynthesizerMode;
import javax.speech.synthesis.Voice;

import org.jvoicexml.jsapi2.sapi.logging.Log4CPlus2JavaLoggingAdapter;
import org.jvoicexml.jsapi2.sapi.recognition.SapiRecognizerMode;
import org.jvoicexml.jsapi2.sapi.synthesis.SapiSynthesizerMode;

import java.util.ArrayList;

/**
 * Factory for the SAPI engines. 
 * @author Dirk Schnelle-Walka
 *
 */
public final class SapiEngineListFactory implements EngineListFactory {
    static {
        //Check the processor architecture
        if (System.getProperty("os.arch").equalsIgnoreCase("x86")) {
            System.loadLibrary("Jsapi2SapiBridge");
        } else {
            System.loadLibrary("Jsapi2SapiBridge_x64");
        }
            
         
        final Log4CPlus2JavaLoggingAdapter adapter =
            new Log4CPlus2JavaLoggingAdapter();
        adapter.start();
        adapter.waitStarted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EngineList createEngineList(final EngineMode require) {
        if (require instanceof SynthesizerMode) {
            final SynthesizerMode mode = (SynthesizerMode) require;
            Voice[] voices = sapiGetVoices();
            if (mode.getVoices() != null) {
                //If a voice preference was presented
                final ArrayList<Voice> selectedVoices = new ArrayList<Voice>();
                for (Voice reqVoice: mode.getVoices()) {
                    for (Voice availVoice: voices) {
                        if (availVoice.match(reqVoice)) {
                            selectedVoices.add(availVoice);
                            break;
                        }
                    }
                }

                //Update voices array
                if (selectedVoices.size() > 0) {
                    voices = selectedVoices.toArray(new Voice[] {});
                } else {
                    //A requested voice was not found...
                    return null;
                }

                voices = selectedVoices.toArray(new Voice[] {});
            }

            final SynthesizerMode[] features = new SynthesizerMode[] {
                    new SapiSynthesizerMode(null, mode.getEngineName(),
                            mode.getRunning(), mode.getSupportsLetterToSound(),
                            Boolean.TRUE, voices)
            };
            return new EngineList(features);
        }            
        if (require instanceof RecognizerMode) {
                final RecognizerMode[] features = new RecognizerMode[] {
                        new SapiRecognizerMode()
            };
            return new EngineList(features);
        }
        
        return null;
    }

    /**
     * Retrieves all voices.
     * @return all voices
     */
    private native Voice[] sapiGetVoices();

}
