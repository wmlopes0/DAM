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

package org.jvoicexml.jsapi2.recognition.sphinx4;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.speech.AudioException;
import javax.speech.EngineException;
import javax.speech.EngineStateException;
import javax.speech.recognition.RecognizerEvent;
import javax.speech.recognition.ResultEvent;
import javax.speech.recognition.RuleGrammar;

import org.jvoicexml.jsapi2.BaseAudioManager;
import org.jvoicexml.jsapi2.BaseEngineProperties;
import org.jvoicexml.jsapi2.recognition.BaseRecognizer;
import org.jvoicexml.jsapi2.recognition.GrammarDefinition;

import edu.cmu.sphinx.decoder.search.Token;
import edu.cmu.sphinx.frontend.DataProcessor;
import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.linguist.language.grammar.Grammar;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.recognizer.Recognizer.State;
import edu.cmu.sphinx.recognizer.StateListener;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;
import edu.cmu.sphinx.util.props.PropertySheet;

/**
 * JSAPI wrapper for sphinx4.
 * 
 * <p>
 * Unfortunately sphinx4 provides no full support for JSAPI, so we try to build
 * our own wrapper. This is going to be a bit troublesome. Hope we can make it
 * ;-)
 * </p>
 * 
 * @author Dirk Schnelle
 * @author Stefan Radomski
 * @version $Revision: 611 $
 */
final class Sphinx4Recognizer extends BaseRecognizer
        implements StateListener {
    /** Logger for this class. */
    private static final Logger LOGGER = Logger
            .getLogger(Sphinx4Recognizer.class.getName());

    /**
     * Msecs to sleep before the status of the recognizer thread is checked
     * again.
     */
    private static final long SLEEP_MSEC = 50;

    /** The encapsulated recognizer. */
    private Recognizer recognizer;

    /** The input device. */
    private DataProcessor dataProcessor;

    /** The grammar manager. */
    private Grammar grammar;

    /** The result listener. */
    private final Sphinx4ResultListener resultListener;

    /**
     * The decoding thread. It points either to the single decoding thread or is
     * <code>null</code> if no recognition thread is started.
     */
    private RecognitionThread recognitionThread;

    /**
     * Construct a new object.
     */
    public Sphinx4Recognizer(SphinxRecognizerMode recognizerMode) {
        super(recognizerMode);

        String configFile = System.getProperty(
                "org.jvoicexml.jsapi2.recognition.sphinx4.configPath",
                "/sphinx4.config.xml");

        URL url = Sphinx4Recognizer.class.getResource(configFile);
        if (url == null) {
            try {
                File tmp = new File(configFile);
                if (tmp.exists()) {
                    url = tmp.toURI().toURL();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                url = null;
            }
        }
        
        // There is no config, use default config
        if (url == null) {
            LOGGER.info("Sphinx4Recognizer using default configuration.");
            url = Sphinx4Recognizer.class.getResource("default.config.xml");
        }

        try {
            final ConfigurationManager configuration = new ConfigurationManager(
                    url);

            recognizer = (Recognizer) configuration.lookup("recognizer");
            dataProcessor = (DataProcessor) configuration
                    .lookup("sphinxInputDataProcessor");
            grammar = (Grammar) configuration.lookup("srgsGrammar");

            if (!(dataProcessor instanceof SphinxInputDataProcessor)) {
                throw new EngineException("Unsupported input type");
            }

        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "error creating engine properties {0}",
                    ex.getMessage());
        }

        // hard-coded audio format
        final AudioFormat format = getAudioFormat();
        ((BaseAudioManager) getAudioManager())
                .setEngineAudioFormat(format);
        resultListener = new Sphinx4ResultListener(this);
    }

    /**
     * Called from the <code>allocate</code> method.
     * 
     * @throws EngineException
     *             if problems are encountered
     */
    public void handleAllocate() throws AudioException, EngineException,
            EngineStateException, SecurityException {

        if (recognizer == null) {
            throw new EngineException(
                    "cannot allocate: no recognizer configured!");
        }

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine("allocating recognizer...");
        }

        // allocate recognizer and wait for State.READY
        recognizer.allocate();
        waitForRecognizerState(State.READY);

        // Register state and result listener
        recognizer.addResultListener(resultListener);
        recognizer.addStateListener(this);

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine("...allocated");
            LOGGER.log(Level.FINE, "state: {0}", recognizer.getState());
        }

        setEngineState(CLEAR_ALL_STATE, ALLOCATED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean handleResume(final InputStream in) {
        if (recognizer == null) {
            LOGGER.warning("no recognizer: cannot resume!");
            return false;
        }

        if (recognitionThread != null) {
            LOGGER.warning("recognition thread already started.");
            return false;
        }

        if (recognizer.getState() != State.READY) {
            LOGGER.log(Level.WARNING,
                    "Cannot resume, recognizer not ready, but in state: {0}",
                    recognizer.getState());
            return false;
        }

        // start data source for sphinx if it is not running
        if (dataProcessor instanceof SphinxInputDataProcessor) {
            final SphinxInputDataProcessor sidp =
                (SphinxInputDataProcessor) dataProcessor;
            sidp.setInputStream(in);
            sidp.isRunning(true);
        }

        // start the recognizer thread and wait for the recognizer to recognize
        recognitionThread = new RecognitionThread(this);
        recognitionThread.start();
        waitForRecognizerState(State.RECOGNIZING);

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine("recognition started");
        }

        return true;
    }

    /**
     * Called from the <code>pause</code> method.
     */
    public void handlePause() {
        if (recognitionThread == null) {
            throw new EngineStateException("Cannot pause, no decoder started");
        }

        // prevent further calls to recognize()
        recognitionThread.stopRecognition();

        // stop the sphinx4 frontend
        if (dataProcessor instanceof Microphone) {
            final Microphone microphone = (Microphone) dataProcessor;
            microphone.stopRecording();
        }
        if (dataProcessor instanceof SphinxInputDataProcessor) {
            final SphinxInputDataProcessor sidp =
                    (SphinxInputDataProcessor) dataProcessor;
            sidp.isRunning(false);
        }

        // wait for the recognizer to transit from RECOGNIZING to READY
        waitForRecognizerState(State.READY);

        // get rid of the recognizer thread
        stopRecognitionThread();
    }

    /**
     * @todo Correctly implement this
     */
    public void handlePause(int flags) {
        handlePause();
    }

    /**
     * Called from the <code>deallocate</code> method.
     * 
     * According to JSAPI2 specs, pause is transitioned before dealloc.
     * 
     * @throws EngineException
     *             if this <code>Engine</code> cannot be deallocated.
     * @todo Implement this com.sun.speech.engine.BaseEngine method
     */
    public void handleDeallocate() {

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine("deallocating recognizer...");
        }

        // pause is not called before dealloc obviously
        if (recognizer.getState() != State.READY)
            handlePause();

        // Deallocate the recognizer and wait until it stops recognizing.
        recognizer.deallocate();
        waitForRecognizerState(State.DEALLOCATED);
        recognizer.resetMonitors();

        // recognizer.removeResultListener(resultListener);

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine("...deallocated");
        }

    }

    /**
     * Selector for the data processor.
     * 
     * @return The used data processor.
     */
    DataProcessor getDataProcessor() {
        return dataProcessor;
    }

    /**
     * Selector for the wrapped sphinx4 recognizer.
     * 
     * @return Recognizer
     */
    Recognizer getRecognizer() {
        return recognizer;
    }

    /**
     * Stop the recognition thread and wait until it is terminated.
     */
    private void stopRecognitionThread() {
        if (recognitionThread == null) {
            LOGGER.fine("recognition thread already stopped");
            return;
        }

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine("stopping recognition thread...");
        }
        recognitionThread.stopRecognition();

        final long maxSleepTime = 5000;
        long sleepTime = 0;

        while (recognitionThread.isAlive() && (sleepTime < maxSleepTime)) {
            try {
                Thread.sleep(SLEEP_MSEC);
                sleepTime += SLEEP_MSEC;
            } catch (InterruptedException ie) {
                if (LOGGER.isLoggable(Level.FINE)) {
                    LOGGER.fine("recognition thread interrupted");
                }
            }
        }

        recognitionThread = null;

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine("recognition thread stopped");
        }
    }

    /**
     * Get the current rule grammar or the one that produced the list of tokens
     * in case of the SRGS container.
     * 
     * @param token
     * 
     * @return Active grammar.
     */
    RuleGrammar getRuleGrammar(final Token token) {
        if (grammar instanceof SRGSGrammar) {
            return ((SRGSGrammar) grammar).getRuleGrammar();
        }
        if (grammar instanceof SRGSGrammarContainer) {
            return ((SRGSGrammarContainer) grammar).getRuleGrammar(token);
        }
        return null;
    }

    /**
     * @todo: in case of grammarDefinition.size > 1, make <one-of> of all the
     *        grammars
     * @param newGrammars
     *            String[]
     * @return boolean
     */
    @Override
    protected boolean setGrammars(final Collection<GrammarDefinition> grammarDefinition) {
        if (grammar instanceof SRGSGrammar) {
            // old behavior with only a single active grammar
            if (grammarDefinition.size() == 1) {
                try {
                    final org.jvoicexml.jsapi2.recognition.GrammarDefinition definition = grammarDefinition.iterator().next();
                    ((SRGSGrammar) grammar)
                            .loadSRGS(definition.getGrammar());
                } catch (IOException ex) {
                    return false;
                }
                return true;
            } else {
                return false;
            }
        } else if (grammar instanceof SRGSGrammarContainer) {
            // the big one-of dispatcher
            try {
                ((SRGSGrammarContainer) grammar)
                        .loadGrammars(grammarDefinition);
            } catch (IOException ex) {
                System.err.println(ex);
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<javax.speech.recognition.Grammar> getBuiltInGrammars() {
        return null;
    }

    public void postStartOfSpeechEvent() {
        postEngineEvent(new RecognizerEvent(this,
                RecognizerEvent.SPEECH_STARTED, 1, 1, null, null, 0));
    }

    public void postEndOfSpeechEvent() {
        // SearchGraph sg = linguist.getSearchGraph();
        // SearchGraphDumper.dumpDot("sg.dot", "foo", sg);

        postEngineEvent(new RecognizerEvent(this,
                RecognizerEvent.SPEECH_STOPPED, 1, 1, null, null, 0));
    }

    public void postProcessingEvent() {
        long states[] = setEngineState(LISTENING, PROCESSING);
        postEngineEvent(states[0], states[1],
                RecognizerEvent.RECOGNIZER_PROCESSING, (long) 0);
    }

    public void postListeningEvent() {
        long states[] = setEngineState(PROCESSING, LISTENING);
        postEngineEvent(states[0], states[1],
                RecognizerEvent.RECOGNIZER_LISTENING, (long) 0);

    }

    /**
     * {@inheritDoc}
     */
    public void postResultEvent(final ResultEvent resultEvent) {
        super.postResultEvent(resultEvent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleRequestFocus() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleReleaseFocus() {
    }

    /**
     * This method gets called by the Sphinx4 Recognizer, when its status
     * changes. We just notify all threads waiting in waitForRecognizerState on
     * this object.
     * 
     * @param status
     */
    @Override
    public synchronized void statusChanged(State status) {
        notifyAll();
    }

    /**
     * Wait for the recognizer to enter the given state.
     * 
     * @param status
     *            The state of the recognizer to wait for.
     */
    private synchronized void waitForRecognizerState(final State status) {
        while (recognizer.getState() != status) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LOGGER.log(Level.INFO, "Sphinx4Recognizer in state: {0}", status);
    }

    /**
     * {@inheritDoc}
     * Not used but for compatibility.
     */
    @Override
    public void newProperties(final PropertySheet ps) throws PropertyException {
    }

    @Override
    protected AudioFormat getAudioFormat() {
       return new AudioFormat(16000f, 16, 1, true, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handlePropertyChangeRequest(
            final BaseEngineProperties properties,
            final String propName, final Object oldValue,
            final Object newValue) {
        LOGGER.warning("changing property '" + propName
                + "' to '" + newValue + "' ignored");
    }
}
