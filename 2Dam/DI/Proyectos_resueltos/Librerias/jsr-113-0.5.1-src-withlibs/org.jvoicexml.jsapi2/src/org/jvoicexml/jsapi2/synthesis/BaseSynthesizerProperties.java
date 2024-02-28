/*
 * File:    $HeadURL: https://svn.code.sf.net/p/jsapi/code/trunk/org.jvoicexml.jsapi2/src/org/jvoicexml/jsapi2/synthesis/BaseSynthesizerProperties.java $
 * Version: $LastChangedRevision: 843 $
 * Date:    $LastChangedDate $
 * Author:  $LastChangedBy: schnelle $
 *
 * JSAPI - An independent reference implementation of JSR 113.
 *
 * Copyright (C) 2007-2011 JVoiceXML group - http://jvoicexml.sourceforge.net
 *
 */

package org.jvoicexml.jsapi2.synthesis;

import javax.speech.Engine;
import javax.speech.synthesis.SynthesizerMode;
import javax.speech.synthesis.SynthesizerProperties;
import javax.speech.synthesis.Voice;

import org.jvoicexml.jsapi2.BaseEngineProperties;

/**
 * Base implementation of {@link SynthesizerProperties}.
 *
 * <p>
 * Actual JSAPI2 implementations may want to override this class to
 * apply the settings to the synthesizer.
 * </p>
 *
 * @author Renato Cassaca
 * @author Dirk Schnelle-Walka
 * @version $Revision: 843 $
 */
public class BaseSynthesizerProperties extends BaseEngineProperties
    implements SynthesizerProperties {
    /** Name of the interruptibility property in events. */
    public static final String INTERRUPTIBILITY = "interruptibility";

    /** Name of the pitch property in events. */
    public static final String PITCH = "pitch";

    /** Name of the pitch range property in events. */
    public static final String PITCH_RANGE = "pitchRange";

    /** Name of the speaking rate property in events. */
    public static final String SPEAKING_RATE = "speakingRate";

    /** Name of the speaking rate property in events. */
    public static final String VOICE = "voice";

    /** Name of the volume property in events. */
    public static final String VOLUME = "volume";

    /** Current value for the interruptibility. */
    private int interruptibility;

    /** Current value for the pitch. */
    private int pitch;

    /** Current value for the pitch range. */
    private int pitchRange;

    /** Current value for the speaking rate. */
    private int speakingRate;

    /** The current voice. */
    private Voice voice;

    /** Current value for the volume. */
    private int volume;

    /**
     * Constructs a new Object.
     * @param synthesizer reference to the synthesizer.
     */
    public BaseSynthesizerProperties(final BaseSynthesizer synthesizer) {
        super(synthesizer);

        interruptibility = OBJECT_LEVEL;
        pitch = 160;
        pitchRange = (int) (160 * 0.60);
        speakingRate = DEFAULT_RATE;
        volume = MEDIUM_VOLUME;
        //Set default voice
        final Engine engine = getEngine();
        final SynthesizerMode mode = (SynthesizerMode) engine.getEngineMode();
        if (mode == null) {
            voice = null;
        } else {
            Voice[] voices = mode.getVoices();
            if ((voices != null) && (voices.length > 0)) {
                voice = voices[0];
            } else {
                voice = null;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getInterruptibility() {
        return interruptibility;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setInterruptibility(final int level) {
        if ((level != WORD_LEVEL) && (level != OBJECT_LEVEL)
                && (level != QUEUE_LEVEL)) {
            throw new IllegalArgumentException("Invalid interruptibiliy level :"
                    + level);
        }
        if (level == interruptibility) {
            return;
        }
        handlePropertyChangeRequest(INTERRUPTIBILITY,
                new Integer(interruptibility),
                new Integer(level));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getPitch() {
        return pitch;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setPitch(final int hertz) {
        if (hertz <= 0) {
            throw new IllegalArgumentException(
                    "Pitch is not a positive integer:"  + hertz);
        }
        if (pitch == hertz) {
            return;
        }
        handlePropertyChangeRequest(PITCH,
                new Integer(pitch), new Integer(hertz));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getPitchRange() {
        return pitchRange;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setPitchRange(final int hertz) {
        if (hertz < 0) {
            throw new IllegalArgumentException(
                    "Pitch is a negative integer:"  + hertz);
        }
        if (pitchRange == hertz) {
            return;
        }
        handlePropertyChangeRequest(PITCH_RANGE,
                new Integer(pitchRange), new Integer(hertz));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getSpeakingRate() {
        return speakingRate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setSpeakingRate(final int wpm) {
        if (wpm < 0) {
            throw new IllegalArgumentException(
                    "Speaking rate is not a postivie integer:"  + wpm);
        }
        if (speakingRate == wpm) {
            return;
        }
        handlePropertyChangeRequest(SPEAKING_RATE,
                new Integer(speakingRate), new Integer(wpm));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Voice getVoice() {
        return voice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVoice(final Voice voice) {
        final Engine synthesizer = getEngine();
        final SynthesizerMode mode =
            (SynthesizerMode) synthesizer.getEngineMode();
        if (mode == null) {
            return;
        }
        final Voice[] voices = mode.getVoices();
        for (int i = 0; i < voices.length; i++) {
            final Voice current = voices[i];
            if (current.match(voice)) {
                handlePropertyChangeRequest(VOICE,
                        this.voice, current);
                return;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getVolume() {
        return volume;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setVolume(final int vol) {
        if ((vol < MIN_VOLUME) || (vol > MAX_VOLUME)) {
            throw new IllegalArgumentException("Volume is out of range: "
                    + vol);
        }
        if (volume == vol) {
            return;
        }
        handlePropertyChangeRequest(VOLUME,
                new Integer(volume), new Integer(vol));
    }

    /**
     * {@inheritDoc}
     */
    public void reset() {
        setInterruptibility(OBJECT_LEVEL);
        setPitch(160);
        setPitchRange((int)(160 * 0.60));
        setSpeakingRate(DEFAULT_RATE);
        setVolume(MEDIUM_VOLUME);
        //Set default voice
        final Engine engine = getEngine();
        final SynthesizerMode mode = (SynthesizerMode) engine.getEngineMode();
        if (mode == null) {
            setVoice(null);
        } else {
            Voice[] voices = mode.getVoices();
            if ((voices != null) && (voices.length > 0)) {
                setVoice(voices[0]);
            } else {
                setVoice(null);
            }
        }

        super.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean setProperty(final String propName, final Object value) {
        if (propName.equals(INTERRUPTIBILITY)) {
            final Integer intVal = (Integer) value;
            interruptibility = intVal.intValue();
            return true;
        } else if (propName.equals(PITCH)) {
            final Integer intVal = (Integer) value;
            pitch = intVal.intValue();
            return true;
        } else if (propName.equals(PITCH_RANGE)) {
            final Integer intVal = (Integer) value;
            pitchRange = intVal.intValue();
            return true;
        } else if (propName.equals(SPEAKING_RATE)) {
            final Integer intVal = (Integer) value;
            speakingRate = intVal.intValue();
            return true;
        } else if (propName.equals(VOICE)) {
            voice = (Voice) value;
            return true;
        } else if (propName.equals(VOLUME)) {
            final Integer intVal = (Integer) value;
            volume = intVal.intValue();
            return true;
        }
        return false;
    }
}
