/*
 * File:    $HeadURL: https://svn.sourceforge.net/svnroot/jvoicexml/trunk/src/org/jvoicexml/Application.java$
 * Version: $LastChangedRevision: 68 $
 * Date:    $LastChangedDate $
 * Author:  $LastChangedBy: schnelle $
 *
 * JSAPI - An independent reference implementation of JSR 113.
 *
 * Copyright (C) 2007-2012 JVoiceXML group - http://jvoicexml.sourceforge.net
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

package org.jvoicexml.jsapi2.recognition;

import java.util.List;

import javax.speech.SpeechEventExecutor;
import javax.speech.SpeechLocale;
import javax.speech.recognition.Grammar;
import javax.speech.recognition.GrammarEvent;
import javax.speech.recognition.GrammarListener;
import javax.speech.recognition.GrammarManager;
import javax.speech.recognition.Recognizer;
import javax.speech.recognition.RecognizerMode;
import javax.speech.recognition.ResultEvent;
import javax.speech.recognition.ResultListener;

/**
 * Implementation of {@link javax.speech.recognition.Grammar}.
 *
 * <p>
 * This implementation adds itself as a {@link ResultListener} to the current
 * recognizer and forwards the received events. Currently there is no filtering
 * if the result matches this grammar.
 * </p>
 * @author Renato Cassaca
 * @author Dirk Schnelle-Walka
 * @version $Revision: 1370 $
 */
public class BaseGrammar implements Grammar, ResultListener {
    /** Reference to the recognizer. */
    private transient Recognizer recognizer;

    /** The language. */
    private SpeechLocale locale;

    /** Registered listeners for grammar changes. */
    private transient List<GrammarListener> grammarListeners;

    /** Registered listeners for result events. */
    private transient List<ResultListener> resultListeners;

    /** Unique reference of this grammar. */
    private String reference;

    /** The activation mode of this grammar. */
    private int activationMode;

    /** <code>true</code> if this grammar can be activated. */
    private boolean activatable;

    /**
     * Creates a new BaseGrammar.
     * @param rec the BaseRecognizer for this Grammar.
     * @param grammarRefererence the reference of this Grammar.
     * @exception IllegalArgumentException
     *            if the grammar reference is null
     */
    public BaseGrammar(final Recognizer rec, final String grammarRefererence)
        throws IllegalArgumentException {
        if (grammarRefererence == null) {
            throw new IllegalArgumentException(
                    "grammar reference must not be null");
        }
        grammarListeners = new java.util.ArrayList<GrammarListener>();
        resultListeners = new java.util.ArrayList<ResultListener>();
        recognizer = rec;
        if (rec != null) {
            recognizer.addResultListener(this);
        }
        reference = grammarRefererence;
        activatable = false;
        activationMode = ACTIVATION_FOCUS;
    }

    /**
     * {@inheritDoc}
     */
    public final Recognizer getRecognizer() {
        return recognizer;
    }

    /**
     * {@inheritDoc}
     */
    public final String getReference() {
        return reference;
    }

    /**
     * {@inheritDoc}
     */
    public final void setActivatable(final boolean value) {
        activatable = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isActivatable() {
        return activatable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setActivationMode(final int mode)
        throws IllegalArgumentException {
        if ((mode != ACTIVATION_GLOBAL)
            && (mode != ACTIVATION_MODAL)
            && (mode != ACTIVATION_FOCUS)
            && (mode != ACTIVATION_INDIRECT)) {
            throw new IllegalArgumentException("Invalid ActivationMode: "
                    + mode);
        } else if (mode != activationMode) {
            activationMode = mode;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getActivationMode() {
        return activationMode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isActive() {
        if (!isActivatable()) {
            return false;
        } else if (getActivationMode() == Grammar.ACTIVATION_GLOBAL) {
            return true;
        } else if (recognizer.testEngineState(Recognizer.FOCUSED)) {
            if (getActivationMode() == Grammar.ACTIVATION_MODAL) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GrammarManager getGrammarManager() {
        return recognizer.getGrammarManager();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addGrammarListener(final GrammarListener listener) {
        if (!grammarListeners.contains(listener)) {
            grammarListeners.add(listener);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void removeGrammarListener(final GrammarListener listener) {
        grammarListeners.remove(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addResultListener(final ResultListener listener) {
        if (!resultListeners.contains(listener)) {
            resultListeners.add(listener);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void removeResultListener(final ResultListener listener) {
        resultListeners.remove(listener);
    }

    /**
     * Retrieves the locale of this grammar.
     * @return the locale
     */
    protected final SpeechLocale getSpeechLocale() {
        if (locale == null) {
            final RecognizerMode mode =
                (RecognizerMode) recognizer.getEngineMode();
            final SpeechLocale[] locales = mode.getSpeechLocales();
            if (locales != null) {
                locale = locales[0];
            }
            if (locale == null) {
                locale = SpeechLocale.getDefault();
            }
        }
        return locale;
    }

    /**
     * Sets the speech locale of this grammar.
     * @param speechLocale the locale
     */
    protected final void setSpeechLocale(final SpeechLocale speechLocale) {
        locale = speechLocale;
    }

    /**
     * Utility function to generate grammar event and post it to the event
     * queue. This method uses the {@link  SpeechEventExecutor} to post the
     * event asynchronously.
     * @param event the event to post
     *
     */
    private void postGrammarEvent(final GrammarEvent event) {
        final SpeechEventExecutor executor =
            recognizer.getSpeechEventExecutor();
        final Runnable runnable = new Runnable() {
                public void run() {
                    fireGrammarEvent(event);
                }
            };
        try {
            executor.execute(runnable);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Utility function to send a grammar event to all grammar
     * listeners.
     * <p>
     * This method runs within the configures {@link SpeechEventExecutor}.
     * </p>
     * @param event the event to fire
     */
    private void fireGrammarEvent(final GrammarEvent event) {
        if (resultListeners != null) {
            for (GrammarListener listener : grammarListeners) {
                listener.grammarUpdate(event);
            }
        }
    }

    /**
     * Utility function to send a GRAMMAR_ACTIVATED event to all result
     * listeners.
     */
    public final void postGrammarActivated() {
        final GrammarEvent event =
            new GrammarEvent(this, GrammarEvent.GRAMMAR_ACTIVATED,
                    true, false, null);
        postGrammarEvent(event);
    }

    /**
     * Utility function to send a GRAMMAR_CHANGES_COMMITTED event to all result
     * listeners.
     */
    public final void postGrammarChangesCommitted() {
        final GrammarEvent event =
            new GrammarEvent(this, GrammarEvent.GRAMMAR_CHANGES_COMMITTED,
                    false, true, null);
        postGrammarEvent(event);
    }

    /**
     * Utility function to send a GRAMMAR_CHANGES_REJECTED event to all result
     * listeners.
     */
    public final void postGrammarChangesRejected() {
        final GrammarEvent event =
            new GrammarEvent(this, GrammarEvent.GRAMMAR_CHANGES_REJECTED,
                    false, true, null);
        postGrammarEvent(event);
    }

    /**
     * Utility function to send a GRAMMAR_DEACTIVATED event to all result
     * listeners.
     */
    public final void postGrammarDeactivated() {
        final GrammarEvent event =
            new GrammarEvent(this, GrammarEvent.GRAMMAR_DEACTIVATED,
                    true, false, null);
        postGrammarEvent(event);
    }

    /**
     * {@inheritDoc}
     */
    public final void resultUpdate(final ResultEvent event) {
        final int id = event.getId();
        // TODO correct the event filter.
        if ((id != ResultEvent.RESULT_ACCEPTED)
                && (id != ResultEvent.RESULT_REJECTED)) {
            return;
        }

        // We are running in the speech event executor, so there is no need to
        // create a new one.
        for (ResultListener listener : resultListeners) {
            listener.resultUpdate(event);
        }
    }
}

