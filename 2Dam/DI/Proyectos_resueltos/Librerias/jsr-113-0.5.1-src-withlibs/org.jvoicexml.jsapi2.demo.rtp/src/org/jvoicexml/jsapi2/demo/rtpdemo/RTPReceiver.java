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

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine.Info;

/**
 * A demo to output a received RTP stream to the speaker.
 * @author Dirk Schnelle-Walka
 */
public final class RTPReceiver {
    /**
     * Do not create from outside.
     */
    private RTPReceiver() {
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

        System.setProperty("java.protocol.handler.pkgs",
            "org.jlibrtp.protocols");

        RTPReceiver testrtpurlreceiver = new RTPReceiver();
        testrtpurlreceiver.doIt();
    }

    /**
     * Receive the audio stream.
     */
    private void doIt() {
        try {
            final AudioFormat receiveFormat =
                new AudioFormat(AudioFormat.Encoding.ULAW,
                                        8000,
                                        8,
                                        1,
                                        1,
                                        8000,
                                        false);

            final AudioFormat playFormat =
                new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    8000,
                    16,
                    1,
                    2,
                    8000,
                    false);


            final Info playbackLineInfo =
                new Info(SourceDataLine.class, playFormat,
                        AudioSystem.NOT_SPECIFIED);
            final SourceDataLine sourceLine =
                (SourceDataLine) AudioSystem.getLine(playbackLineInfo);
            System.out.println("Playing to: " + playbackLineInfo);
            sourceLine.open();
            sourceLine.start();


            URL recvURL = new URL(
                    "rtp://localhost:16384/audio?rate=8000&keepAlive=false");
            URLConnection con = recvURL.openConnection();
            con.connect();
            InputStream in = con.getInputStream();

            byte[] buffer = new byte[1024];
            int br;
            OutputStream os = new FileOutputStream("rtp_received.raw");

            AudioInputStream receiveStream =
                new AudioInputStream(in, receiveFormat,
                        AudioSystem.NOT_SPECIFIED);
            AudioInputStream convStream =
                AudioSystem.getAudioInputStream(playFormat, receiveStream);

            while ((br = convStream.read(buffer)) != -1) {
                os.write(buffer, 0, br);
                sourceLine.write(buffer, 0, br);
            }

            os.close();
            in.close();
            convStream.close();
            receiveStream.close();

            System.out.println("Finished Receiver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
