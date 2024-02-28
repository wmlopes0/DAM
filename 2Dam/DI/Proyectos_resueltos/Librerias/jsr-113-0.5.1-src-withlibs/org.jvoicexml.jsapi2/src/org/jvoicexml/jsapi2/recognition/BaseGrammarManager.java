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

package org.jvoicexml.jsapi2.recognition;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.speech.EngineException;
import javax.speech.EngineMode;
import javax.speech.EngineStateException;
import javax.speech.SpeechLocale;
import javax.speech.recognition.Grammar;
import javax.speech.recognition.GrammarEvent;
import javax.speech.recognition.GrammarException;
import javax.speech.recognition.GrammarListener;
import javax.speech.recognition.GrammarManager;
import javax.speech.recognition.Recognizer;
import javax.speech.recognition.Rule;
import javax.speech.recognition.RuleGrammar;

/**
 * A base implementation of a {@link GrammarManager}.
 *
 * @author Renato Cassaca
 * @author Dirk Schnelle-Walka
 */
public class BaseGrammarManager implements GrammarManager {
    /** Logger for this class. */
    private static final Logger LOGGER =
            Logger.getLogger(BaseGrammarManager.class.getName());
    
    /** The listeners of grammar events. */
    protected final List<GrammarListener> grammarListeners;

    /** Storage of created grammars. */
    protected HashMap<String, Grammar> grammars;

    /** Mask that filter events. */
    private int grammarMask;

    /** Recognizer which the GrammarManager belongs. */
    private final BaseRecognizer recognizer;

    /**
     * Constructor that associates a Recognizer.
     * with a GrammarManager
     *
     * @param reco BaseRecognizer
     */
    public BaseGrammarManager(final BaseRecognizer reco) {
        grammarListeners = new ArrayList<GrammarListener>();
        grammars = new HashMap<String, Grammar>();
        grammarMask = GrammarEvent.DEFAULT_MASK;
        recognizer = reco;
    }

    /**
     * Constructor that allows to use a GrammarManager in
     * standalone mode.
     */
    public BaseGrammarManager() {
        this(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addGrammarListener(final GrammarListener listener) {
        grammarListeners.add(listener);
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
    public RuleGrammar createRuleGrammar(final String grammarReference,
            final String rootName)
            throws IllegalArgumentException, EngineStateException,
                EngineException {
        final SpeechLocale locale = SpeechLocale.getDefault();
        return createRuleGrammar(grammarReference, rootName, locale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RuleGrammar createRuleGrammar(final String grammarReference,
            final String rootName, final SpeechLocale locale)
                    throws IllegalArgumentException, EngineStateException,
                        EngineException {

        // Validate current state
        ensureValidEngineState();

        if (grammars.containsKey(grammarReference)) {
            throw new IllegalArgumentException("Duplicate grammar name: " +
                                               grammarReference);
        }

        // Create grammar
        final BaseRuleGrammar brg =
            new BaseRuleGrammar(recognizer, grammarReference);
        brg.setAttribute("xml:lang", locale.toString());
        brg.setRoot("test");

        //Register it
        grammars.put(grammarReference, brg);

        return brg;
    }

    /**
     * Deletes a Grammar.
     *
     * @param grammar Grammar
     * @throws IllegalArgumentException
     * @throws EngineStateException
     */
    public void deleteGrammar(final Grammar grammar)
            throws IllegalArgumentException,  EngineStateException {

        // Validate current state
        ensureValidEngineState();

        if (!grammars.containsKey(grammar.getReference())) {
            throw new IllegalArgumentException("The grammar is unknown");
        }

        //Remove the grammar
        Grammar key = grammars.remove(grammar.getReference());
        
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "Removed grammar :{0}", key.getReference());
            Iterator<String> keys = grammars.keySet().iterator();

            while (keys.hasNext()) {
                LOGGER.log(Level.FINE, "Grammar :{0}", keys.next());
            }
        }
        
        
    }

    /**
     * Lists the Grammars known to this Recognizer.
     *
     * @return Grammar[]
     * @throws EngineStateException
     */
    public Grammar[] listGrammars() throws EngineStateException {

        // Validate current state
        ensureValidEngineState();

        // List of all grammars
        Collection<Grammar> allGrammars =
                new java.util.ArrayList<Grammar> ();

        // Get engine built-in grammars
        if (recognizer != null) {
            Collection<Grammar> builtInGrammars =
                    recognizer.getBuiltInGrammars();
            if (builtInGrammars != null) {
                allGrammars.addAll(builtInGrammars);
            }
        }

        // Add local managed grammars
        if (grammars != null) {
            allGrammars.addAll(grammars.values());
        }
        
        // Return an array with all know grammars
        return (Grammar[]) allGrammars.toArray(new Grammar[allGrammars.size()]);
    }

    /**
     * Gets the RuleGrammar with the specified grammarReference.
     *
     * @param grammarReference String
     * @return RuleGrammar
     * @throws EngineStateException
     */
    public Grammar getGrammar(final String grammarReference)
            throws EngineStateException {

        // Validate current state
        ensureValidEngineState();

        return grammars.get(grammarReference);
    }

    /**
     * Loads a RuleGrammar from a URI or named resource.
     *
     * @param grammarReference String
     * @return RuleGrammar
     * @throws GrammarException
     * @throws IllegalArgumentException
     * @throws IOException
     * @throws EngineStateException
     * @throws EngineException
     */
    public Grammar loadGrammar(String grammarReference, String mediaType) throws
            GrammarException, IllegalArgumentException, IOException,
            EngineStateException, EngineException {
        
        if(LOGGER.isLoggable(Level.FINE)){
            LOGGER.log(Level.FINE, "Load Grammar : {0} with media Type:{1}",
                    new Object[]{grammarReference, mediaType});
        }
               
        return loadGrammar(grammarReference, mediaType, true, false, null);
    }

    /**
     * Loads a RuleGrammar from a URI or named resource
     * and optionally loads any referenced Grammars.
     *
     * @param grammarReference String
     * @param loadReferences boolean
     * @param reloadGrammars boolean
     * @param loadedGrammars Vector
     * @return RuleGrammar
     * @throws GrammarException
     * @throws IllegalArgumentException
     * @throws IOException
     * @throws EngineStateException
     * @throws EngineException
     */
    @Override
    public Grammar loadGrammar(String grammarReference,
                               String mediaType,
                               boolean loadReferences,
                               boolean reloadGrammars,
                               @SuppressWarnings("rawtypes") Vector loadedGrammars) throws
            GrammarException, IllegalArgumentException,
            IOException, EngineStateException, EngineException {
        
        if(LOGGER.isLoggable(Level.FINE)){
            LOGGER.log(Level.FINE, "Load Grammar : {0} with media Type:{1}",
                    new Object[]{grammarReference, mediaType});
            LOGGER.log(Level.FINE, "loadReferences : {0} reloadGrammars:{1}",
                    new Object[]{loadReferences, reloadGrammars});
            LOGGER.log(Level.FINE, "there are {0} loaded grammars:",
                    loadedGrammars.size());
        }
        
        //Validate current state
        ensureValidEngineState();

        // Make sure that recognizer supports markup
        // TODO: Is this really correct? Maybe we should change that
        if (recognizer != null) {
            final EngineMode mode = recognizer.getEngineMode();
            if (!mode.getSupportsMarkup()) {
                throw new EngineException("Engine doesn't support markup");
            }
        }

        //Proccess grammar
        URL url = new URL(grammarReference);
        InputStream grammarStream = url.openStream();
        
        SrgsRuleGrammarParser srgsParser = new SrgsRuleGrammarParser();
        Rule[] rules = srgsParser.load(grammarStream);
        if (rules != null) {
            //Initialize rule grammar
            BaseRuleGrammar brg = new BaseRuleGrammar(recognizer,
                    grammarReference);
            brg.addRules(rules);
            final Map<String, String> attributes = srgsParser.getAttributes();
            brg.setAttributes(attributes);

            //Register grammar
            grammars.put(grammarReference, brg);

            return brg;
        }

        return null;
    }

    /**
     * Creates a RuleGrammar from grammar text provided by a Reader.
     *
     * @param grammarReference a unique reference to the {@link Grammar} to load
     * @param mediaType the type of {@link Grammar} to load
     * @param reader the {@link Reader} from which the {@link Grammar} text is loaded
     * @return a {@link Grammar} object
     * @throws GrammarException
     *         if the <code>mediaType</code> is not supported or if any loaded
     *         grammar text contains an error
     * @throws IllegalArgumentException
     *         if <code>grammarReference</code> is invalid
     * @throws IOException
     *         if an I/O error occurs 
     * @throws EngineStateException
     *         when not in the standard Conditions for Operation
     * @throws EngineException
     *         if {@link RuleGrammar}s are not supported
     *
     */
    @SuppressWarnings("unchecked")
    public Grammar loadGrammar(final String grammarReference,
                final  String mediaType, final Reader reader)
                throws  GrammarException, IllegalArgumentException, IOException,
                    EngineStateException, EngineException {
        
        if(LOGGER.isLoggable(Level.FINE)){
            LOGGER.log(Level.FINE,
                    "Load Grammar : {0} with media Type:{1} and Reader :{2}",
                    new Object[]{grammarReference, mediaType, reader});
        }

        // Validate current state
        ensureValidEngineState();

        // Make sure that recognizer supports markup
        if (recognizer != null) {
            final EngineMode mode = recognizer.getEngineMode();
            Boolean supportsMarkup = mode.getSupportsMarkup();
            if (Boolean.FALSE.equals(supportsMarkup)) {
                throw new EngineException("Engine doesn't support markup");
            }
        }

        // Process grammar
        final SrgsRuleGrammarParser srgsParser = new SrgsRuleGrammarParser(); 
        final Rule[] rules = srgsParser.load(reader);
        if (rules == null) {
            throw new IOException("Unable to load grammar '" + grammarReference
                    + "'");
        } 
        
        if(LOGGER.isLoggable(Level.FINE)){
            LOGGER.fine("SrgsRuleGrammarParser parsed rules:" );
            for(Rule rule: rules){               
                LOGGER.fine(rule.getRuleName());
            }           
        }
        
        // Initialize rule grammar
        final BaseRuleGrammar brg =
            new BaseRuleGrammar(recognizer, grammarReference);
        
        if(LOGGER.isLoggable(Level.FINE)){
            LOGGER.log(Level.FINE, "new BaseRuleGrammar:{0}",
                    brg.getReference());
        }       
       
        brg.addRules(rules);
        
        @SuppressWarnings("rawtypes")       
        final Map attributes = srgsParser.getAttributes();
        
        if(LOGGER.isLoggable(Level.FINE)){
            LOGGER.log(Level.FINE,
                    "recieved from srgsParser.getAttributes(), root:{0}",
                    attributes.get("root"));
        }   
        
        brg.setAttributes(attributes);

        // Register grammar
        grammars.put(grammarReference, brg);

        return brg;
    }

    /**
     * Creates a RuleGrammar from grammar text provided as a String.
     *
     * @param grammarReference String
     * @param grammarText String
     * @return RuleGrammar
     * @throws GrammarException
     * @throws IllegalArgumentException
     * @throws IOException
     * @throws EngineStateException
     * @throws EngineException
     */
    public Grammar loadGrammar(String grammarReference,
                               String mediaType,
                               String grammarText) throws
            GrammarException, IllegalArgumentException, IOException,
            EngineStateException, EngineException {
        return loadGrammar(grammarReference, mediaType,
                           new StringReader(grammarText));
    }

    /**
     *
     * @param grammarReference String
     * @param mediaType String
     * @param byteStream InputStream
     * @param encoding String
     * @return Grammar
     * @throws GrammarException
     * @throws IllegalArgumentException
     * @throws IOException
     * @throws EngineStateException
     * @throws EngineException
     */
    public Grammar loadGrammar(String grammarReference,
                               String mediaType,
                               InputStream byteStream,
                               String encoding) throws GrammarException,
            IllegalArgumentException,
            IOException,
            EngineStateException,
            EngineException {
        if (byteStream == null) {
            throw new IOException("Unable to read from a null stream!");
        }
        final InputStreamReader reader =
            new InputStreamReader(byteStream, encoding);
        return loadGrammar(grammarReference, mediaType, reader);
    }


    public void setGrammarMask(int mask) {
        grammarMask = mask;
    }

    public int getGrammarMask() {
        return grammarMask;
    }

    /**
     * Checks if the recognizer is in a valid state to perform grammar
     * operations. If the recognizer is currently allocating resources, this
     * methods waits until the the resoucres are allocated.
     * @throws EngineStateException
     *         invalid engine state
     */
    private void ensureValidEngineState() throws EngineStateException {
        if (recognizer == null) {
            return;
        }
        //Validate current state
        if (recognizer.testEngineState(
                Recognizer.DEALLOCATED | Recognizer.DEALLOCATING_RESOURCES)) {
            throw new EngineStateException(
              "Cannot execute GrammarManager operation: invalid engine state: "
              + recognizer.stateToString(recognizer.getEngineState()));
        }

        //Wait until end of allocating (if it's currently allocating)
        while (recognizer.testEngineState(Recognizer.ALLOCATING_RESOURCES)) {
            try {
                recognizer.waitEngineState(Recognizer.ALLOCATED);
            } catch (InterruptedException ex) {
                throw new EngineStateException(ex.getMessage());
            }
        }
    }
}
