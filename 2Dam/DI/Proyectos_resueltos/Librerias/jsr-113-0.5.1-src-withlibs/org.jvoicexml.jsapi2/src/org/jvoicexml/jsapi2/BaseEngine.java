/*
 * File:    $HeadURL: https://svn.code.sf.net/p/jsapi/code/trunk/org.jvoicexml.jsapi2/src/org/jvoicexml/jsapi2/BaseEngine.java $
 * Version: $LastChangedRevision: 844 $
 * Date:    $LastChangedDate $
 * Author:  $LastChangedBy: schnelle $
 *
 * JSAPI - An independent reference implementation of JSR 113.
 *
 * Copyright (C) 2007-2013 JVoiceXML group - http://jvoicexml.sourceforge.net
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

package org.jvoicexml.jsapi2;

import java.util.Collection;

import javax.speech.AudioException;
import javax.speech.AudioManager;
import javax.speech.Engine;
import javax.speech.EngineEvent;
import javax.speech.EngineException;
import javax.speech.EngineListener;
import javax.speech.EngineMode;
import javax.speech.EngineStateException;
import javax.speech.SpeechEventExecutor;
import javax.speech.VocabularyManager;

/**
 * Supports the JSAPI 2.0 {@link Engine} interface.
 * <p>
 * Actual JSAPI implementations might want to extend or modify this
 * implementation.
 * </p>
 * <p>
 * A base engine has to provide several factory methods:
 * <ul>
 * <li>{@link #createAudioManager()} to create an {@link javax.speech.AudioManager}</li>
 * <li>{@link #createSpeechEventExecutor()} to create the {@link javax.speech.SpeechEventExecutor}</li>
 * <li>{@link #createVocabularyManager()} to create the {@link javax.speech.VocabularyManager}</li>
 * </ul>
 * </p>
 * @author Renato Cassaca
 * @author Dirk Schnelle-Walka
 * @version $Revision: 844 $
 */
public abstract class BaseEngine implements Engine {

    /**
     * A bitmask holding the current state of this <code>Engine</code>.
     */
    private long engineState;

    /**
     * An <code>Object</code> used for synchronizing access to
     * <code>engineState</code>.
     * @see #engineState
     */
    private final Object engineStateLock;
    
    /**
     * A counter keeping track of nested calls to <code>pause</code>
     * and <code>resume</code>
     * A value greater than 1 means nested pauses.
     * If the value is 1 or lower, the Engine can be resumed immediately.
     * @see #pause()
     * @see #resume()
     */
    private int pauses;

    /**
     * List of <code>EngineListeners</code> registered for
     * <code>EngineEvents</code> on this <code>Engine</code>.
     * <p>
     * For a synthesizer this will contain only
     * {@link javax.speech.synthesis.SynthesizerListener}s
     * and for a recognizer only
     * {@link javax.speech.recognition.RecognizerListener}s.
     */
    private final Collection<EngineListener> engineListeners;

    /**
     * The <code>AudioManager</code> for this <code>Engine</code>.
     */
    private AudioManager audioManager;

    /**
     * The <code>VocabularyManager</code> for this <code>Engine</code>.
     */
    private VocabularyManager vocabularyManager;

    /**
     * The <code>EngineModeDesc</code> for this <code>Engine</code>.
     */
    private EngineMode engineMode;

    /** The current speech event executor. */
    private SpeechEventExecutor speechEventExecutor;

    /**
     * Utility state for clearing the <code>engineState</code>.
     */
    protected static final long CLEAR_ALL_STATE = ~(0L);

    /** The engine mask. */
    private int engineMask = EngineEvent.DEFAULT_MASK;

    /**
     * Creates a new <code>Engine</code> in the
     * <code>DEALLOCATED</code> state.
     *
     * @param mode the operating mode of this <code>Engine</code>
     */
    public BaseEngine(final EngineMode mode) {
        engineMode = mode;
        engineListeners = new java.util.ArrayList<EngineListener>();
        engineState = DEALLOCATED;
        engineStateLock = new Object();
        pauses = 0;
    }

    /**
     * Returns a or'ed set of flags indicating the current state of
     * this <code>Engine</code>.
     *
     * <p>An <code>EngineEvent</code> is issued each time this
     * <code>Engine</code> changes state.
     *
     * <p>The <code>getEngineState</code> method can be called successfully
     * in any <code>Engine</code> state.
     *
     * @return the current state of this <code>Engine</code>
     *
     * @see #getEngineState
     * @see #waitEngineState
     */
    public final long getEngineState() {
        return engineState;
    }

    /**
     * {@inheritDoc}
     */
    public final long waitEngineState(final long state)
        throws InterruptedException {
        return waitEngineState(state, 0);
    }

    /**
     * {@inheritDoc}
     */
    public final long waitEngineState(final long state, final long timeout)
        throws InterruptedException {
        if (!isValid(state)) {
            throw new IllegalArgumentException(
                    "Cannot wait for impossible state: "
                    + stateToString(state));
        }
        if (testEngineState(state)) {
            return state;
        }

        if (!isReachable(state)) {
            throw new IllegalStateException("State is not reachable: "
                    + stateToString(state));
        }

        // Wait for a state change
        if (timeout > 0) {
            synchronized (engineStateLock) {
                engineStateLock.wait(timeout);
            }
        } else {
            // Will wait forever to reach that state
            while (!testEngineState(state)) {
                synchronized (engineStateLock) {
                    engineStateLock.wait();
                }
            }
        }
        return getEngineState();
    }

    /**
     * Returns <code>true</code> if this state of this
     * <code>Engine</code> matches the specified state.
     *
     * <p>The test performed is not an exact match to the current
     * state.  Only the specified states are tested.  For
     * example the following returns true only if the
     * <code>Synthesizer</code> queue is empty, irrespective
     * of the pause/resume and allocation states.
     *
     * <PRE>
     *    if (synth.testEngineState(Synthesizer.QUEUE_EMPTY)) ...
     * </PRE>
     *
     * <p>The <code>testEngineState</code> method is equivalent to:
     *
     * <PRE>
     *      if ((engine.getEngineState() & state) == state)
     * </PRE>
     *
     * {@inheritDoc}
     */
    public final boolean testEngineState(final long state) {
        return (getEngineState() & state) == state;
    }

    /**
     * Updates this {@link Engine} state by clearing defined bits,
     * then setting other specified bits.
     * @param clear the flags to clear
     * @param set the flags to set
     * @return a length-2 array with old and new state values.
     */
    public final long[] setEngineState(final long clear, final long set) {
        long[] states = new long[2];
        synchronized (engineStateLock) {
            states[0] = engineState;
            engineState = engineState & (~clear);
            engineState = engineState | set;
            states[1] = engineState;
            engineStateLock.notifyAll();
        }
        return states;
    }

    /**
     * {@inheritDoc}
     */
    public final void allocate() throws AudioException, EngineException,
        EngineStateException, SecurityException {
        // Validate current state
        if (testEngineState(ALLOCATED)
                || testEngineState(ALLOCATING_RESOURCES)) {
            return;
        }

        checkEngineState(DEALLOCATING_RESOURCES);

        // Update current state
        long[] states = setEngineState(CLEAR_ALL_STATE, ALLOCATING_RESOURCES);
        postStateTransitionEngineEvent(states[0], states[1],
                EngineEvent.ENGINE_ALLOCATING_RESOURCES);

        // Handle engine allocation
        boolean success = false;
        try {
            pauses = 0;
            // Handle allocate
            baseAllocate();
            success = true;
        } finally {
            if (!success) {
                states = setEngineState(CLEAR_ALL_STATE, DEALLOCATED);
                postStateTransitionEngineEvent(states[0], states[1],
                        EngineEvent.ENGINE_DEALLOCATED);
            }
        }
    }

    /**
     * Called from the {@link #allocate()} method.
     * <p>
     * Within this method, also the state transition to 
     * {@link Engine#ALLOCATED} must be posted, since the successive state is
     * engine dependent.
     * </p>
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
    protected abstract void baseAllocate()
        throws AudioException, EngineException, EngineStateException,
            SecurityException;

    /**
     * {@inheritDoc}
     */
    public final void allocate(final int mode)
        throws AudioException, EngineException, EngineStateException,
        SecurityException, IllegalArgumentException {
        if (mode == ASYNCHRONOUS_MODE) {
            final Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        allocate();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            };
            final SpeechEventExecutor executor = getSpeechEventExecutor();
            executor.execute(runnable);
        } else if (mode == 0) {
            allocate();
        } else {
            throw new IllegalArgumentException("Unsupported mode: " + mode);
        }
    }

    /**
     * {@inheritDoc}
     */
    public final void deallocate()
        throws AudioException, EngineException, EngineStateException {

        // Validate current state
        if (testEngineState(DEALLOCATED)
                || testEngineState(DEALLOCATING_RESOURCES)) {
            return;
        }

        checkEngineState(ALLOCATING_RESOURCES);

        // Update current state
        long[] states = setEngineState(CLEAR_ALL_STATE,
                                       DEALLOCATING_RESOURCES);
        postStateTransitionEngineEvent(states[0], states[1],
                EngineEvent.ENGINE_DEALLOCATING_RESOURCES);
        baseDeallocate();
    }


    /**
     * {@inheritDoc}
     */
    public final void deallocate(final int mode)
        throws AudioException, EngineException {
        if (mode == ASYNCHRONOUS_MODE) {
            final Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        deallocate();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            };
            final SpeechEventExecutor executor = getSpeechEventExecutor();
            executor.execute(runnable);
        } else if ((mode == 0) || (mode == IMMEDIATE_MODE)) {
            deallocate();
        } else {
            throw new IllegalArgumentException("Unsupported mode: " + mode);
        }
    }


    /**
     * {@inheritDoc}
     */
    public final void pause() {
        //Validate current state
        if (testEngineState(PAUSED)) {
            // Increase internal state counter for nested pauses/resumes
            synchronized (this) {
                pauses++;
            }
            return;
        }

        checkEngineState(DEALLOCATED | DEALLOCATING_RESOURCES);

        while (testEngineState(ALLOCATING_RESOURCES)) {
            try {
                waitEngineState(ALLOCATED);
            } catch (InterruptedException ex) {
                return;
            }
        }

        synchronized (this) {
            // Increase internal state counter for nested pauses/resumes
            pauses++;
        }
        
        // Handle pause
        basePause();

        // Adapt the state
        long[] states = setEngineState(RESUMED, PAUSED);
        postStateTransitionEngineEvent(states[0], states[1],
                EngineEvent.ENGINE_PAUSED);
    }


    /**
     * {@inheritDoc}
     */
    public final boolean resume() throws EngineStateException {
        // Do nothing if we are already resumed
        if (testEngineState(RESUMED)) {
            return true;
        }

        checkEngineState(DEALLOCATED | DEALLOCATING_RESOURCES);

        while (testEngineState(ALLOCATING_RESOURCES)) {
            try {
                waitEngineState(ALLOCATED);
            } catch (InterruptedException ex) {
                return false;
            }
        }

        // Check, if we are ready to resume.
        // This depends on the number of calls to pause()
        boolean resumeNow = false;
        synchronized (this) {
            if (pauses <= 1) {
                resumeNow = true;
            }
            pauses--;
        }
        if (resumeNow) {
            //Handle resume
            if (baseResume()) {
                long[] states = setEngineState(PAUSED, RESUMED);
                postStateTransitionEngineEvent(states[0], states[1],
                        EngineEvent.ENGINE_RESUMED);
                return true;
            } else {
                return false;
            }
        } else {
            // Every pause must be resumed separately. 
            // If the code reaches this point, we have a nested resume hence we
            // do NOT actually resume, but only decreased the pause-counter
            // further above
            return false;
        }
    }


    /**
     * {@inheritDoc}
     */
    public final AudioManager getAudioManager() {
        if (audioManager == null) {
            audioManager = createAudioManager();
        }
        return audioManager;
    }

    /**
     * Creates a new audio manager for this engine.
     * @return new audio manager for this engine.
     */
    protected abstract AudioManager createAudioManager();

    /**
     * Checks if audio management is supported.
     * @return <code>true</code> if audio management is supported.
     */
    protected final boolean isSupportsAudioManagement() {
        final String management =
            System.getProperty("javax.speech.supports.audio.management");
        if (management == null) {
            return false;
        }
        return management.equals("true");
    }

    /**
     * {@inheritDoc}
     */
    public final VocabularyManager getVocabularyManager() {
        if (vocabularyManager == null) {
            vocabularyManager = createVocabularyManager();
        }
        return vocabularyManager;
    }

    /**
     * Creates a new vocabulary manager for this engine.
     * @return new vocabulary manager for this engine.
     */
    protected abstract VocabularyManager createVocabularyManager();

    /**
     * Gets the current operating properties and mode of
     * this <code>Engine</code>.
     *
     * @return the operating mode of this <code>Engine</code>
     *
     */
    public final EngineMode getEngineMode() {
        return engineMode;
    }

    /**
     * Sets the current operating properties and mode of
     * this <code>Engine</code>.
     *
     * @param mode the new operating mode of this <code>Engine</code>
     */
    protected final void setEngineMode(final EngineMode mode) {
        engineMode = mode;
    }

    /**
     * {@inheritDoc}
     */
    public final SpeechEventExecutor getSpeechEventExecutor() {
        if (speechEventExecutor == null) {
            speechEventExecutor = createSpeechEventExecutor();
        }
        return speechEventExecutor;
    }

    /**
     * Creates a new speech event executor.
     * @return the created speech event executor
     */
    protected abstract SpeechEventExecutor createSpeechEventExecutor();

    /**
     * {@inheritDoc}
     */
    public final void setSpeechEventExecutor(
            final SpeechEventExecutor executor) {
        // Terminate a previously running executor.
        if (speechEventExecutor instanceof TerminatableSpeechEventExecutor) {
            final TerminatableSpeechEventExecutor baseExecutor =
                (TerminatableSpeechEventExecutor) this.speechEventExecutor;
            baseExecutor.terminate();
        }
        speechEventExecutor = executor;
    }

    /**
     * Requests notification of <code>EngineEvents</code> from this
     * <code>Engine</code>.
     *
     * @param listener the listener to add.
     */
    protected final void addEngineListener(final EngineListener listener) {
        synchronized (engineListeners) {
            if (!engineListeners.contains(listener)) {
                engineListeners.add(listener);
            }
        }
    }

    /**
     * Removes an <code>EngineListener</code> from the list of
     * <code>EngineListeners</code>.
     *
     * @param listener the listener to remove.
     */
    protected final void removeEngineListener(final EngineListener listener) {
        synchronized (engineListeners) {
            engineListeners.remove(listener);
        }
    }

    /**
     * {@inheritDoc}
     */
    public final void setEngineMask(final int mask) {
        engineMask = mask;
    }

    /**
     * {@inheritDoc}
     */
    public final int getEngineMask() {
        return engineMask;
    }

    /**
     * Posts the given event using the current {@link SpeechEventExecutor}.
     *
     * @param event the engine event to post.
     */
    protected final void postEngineEvent(final EngineEvent event) {
        // Filter all events which are not observable due to the engine mask
        final int id = event.getId();
        if ((engineMask & id) != id) {
            return;
        }

        // Post the event in the configured speech event executor
        final Runnable runnable = new Runnable() {
            public void run() {
                final Collection<EngineListener> listeners;
                synchronized (engineListeners) {
                    listeners =
                      new java.util.ArrayList<EngineListener>(engineListeners);
                }
                fireEvent(listeners, event);
            }
        };
        
        final SpeechEventExecutor executor = getSpeechEventExecutor();
        executor.execute(runnable);
    }

    /**
     * Convenience method that throws an <code>EngineStateException</code>
     * if any of the bits in the passed state are set in the
     * <code>state</code>.
     *
     * @param state the <code>Engine</code> state to check
     * @exception EngineStateException
     *            state is not the expected state
     */
    protected final void checkEngineState(final long state)
        throws EngineStateException {
        long currentState = getEngineState();
        if ((currentState & state) != 0) {
            throw new EngineStateException("Invalid EngineState: expected=("
                     + stateToString(state) + ") current state=("
                     + stateToString(currentState) + ")");
        }
    }

    /**
     * Returns a <code>String</code> of the names of all the
     * <code>Engine</code> states in the given <code>Engine</code>
     * state.
     *
     * @param state the bitmask of states
     *
     * @return a <code>String</code> containing the names of all the
     *   states set in <code>state</code>
     */
    public String stateToString(final long state) {
        StringBuffer buf = new StringBuffer();
        if ((state & Engine.DEALLOCATED) != 0) {
            buf.append("DEALLOCATED");
        }
        if ((state & Engine.ALLOCATING_RESOURCES) != 0) {
            if (buf.length() > 0) {
                buf.append(' ');
            }
            buf.append("ALLOCATING_RESOURCES");
        }
        if ((state & Engine.ALLOCATED) != 0) {
            if (buf.length() > 0) {
                buf.append(' ');
            }
            buf.append("ALLOCATED");
        }
        if ((state & Engine.DEALLOCATING_RESOURCES) != 0) {
            if (buf.length() > 0) {
                buf.append(' ');
            }
            buf.append("DEALLOCATING_RESOURCES");
        }
        if ((state & Engine.PAUSED) != 0) {
            if (buf.length() > 0) {
                buf.append(' ');
            }
            buf.append("PAUSED");
        }
        if ((state & Engine.RESUMED) != 0) {
            if (buf.length() > 0) {
                buf.append(' ');
            }
            buf.append("RESUMED");
        }
        if ((state & Engine.FOCUSED) != 0) {
            if (buf.length() > 0) {
                buf.append(' ');
            }
            buf.append("FOCUSED");
        }
        if ((state & Engine.DEFOCUSED) != 0) {
            if (buf.length() > 0) {
                buf.append(' ');
            }
            buf.append("DEFOCUSED");
        }
        return buf.toString();
    }

    /**
     * Returns the engine name and mode for debug purposes.
     *
     * @return the engine name and mode.
     */
    public String toString() {
        final EngineMode mode = getEngineMode();
        if (mode == null) {
            return super.toString();
        }
        return mode.getEngineName() + ":" + mode.getModeName();
    }

    /**
     * Retrieves the bit mask of all possible states of this engine.
     * @return bit mask
     */
    protected long getEngineStates() {
        return PAUSED | RESUMED | FOCUSED | DEFOCUSED | ALLOCATED
                | ALLOCATING_RESOURCES | DEALLOCATED
                | DEALLOCATING_RESOURCES;
    }

    /**
     * Checks if the given state is a valid state.
     *
     * @param state long
     * @return boolean
     */
    private boolean isValid(final long state) {
        if (state == 0) {
            return false;
        }
        final long states = getEngineStates();
        return (state | states) == states;
    }


    /**
     * @todo Finish conditions
     *
     * @param state long
     * @return boolean
     */
    protected boolean isReachable(final long state) {
        if ((state & ERROR_OCCURRED) == ERROR_OCCURRED) {
            return false;
        }

        if (!testEngineState(ALLOCATED)) {
            if (((state & RESUMED) == RESUMED)
                || ((state & PAUSED) == PAUSED)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Called from the <code>deallocate</code> method.  Override this in
     * subclasses.
     *
     * @throws EngineException if this <code>Engine</code> cannot be
     *   deallocated.
     * @throws EngineStateException if called for an {@link Engine} in the
     *   {@link Engine#DEALLOCATING_RESOURCES} state 
     * @throws AudioException if an audio request fails
     */
    protected abstract void baseDeallocate()
        throws EngineStateException, EngineException, AudioException;

    /**
     * Called from the <code>pause</code> method.  Override this in subclasses.
     * @exception EngineStateException
     *                  when not in the standard conditions for Operation
     */
    protected abstract void basePause() throws EngineStateException;

    /**
     * Called from the <code>resume</code> method.  Override in subclasses.
     * @return <code>true</code> if the {@link Engine} is in or is about to
     *  enter the {@link Engine#RESUMED} state; or <code>false</code> if it
     *  will remain {@link Engine#PAUSED} due to nested calls to pause.
     * 
     * @exception EngineStateException
     *                  when not in the standard conditions for Operation
     */
    protected abstract boolean baseResume() throws EngineStateException;

    /**
     * Notifies all listeners about the given event. This method is
     * being called using the currently configured {@link SpeechEventExecutor}.
     * <p>
     * This is needed since the event listeners for the synthesizer and
     * the recognizer have different notification signatures.
     * </p>
     * @param listeners all listeners to notify
     * @param event the event
     */
    protected abstract void fireEvent(
            final Collection<EngineListener> listeners,
            final EngineEvent event);

    /**
     * Notifies all registered listeners about a state transition.
     * @param oldState the old engine state
     * @param newState the new engine state.
     * @param id the event identifier.
     */
    protected final void postStateTransitionEngineEvent(final long oldState,
            final long newState, final int id) {
        final EngineEvent event = createStateTransitionEngineEvent(oldState,
                newState, id);
        postEngineEvent(event);
    }

    /**
     * Constructs an engine specific state transition event. Since
     * {@link EngineEvent}s are engine specific for
     * {@link javax.speech.synthesis.Synthesizer} and
     * {@link javax.speech.recognition.Recognizer} we need this factory
     * method.
     * @param oldState the old engine state
     * @param newState the new engine state.
     * @param id the event identifier.
     * @return created event
     */
    protected abstract EngineEvent createStateTransitionEngineEvent(
            final long oldState, final long newState, final int id);

    /**
     * Engines must override this method to apply a pending property change
     * request. After a pending request is handled, the
     * engine implementation must take care to call
     * {@link BaseEngineProperties#commitPropertyChange(String, Object, Object)}
     * to notify registered {@link javax.speech.EnginePropertyListener}s
     * that the change took effect.
     * <p>
     * Depending on the property, implementations may throw an
     * {@link IllegalArgumentException} in case the the value is not supported.
     * The requested value is considered to be a hint and may be ignored.
     * </p>
     * @param properties
     *            the engine properties
     * @param propName
     *            the name of the property
     * @param oldValue
     *            the old value
     * @param newValue
     *            the requested new value
     */
    protected abstract void handlePropertyChangeRequest(
            final BaseEngineProperties properties,
            final String propName, final Object oldValue,
            final Object newValue);
}
