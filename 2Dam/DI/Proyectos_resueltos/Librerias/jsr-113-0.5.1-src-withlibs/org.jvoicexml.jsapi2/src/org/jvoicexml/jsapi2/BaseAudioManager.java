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

package org.jvoicexml.jsapi2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.Permission;
import java.util.Collection;

import javax.sound.sampled.AudioFormat;
import javax.speech.AudioEvent;
import javax.speech.AudioException;
import javax.speech.AudioListener;
import javax.speech.AudioManager;
import javax.speech.Engine;
import javax.speech.EngineStateException;
import javax.speech.SpeechEventExecutor;
import javax.speech.SpeechPermission;

/**
 * Supports the JSAPI 2.0 {@link AudioManager}
 * interface.  Actual JSAPI implementations might want to extend
 * or modify this implementation. Usually, this will be required for
 * {@link javax.speech.synthesis.Synthesizer}s and
 * {@link javax.speech.recognition.Recognizer}s to retrieve the
 * {@link OutputStream} (via {@link #getOutputStream()} or
 * {@link InputStream} (via {@link #getInputStream()}) respectively.
 */
public abstract class BaseAudioManager implements AudioManager {
    /**
     * List of <code>AudioListeners</code> registered for
     * <code>AudioEvents</code> on this object.
     */
    private final Collection<AudioListener> audioListeners;

    /**  Mask to filter events. */ 
    private int audioMask;

    /** The media locator. */
    private String mediaLocator;
    
    /** The associated engine. */
    private final Engine engine;

    /** <code>true</code> if audio has been started. */
    private boolean audioStarted;

    /**
     * Audio format of the audio natively produced by the engine.
     */
    private AudioFormat engineAudioFormat;

    /** Audio format of that is being received or that is being delivered. */
    private AudioFormat targetAudioFormat;

    /**
     * Constructs a new object.
     * @param eng the associated engine
     * @param format native engine audio format
     */
    public BaseAudioManager(final Engine eng, final AudioFormat format) {
        this(eng);
        engineAudioFormat = format;
        targetAudioFormat = engineAudioFormat;
    }


    /**
     * Creates a new object.
     * @param eng the associated engine
     */
    protected BaseAudioManager(final Engine eng) {
        audioListeners = new java.util.ArrayList<AudioListener>();
        audioMask = AudioEvent.DEFAULT_MASK;
        engine = eng;
    }

    /**
     * Requests notification of {@link AudioEvent}s from the
     * {@link AudioManager}.
     *
     * @param listener the listener to add
     */
    public final void addAudioListener(final AudioListener listener) {
        synchronized (audioListeners) {
            if (!audioListeners.contains(listener)) {
                audioListeners.add(listener);
            }
        }
    }

    /**
     * Removes an <code>AudioListener</code> from the list of
     * <code>AudioListeners</code>.
     *
     * @param listener the listener to remove
     */
    public final void removeAudioListener(final AudioListener listener) {
        synchronized (audioListeners) {
            audioListeners.remove(listener);
        }
    }

    /**
     * {@inheritDoc}
     */
    public final int getAudioMask() {
        return audioMask;
    }

    /**
     * {@inheritDoc}
     */
    public final void setAudioMask(final int mask) {
        audioMask = mask;
    }

    /**
     * {@inheritDoc}
     */
    public final void audioStart() throws SecurityException,
            AudioException, EngineStateException {
        final SecurityManager security = System.getSecurityManager();
        if (security != null) {
            final Permission permission = new SpeechPermission(
                    "javax.speech.AudioManager.control");
            security.checkPermission(permission);
        }

        handleAudioStart();
        audioStarted = true;

        // Notify all listeners.
        final AudioEvent event = new AudioEvent(engine,
                AudioEvent.AUDIO_STARTED);
        postAudioEvent(event);
    }

    /**
     * Handles further processing if the audio output has to be started by
     * a call to {@link #audioStart()}.
     * @throws AudioException
     *         error stopping
     */
    protected abstract void handleAudioStart() throws AudioException;

    /**
     * {@inheritDoc}
     */
    public final void audioStop() throws SecurityException,
            AudioException, EngineStateException {
        final SecurityManager security = System.getSecurityManager();
        if (security != null) {
            final Permission permission = new SpeechPermission(
                    "javax.speech.AudioManager.control");
            security.checkPermission(permission);
        }
        
        if (!(engine.testEngineState(Engine.PAUSED)
                || engine.testEngineState(Engine.DEALLOCATING_RESOURCES))) {
            throw new EngineStateException(
                    "The Engine has not been paused");
        }

        handleAudioStop();
        audioStarted = false;

        // Notify all listeners.
        final AudioEvent event = new AudioEvent(engine,
                AudioEvent.AUDIO_STOPPED);
        postAudioEvent(event);
    }

    /**
     * Handles further processing if the audio output has to be stopped by
     * a call to {@link #audioStop()}.
     * <p>
     * Closes the format converter. May be overridden to handle further cleanup.
     * </p>
     * @throws AudioException
     *         error stopping
     */
    protected void handleAudioStop() throws AudioException {
    }

    /**
     * Checks if the audio has been started.
     * @return <code>true</code> if the audio has been started.
     */
    protected final boolean isAudioStarted() {
        return audioStarted;
    }

    /**
     * {@inheritDoc}
     */
    public final void setMediaLocator(final String locator)
        throws AudioException, EngineStateException, IllegalArgumentException,
            SecurityException {

        final SecurityManager security = System.getSecurityManager();
        if (security != null) {
            final Permission permission = new SpeechPermission(
                    "javax.speech.AudioManager.control");
            security.checkPermission(permission);
        }

        // Ensure that media locator is supported
        if (!isSupportedMediaLocator(locator)) {
            throw new AudioException("Unsupported locator: " + locator);
        }

        // Ensure that the audio has been stopped.
        if (audioStarted) {
            throw new IllegalStateException("Audio has not been stopped!");
        }

        mediaLocator = locator;
        
        final AudioEvent event = new AudioEvent(engine,
                AudioEvent.AUDIO_CHANGED);
        postAudioEvent(event);
    }

    /**
     * {@inheritDoc}
     */
    public final String getMediaLocator() {
        return mediaLocator;
    }

    /**
     * {@inheritDoc}
     * @todo This is just a dummy implementation
     */
    public String[] getSupportedMediaLocators(final String locator)
        throws IllegalArgumentException {
        return new String[] {mediaLocator};
    }

    /**
     * {@inheritDoc}
     */
    public final boolean isSupportedMediaLocator(final String locator)
        throws IllegalArgumentException {
        final String[] supportedMediaLocators = getSupportedMediaLocators(
                locator);
        return supportedMediaLocators != null;
    }

    /**
     * {@inheritDoc}
     *
     * TODO: This implementation checks only for equal media locators.
     */
    public boolean isSameChannel(final AudioManager audioManager) {
        if (audioManager == null) {
            return false;
        }
        final String otherLocator = audioManager.getMediaLocator();
        if (otherLocator == null) {
            return mediaLocator == null;
        }
        if (mediaLocator == null) {
            return false;
        }
        return mediaLocator.equals(otherLocator);
    }

    
    /**
     * Notifies all listeners about the audio event using the configured
     * {@link javax.speech.SpeechEventExecutor}.
     * @param event the event to notify.
     */
    protected final void postAudioEvent(final AudioEvent event) {
        final int eventId = event.getId();
        if ((getAudioMask() & eventId) == eventId) {

            final Runnable runnable = new Runnable() {
                public void run() {
                    synchronized (audioListeners) {
                        for (AudioListener listener : audioListeners) {
                            listener.audioUpdate(event);
                        }
                    }
                }
            };

            try {
                final SpeechEventExecutor executor =
                    engine.getSpeechEventExecutor();
                executor.execute(runnable);
            } catch (RuntimeException ex) {
                //Ignore exception
                ex.printStackTrace();
            }
        }
    }

    /**
     * Retrieves the output stream associated with the given media locator.
     * @return output stream, <code>null</code> if streaming is not supported.
     */
    public abstract OutputStream getOutputStream();

    /**
     * Retrieves the input stream associated with the given media locator.
     * @return input stream, <code>null</code> if streaming is not supported.
     */
    public abstract InputStream getInputStream();

    /**
     * Opens the connection to the configured media locator.
     * @return opened connection
     * @throws IOException
     *         error opening the connection.
     */
    protected final URLConnection openURLConnection() throws IOException {
        final String locator = getMediaLocator();
        if (locator == null) {
            return null;
        }

        final URL url;
        try {
            url = new URL(locator);
        } catch (MalformedURLException ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }

        // Open a connection to URL
        final URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        connection.connect();
        return connection;
    }

    /**
     * Sets the audio format that is being used by this engine.
     * @param format new audio format.
     */
    public final void setEngineAudioFormat(final AudioFormat format) {
        engineAudioFormat = format;
    }

    /**
     * Retrieves the audio format that is used by the associated engine.
     * @return audio format used by this engine.
     */
    public final AudioFormat getEngineAudioFormat() {
        return engineAudioFormat;
    }

    /**
     * Retrieves the target audio format.
     * @return target audio format.
     */
    public final AudioFormat getTargetAudioFormat() {
        return targetAudioFormat;
    }
    
    /**
     * Sets the target audio format used by the stream that is consumed by the
     * associated engine.
     * @param format the target audio format
     */
    protected final void setTargetAudioFormat(final AudioFormat format) {
        targetAudioFormat = format;
    }
}

