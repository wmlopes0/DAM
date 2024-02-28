/*
 * File:    $HeadURL: https://svn.sourceforge.net/svnroot/jvoicexml/trunk/src/org/jvoicexml/Application.java$
 * Version: $LastChangedRevision: 68 $
 * Date:    $LastChangedDate $
 * Author:  $LastChangedBy: schnelle $
 *
 * JSAPI - An independent reference implementation of JSR 113.
 *
 * Copyright (C) 2010-2012 JVoiceXML group - http://jvoicexml.sourceforge.net
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

package org.jvoicexml.jsapi2.sapi.recognition;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.speech.AudioException;
import javax.speech.EngineEvent;
import javax.speech.EngineException;
import javax.speech.EngineStateException;
import javax.speech.recognition.Grammar;
import javax.speech.recognition.GrammarManager;
import javax.speech.recognition.RecognizerEvent;
import javax.speech.recognition.RecognizerProperties;
import javax.speech.recognition.Result;
import javax.speech.recognition.ResultEvent;
import javax.speech.recognition.ResultToken;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.jvoicexml.jsapi2.BaseAudioManager;
import org.jvoicexml.jsapi2.BaseEngineProperties;
import org.jvoicexml.jsapi2.recognition.BaseRecognizer;
import org.jvoicexml.jsapi2.recognition.BaseResultToken;
import org.jvoicexml.jsapi2.recognition.GrammarDefinition;

/**
 * A SAPI recognizer.
 * @author Dirk Schnelle-Walka
 * @author Markus Baumgart
 *
 */
public final class SapiRecognizer extends BaseRecognizer {
    /** Logger for this class. */
    private static final Logger LOGGER =
        Logger.getLogger(SapiRecognizer.class.getName());

    /** SAPI recognizer Handle. **/
    private long recognizerHandle;

    /** Asynchronous recognition. */
    private SapiRecognitionThread recognitionThread;
    
    /** Listener for AudioEvents (e.g. <code>AudioEvent.AUDIO_CHANGED</code>) */
    private SapiRecognizerAudioEventListener listener;

    /**
     * Constructs a new object.
     * @param mode the recognizer mode.
     */
    public SapiRecognizer(final SapiRecognizerMode mode) {
        super(mode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Grammar> getBuiltInGrammars() {
        return sapiGetBuildInGrammars(recognizerHandle);
    }

    private native Collection<Grammar> sapiGetBuildInGrammars(long handle);

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleAllocate() throws EngineStateException, EngineException,
            AudioException, SecurityException {
        // allocate the CPP-Recognizer
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine("Allocationg SAPI-recognizer...");
        }
        recognizerHandle = sapiAllocate();
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine("... allocated SAPI-recognizer");
        }
    }
    
    /**
     * Sets the input stream for the recognizer.
     * @return
     */
    protected boolean setRecognizerInputStream() {
     // Get the source audioStream
        BaseAudioManager audioManager = (BaseAudioManager) getAudioManager();
        //audioManager.audioStart();
        InputStream in = audioManager.getInputStream();
        return setRecognizerInputStream(in);
    }

    /**
     * Sets the input stream for the recognizer.
     * @return
     */
    private boolean setRecognizerInputStream(final InputStream in) {
        /* problem: TypeMismatch JSAPI2-AudioFormat <-> JAVAX-AudioFormat */
        //AudioFormat audioFormat = audioManager.getAudioFormat();
        // => alternatively: hardcoded streamInfo
        final float sampleRate = 16000;
        final int bitsPerSample = 16;
        final int channels = 1;
        final boolean endian = false;
        final boolean signed = true;

        return sapiSetRecognizerInputStream(recognizerHandle,
                    in, 
                    sampleRate, 
                    bitsPerSample, 
                    channels, 
                    endian, 
                    signed, 
                    AudioFormat.Encoding.PCM_SIGNED.toString().toLowerCase()
                );
    }
    
    private native long sapiAllocate();
    
    private native boolean sapiSetRecognizerInputStream(
            final long handle,
            final InputStream in, 
            final float sampleRate, 
            final int bitsPerSec, 
            final int channels, 
            final boolean endian, 
            final boolean signed, 
            final String encoding);

    @Override
    public void handleDeallocate() {
        sapiDeallocate(recognizerHandle);
    }

    private native void sapiDeallocate(long handle);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handlePause() {
        sapiPause(recognizerHandle);
        if (recognitionThread != null) {
            recognitionThread.stopRecognition();
            recognitionThread = null;
        }
    }

    private native void sapiPause(long handle);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handlePause(final int flags) {
        sapiPause(recognizerHandle, flags);
        if (recognitionThread != null) {
            recognitionThread.stopRecognition();
            recognitionThread = null;
        }
    }

    private native void sapiPause(long handle, int flags);

    /**
     * Start recognition.
     * @param handle the recognizer handle
     * @return recognition result
     * @exception EngineException
     *            if there were errors during recognition
     */
    native int sapiRecognize(final long handle, String[] result) throws EngineException;

    public long getRecognizerHandle() {
        return recognizerHandle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean handleResume(final InputStream in)
        throws EngineStateException {
        setRecognizerInputStream(in);

        final GrammarManager manager = getGrammarManager();
        final Grammar[] grammars = manager.listGrammars();
        final String[] grammarSources = new String[grammars.length];
        final String[] grammarReferences = new String[grammars.length];
        int i = 0;

        if (LOGGER.isLoggable(Level.FINE)) {
            for (Grammar grammar : grammars) {
                LOGGER.log(Level.FINE, "Activate Grammar: {0}",
                        grammar.getReference());
            }
        }
        
        for (Grammar grammar : grammars) {
            grammarSources[i] = grammar.toString();
            grammarReferences[i] = grammar.getReference();
            i++;
        }
        
        sapiResume(recognizerHandle, grammarSources, grammarReferences);
        recognitionThread = new SapiRecognitionThread(this);
        recognitionThread.start();

        return true;
    }

    private native boolean sapiResume(long handle, String[] grammars,
            String[] references)
        throws EngineStateException;


    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean setGrammars(
            Collection<GrammarDefinition> grammarDefinition) {
        return false;
    }

    public boolean setGrammar(final String grammarPath, String reference) {
        return sapiSetGrammar(recognizerHandle, grammarPath, reference);
    }

    private native boolean sapiSetGrammar(long handle, String grammarPath, String reference);
    
    public boolean setGrammarContent(final String grammarContent, String reference) {
        return sapiSetGrammarContent(recognizerHandle, grammarContent, reference);
    }

    private native boolean sapiSetGrammarContent(long handle, String grammarPath, String reference);

    private native void start(long handle);

    /**
     * Callback of the SAPI recognizer if the recognition failed.
     */
    public void reportResultRejected() {
        postResultCreated();

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine("No Match Recognized => False Recognition ...");
        }
        postResultRejected();

        return;
    }

    /**
     * Callback of the SAPI recognizer if the recognition succeeded.
     * @param ruleName name of the rule matching the utterance
     * @param utterance the utterance
     */
    public void reportResult(final String ruleName, final String utterance) {
        postResultCreated();
        SapiResult result = new SapiResult();
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "SML Result String : {0}", utterance);
        }
        result.setSml(utterance);

        // parse our tags from SML
        final SmlInterpretationExtractor extractor;
        try {
            extractor = parseSml(utterance);
        } catch (TransformerException ex) {
            LOGGER.log(Level.WARNING, "error parsing SML: {0}",
                    ex.getMessage());
            final EngineException ee = new EngineException(ex.getMessage());
            postEngineException(ee);
            return;
        }

        // Copy basic values
        final String spokenWords = extractor.getUtterance();
        result.setUtterance(spokenWords);
        final float confidence = extractor.getConfidence();

        // Check if the utterance was only noise 
        if (utterance.equals("...")) { 
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.fine("Single Garbage Recognized ...");
            }
            postResultRejected(result);

            return;
         }

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE,
                    "Recognized utterance : ''{0}'' Confidence : ''{1}''",
                    new Object[]{utterance, result.getConfidence()});
        }

        // set recognized tokens
        result.setConfidence(confidence);
        final String[] utteranceTok = spokenWords.split(" ");
        final ResultToken[] token = new ResultToken[utteranceTok.length];
        for (int i = 0; i < token.length; i++) {
            token[i] = new BaseResultToken(result, utteranceTok[i]);
        }
        result.setTokens(token);

        // iterate through tags and set resultTags
        final List<SmlInterpretation> interpretations =
                extractor.getInterpretations();
        final String[] tags;
        int i = 0;
        final String utteranceTag = extractor.getUtteranceTag();
        if (utteranceTag.isEmpty()
                || utteranceTag.equals(spokenWords)) {
            tags = new String[interpretations.size()];
        } else {
            tags = new String[interpretations.size() + 1];
            tags[i] = utteranceTag;
            i++;
        }
        for (SmlInterpretation interpretation : interpretations) {
            final String tag = interpretation.getTag();
            final String value = interpretation.getValue();
            
            // SRGS-tags like <tag>FOO</tag>
            tags[i] = tag; 
            
            // for the time being, a help tag is of the form "*.help = 'help'",
            // e.g. "out.help = 'help'"
            boolean specialTag =  
               (tag.equalsIgnoreCase("help")
                       && value.equalsIgnoreCase("help"))
               || (tag.equalsIgnoreCase("cancel")
                       && value.equalsIgnoreCase("cancel"));
            // SRGS-tags like <tag>FOO="bar"</tag>
            if (!specialTag && (value != null) && !value.isEmpty()) {
                    tags[i] += "=" + value; 
            }
            i++;
        }
        result.setTags(tags);
        
        final ResultEvent tokensUpdated = new ResultEvent(result,
                ResultEvent.RESULT_UPDATED, true, false);
        postResultEvent(tokensUpdated);
    
        // Set the grammar, which led to recognition
        final GrammarManager manager = getGrammarManager();
        Grammar gram = manager.getGrammar("grammar:" + ruleName);
        if (null == gram) {
            gram = manager.getGrammar(ruleName);
        }
        
        result.setGrammar(gram);
        if (null == result.getGrammar()) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.fine("Could not find the RuleGrammar");
            }
            postResultRejected(result);

            return;
        }
        final ResultEvent grammarFinalized =
            new ResultEvent(result, ResultEvent.GRAMMAR_FINALIZED);
        postResultEvent(grammarFinalized);

        // set the confidenceLevel
        //map the actual confidence ([0; 1] (float)) to a new Integer-Value in javax.speech's range [MIN_CONFIDENCE; MAX_CONFIDENCE]
        // e.g. be MAX_CONFIDENCE = 20; MIN_CONFIDENCE = -10;
        // then, a value of 0.4f from the Recognizer (working in [0; 1]) should be mapped to +2 (in [-10; 20])
        // [because +2 is 2/5th of the complete RecognizerProperties' range]
        
        //get the whole range (in the example above => 20 - -10 = 30;
        int range = RecognizerProperties.MAX_CONFIDENCE
                - RecognizerProperties.MIN_CONFIDENCE;
        
        //set the value and shift it (again, with the sample above: set the
        // value to +12 from [0; 30] and shift it to +2 [-10; 20]
        float confTmp = (result.getConfidence() * range)
            + RecognizerProperties.MIN_CONFIDENCE;
        int resultconfidenceLevel = Math.round(confTmp);
        result.setConfidenceLevel(resultconfidenceLevel);

        // if the actual confidenceLevel is below the required one, reject the
        // result
        int minConfidenceLevel = recognizerProperties.getConfidenceThreshold(); 
        if (resultconfidenceLevel < minConfidenceLevel) {
            result.setResultState(Result.REJECTED);
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE,
                        "Result confidence too low, new ResultState: ''{0}''",
                        result.getResultState());
            }
        }
        
        /** post the result */
        if (result.getResultState() == Result.REJECTED) {
            postResultRejected(result);
        } else {
            result.setResultState(Result.ACCEPTED);
            final ResultEvent accepted = new ResultEvent(result,
                        ResultEvent.RESULT_ACCEPTED, false, false);
            postResultEvent(accepted);
        }
    }

    /**
     * Parses the given SML string.
     * @param sml the SML to parse
     * @return the parsed information
     * @throws TransformerException
     *         error parsing
     */
    private SmlInterpretationExtractor parseSml(final String sml)
            throws TransformerException {
        final TransformerFactory factory = TransformerFactory.newInstance();
        final Transformer transformer = factory.newTransformer();
        final Reader reader = new StringReader(sml);
        final Source source = new StreamSource(reader);
        final SmlInterpretationExtractor extractor =
                new SmlInterpretationExtractor();
        final javax.xml.transform.Result result = new SAXResult(extractor);
        transformer.transform(source, result);
        return extractor;
    }

    /**
     * Notifies all listeners that a recognition result has been created.
     */
    private void postResultCreated() {
         final SapiResult result = new SapiResult();
         result.setResultState(Result.UNFINALIZED);
         result.setConfidenceLevel(0);
         final ResultEvent created = 
             new ResultEvent(result, ResultEvent.RESULT_CREATED, 
                     false, false);
         postResultEvent(created);
    }

    /**
     * Notifies all listeners that a recognition result has been created.
     */
    private void postResultRejected() {
         final SapiResult result = new SapiResult();
         postResultRejected(result);
    }

    /**
     * Notifies all listeners that a recognition result has been created.
     * @param result the current result.
     */
    private void postResultRejected(final SapiResult result) {
         result.setResultState(Result.REJECTED);
         final ResultEvent rejected =
             new ResultEvent(result, ResultEvent.RESULT_REJECTED,
                     false, false);
         postResultEvent(rejected);
    }
    
    protected void postEngineException(final EngineException exc) {
        final long oldEngineState = getEngineState();
        setEngineState(~CLEAR_ALL_STATE, ERROR_OCCURRED);
        final long newEngineState = getEngineState();

        final RecognizerEvent event = 
                new RecognizerEvent(this, 
                        EngineEvent.ENGINE_ERROR, 
                        oldEngineState, 
                        newEngineState, 
                        exc, 
                        null, 
                        RecognizerEvent.UNKNOWN_AUDIO_POSITION);
        postEngineEvent(event);
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
    
    native void sapiAbortRecognition(long handle);

    /**
     * {@inheritDoc}
     */
    @Override
    protected AudioFormat getAudioFormat() {
        return sapiGetAudioFormat(recognizerHandle);
    }

    /**
     * Retrieves the default audio format.
     * @param handle recognizer handle.
     * @return native audio format
     */
    private native AudioFormat sapiGetAudioFormat(final long handle);

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
