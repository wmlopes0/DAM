/*
 * File:    $HeadURL: https://svn.sourceforge.net/svnroot/jvoicexml/trunk/src/org/jvoicexml/Application.java$
 * Version: $LastChangedRevision: 296 $
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

package org.jvoicexml.jsapi2.synthesis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.speech.AudioManager;
import javax.speech.AudioSegment;
import javax.speech.Engine;
import javax.speech.EngineStateException;
import javax.speech.synthesis.PhoneInfo;
import javax.speech.synthesis.SpeakableEvent;
import javax.speech.synthesis.SpeakableListener;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerEvent;

import org.jvoicexml.jsapi2.BaseAudioManager;

/**
 * Play back the audio coming from the synthesizer.
 * @author Dirk Schnelle-Walka
 */
class PlayQueue implements Runnable {
    /** Reference to the queue manager. */
    private final QueueManager queueManager;

    /** Buffer size when reading from the audio segment input stream. */
    private static final int BUFFER_LENGTH = 256;

    /** The items to be played back. */
    private List<QueueItem> queue;

    /**
     * Constructs a new object.
     * @param manager the queue manager
     */
    public PlayQueue(final QueueManager manager) {
        queueManager = manager;
        queue = new java.util.ArrayList<QueueItem>();
    }

    /**
     * Retrieves a stream that matches the target audio format.
     * @param manager the audio manager
     * @param stream the current stream
     * @return a converting stream
     * @exception IOException
     *            error converting the stream
     */
    private AudioInputStream getConvertedStream(
            final BaseAudioManager manager,
            final InputStream stream) throws IOException {
        final AudioInputStream in;
        final AudioFormat engineFormat = manager.getEngineAudioFormat();
        if (stream instanceof AudioInputStream) {
            in = (AudioInputStream) stream;
        } else {
            in = new AudioInputStream(stream, engineFormat, stream.available());
        }
        final AudioFormat targetFormat = manager.getTargetAudioFormat();
        return AudioSystem.getAudioInputStream(targetFormat, in);
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        int playIndex = 0;
        int wordIndex = 0;
        int wordStart = 0;
        int phonemeIndex = 0;
        double timeNextPhone = 0;
        final byte[] buffer = new byte[BUFFER_LENGTH];

        while (!queueManager.isDone()) {
            final QueueItem item = getNextQueueItem();
            final Object source = item.getSource();
            final int id = item.getId();
            final SpeakableListener listener = item.getListener();
            postTopOfQueue(item);
            try {
                delayUntilResumed(item);
            } catch (InterruptedException e1) {
                return;
            }

            final BaseSynthesizer synthesizer = queueManager.getSynthesizer();
            final SpeakableEvent startedEvent = new SpeakableEvent(source,
                    SpeakableEvent.SPEAKABLE_STARTED, id);
            synthesizer.postSpeakableEvent(startedEvent, listener);

            playIndex = 0;
            wordIndex = 0;
            wordStart = 0;
            phonemeIndex = 0;
            timeNextPhone = 0;
            int bytesRead = 0;
            final BaseAudioManager manager =
                (BaseAudioManager) synthesizer.getAudioManager();
            final AudioFormat format = manager.getEngineAudioFormat();
            final float sampleRate = format.getSampleRate();
            try {
                final AudioSegment segment = item.getAudioSegment();
                final InputStream stream = segment.openInputStream();
                if (stream == null) {
                    break;
                }
                final InputStream inputStream =
                        getConvertedStream(manager, stream);
                while ((bytesRead = inputStream.read(buffer)) >= 0) {
                    try {
                        delayUntilResumed(item);
                    } catch (InterruptedException e) {
                        return;
                    }

                    synchronized (queueManager.cancelLock) {
                        if (queueManager.cancelFirstItem) {
                            synthesizer.postSpeakableEvent(new SpeakableEvent(
                                    source,
                                    SpeakableEvent.SPEAKABLE_CANCELLED, id),
                                    listener);
                            break;
                        }
                    }

                    while (wordIndex < item.getWords().length
                            && (item.getWordsStartTime()[wordIndex] * sampleRate
                                <= playIndex * bytesRead)) {
                        synthesizer.postSpeakableEvent(new SpeakableEvent(
                                source, SpeakableEvent.WORD_STARTED, id,
                                item.getWords()[wordIndex],
                                    wordStart, wordStart
                                    + item.getWords()[wordIndex].length()),
                                listener);
                        wordStart += item.getWords()[wordIndex].length() + 1;
                        wordIndex++;
                    }

                    while (phonemeIndex < item.getPhonesInfo().length
                            && timeNextPhone * sampleRate < playIndex
                                    * bytesRead) {
                        synthesizer.postSpeakableEvent(new SpeakableEvent(
                                source, SpeakableEvent.PHONEME_STARTED,
                                id, item.getWords()[wordIndex - 1],
                                item.getPhonesInfo(), phonemeIndex),
                                listener);
                        timeNextPhone +=
                            (double) item.getPhonesInfo()[phonemeIndex]
                                .getDuration() / (double) 1000;
                        phonemeIndex++;
                    }

                    playIndex++;

                    final OutputStream out = manager.getOutputStream();
                    out.write(buffer, 0, bytesRead);
                }

                // Flush audio in the stream
                final OutputStream out = manager.getOutputStream();
                if (out != null) {
                    out.flush();
                }
            } catch (IOException ex) {
                return;
            }

            if (!queueManager.cancelFirstItem) {
                synthesizer.postSpeakableEvent(new SpeakableEvent(
                        source, SpeakableEvent.SPEAKABLE_ENDED, id),
                        listener);

                removeQueueItem(item);
            }

            synchronized (queueManager.cancelLock) {
                queueManager.cancelFirstItem = false;
            }
            postEventsAfterPlay();
        }
    }

    /**
     * Removes the given item from the play queue.
     * @param item the item to remove
     */
    private void removeQueueItem(final QueueItem item) {
        synchronized (queue) {
            queue.remove(item);
        }
    }

    /**
     * Posts an event that processing of the queue has started.
     * @param item the top most queue item
     */
    private void postTopOfQueue(final QueueItem item) {
        final SpeakableListener listener = item.getListener();
        if (listener == null) {
            return;
        }
        final Object source = item.getSource();
        final int id = item.getId();
        final BaseSynthesizer synthesizer = queueManager.getSynthesizer();
        synthesizer.postSpeakableEvent(new SpeakableEvent(source,
                SpeakableEvent.TOP_OF_QUEUE, id), listener);
    }

    /**
     * Delays until the synthesizer moved into the
     * {@link Synthesizer.RESUMED} state.
     * <p>
     * If the {@link Synthesizer} is in the {@link Synthesizer.PAUSED}
     * state, first a {@link SpeakableEvent.SPEAKABLE_PAUSED} is issued.
     * Once the {@link Synthesizer} is resumed, a
     * {@link SpeakableEvent.SPEAKABLE_RESUMED} is issued.
     * </p>
     * @param item the next queue item to play
     * @throws InterruptedException
     *         if the waiting was interrupted
     */
    private void delayUntilResumed(final QueueItem item)
            throws InterruptedException {
        final BaseSynthesizer synthesizer = queueManager.getSynthesizer();
        while (synthesizer.testEngineState(Synthesizer.PAUSED)) {
            final SpeakableListener listener = item.getListener();
            final Object source = item.getSource();
            final int id = item.getId();
            final SpeakableEvent pausedEvent = new SpeakableEvent(
                            source, SpeakableEvent.SPEAKABLE_PAUSED,
                            id);
            synthesizer.postSpeakableEvent(pausedEvent, listener);

            synthesizer.waitEngineState(Engine.RESUMED);
            final SpeakableEvent resumedEvent =
                new SpeakableEvent(source,
                        SpeakableEvent.SPEAKABLE_RESUMED, id);
            synthesizer.postSpeakableEvent(resumedEvent, listener);
        }
    }

    /**
     * Posts an update event after a play has been performed. This is
     * either a {@link Synthesizer.QUEUE_EMPTIED} event if the
     * queue is empty or {@link Synthesizer.QUEUE_UPDATED} if there
     * are more speakables to process,
     */
    private void postEventsAfterPlay() {
        final BaseSynthesizer synthesizer = queueManager.getSynthesizer();
        if (isQueueEmpty()) {
            long[] states = synthesizer.setEngineState(
                  Synthesizer.QUEUE_NOT_EMPTY, Synthesizer.QUEUE_EMPTY);
            synthesizer.postSynthesizerEvent(states[0], states[1],
                    SynthesizerEvent.QUEUE_EMPTIED, true);
        } else {
            long[] states = synthesizer.setEngineState(
                    Synthesizer.QUEUE_NOT_EMPTY,
                    Synthesizer.QUEUE_NOT_EMPTY);
            synthesizer.postSynthesizerEvent(states[0], states[1],
                    SynthesizerEvent.QUEUE_UPDATED, true);
        }
    }

    /**
     * Notifies the play queue that the given item has changed.
     * @param item the item that has changed
     */
    public void itemChanged(final QueueItem item) {
        synchronized (queue) {
            queue.notifyAll();
        }
    }

    /**
     * Adds the given item to the play queue.
     * @param item the item to add
     */
    public void addQueueItem(final QueueItem item) {
        synchronized (queue) {
            queue.add(item);
            queue.notifyAll();
        }
    }

    /**
     * Retrieves the queue item with the given id.
     * @param id the id of the queue item to look for
     * @return found queue item, or <code>null</code> if there is no
     *      queue item with the given id
     */
    public QueueItem getQueueItem(final int id) {
        synchronized (queue) {
            for (QueueItem item : queue) {
                if (item.getId() == id) {
                    return item;
                }
            }
        }
        return null;
    }

    /**
     * Return, but do not remove, the first item on the play queue.
     *
     * @return a queue item to play
     */
    protected QueueItem getNextQueueItem() {
        synchronized (queue) {
            while ((queue.isEmpty() || !isSynthesized(0))
                    && !queueManager.isDone()) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
            if (queueManager.isDone()) {
                return null;
            }
            return queue.get(0);
        }
    }

    /**
     * Checks if the queue item at the given index has already been synthesized.
     * @param index the index to look for the queue item
     * @return <code>true</code> if the item at the given index has been
     *           synthesized
     */
    private boolean isSynthesized(final int index) {
        synchronized (queue) {
            final QueueItem item = queue.get(index);
            return item.isSynthesized();
        }
    }

    /**
     * Determines if the input queue is empty.
     *
     * @return <code>true</code> if the queue is empty; otherwise
     * <code>false</code>
     */
    public boolean isQueueEmpty() {
        synchronized (queue) {
            return queue.isEmpty();
        }
    }

    /**
     * Cancels the item at the top of the queue.
     * @return <code>true</code> if an item was canceled
     * @exception EngineStateException
     *            if the engine is in an invalid state
     */
    protected boolean cancelItemAtTopOfQueue() throws EngineStateException {
        synchronized (queue) {
            if (queue.isEmpty()) {
                return false;
            }
            final QueueItem item = queue.get(0);
            if (item.isSynthesized()) {
                final BaseSynthesizer synthesizer =
                        queueManager.getSynthesizer();
                final BaseAudioManager manager =
                        (BaseAudioManager) synthesizer.getAudioManager();
                final OutputStream out = manager.getOutputStream();
                // TODO should cancel the output stream
            } else {
                final BaseSynthesizer synthesizer =
                        queueManager.getSynthesizer();
                synthesizer.handleCancel();
                final Object source = item.getSource();
                final int id = item.getId();
                final SpeakableListener listener = item.getListener();
                synthesizer.postSpeakableEvent(new SpeakableEvent(
                        source, SpeakableEvent.SPEAKABLE_CANCELLED, id),
                        listener);
            }
            queue.remove(0);
            synchronized (this.queueManager.cancelLock) {
                queueManager.cancelFirstItem = true;
            }
            return true;
        }
    }

    /**
     * Cancels the playback of the speakable with the given id.
     * @param id the speakable to cancel
     * @return <code>true</code> if the speakable was canceled
     */
    protected boolean cancelItem(final int id) {
        boolean found = false;

        // search item in playqueue
        synchronized (queue) {
            for (int i = 0; i < queue.size(); ++i) {
                final QueueItem item = queue.get(i);
                final int currentId = item.getId();
                if (currentId == id) {
                    if (i == 0) {
                        found = cancelItemAtTopOfQueue();
                    } else {
                        final BaseSynthesizer synthesizer =
                                queueManager.getSynthesizer();
                        if (!item.isSynthesized()) {
                            synthesizer.handleCancel(i);
                        }
                        synthesizer.postSpeakableEvent(new SpeakableEvent(item
                                .getSource(),
                                SpeakableEvent.SPEAKABLE_CANCELLED, currentId),
                                item.getListener());
                        synthesizer.postSynthesizerEvent(
                                synthesizer.getEngineState(),
                                synthesizer.getEngineState(),
                                SynthesizerEvent.QUEUE_UPDATED, false);
                        queue.remove(i);
                        found = true;
                    }
                }
            }
        }
        return found;
    }

    public void setWords(final int id, final String[] words) {
        synchronized (queue) {
            final QueueItem item = getQueueItem(id);
            if (item == null) {
                return;
            }
            item.setWords(words);
            itemChanged(item);
        }
    }

    public void setWordsStartTimes(final int id,
            final float[] starttimes) {
        synchronized (queue) {
            final QueueItem item = getQueueItem(id);
            if (item == null) {
                return;
            }
            item.setWordsStartTimes(starttimes);
            itemChanged(item);
        }
    }

    public void setPhonesInfo(final int itemId,
            final PhoneInfo[] phonesinfo) {
        synchronized (queue) {
            final QueueItem item = getQueueItem(itemId);
            if (item == null) {
                return;
            }
            item.setPhonesInfo(phonesinfo);
            itemChanged(item);
        }
    }
}
