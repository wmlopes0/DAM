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

package org.jvoicexml.jsapi2.synthesis;

import java.io.IOException;
import java.io.OutputStream;

import javax.speech.AudioSegment;
import javax.speech.EngineStateException;
import javax.speech.synthesis.PhoneInfo;
import javax.speech.synthesis.Speakable;
import javax.speech.synthesis.SpeakableListener;

import org.jvoicexml.jsapi2.BaseAudioManager;

/**
 * The {@link QueueManager} basically accepts the speech segments to
 * synthesized, appends them to a corresponding queue and hands them to the
 * synthesizer to convert those pieces into audio chunks. These chunks are added
 * to the play queue to be delivered via the configured media locator.
 *
 * @author Renato Cassaca
 * @author Dirk Schnelle-Walka
 * @version $Revision: 296 $
 */
public class QueueManager {
    /** Reference to the synthesizer. */
    private BaseSynthesizer synthesizer;
    /** Queued play items. */
    private SynthesisQueue synthQueue;
    /** Synthesized play items. */
    private PlayQueue playQueue;
    /** <code>true</code> if the queue manager is terminated. */
    private boolean done;

    boolean cancelFirstItem;
    final Object cancelLock;

    /**
     * Constructs a new object.
     * @param synth the synthesizer whose queue is managed here.
     */
    public QueueManager(final BaseSynthesizer synth) {
        synthesizer = synth;
        cancelFirstItem = false;
        cancelLock = new Object();

        playQueue = new PlayQueue(this);
        synthQueue = new SynthesisQueue(this, playQueue);

        final Thread synthThread = new Thread(synthQueue);
        synthThread.start();
        final Thread playThread = new Thread(playQueue);
        playThread.start();
    }

    /**
     * Retrieves the synthesis queue.
     * @return the synthesis queue
     */
    final SynthesisQueue getSynthesisQueue() {
        return synthQueue;
    }

    /**
     * Retrieves the play queue.
     * @return the play queue
     */
    final PlayQueue getPlayQueue() {
        return playQueue;
    }

    /**
     * Retrieves the synthesizer.
     * @return the synthesizer
     */
    final BaseSynthesizer getSynthesizer() {
        return synthesizer;
    }

    /**
     * Terminates the queue manager.
     */
    public final void terminate() {
        synthQueue.terminate();
        // No need to terminate the play queue since this will terminate once
        // the synthQueue terminates
    }

    /**
     * Notifies the queue manager that the synthesis queue has stopped.
     */
    void done() {
        done = true;
    }

    /**
     * Checks if the queue manager has terminated.
     * @return <code>true</code> if the queue manager has terminated
     */
    final boolean isDone() {
        return done;
    }

    /**
     * Add a speakable item to be spoken to the output queue. Fires the
     * appropriate queue events.
     *
     * @param speakable the speakable item to add
     * @param listener a listener to notify about events of this item
     * @return queue id.
     */
    public final int appendItem(final Speakable speakable,
            final SpeakableListener listener) {
        return synthQueue.appendItem(speakable, listener, null);
    }

    /**
     * Add a speakable item to be spoken to the output queue. Fires the
     * appropriate queue events.
     *
     * @param speakable the speakable item to add
     * @param listener a listener to notify about events of this item
     * @param text the text to be spoken
     * @return queue id.
     */
    public final int appendItem(final Speakable speakable,
            final SpeakableListener listener, final String text) {
        return synthQueue.appendItem(speakable, listener, text);
    }

    /**
     * Add an item to be spoken to the output queue. Fires the appropriate queue
     * events
     *
     * @param item
     *                the item to add to the queue
     */
    public int appendItem(AudioSegment audioSegment, SpeakableListener listener) {
        return synthQueue.appendItem(audioSegment, listener);
    }

    public void setWords(final int itemId, final String[] words) {
        playQueue.setWords(itemId, words);
    }

    public void setWordsStartTimes(final int itemId, final float[] starttimes) {
        playQueue.setWordsStartTimes(itemId, starttimes);
    }

    public void setPhonesInfo(final int itemId, final PhoneInfo[] phonesinfo) {
        playQueue.setPhonesInfo(itemId, phonesinfo);
    }

    /**
     * Determines if the input queue is empty.
     *
     * @return true if the queue is empty; otherwise false
     */
    public boolean isQueueEmpty() {
        return synthQueue.isQueueEmpty() && playQueue.isQueueEmpty();
    }

    /**
     * Cancels the current item.
     * @return <code>true</code> if an item was canceled
     */
    protected boolean cancelItem() throws EngineStateException {
        if (playQueue.isQueueEmpty()) {
            return synthQueue.cancelFirstItem();
        } else {
            final BaseAudioManager manager =
                (BaseAudioManager) synthesizer.getAudioManager();
            final OutputStream out = manager.getOutputStream();
            try {
                out.close();
            } catch (IOException e) {
                throw new EngineStateException(e.getMessage());
            }
            return playQueue.cancelItemAtTopOfQueue();
        }
    }

    /**
     * Cancels all items in the queue.
     */
    public boolean cancelAllItems() {
        synthesizer.handleCancelAll();
        boolean found = false;

        // First remove all pending requests...
        while (!synthQueue.isQueueEmpty()) {
            synthQueue.cancelFirstItem();
            found = true;
        }

        // ...then remove all the stuff being played back
        if (!playQueue.isQueueEmpty()) {
            cancelItem(); // cancel and remove first item
            while (!playQueue.isQueueEmpty()) {
                playQueue.cancelItemAtTopOfQueue();
                found = true;
            }
        }

        return found;
    }

    /**
     * Cancel the given item.
     *
     * @param source
     *                the item to cancel.
     */
    protected void cancelItem(final Object source) {
        /*
         * Speakable item = null; synchronized (queue) { int index =
         * queue.indexOf(source); if (index == 0) { cancelItem(); } else { item =
         * (Speakable) queue.remove(index); if (item != null) { //
         * item.postSpeakableCancelled(); item.cancelled(); queueDrained(); } } }
         */
    }

    /**
     * Cancels the playback of the speakable with the given id. This is 
     * done by trying to remove it from the play queue and from
     * the synthesis queue.
     * @param id the id of the speakable to cancel
     * @return <code>true</code> if the speakable could be canceled.
     */
    protected boolean cancelItem(final int id) {
        final boolean found = playQueue.cancelItem(id);
        return found || synthQueue.cancelItem(id);
    }

    /**
     * Returns, but does not remove, the first item on the queue.
     *
     * @return the first queue item
     */
    protected QueueItem getQueueItem() {
        return synthQueue.getNextQueueItem();
    }

    /**
     * Removes the given item, posting the appropriate events. The item may have
     * already been removed (due to a cancel).
     *
     * @param item
     *                the item to remove
     */
    protected void removeQueueItem(final QueueItem item) {
        synthQueue.removeQueueItem(item);
    }

    /**
     * Should be called if one or more items have been removed from the queue.
     * Generates the appropriate state changes and events.
     */
    void queueDrained() {
        /*
         * if (queue.size() == 0) { long[] states =
         * setEngineState(synthesizer.QUEUE_NOT_EMPTY, synthesizer.QUEUE_EMPTY);
         * postQueueEmptied(states[0], states[1]); } else { long[] states =
         * setEngineState(synthesizer.QUEUE_NOT_EMPTY,
         * synthesizer.QUEUE_NOT_EMPTY); postQueueUpdated(true, states[0],
         * states[1]); }
         */
    }
}
