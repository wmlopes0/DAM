/*
 * File:    $HeadURL: https://svn.sourceforge.net/svnroot/jvoicexml/trunk/src/org/jvoicexml/Application.java$
 * Version: $LastChangedRevision: 296 $
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

package org.jvoicexml.jsapi2.demo.helloworld;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.speech.AudioManager;
import javax.speech.EngineManager;
import javax.speech.synthesis.SpeakableEvent;
import javax.speech.synthesis.SpeakableListener;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerEvent;
import javax.speech.synthesis.SynthesizerListener;
import javax.speech.synthesis.SynthesizerMode;

import org.jvoicexml.jsapi2.synthesis.freetts.FreeTTSEngineListFactory;

/**
 * A demo to output a synthesized text to the speaker.
 * @author Dirk Schnelle-Walka
 * @version $Revision: 593 $
 */
public final class HelloWorldDemo implements SpeakableListener, SynthesizerListener {
    /**
     * Do not create from outside.
     */
    private HelloWorldDemo() {
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
            // Create a synthesizer for the default Locale
            Synthesizer synthesizer = (Synthesizer) EngineManager
                    .createEngine(SynthesizerMode.DEFAULT);
            HelloWorldDemo demo = new HelloWorldDemo();
            final AudioManager manager = synthesizer.getAudioManager();
//            manager.setMediaLocator("file:/home/dirk/test.wav");
            synthesizer.addSynthesizerListener(demo);
            // Get it ready to speak
            synthesizer.allocate();
            synthesizer.resume();
            synthesizer.waitEngineState(Synthesizer.RESUMED);

            // Speak the "hello world" string
            System.out.println("Speaking 'Hello, world!'...");
            synthesizer.speak("Hello, world!", demo);
            synthesizer.speakMarkup("<?xml version=\"1.0\"?>"
                    + "<speak>Goodbye!</speak>", demo);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
            System.out.println("done.");
            // Clean up - includes waiting for the queue to empty
            synthesizer.deallocate();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void speakableUpdate(SpeakableEvent e) {
        System.out.println(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void synthesizerUpdate(SynthesizerEvent e) {
        System.out.println(e);
    }
}
