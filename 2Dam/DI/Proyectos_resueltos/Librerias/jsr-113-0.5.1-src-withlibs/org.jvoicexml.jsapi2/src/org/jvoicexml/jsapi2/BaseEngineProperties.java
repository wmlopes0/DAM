/*
 * File:    $HeadURL: https://svn.sourceforge.net/svnroot/jvoicexml/trunk/src/org/jvoicexml/Application.java$
 * Version: $LastChangedRevision: 844 $
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

import java.util.Collection;

import javax.speech.Engine;
import javax.speech.EngineProperties;
import javax.speech.EnginePropertyEvent;
import javax.speech.EnginePropertyListener;
import javax.speech.SpeechEventExecutor;

/**
 * Supports the JSAPI 2.0 {@link EngineProperties} interface.
 * 
 * <p>
 * Actual JSAPI 2 implementations may want to override this class to apply the
 * settings to the engine.
 * </p>
 *
 * <p>
 * Properties are pending when the corresponding <code>set...</code> method is
 * called. Notifications are delegated to the engine.
 * They may apply the changes at any time and remove the pending status by
 * calling {@link #commitPropertyChange(String, Object, Object)}. This also
 * triggers the posts of the property change request.
 *
 * @author Renato Cassaca
 * @author Dirk Schnelle-Walka
 * @version $Revision: 844 $
 */
public abstract class BaseEngineProperties implements EngineProperties {
    /** Name of the base property in events. */
    public static final String BASE = "base";

    /** Name of the priority property in events. */
    public static final String PRIORITY = "priority";

    /**
     * List of {@link EnginePropertyListener}s registered for
     * {@link EnginePropertyEvent}s of this object.
     */
    private final Collection<EnginePropertyListener> propertyChangeListeners;

    /** The engine for which these properties apply. */
    private final BaseEngine engine;

    /**
     * The base location to resolve relative URLs against for this Engine
     * instance.
     */
    private String base;

    /** The priority for this engine instance. */
    private int priority;

    /**
     * Constructs a new object.
     * 
     * @param eng
     *            the engine for which these properties apply.
     */
    protected BaseEngineProperties(final BaseEngine eng) {
        propertyChangeListeners =
                new java.util.ArrayList<EnginePropertyListener>();
        engine = eng;
        priority = EngineProperties.NORM_TRUSTED_PRIORITY;
        base = "";
    }

    /**
     * Retrieves the engine.
     * 
     * @return the engine
     */
    protected final Engine getEngine() {
        return engine;
    }

    /**
     * Returns all properties to reasonable defaults for the <code>Engine</code>
     * . An {@link EnginePropertyEvent} is issued for each property that
     * changes as the reset takes effect.
     */
    public void reset() {
        setPriority(EngineProperties.NORM_TRUSTED_PRIORITY);
        setBase("");
    }

    /**
     * {@inheritDoc}
     */
    public final int getPriority() {
        return priority;
    }

    /**
     * {@inheritDoc}
     */
    public final void setPriority(final int prio) {
        if (prio == priority) {
            return;
        }
        engine.handlePropertyChangeRequest(this, PRIORITY,
                new Integer(priority),
                new Integer(prio));
    }

    /**
     * {@inheritDoc}
     */
    public final void setBase(final String uri) {
        if (base == null) {
            if (uri == null) {
                return;
            }
        } else {
            if (base.equals(uri)) {
                return;
            }
        }
        engine.handlePropertyChangeRequest(this, BASE, base, uri);
    }

    /**
     * {@inheritDoc}
     */
    public final String getBase() {
        return base;
    }

    /**
     * {@inheritDoc}
     */
    public final void addEnginePropertyListener(
            final EnginePropertyListener listener) {
        if (!propertyChangeListeners.contains(listener)) {
            propertyChangeListeners.add(listener);
        }
    }

    /**
     * {@inheritDoc}
     */
    public final void removeEnginePropertyListener(
            final EnginePropertyListener listener) {
        propertyChangeListeners.remove(listener);
    }

    /**
     * Commit the property changes and sets the value.
     * <p>
     * This method is called after the new values are applied to the engine.
     * If a property is not known by this implementation, it is forwarded
     * to {@link #setProperty(String, Object)}. If successful, all listeners
     * are informed about the change.
     * </p>
     * @param propName name of the property
     * @param oldValue old value
     * @param newValue new value
     * @exception IllegalArgumentException if the property name is not known
     */
    public final void commitPropertyChange(final String propName,
            final Object oldValue, final Object newValue)
                    throws IllegalArgumentException {
        if (propName == null) {
            throw new IllegalArgumentException(
                    "Property name must not be null!");
        }
        if (propName.equals(PRIORITY)) {
            final Integer value = (Integer) newValue;
            priority = value.intValue();
        } else if (propName.equals("base")) {
            base = (String) newValue;
        } else {
            final boolean set = setProperty(propName, newValue);
            if (!set) {
                throw new IllegalArgumentException("Unknown property '"
                        + propName + "'");
            }
        }
        postPropertyChangeEvent(propName, oldValue, newValue);
    }

    /**
     * Must be overwritten to set engine specific properties which are not
     * covered by the standard after the values are applied to the engine.
     * @param propName name of the property
     * @param value value to set
     * @return <code>true</code> if the value has been set.
     */
    protected abstract boolean setProperty(final String propName,
            final Object value);

    /**
     * Forwards the change request to the enine.
     * @param propName
     *            the name of the property
     * @param oldValue
     *            the old value
     * @param newValue
     *            the requested new value
     */
    protected final void handlePropertyChangeRequest(
            final String propName, final Object oldValue,
            final Object newValue) {
        engine.handlePropertyChangeRequest(this, propName, oldValue,
                newValue);
    }
    
    /**
     * Generates a {@link EnginePropertyEvent} for an {@link Object} value
     * and posts it to the event queue using the configured
     * {@link SpeechEventExecutor}.
     * 
     * <p>
     * Registered listeners are notified using the
     * {@link #firePropertyChangeEvent(EnginePropertyEvent)} method.
     * </p>
     * 
     * @param propName
     *            the name of the property
     * @param oldValue
     *            the old value
     * @param newValue
     *            the new value
     * 
     * @see #firePropertyChangeEvent
     * @see #dispatchSpeechEvent
     */
    protected final void postPropertyChangeEvent(final String propName,
            final Object oldValue, final Object newValue) {

        if (propertyChangeListeners.size() < 1) {
            return;
        }

        final EnginePropertyEvent event = new EnginePropertyEvent(this,
                propName, oldValue, newValue);

        final Runnable runnable = new Runnable() {
            public void run() {
                firePropertyChangeEvent(event);
            }
        };

        final SpeechEventExecutor executor = engine.getSpeechEventExecutor();
        executor.execute(runnable);
    }

    /**
     * Sends a {@link EnginePropertyEvent} to all
     * {@link EnginePropertyListener} registered with this object.
     * 
     * <p>
     * This method runs within the configured {@link SpeechEventExecutor}.
     * </p>
     * 
     * @param event
     *            the {@link EnginePropertyEvent} to send
     * 
     * @see #firePropertyChangeEvent
     * @see #dispatchSpeechEvent
     */
    private void firePropertyChangeEvent(final EnginePropertyEvent event) {
        for (EnginePropertyListener listener : propertyChangeListeners) {
            listener.propertyUpdate(event);
        }
    }
}
