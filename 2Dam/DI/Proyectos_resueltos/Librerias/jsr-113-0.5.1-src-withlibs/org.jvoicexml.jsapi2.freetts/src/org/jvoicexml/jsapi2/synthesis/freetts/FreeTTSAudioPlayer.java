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

package org.jvoicexml.jsapi2.synthesis.freetts;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.jvoicexml.jsapi2.BaseAudioManager;

import com.sun.speech.freetts.audio.AudioPlayer;

/**
 * Audioplayer for the JSAPI 2 base implementation.
 * <p>
 * This audio player mainly stores the synthesized audio in a byte buffer
 * which can be retrieved later on by the audio manager.
 * </p>
 *
 * @author Renato Cassaca
 * @author Dirk Schnelle-Walka
 * @version 1.0
 */
public final class FreeTTSAudioPlayer implements AudioPlayer {
    /** The collected audio data. */
    private ByteArrayOutputStream buffer;

    /** Reference to the audio manager. */
    private BaseAudioManager baseAudioManager;

    /** The audio format to use. */
    private AudioFormat audioFormat;

    /**
     * Constructs a new object.
     * @param manager the audio manager.
     */
    public FreeTTSAudioPlayer(final BaseAudioManager manager) {
        baseAudioManager = manager;
        buffer = new ByteArrayOutputStream();
        audioFormat = baseAudioManager.getEngineAudioFormat();
    }

    /**
     * {@inheritDoc}
     */
    public void begin(final int size) {
    }

    /**
     * {@inheritDoc}
     */
    public void cancel() {
    }

    /**
     * {@inheritDoc}
     */
    public void close() {
    }

    /**
     * {@inheritDoc}
     */
    public boolean drain() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean end() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public AudioFormat getAudioFormat() {
        return audioFormat;
    }

    /**
     * {@inheritDoc}
     */
    public long getTime() {
        return 0L;
    }

    /**
     * {@inheritDoc}
     */
    public float getVolume() {
        return 0.0F;
    }

    /**
     * {@inheritDoc}
     */
    public void pause() {
    }

    /**
     * {@inheritDoc}
     */
    public void reset() {
        synchronized (buffer) {
            buffer.reset();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void resetTime() {
    }

    /**
     * {@inheritDoc}
     */
    public void resume() {
    }

    /**
     * {@inheritDoc}
     */
    public void setAudioFormat(final AudioFormat format) {
        audioFormat = format;
    }

    /**
     * {@inheritDoc}
     */
    public void setVolume(final float volume) {
    }

    /**
     * {@inheritDoc}
     */
    public void showMetrics() {
    }

    /**
     * {@inheritDoc}
     */
    public void startFirstSampleTimer() {
    }

    /**
     * {@inheritDoc}
     */
    public boolean write(final byte[] audioData) {
        try {
            synchronized (buffer) {
                buffer.write(audioData);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean write(final byte[] audioData, final int offset,
            final int size) {
        synchronized (buffer) {
            buffer.write(audioData, offset, size);
        }
        return true;
    }

    /**
     * Retrieves the collected audio data.
     * @return the collected audio data.
     * @exception IOException
     *            error reading the audio data
     */
    public InputStream getAudioBytes() throws IOException {
        byte[] res;
        synchronized (buffer) {
            res = buffer.toByteArray();
            buffer.reset();
        }
        final ByteArrayInputStream in = new ByteArrayInputStream(res);
        return new AudioInputStream(in, audioFormat,
                in.available());
    }
}
