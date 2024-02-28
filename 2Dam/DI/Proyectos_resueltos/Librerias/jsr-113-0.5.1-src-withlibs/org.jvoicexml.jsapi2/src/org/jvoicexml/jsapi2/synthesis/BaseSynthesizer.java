/*
 * File:    $HeadURL: https://svn.sourceforge.net/svnroot/jvoicexml/trunk/src/org/jvoicexml/Application.java$
 * Version: $LastChangedRevision: 68 $
 * Date:    $LastChangedDate $
 * Author:  $LastChangedBy: schnelle $
 *
 * JSAPI - An independent reference implementation of JSR 113.
 *
 * Copyright (C) 2007-2013 JVoiceXML group - http://jvoicexml.sourceforge.net
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

package org.jvoicexml.jsapi2.synthesis;

import java.util.Collection;

import javax.sound.sampled.AudioFormat;
import javax.speech.AudioException;
import javax.speech.AudioManager;
import javax.speech.AudioSegment;
import javax.speech.EngineEvent;
import javax.speech.EngineException;
import javax.speech.EngineListener;
import javax.speech.EngineStateException;
import javax.speech.SpeechEventExecutor;
import javax.speech.VocabularyManager;
import javax.speech.synthesis.PhoneInfo;
import javax.speech.synthesis.Speakable;
import javax.speech.synthesis.SpeakableEvent;
import javax.speech.synthesis.SpeakableException;
import javax.speech.synthesis.SpeakableListener;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerEvent;
import javax.speech.synthesis.SynthesizerListener;
import javax.speech.synthesis.SynthesizerMode;
import javax.speech.synthesis.SynthesizerProperties;

import org.jvoicexml.jsapi2.BaseEngine;
import org.jvoicexml.jsapi2.BaseVocabularyManager;
import org.jvoicexml.jsapi2.ThreadSpeechEventExecutor;


/**
 * Basic synthesizer functions.
 *
 * @author Renato Cassaca
 * @author Dirk Schnelle-Walka
 * @version $Revision: $
 */
public abstract class BaseSynthesizer extends BaseEngine
    implements Synthesizer {
    /** Registered listeners for this synthesizer. */
    private final Collection<SpeakableListener> speakableListeners;
    /** Current synthesizer properties. */
    private final SynthesizerProperties synthesizerProperties;
    /** Mask for events. */
    private int speakableMask;
    /** Employed queued manager. */
    private final QueueManager queueManager;

    /**
     * Constructs a new object.
     */
    public BaseSynthesizer() {
        this(null);
    }

    /**
     * Constructs a new object.
     * @param engineMode the engine mode for this synthesizer
     */
    public BaseSynthesizer(final SynthesizerMode engineMode) {
        super(engineMode);
        speakableListeners = new java.util.ArrayList<SpeakableListener>();
        synthesizerProperties = createSynthesizerProperties();
        speakableMask = SpeakableEvent.DEFAULT_MASK;
        setEngineMask(getEngineMask() | SynthesizerEvent.DEFAULT_MASK);
        queueManager = new QueueManager(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void fireEvent(final Collection<EngineListener> listeners,
            final EngineEvent event) {
        final SynthesizerEvent synthesizerEvent = (SynthesizerEvent) event;
        for (EngineListener listener : listeners) {
            SynthesizerListener synthesizerListener =
                    (SynthesizerListener) listener;
            synthesizerListener.synthesizerUpdate(synthesizerEvent);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final EngineEvent createStateTransitionEngineEvent(
            final long oldState, final long newState, final int eventType) {
        return new SynthesizerEvent(this, eventType,
                oldState, newState, null, false);
    }

    /**
     * Utility method to post a {@link SynthesizerEvent} using the current
     * {@link SpeechEventExecutor} created from the given parameters.
     * @param oldState the old synthesizer state
     * @param newState the new synthesizer state
     * @param eventType the type of the event
     * @param changedTopOfQueue <code>true</code> if the top of the queue
     *          was changed
     */
    protected final void postSynthesizerEvent(final long oldState,
            final long newState, final int eventType,
            final boolean changedTopOfQueue) {
        boolean topChanged = changedTopOfQueue;
        switch (eventType) {
        case SynthesizerEvent.QUEUE_UPDATED:
        case SynthesizerEvent.QUEUE_EMPTIED:
            break;
        default:
            topChanged = false;
        }
        final SynthesizerEvent event = new SynthesizerEvent(this,
                eventType, oldState, newState, null, topChanged);

        postEngineEvent(event);
    }

    /**
     * Posts the given {@link SpeakableEvent} to the given listener using the
     * current {@link SpeechEventExecutor}.
     * @param event the event to send
     * @param extraSpeakableListener the listener to notify, maybe
     *          <code>null</code>
     */
    protected final void postSpeakableEvent(final SpeakableEvent event,
            final SpeakableListener extraSpeakableListener) {
        // Firstly, check if the event is filtered by the mask
        final int id = event.getId();
        if ((speakableMask & id) != id) {
            return;
        }
        
        // Fire the event
        final Runnable runnable = new Runnable() {
            public void run() {
                if (extraSpeakableListener != null) {
                    extraSpeakableListener.speakableUpdate(event);
                }

                if (speakableListeners != null) {
                    for (SpeakableListener listener : speakableListeners) {
                        listener.speakableUpdate(event);
                    }
                }
            }
        };
        final SpeechEventExecutor executor = getSpeechEventExecutor();
        executor.execute(runnable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected long getEngineStates() {
        return super.getEngineStates() | Synthesizer.QUEUE_EMPTY
                | Synthesizer.QUEUE_NOT_EMPTY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addSpeakableListener(final SpeakableListener listener) {
        if (!speakableListeners.contains(listener)) {
            speakableListeners.add(listener);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void removeSpeakableListener(
            final SpeakableListener listener) {
        speakableListeners.remove(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addSynthesizerListener(
            final SynthesizerListener listener) {
        addEngineListener(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void removeSynthesizerListener(
            final SynthesizerListener listener) {
        removeEngineListener(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean cancel() throws EngineStateException {
        checkEngineState(DEALLOCATED | DEALLOCATING_RESOURCES);

        // Wait to finalize allocation
        if (testEngineState(ALLOCATING_RESOURCES)) {
            try {
                waitEngineState(ALLOCATED);
            } catch (InterruptedException ex) {
                return false;
            }
        }
        return queueManager.cancelItem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean cancel(final int id) throws IllegalArgumentException,
            EngineStateException {
        checkEngineState(DEALLOCATED | DEALLOCATING_RESOURCES);

        // Wait to finalize allocation
        while (testEngineState(ALLOCATING_RESOURCES)) {
            try {
                waitEngineState(ALLOCATED);
            } catch (InterruptedException ex) {
                return false;
            }
        }
        return queueManager.cancelItem(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean cancelAll() throws EngineStateException {
        checkEngineState(DEALLOCATED | DEALLOCATING_RESOURCES);

        // Wait to finalize allocation
        while (testEngineState(ALLOCATING_RESOURCES)) {
            try {
                waitEngineState(ALLOCATED);
            } catch (InterruptedException ex) {
                return false;
            }
        }
        return queueManager.cancelAllItems();
    }

    public String getPhonemes(String text) throws EngineStateException {
        return "";
    }

    /**
     * Maybe be overwritten by the implementing synthesizer to provide specific
     * {@link SynthesizerProperties}.
     * @return created synthesizer properties. This implementation returns an
     *          instance of {@link BaseSynthesizerProperties}.
     */
    protected SynthesizerProperties createSynthesizerProperties() {
        return new BaseSynthesizerProperties(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final SynthesizerProperties getSynthesizerProperties() {
        return synthesizerProperties;
    }

    public void setSpeakableMask(int mask) {
        speakableMask = mask;
    }

    public int getSpeakableMask() {
        return speakableMask;
    }

    public int speak(AudioSegment audio, SpeakableListener listener)
        throws EngineStateException, IllegalArgumentException {
        return queueManager.appendItem(audio, listener);
    }


    public int speak(Speakable speakable, SpeakableListener listener)
            throws EngineStateException, SpeakableException,
            IllegalArgumentException {

        // Wait to finalize allocation
        while (testEngineState(ALLOCATING_RESOURCES)) {
            try {
                waitEngineState(ALLOCATED);
            } catch (InterruptedException ex) {
                return -1;
            }
        }

        return queueManager.appendItem(speakable, listener);
    }

    /**
     * {@inheritDoc}
     */
    public int speak(final String text, final SpeakableListener listener)
            throws EngineStateException {
        checkEngineState(DEALLOCATED | DEALLOCATING_RESOURCES);

        // Wait to finalize allocation
        while (testEngineState(ALLOCATING_RESOURCES)) {
            try {
                final long delay = 300;
                waitEngineState(ALLOCATED, delay);
            } catch (InterruptedException ex) {
                throw new EngineStateException(
                        "wait engine state interrupted: " + ex.getMessage());
            }
        }

        final Speakable speakable = new BaseSpeakable(text);
        return queueManager.appendItem(speakable, listener, text);
    }

    /**
     * {@inheritDoc}
     */
    public int speakMarkup(final String synthesisMarkup,
            final SpeakableListener listener) throws EngineStateException,
            SpeakableException, IllegalArgumentException {

        // Wait to finalize allocation
        while (testEngineState(ALLOCATING_RESOURCES)) {
            try {
                waitEngineState(ALLOCATED);
            } catch (InterruptedException ex) {
                return -1;
            }
        }

        final Speakable speakable = new BaseSpeakable(synthesisMarkup);
        return queueManager.appendItem(speakable, listener);
    }

    /**
     * {@inheritDoc}
     */
    protected final void baseAllocate() throws EngineStateException,
            EngineException, AudioException, SecurityException {

        // Starts AudioManager
        final AudioManager audioManager = getAudioManager();
        audioManager.audioStart();
                
        // Proceed to real engine allocation
        handleAllocate();
        long[] states = setEngineState(CLEAR_ALL_STATE, ALLOCATED
                | DEFOCUSED | QUEUE_EMPTY | RESUMED);

        postStateTransitionEngineEvent(states[0], states[1],
                EngineEvent.ENGINE_ALLOCATED);
    }

    /**
     * Perform the real allocation of the synthesizer.
     * @throws AudioException
     *          if any audio request fails 
     * @throws EngineException
     *          if an allocation error occurred or the Engine is not
     *          operational. 
     * @throws EngineStateException
     *          if called for an Engine in the DEALLOCATING_RESOURCES state 
     * @throws SecurityException
     *          if the application does not have permission for this Engine
     */
    protected abstract void handleAllocate()
            throws EngineStateException,
        EngineException, AudioException, SecurityException;


    /**
     * {@inheritDoc}
     */
    protected void baseDeallocate() throws EngineStateException,
            EngineException, AudioException {

        // Stops AudioManager if audio management is supported.
        if (isSupportsAudioManagement()) {
            final AudioManager audioManager = getAudioManager();
            audioManager.audioStop();
        }

        // Procceed to real engine deallocation
        handleDeallocate();
        
        // Adapt the state
        long[] states = setEngineState(CLEAR_ALL_STATE, DEALLOCATED);
        postStateTransitionEngineEvent(states[0], states[1],
                EngineEvent.ENGINE_DEALLOCATED);
    }

    /**
     * {@inheritDoc}
     */
    protected void basePause() {
        handlePause();
    }

    /**
     * Called from the <code>resume</code> method. Override in subclasses.
     *
     * @todo Handle grammar updates
     */
    protected boolean baseResume() {
        return handleResume();
    }

    abstract protected void handleDeallocate() throws EngineStateException,
        EngineException, AudioException;

    abstract protected void handlePause();

    abstract protected boolean handleResume();

    /**
     * Speaks the item with the given id.
     * <p>
     * Implementations synthesize the text to speak and make it available
     * via an {@link java.io.InputStream}. This input stream is used to
     * create a {@link javax.speech.AudioSegment} which is passed to the
     * {@link QueueManager} to be played back using the settings from the
     * {@link javax.speech.AudioManager}.
     * </p>
     * @param id id of the text to speak
     * @param item the text to speak
     * @return the audio segment that can be used to play back the audio
     */
    protected abstract AudioSegment handleSpeak(final int id,
            final String item);

    /**
     * Speaks the SSML item with the given id.
     * <p>
     * Implementations synthesize the text to speak and make it available
     * via an {@link java.io.InputStream}. This input stream is used to
     * create a {@link javax.speech.AudioSegment} which is passed to the
     * {@link QueueManager} to be played back using the settings from the
     * {@link javax.speech.AudioManager}.
     * </p>
     * @param id id of the SSML markup text to speak
     * @param item the SSML markup text to speak
     * @return the audio segment that can be used to play back the audio
     */
    protected abstract AudioSegment handleSpeak(final int id,
            final Speakable item);

    /**
     * Returns a <code>String</code> of the names of all the
     * <code>Engine</code> states in the given <code>Engine</code> state.
     *
     * @param state
     *                the bitmask of states
     *
     * @return a <code>String</code> containing the names of all the states
     *         set in <code>state</code>
     */
    public String stateToString(final long state) {
        final String stateString = super.stateToString(state);
        final StringBuffer buf = new StringBuffer(stateString);
        if ((state & Synthesizer.QUEUE_EMPTY) != 0) {
            buf.append(" QUEUE_EMPTY ");
        }
        if ((state & Synthesizer.QUEUE_NOT_EMPTY) != 0) {
            buf.append(" QUEUE_NOT_EMPTY ");
        }
        return buf.toString();
    }

    /**
     * Cancels the item that is currently being played back.
     * @return <code>true</code> if the item was canceled
     * @exception EngineStateException
     *            if the engine was in an invalid state
     */
    protected abstract boolean handleCancel() throws EngineStateException;

    /**
     * Cancels all items in the play queue.
     * @return <code>true</code> if at least one item was canceled
     * @exception EngineStateException
     *            if the engine was in an invalid state
     */
    protected abstract boolean handleCancelAll() throws EngineStateException;

    /**
     * Cancels the item with the given id.
     * @param id id of the item to cancel
     * @return <code>true</code> if the item with the given id was canceled.
     * @exception EngineStateException
     *            if the engine was in an invalid state
     */
    protected abstract boolean handleCancel(final int id)
        throws EngineStateException;

    /**
     * Utility method to set words in a queue item.
     *
     * @param itemId the id of the queued item
     * @param String[] words
     */
    protected void setWords(int itemId, String[] words) {
        queueManager.setWords(itemId, words);
    }

    /**
     * Set words times in a queueItem (Not JSAPI2)
     *
     * @param itemId int
     * @param float[] words
     */
    protected void setWordsStartTimes(int itemId, float[] starttimes) {
        queueManager.setWordsStartTimes(itemId, starttimes);
    }

    protected void setPhonesInfo(int itemId, PhoneInfo[] phonesinfo) {
        queueManager.setPhonesInfo(itemId, phonesinfo);
    }

    /**
     * Retrieves the queue manager.
     * @return the queue manager
     */
    protected QueueManager getQueueManager() {
        return queueManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected SpeechEventExecutor createSpeechEventExecutor() {
        return new ThreadSpeechEventExecutor();
    }

    /**
     * Retrieves the audio format that is used by this synthesizer.
     * @return used audio format
     */
    protected abstract AudioFormat getEngineAudioFormat();

    /**
     * {@inheritDoc}
     */
    @Override
    protected AudioManager createAudioManager() {
        final AudioFormat format = getEngineAudioFormat();
        return new BaseSynthesizerAudioManager(this, format);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected VocabularyManager createVocabularyManager() {
        return new BaseVocabularyManager();
    }
}
