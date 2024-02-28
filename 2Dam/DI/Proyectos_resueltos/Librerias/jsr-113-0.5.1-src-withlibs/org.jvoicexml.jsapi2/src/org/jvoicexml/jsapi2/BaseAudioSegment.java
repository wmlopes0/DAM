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
import java.net.URL;
import java.security.Permission;

import javax.speech.AudioSegment;
import javax.speech.SpeechPermission;

/**
 * Basic implementation of an {@link AudioSegment}.
 * <p>
 * The association of audio data to a representation is based on an
 * {@link InputStream} or retrieved from the given media locator.
 * </p>
 * @author Renato Cassaca
 * @version $Revision: 1370 $

 */
public class BaseAudioSegment extends AudioSegment {
    /** The audio data. */
    private final InputStream is;

    /**
     * Constructs a new audio segment without an input stream and without
     * a media locator. This means that the data can not be retrieved from
     * the locator.
     * The locator is assigned a value of <code>http://localhost/dummy</code>
     * since it must not be null.
     * @param markupText the alternate markup text
     * @param in the input stream for the audio data.
     */
    public BaseAudioSegment(final String markupText, final InputStream in) {
        this("http://localhost/dummy", markupText, in);
    }

    /**
     * Constructs a new object without an input stream. This means that
     * there is no associated audio data.
     * @param locator a non-null media locator description.
     * @param markupText the alternate markup text
     */
    public BaseAudioSegment(final String locator, final String markupText) {
        super(locator, markupText);
        is = null;
    }

    /**
     * Constructs a new object with the given input stream.
     * @param locator a non-null media locator description.
     * @param markupText the alternate markup text
     * @param input the input stream for the audio data.
     */
    public BaseAudioSegment(final String locator, final String markupText,
            final InputStream input) {
        super(locator, markupText);
        is = input;
    }

    /**
     * {@inheritDoc}
     * Opens the input stream. If an input stream is given when creating this
     * object, this one is returned, otherwise this implementation tries
     * to open a stream to the given media locator.
     */
    @Override
    public final InputStream openInputStream()
            throws IOException, SecurityException {
        // Firstly check the security settings
        if (!isGettable()) {
            final SecurityManager security = System.getSecurityManager();
            if (security != null) {
                final Permission permission = new SpeechPermission(
                        "javax.speech.AudioSegment.openInputStream");
                security.checkPermission(permission);
            }
        }

        if (is == null) {
            final String locator = getMediaLocator();
            final URL url = new URL(locator);
            return url.openStream();
        } else {
            return is;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isGettable() {
        final SecurityManager security = System.getSecurityManager();
        if (security != null) {
            final Permission permission = new SpeechPermission(
                    "javax.speech.AudioSegment.openInputStream");
            try {
                security.checkPermission(permission);
            } catch (SecurityException e) {
                return false;
            }
        }
        return super.isGettable();
    }
}
