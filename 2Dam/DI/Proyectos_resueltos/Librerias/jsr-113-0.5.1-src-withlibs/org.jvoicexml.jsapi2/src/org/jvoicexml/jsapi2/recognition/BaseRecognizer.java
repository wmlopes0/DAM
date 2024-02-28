/*
 * File:    $HeadURL: $
 * Version: $LastChangedRevision:  $
 * Date:    $LastChangedDate $
 * Author:  $LastChangedBy: lyncher $
 *
 * JSAPI - An independent reference implementation of JSR 113.
 *
 * Copyright (C) 2007 JVoiceXML group - http://jvoicexml.sourceforge.net
 *
 * This class is based on work by SUN Microsystems and
 * Carnegie Mellon University
 *
 * Copyright 1998-2003 Sun Microsystems, Inc.
 *
 * Portions Copyright 2001-2004 Sun Microsystems, Inc.
 * Portions Copyright 1999-2001 Language Technologies Institute,
 * Carnegie Mellon University.
 * All Rights Reserved.  Use is subject to license terms.
 *
 * Permission is hereby granted, free of charge, to use and distribute
 * this software and its documentation without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of this work, and to
 * permit persons to whom this work is furnished to do so, subject to
 * the following conditions:
 *
 * 1. The code must retain the above copyright notice, this list of
 *    conditions and the following disclaimer.
 * 2. Any modifications must be clearly marked as such.
 * 3. Original authors' names are not deleted.
 * 4. The authors' names are not used to endorse or promote products
 *    derived from this software without specific prior written
 *   permission.
 *
 * SUN MICROSYSTEMS, INC., CARNEGIE MELLON UNIVERSITY AND THE
 * CONTRIBUTORS TO THIS WORK DISCLAIM ALL WARRANTIES WITH REGARD TO THIS
 * SOFTWARE, INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS, IN NO EVENT SHALL SUN MICROSYSTEMS, INC., CARNEGIE MELLON
 * UNIVERSITY NOR THE CONTRIBUTORS BE LIABLE FOR ANY SPECIAL, INDIRECT OR
 * CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF
 * USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR
 * OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THIS SOFTWARE.
 */
package org.jvoicexml.jsapi2.recognition;

import java.io.InputStream;
import java.security.Permission;
import java.util.Collection;

import javax.sound.sampled.AudioFormat;
import javax.speech.AudioException;
import javax.speech.AudioManager;
import javax.speech.EngineEvent;
import javax.speech.EngineException;
import javax.speech.EngineListener;
import javax.speech.EngineStateException;
import javax.speech.SpeechEventExecutor;
import javax.speech.SpeechPermission;
import javax.speech.VocabularyManager;
import javax.speech.recognition.Grammar;
import javax.speech.recognition.GrammarException;
import javax.speech.recognition.GrammarManager;
import javax.speech.recognition.Recognizer;
import javax.speech.recognition.RecognizerEvent;
import javax.speech.recognition.RecognizerListener;
import javax.speech.recognition.RecognizerMode;
import javax.speech.recognition.RecognizerProperties;
import javax.speech.recognition.Result;
import javax.speech.recognition.ResultEvent;
import javax.speech.recognition.ResultListener;
import javax.speech.recognition.RuleGrammar;
import javax.speech.recognition.SpeakerManager;

import org.jvoicexml.jsapi2.BaseAudioManager;
import org.jvoicexml.jsapi2.BaseEngine;
import org.jvoicexml.jsapi2.BaseVocabularyManager;
import org.jvoicexml.jsapi2.ThreadSpeechEventExecutor;

/**
 * Skeletal Implementation of the JSAPI Recognizer interface.
 *
 * This class is useful by itself for debugging, e.g. you
 * can load grammars and simulate a recognizer recognizing
 * some text, etc.
 *
 * <p>
 * Actual JSAPI recognizer implementations might want to extend or
 * modify this implementation.
 * </p>
 *
 */
public abstract class BaseRecognizer extends BaseEngine implements Recognizer {
    /** Registered result listeners. */
    private Collection<ResultListener> resultListeners;

    protected boolean hasModalGrammars;

    protected boolean supportsNULL = true;
    protected boolean supportsVOID = true;

    /** used when printing grammars. */
    protected RuleGrammar currentGrammar;

    private final SpeakerManager speakerManager;
    protected RecognizerProperties recognizerProperties;
    private int resultMask;

    /** The related grammar manager. */
    private final GrammarManager grammarManager;

    /**
     * Create a new Recognizer in the DEALLOCATED state.
     */
    public BaseRecognizer() {
        this(null);
    }

    /**
     * Create a new Recognizer in the DEALLOCATED state.
     * @param mode the recognizer mode
     */
    public BaseRecognizer(final RecognizerMode mode) {
        super(mode);
        resultListeners = new java.util.ArrayList<ResultListener>();
        speakerManager = new BaseSpeakerManager();
        final RecognizerProperties props =
            new BaseRecognizerProperties(this);
        setRecognizerProperties(props);
        grammarManager = new BaseGrammarManager(this);
        resultMask = ResultEvent.DEFAULT_MASK;
        setEngineMask(getEngineMask() | RecognizerEvent.DEFAULT_MASK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected VocabularyManager createVocabularyManager() {
        return new BaseVocabularyManager();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GrammarManager getGrammarManager() {
        return grammarManager;
    }

    /**
     * {@inheritDoc}
     */
    public final void requestFocus() throws EngineStateException {
        checkEngineState(DEALLOCATED | DEALLOCATING_RESOURCES);

        if (testEngineState(ALLOCATING_RESOURCES)) {
            try {
                waitEngineState(ALLOCATED);
            } catch (InterruptedException ex) {
                return;
            }
        }

        // Do nothing if the state is already OK
        if (testEngineState(FOCUSED)) {
            return;
        }

        long[] states = setEngineState(DEFOCUSED, FOCUSED);
        postStateTransitionEngineEvent(states[0], states[1],
                EngineEvent.ENGINE_FOCUSED);

        notifyGrammarActivation();
        handleRequestFocus();
    }

    /**
     * {@inheritDoc}
     */
    public final void releaseFocus() throws EngineStateException {
        checkEngineState(DEALLOCATED | DEALLOCATING_RESOURCES);

        if (testEngineState(ALLOCATING_RESOURCES)) {
            try {
                waitEngineState(ALLOCATED);
            } catch (InterruptedException ex) {
                return;
            }
        }

        if (testEngineState(DEFOCUSED)) {
            return;
        }

        long[] states = setEngineState(FOCUSED, DEFOCUSED);
        postStateTransitionEngineEvent(states[0], states[1],
                EngineEvent.ENGINE_DEFOCUSED);

        notifyGrammarActivation();
        handleReleaseFocus();
    }

    /**
     * {@inheritDoc}
     */
    public final void pause(final int mode) throws EngineStateException {
        // Validate current state
        if (testEngineState(PAUSED)) {
            return;
        }

        checkEngineState(DEALLOCATED | DEALLOCATING_RESOURCES);

        if (testEngineState(ALLOCATING_RESOURCES)) {
            try {
                waitEngineState(ALLOCATED);
            } catch (InterruptedException ex) {
                return;
            }
        }

        // Handle pause
        basePause(mode);
        long[] states = setEngineState(RESUMED, PAUSED);
        postStateTransitionEngineEvent(states[0], states[1],
                EngineEvent.ENGINE_PAUSED);
    }

    /**
     * Notify any grammars if their activation state has been changed.
     */
    protected void notifyGrammarActivation() {
        /*if (grammars == null) {
            return;
        }*/
        /*  Enumeration e = grammars.elements();
          while (e.hasMoreElements()) {
              RuleGrammar rg = (RuleGrammar) e.nextElement();
              boolean active = isActive(rg);*/
        /*     if (active != rg.isActive()) {
                 rg.grammarActive = active;
                 if (active) {
                     rg.postGrammarActivated();
                 } else {
                     rg.postGrammarDeactivated();
                 }
             }*/
        // }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void fireEvent(final Collection<EngineListener> listeners,
            final EngineEvent event) {
        final RecognizerEvent recognizerEvent = (RecognizerEvent) event;
        for (EngineListener listener : listeners) {
            RecognizerListener recognizerListener =
                    (RecognizerListener) listener;
            recognizerListener.recognizerUpdate(recognizerEvent);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public EngineEvent createStateTransitionEngineEvent(final long oldState,
            final long newState, final int eventType) {
        // TODO: Can we determine the audio position?
        return new RecognizerEvent(this, eventType, oldState, newState, null,
                null, RecognizerEvent.UNKNOWN_AUDIO_POSITION);
    }

    public void postEngineEvent(long oldState, long newState, int eventType,
                                long audioPosition) {
        switch (eventType) {
        case RecognizerEvent.SPEECH_STARTED:
        case RecognizerEvent.SPEECH_STOPPED:
        case RecognizerEvent.RECOGNIZER_BUFFERING:
        case RecognizerEvent.RECOGNIZER_NOT_BUFFERING:
            break;
        default:
            audioPosition = RecognizerEvent.UNKNOWN_AUDIO_POSITION;
        }

        final RecognizerEvent event = new RecognizerEvent(this,
                eventType,
                oldState,
                newState,
                null,
                null,
                audioPosition);
        postEngineEvent(event);
    }


    protected void postResultEvent(final ResultEvent event) {
        final SpeechEventExecutor executor = getSpeechEventExecutor();
        try {
            executor.execute(new Runnable() {
                public void run() {
                    fireResultEvent(event);
                }
            });
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        final Result result = (Result) event.getSource();
        postResultEvent(result, event, executor);
    }

    /**
     * Notification of the result to the registered {@link ResultListener}s
     * about the given result event.
     * @param result the result
     * @param event the event
     * @param executor the speech event executor
     */
    protected void postResultEvent(final Result result,
            final ResultEvent event,
            final SpeechEventExecutor executor) {
        final BaseResult base = (BaseResult) result;
        base.postResultEvent(executor, event);
    }

    public void fireResultEvent(final ResultEvent event) {
        for (ResultListener listener : resultListeners) {
            listener.resultUpdate(event);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addRecognizerListener(final RecognizerListener listener) {
        addEngineListener(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void removeRecognizerListener(
            final RecognizerListener listener) {
        removeEngineListener(listener);
    }

    /**
     * Request notification of Result events from the Recognizer.
     * From javax.speech.recognition.Recognizer.
     * @param listener the listener to add.
     */
    public void addResultListener(final ResultListener listener) {
        if (!resultListeners.contains(listener)) {
            resultListeners.add(listener);
        }
    }

    /**
     * Remove a ResultListener from the list of ResultListeners.
     * From javax.speech.recognition.Recognizer.
     * @param listener the listener to remove.
     */
    public void removeResultListener(ResultListener listener) {
        resultListeners.remove(listener);
    }

    /**
     * Get the RecognizerProperties of this Recognizer.
     * From javax.speech.recognition.Recognizer.
     */
    public RecognizerProperties getRecognizerProperties() {
        return recognizerProperties;
    }

    /**
     * Sets the properties for this recognizer.
     * @param properties the properties
     */
    protected void setRecognizerProperties(RecognizerProperties
                                        properties) {
        recognizerProperties = properties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final SpeakerManager getSpeakerManager() {
        final SecurityManager security = System.getSecurityManager();
        if (security != null) {
            final Permission permission = new SpeechPermission(
                    "javax.speech.recognition.SpeakerManager");
            security.checkPermission(permission);
        }
        return speakerManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setResultMask(final int mask) {
        resultMask = mask;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getResultMask() {
        return resultMask;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void processGrammars() throws EngineStateException {
        // Flag that indicates if grammars were changed
        boolean existChanges = false;

        // Build a new grammar set, with all enabled grammars
        Collection<GrammarDefinition> newGrammars =
                new java.util.ArrayList<GrammarDefinition>();

        // Commit all grammars pending changes
        final Grammar[] grammars = grammarManager.listGrammars();
        for (int i = 0; i < grammars.length; i++) {
            final Grammar grammar = grammars[i];
            boolean updated;
            try {
                updated = processGrammar(grammar);
            } catch (GrammarException e) {
                updated = false;
            }
            // Update "modified-flag"
            existChanges = existChanges || updated;
            if (grammar.isActivatable()) {
                final GrammarDefinition definition =
                    new GrammarDefinition(grammar.toString(),
                            grammar.getReference(), existChanges);
                newGrammars.add(definition);
            }
        }

        // Set grammars
        boolean setGrammarsResult = setGrammars(newGrammars);

        // Raise proper events
        if (!existChanges) {
            return;
        }
        if (setGrammarsResult) {
            postStateTransitionEngineEvent(PAUSED, RESUMED,
                    RecognizerEvent.CHANGES_COMMITTED);
            for (int i = 0; i < grammars.length; i++) {
                final BaseGrammar baseGrammar = (BaseGrammar) grammars[i];
                baseGrammar.postGrammarChangesCommitted();
            }
        } else {
            for (int i = 0; i < grammars.length; i++) {
                final BaseGrammar baseGrammar = (BaseGrammar) grammars[i];
                baseGrammar.postGrammarChangesRejected();
            }
        }
    }

    /**
     * Processes the given grammar.
     * @param grammar the grammar to process.
     * @exception GrammarException error processing the grammar
     * @return <code>true</code> if the grammar has been updated
     */
    protected boolean processGrammar(final Grammar grammar)
        throws GrammarException {
        if (grammar instanceof BaseRuleGrammar) {
            BaseRuleGrammar baseRuleGrammar = (BaseRuleGrammar) grammar;
            return baseRuleGrammar.commitChanges();
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected long getEngineStates() {
        return super.getEngineStates() | Recognizer.LISTENING
            | Recognizer.PROCESSING;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String stateToString(final long state) {
        StringBuffer buf = new StringBuffer(super.stateToString(state));
        if ((state & Recognizer.LISTENING) != 0) {
            buf.append(" LISTENING ");
        }
        if ((state & Recognizer.PROCESSING) != 0) {
            buf.append(" PROCESSING ");
        }
        return buf.toString();
    }


    /**
     * Called from the <code>allocate</code> method.
     *
     * @see #allocate
     *
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
    protected final void baseAllocate() throws EngineStateException,
            EngineException, AudioException, SecurityException {
        final AudioManager audioManager = getAudioManager();
        audioManager.audioStart();

        // Proceed to real engine allocation
        try {
            handleAllocate();
            long[] states = setEngineState(CLEAR_ALL_STATE,
                    ALLOCATED | PAUSED | DEFOCUSED | NOT_BUFFERING);
            postStateTransitionEngineEvent(states[0], states[1],
                    EngineEvent.ENGINE_ALLOCATED);
        } catch (EngineStateException e) {
            audioManager.audioStop();
        } catch (EngineException e) {
            audioManager.audioStop();
        } catch (AudioException e) {
            audioManager.audioStop();
        } catch (SecurityException e) {
            audioManager.audioStop();
        }
    }

    /**
     * Perform the real allocation of the recognizer. When this
     * method is called, the {@link javax.speech.AudioManager} has already
     * been started.
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
    protected abstract void handleAllocate() throws EngineStateException,
        EngineException, AudioException, SecurityException;

    /**
     * Called from the <code>deallocate</code> method.  Override this in
     * subclasses.
     *
     * @throws EngineException if this {@link javax.speech.Engine}
     *          cannot be deallocated.
     */
    protected final void baseDeallocate() throws EngineStateException,
            EngineException, AudioException {

        // Procceed to real engine deallocation
        try {
            handleDeallocate();
        } finally {
            // Stops AudioManager
            final AudioManager audioManager = getAudioManager();
            audioManager.audioStop();
        }

        // Post the state transition
        long[] states = setEngineState(CLEAR_ALL_STATE, DEALLOCATED);
        postStateTransitionEngineEvent(states[0], states[1],
                EngineEvent.ENGINE_DEALLOCATED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void basePause() {
        handlePause();
        setEngineState(LISTENING | PROCESSING,
                       getEngineState() & ~LISTENING & ~PROCESSING);
        setEngineState(BUFFERING, NOT_BUFFERING);
    }

    /**
     * {@inheritDoc}
     */
    protected final void basePause(final int flags) {
        handlePause(flags);
        setEngineState(LISTENING | PROCESSING,
                       getEngineState() & ~LISTENING & ~PROCESSING);
    }

    /**
     * Called from the {@link #resume()} method.
     *
     * @exception EngineStateException
     *            when not in the standard Conditions for operation
     * @todo Handle grammar updates
     */
    protected final boolean baseResume() throws EngineStateException {

        //Process grammars
        processGrammars();
        final AudioManager manager = getAudioManager();
        
        // resume streaming of audio
        InputStream in = null;
        if (manager instanceof BaseAudioManager) {
            final BaseAudioManager baseManager = (BaseAudioManager) manager;
            in = baseManager.getInputStream();
        }
        boolean status = handleResume(in);
        if (!status) {
            return false;
        }

        // Advance the states
        setEngineState(0, LISTENING);
        postStateTransitionEngineEvent(0, LISTENING,
                RecognizerEvent.RECOGNIZER_LISTENING);
        setEngineState(NOT_BUFFERING, BUFFERING);

        return true;
    }


    protected abstract void handleDeallocate();

    protected abstract void handlePause();

    protected abstract void handlePause(int flags);

    /**
     * Further handling of resuming the recognizer.
     * @param in the input stream where to read data from.
     * @return <code>true</code> if the recognizer was resumed
     * @throws EngineStateException
     *         error resuming the recognizer
     */
    protected abstract boolean handleResume(final InputStream in)
        throws EngineStateException;

    protected abstract boolean setGrammars(Collection<GrammarDefinition> grammarDefinition);

    protected abstract void handleRequestFocus();

    protected abstract void handleReleaseFocus();

    /**
     * Returns a list of engine built-in grammars.
     * @return list of buildtin grammars
     */
    public abstract Collection<Grammar> getBuiltInGrammars();


    /**
     * Retrieves the audio format that is produced by this recognizer.
     * @return audio format.
     */
    protected abstract AudioFormat getAudioFormat();

    /**
     * {@inheritDoc}
     */
    @Override
    protected AudioManager createAudioManager() {
        final AudioFormat format = getAudioFormat();
        return new BaseRecognizerAudioManager(this, format);
    }

    /**
     * {@inheritDoc}
     * @return a new instance of {@link ThreadSpeechEventExecutor}.
     */
    @Override
    protected SpeechEventExecutor createSpeechEventExecutor() {
        return new ThreadSpeechEventExecutor();
    }
}
