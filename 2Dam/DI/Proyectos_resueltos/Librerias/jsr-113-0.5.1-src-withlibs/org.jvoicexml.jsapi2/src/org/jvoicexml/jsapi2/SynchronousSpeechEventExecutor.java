/*
 * File:    $HeadURL: https://svn.code.sf.net/p/jsapi/code/trunk/org.jvoicexml.jsapi2/src/org/jvoicexml/jsapi2/ThreadSpeechEventExecutor.java $
 * Version: $LastChangedRevision: 799 $
 * Date:    $LastChangedDate $
 * Author:  $LastChangedBy: schnelle $
 *
 * JSAPI - An independent reference implementation of JSR 113.
 *
 * Copyright (C) 2007 JVoiceXML group - http://jvoicexml.sourceforge.net
 *
 */

package org.jvoicexml.jsapi2;

import javax.speech.SpeechEventExecutor;

/**
 * A {@link SpeechEventExecutor} that runs synchronously.
 * @author Dirk Schnelle-Walka
 *
 */
public final class SynchronousSpeechEventExecutor
    implements SpeechEventExecutor {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Runnable command) throws IllegalStateException,
            NullPointerException {
        command.run();
    }

}
