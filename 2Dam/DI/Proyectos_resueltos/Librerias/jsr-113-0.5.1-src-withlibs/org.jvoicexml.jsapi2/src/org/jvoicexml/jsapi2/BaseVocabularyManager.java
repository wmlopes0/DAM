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

import java.security.Permission;

import javax.speech.EngineStateException;
import javax.speech.SpeechLocale;
import javax.speech.SpeechPermission;
import javax.speech.VocabularyManager;
import javax.speech.Word;

/**
 * Base implementation of a {@link VocabularyManager}. This implementation is
 * currently empty.
 * @author Dirk Schnelle-Walka
 *
 */
public class BaseVocabularyManager implements VocabularyManager {
    /**
     * {@inheritDoc}
     */
    @Override
    public void addWord(final Word word) throws EngineStateException,
            SecurityException {
        final SecurityManager security = System.getSecurityManager();
        if (security != null) {
            final Permission permission = new SpeechPermission(
                    "javax.speech.VocabularyManager.update");
            security.checkPermission(permission);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addWords(final Word[] words) throws EngineStateException,
            SecurityException {
        final SecurityManager security = System.getSecurityManager();
        if (security != null) {
            final Permission permission = new SpeechPermission(
                    "javax.speech.VocabularyManager.update");
            security.checkPermission(permission);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getPronounciations(final String text,
            final SpeechLocale locale)
            throws EngineStateException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Word[] getWords(final String text, final SpeechLocale locale)
            throws EngineStateException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeWord(final Word word) throws EngineStateException,
            IllegalArgumentException, SecurityException {
        final SecurityManager security = System.getSecurityManager();
        if (security != null) {
            final Permission permission = new SpeechPermission(
                    "javax.speech.VocabularyManager.update");
            security.checkPermission(permission);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeWords(final Word[] words) throws EngineStateException,
            IllegalArgumentException, SecurityException {
        final SecurityManager security = System.getSecurityManager();
        if (security != null) {
            final Permission permission = new SpeechPermission(
                    "javax.speech.VocabularyManager.update");
            security.checkPermission(permission);
        }
    }
}
