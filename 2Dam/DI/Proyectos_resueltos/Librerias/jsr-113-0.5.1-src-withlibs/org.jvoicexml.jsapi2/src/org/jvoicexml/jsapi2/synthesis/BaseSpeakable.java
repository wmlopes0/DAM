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

import javax.speech.synthesis.Speakable;

/**
 * A basic implementation of a {@link Speakable}.
 *
 * @author Renato Cassaca
 * author Dirk Schnelle-Walka
 */
public class BaseSpeakable implements Speakable {
    /** The markup. */
    private String markup;

    /**
     * Constructs a new object.
     * @param text the markup.
     */
    public BaseSpeakable(final String text) {
        markup = text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getMarkupText() {
        return markup;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder();
        str.append(getClass().getName());
        str.append("[");
        if (markup != null) {
            str.append(markup);
        }
        str.append("]");

        return str.toString();
    }
}
