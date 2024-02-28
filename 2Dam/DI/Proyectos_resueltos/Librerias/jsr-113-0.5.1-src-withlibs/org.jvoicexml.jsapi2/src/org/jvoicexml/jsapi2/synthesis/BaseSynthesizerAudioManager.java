/*
 * File:    $HeadURL: https://svn.code.sf.net/p/jsapi/code/trunk/org.jvoicexml.jsapi2/src/org/jvoicexml/jsapi2/synthesis/BaseSynthesizerAudioManager.java $
 * Version: $LastChangedRevision: 843 $
 * Date:    $LastChangedDate $
 * Author:  $LastChangedBy: schnelle $
 *
 * JSAPI - An independent reference implementation of JSR 113.
 *
 * Copyright (C) 2007-2013 JVoiceXML group - http://jvoicexml.sourceforge.net
 *
 */
package org.jvoicexml.jsapi2.synthesis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.sound.sampled.AudioFormat;
import javax.speech.AudioException;
import javax.speech.Engine;
import javax.speech.EngineStateException;

import org.jvoicexml.jsapi2.BaseAudioManager;
import org.jvoicexml.jsapi2.protocols.JavaSoundParser;

/**
 * Supports the JSAPI 2.0 {@link javax.speech.AudioManager} interface. Actual JSAPI
 * implementations might want to extend or modify this implementation.
 */
public class BaseSynthesizerAudioManager extends BaseAudioManager {
    /** The output stream from the synthesizer. */
    private OutputStream outputStream;

    /**
     * Constructs a new object.
     * @param engine the associated engine
     * @param format native engine audio format
     */
    public BaseSynthesizerAudioManager(final Engine engine,
            final AudioFormat format) {
        super(engine, format);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleAudioStart() throws AudioException {
        final String locator = getMediaLocator();
        if (locator == null) {
            outputStream = new SpeakerOutputStream(this);
        } else {
            // Parse the target audio format
            // TODO: check if this is really correct. The URL encoding is only
            // used by some protocol handlers
            try {
                final URL url = new URL(locator);
                final AudioFormat format = JavaSoundParser.parse(url);
                setTargetAudioFormat(format);
            } catch (MalformedURLException e) {
                throw new AudioException(e.getMessage());
            } catch (URISyntaxException e) {
                throw new AudioException(e.getMessage());
            }

            // Gets IO from that connection if not already present
            if (outputStream == null) {
                // Open URL described in locator
                final URLConnection urlConnection;
                try {
                    urlConnection = openURLConnection();
                    outputStream = urlConnection.getOutputStream();
                } catch (IOException ex) {
                    throw new AudioException(
                            "Cannot get OutputStream from URL: "
                            + ex.getMessage());
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleAudioStop() throws AudioException {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException ex) {
                throw new AudioException(ex.getMessage());
            } finally {
                outputStream = null;
            }
        }
    }

    /**
     * Retrieves the output stream from the synthesizer.
     * @return the output stream.
     */
    public final OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMediaLocator(final String locator, final OutputStream stream)
            throws AudioException {
        super.setMediaLocator(locator);
        this.outputStream = stream;
    }

    /**
     * {@inheritDoc}
     *
     * Throws an {@link IllegalArgumentException} since output streams are not
     * supported.
     */
    @Override
    public final void setMediaLocator(final String locator,
            final InputStream stream)
            throws AudioException, EngineStateException,
            IllegalArgumentException, SecurityException {
        throw new IllegalArgumentException("input streams are not supported");
    }

    /**
     * {@inheritDoc}
     *
     * Throws an {@link IllegalArgumentException} since output streams are not
     * supported.
     */
    @Override
    public final InputStream getInputStream() {
        throw new IllegalArgumentException("input streams are not supported");
    }
}
