/*
 * File:    $HeadURL: https://jsapi.svn.sourceforge.net/svnroot/jsapi/trunk/org.jvoicexml.jsapi2.jse/src/org/jvoicexml/jsapi2/jse/synthesis/freetts/DomainLocale.java $
 * Version: $LastChangedRevision: 292 $
 * Date:    $LastChangedDate $
 * Author:  $LastChangedBy: schnelle $
 *
 * JSAPI - An base implementation for JSR 113.
 *
 * Copyright (C) 2009 JVoiceXML group - http://jvoicexml.sourceforge.net
 *
 */

package org.jvoicexml.jsapi2.synthesis.freetts;

import java.util.Locale;
import java.util.Vector;

import com.sun.speech.freetts.Voice;


/**
 * Used to be able to generate a list of voices based on unique
 * combinations of domain/locale pairs.
 */
class DomainLocale {
    /** The domain. */
    private final String domain;

    /** The locale. */
    private final Locale locale;

    /** Voices for the current domain and locale. */
    private final Vector<Voice> voices;

    /**
     * Constructs a new object.
     *
     * @param domain the domain to use
     * @param locale the locale to use
     */
    public DomainLocale(final String domain, final Locale locale) {
        this.domain = domain;
        this.locale = locale;
        this.voices = new Vector<Voice>();
    }

    /**
     * See if two DomainLocale objects are equal.
     * The voices are NOT compared.
     *
     * @param o, the object to compare to
     *
     * @return true if the domain and locale are both equal, else
     * false
     */
    public boolean equals(final Object o) {
        if (!(o instanceof DomainLocale)) {
            return false;
        }
        return (domain.equals(((DomainLocale) o).getDomain())
                && locale.equals(((DomainLocale) o).getLocale()));
    }

    /**
     * Gets the domain.
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Gets the locale.
     * @return the locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Adds a voice to this instance.
     *
     * @param voice the voice to add
     */
    public void addVoice(final Voice voice) {
        voices.add(voice);
    }

    /**
     * Gets the voices of this instance.
     *
     * @return all of the voices that have been added to this
     * instance.
     */
    public Voice[] getVoices() {
        Voice[] voiceArray =  new Voice[voices.size()];
        return (Voice[]) voices.toArray(voiceArray);
    }
}
