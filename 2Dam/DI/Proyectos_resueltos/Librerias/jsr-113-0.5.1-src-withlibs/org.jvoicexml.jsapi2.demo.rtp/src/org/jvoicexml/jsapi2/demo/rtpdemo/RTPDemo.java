/*
 * File:    $HeadURL$
 * Version: $LastChangedRevision$
 * Date:    $LastChangedDate $
 * Author:  $LastChangedBy$
 *
 * JSAPI - An base implementation for JSR 113.
 *
 * Copyright (C) 2009 JVoiceXML group - http://jvoicexml.sourceforge.net
 *
 */

package org.jvoicexml.jsapi2.demo.rtpdemo;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.speech.AudioManager;
import javax.speech.EngineManager;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerMode;

import org.jvoicexml.jsapi2.synthesis.freetts.FreeTTSEngineListFactory;

/**
 * A demo to output a synthesized text to the speaker.
 * @author Dirk Schnelle-Walka
 */
public final class RTPDemo {
    /**
     * Do not create from outside.
     */
    private RTPDemo() {
    }

    /**
     * Starts this demo.
     * @param args command line arguments.
     */
    public static void main(final String[] args) {
        // Enable logging at all levels.
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        Logger.getLogger("").addHandler(handler);
        Logger.getLogger("").setLevel(Level.ALL);

        try {
            EngineManager
                    .registerEngineListFactory(FreeTTSEngineListFactory.class
                            .getName());
            System.setProperty("java.protocol.handler.pkgs",
                    "org.jlibrtp.protocols");
            // Create a synthesizer for the default Locale
            Synthesizer synth = (Synthesizer) EngineManager
                    .createEngine(SynthesizerMode.DEFAULT);
            AudioManager manager = synth.getAudioManager();
            manager.setMediaLocator("rtp://test:4343/audio?"
                    + "participant=localhost:16384&rate=8000&encoding=ulaw"
                    + "&bits=8");

            // Get it ready to speak
            synth.allocate();

            // Speak the "hello world" string
            System.out.println("Speaking 'Hello, world!'...");
            synth.speak("Hello, world!", null);
            synth.waitEngineState(Synthesizer.QUEUE_EMPTY);
//            System.out.println("Speaking 'Goodybe!'...");
//            synth.speakMarkup("<?xml version=\"1.0\"?>"
//                    + "<speak>Goodbye!</speak>", null);
//            synth.waitEngineState(Synthesizer.QUEUE_EMPTY);
            System.out.println("done.");

            // Clean up - includes waiting for the queue to empty
            synth.deallocate();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
