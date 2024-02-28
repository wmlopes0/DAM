package org.jvoicexml.jsapi2.sapi.recognition;

import java.util.logging.Logger;

import javax.speech.AudioEvent;
import javax.speech.AudioListener;
import javax.speech.Engine;

/**
 * This AudioEventListener keeps track of the various audio events happening
 * during runtime. If changes to the inputStream are made, the listener adds
 * a {@link javax.speech.recognition.Recognizer#pause()} to the Recognizer's 
 * pause-counter. This ensures, that the associated recognizer can only be
 * resumed AFTER all changes to the inputStream are fully committed.
 * 
 * @author Markus Baumgart
 * @author Dirk Schnelle-Walka
 *
 */
public final class SapiRecognizerAudioEventListener implements AudioListener {
    /** Logger instance. */
    private static final Logger LOGGER = Logger.getLogger(
                SapiRecognizerAudioEventListener.class.getCanonicalName());

    /**
     * The associated recognizer.
     */
    private SapiRecognizer recognizer;

    /**
     * Keeps track of the audioinput's changes.<br>
     * Between an {@link javax.speech.AudioManager#audioStop()} and
     * {@link javax.speech.AudioManager#audioStart()} 
     * the mediaLocator could be changed  many times.
     * This AudioEventLister must not hold more than one pause on the stack.
     */
    private boolean audioChanged;
    
    /**
     * Constructs a new object.
     * @param rec the recognizer
     */
    public SapiRecognizerAudioEventListener(final SapiRecognizer rec) {
        recognizer = rec;
        audioChanged = false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void audioUpdate(final AudioEvent e) {
        switch(e.getId()) {
            case AudioEvent.AUDIO_CHANGED:
                LOGGER.fine("AudioEvent: Audio Changed!");
                if (!audioChanged) {
                    recognizer.pause();
                    audioChanged = true;
                }
                break;
            case AudioEvent.AUDIO_STARTED:
                LOGGER.fine("AudioEvent: Audio Started!");
                
                if (recognizer.testEngineState(Engine.ALLOCATING_RESOURCES)) {
                    try {
                        recognizer.waitEngineState(Engine.ALLOCATED);
                        recognizer.waitEngineState(Engine.PAUSED);
                        recognizer.pause();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                
                // tell the recognizer to get the new InputStream and set it
                // as its new source
                audioChanged = false;
                if (!(recognizer.testEngineState(Engine.DEALLOCATED)
                        || recognizer.testEngineState(
                                Engine.DEALLOCATING_RESOURCES))) {
                    recognizer.resume();
                }
                break;
            default:
                //not interested
        }
    }
}
