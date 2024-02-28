package org.jvoicexml.jsapi2.recognition;

import java.util.Collection;

import javax.speech.EngineStateException;
import javax.speech.recognition.SpeakerManager;
import javax.speech.recognition.SpeakerManagerUI;
import javax.speech.recognition.SpeakerProfile;

/**
 * Basic implementation of a speaker manager.
 * @author Renato Cassaca
 * @author Dirk Schnelle-Walka
 */
public class BaseSpeakerManager implements SpeakerManager {

    private Collection<SpeakerProfile> speakerProfiles;

    private SpeakerProfile currentSpeaker;

    public BaseSpeakerManager() {
        speakerProfiles = new java.util.ArrayList<SpeakerProfile>();
        currentSpeaker = null;
    }

    /**
     * createSpeaker
     *
     * @param speaker SpeakerProfile
     */
    public void createSpeaker(final SpeakerProfile speaker) {
        speakerProfiles.add(speaker);
    }

    /**
     * deleteSpeaker
     *
     * @param speaker SpeakerProfile
     */
    public void deleteSpeaker(SpeakerProfile speaker) {
        speakerProfiles.remove(speaker);
    }

    /**
     * getCurrentSpeaker
     *
     * @return SpeakerProfile
     */
    public SpeakerProfile getCurrentSpeaker() {
        return currentSpeaker;
    }

    /**
     * getSpeakerManagerUI
     *
     * @return SpeakerManagerUI
     */
    public SpeakerManagerUI getSpeakerManagerUI() {
        return null;
    }

    /**
     * listKnownSpeakers
     *
     * @return SpeakerProfile[]
     */
    public SpeakerProfile[] listKnownSpeakers() {
        final SpeakerProfile[] profiles =
                new SpeakerProfile[speakerProfiles.size()];
        speakerProfiles.toArray(profiles);
        return profiles;
    }

    /**
     * renameSpeaker
     *
     * @param oldSpeaker SpeakerProfile
     * @param newSpeaker SpeakerProfile
     */
    public void renameSpeaker(SpeakerProfile oldSpeaker,
                              SpeakerProfile newSpeaker) {
    }

    /**
     * restoreCurrentSpeaker
     *
     * @throws EngineStateException
     */
    public void restoreCurrentSpeaker() throws EngineStateException {
    }

    /**
     * saveCurrentSpeaker
     *
     * @throws EngineStateException
     */
    public void saveCurrentSpeaker() throws EngineStateException {
    }

    /**
     * setCurrentSpeaker
     *
     * @param speaker SpeakerProfile
     */
    public void setCurrentSpeaker(SpeakerProfile speaker) {
        if (speakerProfiles.contains(speaker) == false) {
            createSpeaker(speaker);
        }
        currentSpeaker = speaker;
    }
}
