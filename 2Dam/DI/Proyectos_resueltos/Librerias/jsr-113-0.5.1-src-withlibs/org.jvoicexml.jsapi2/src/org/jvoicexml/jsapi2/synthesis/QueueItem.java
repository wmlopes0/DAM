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

package org.jvoicexml.jsapi2.synthesis;

import javax.speech.synthesis.Speakable;
import javax.speech.synthesis.SpeakableListener;
import javax.speech.AudioSegment;
import javax.speech.synthesis.PhoneInfo;

/**
 * An item of the {@link QueueManager}. The data is initialized with the
 * data from the speak requests and are updated as the synthesis process
 * is progressing.
 * @author Renato Cassaca
 * @author Dirk Schnelle-Walka
 * @version $Revision: 1370 $
 */
public class QueueItem {
    /** The text (or markup) to be synthesized. */
    private Object source;

    /** A unique id for this queued item. */
    private final int id;

    /** The queued speakable. */
    private final Speakable speakable;

    /** The associated speakable listener. */
    private final SpeakableListener listener;

    /** The associated audio segment. */
    private AudioSegment segment;

    /** the words in the text or markup to be synthesized. */
    private String[] words;
    
    private float[] wordsStartTimes;
    private PhoneInfo[] phonesInfo;

    /**
     *  <code>true</code> if this queue item has already been passed to the
     *  synthesizer.
     */
    private boolean synthesized;

    /**
     * Constructs a new object.
     * @param itemId the unique id of the speakable
     * @param queuedSpeakable the speakable
     * @param itemListener a listener to notify about events of this item
     */
    public QueueItem(final int itemId, final Speakable queuedSpeakable,
            final SpeakableListener itemListener) {
        this.id = itemId;
        this.speakable = queuedSpeakable;
        this.listener = itemListener;
        this.segment = null;
        this.words = new String[0];
        this.wordsStartTimes = new float[0];
        this.phonesInfo = new PhoneInfo[0];
        this.source = queuedSpeakable;
    }

    /**
     * Constructs a new object.
     * @param itemId the unique id of the speakable
     * @param queuedSpeakable the speakable
     * @param itemListener a listener to notify about events of this item
     * @param text the text to be spoken, maybe <code>null</code> if the
     *             speakable contains markup text
     */
    public QueueItem(final int itemId, final Speakable queuedSpeakable,
            final SpeakableListener itemListener,
            final String text) {
        this(itemId, queuedSpeakable, itemListener);
        source = text;
    }

    /**
     * Constructs a new object.
     * @param itemId the unique id of the speakable
     * @param audioSegment the audio segment to be synthesized
     * @param listener a listener to notify about events of this item
     */
    public QueueItem(final int itemId, AudioSegment audioSegment,
            SpeakableListener listener) {
        this.id = itemId;
        this.speakable = null;
        this.listener = listener;
        this.segment = audioSegment;
        this.words = new String[0];
        this.wordsStartTimes = new float[0];
        this.phonesInfo = new PhoneInfo[0];
        this.source = audioSegment.getMarkupText();
    }

    /**
     * Checks, if this queue item has already been passed to the synthesizer.
     * @return <code>true</code> if this queue item is already synthesizer.
     */
    public boolean isSynthesized() {
        return synthesized;
    }

    /**
     * Modifies the synthesized state of this queue item.
     * @param synthesized <code>true</code> if this queue item has been
     *          synthesized
     */
    public void setSynthesized(boolean synthesized) {
        this.synthesized = synthesized;
    }

    /**
     * Retrieves the speakable.
     * @return the speakable.
     */
    public Speakable getSpeakable() {
        return speakable;
    }

    /**
     * Retrieves the speakable listener for this queue item.
     * @return the speakable listener
     */
    public SpeakableListener getListener() {
        return listener;
    }

    /**
     * Retrieves the id of this item.
     * @return id of this queue item.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the text or markup to be synthesized.
     * @return the text or markup to be synthesized
     */
    public Object getSource() {
        return source;
    }

    /**
     * Retrieves the associated audio segment.
     * @return the audio segment.
     */
    public AudioSegment getAudioSegment() {
        return segment;
    }

    /**
     * Sets the audio segment.
     * @param audiosegment new value for the audio segment.
     */
    public void setAudioSegment(final AudioSegment audiosegment) {
        segment = audiosegment;
    }

    /**
     * Retrieves the words in the text or markup to be synthesized.
     * @return the words in the text or markup to be synthesized.
     */
    public String[] getWords() {
        return words;
    }

    /**
     * Sets the words in the text or markup to be synthesized.
     * @param w words in the text or markup to be synthesized
     */
    public void setWords(final String[] w) {
        words = w;
    }

    public float[] getWordsStartTime() {
        return wordsStartTimes;
    }

    public void setWordsStartTimes(float[] starttimes) {
        wordsStartTimes = starttimes;
    }

    public PhoneInfo[] getPhonesInfo() {
        return phonesInfo;
    }

    public void setPhonesInfo(PhoneInfo[] info) {
        phonesInfo = info;
    }
}
