/*
 * The Log4FIX Software License
 * Copyright (c) 2006 - 2011 Brian M. Coyner  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. Neither the name of the product (Log4FIX), nor Brian M. Coyner,
 *    nor the names of its contributors may be used to endorse or promote
 *    products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL BRIAN M. COYNER OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */

package org.opentradingsolutions.log4fix.core;

import quickfix.SessionID;

import java.beans.PropertyChangeListener;
import java.util.List;
import org.opentradingsolutions.log4fix.ui.fields.FieldHighlighter;

/**
 * Implementations must provide thread-safe "add" operations if more than one
 * thread add messages or events. This is true for the "live" update mode because
 * the incoming and outgoing messages are processed by separate threads.
 *
 * @author Brian M. Coyner
 */
public interface MemoryLogModel {

    /**
     * @return the session Id corresponding to the FIX messages. May return <code>null</code>.
     */
    SessionID getSessionId();

    void setSessionId(SessionID sessionId);

    /**
     * @return non-null list of zero or more {@link LogMessage}s.
     */
    List<LogMessage> getMessages();

    /**
     * @return non-null list of zero or more {@link LogEvent}s.
     */
    List<LogEvent> getEvents();

    /**
     * Use this method to add a new log message to the core. Implementations
     * <strong>must</strong> ensure that the list is modified in a thread-safe manner
     * because multiple threads may attempt to add a message at the same time.
     *
     * @param logMessage the new message.
     */
    void addLogMessage(LogMessage logMessage);

    /**
     * Use this method to add a new log event to the core. Implementations
     * <strong>must</strong> ensure that the list is modified in a thread-safe manner
     * because multiple threads may attempt to add an event at the same time.
     *
     * @param logEvent the new event
     */
    void addLogEvent(LogEvent logEvent);

    /**
     * Clears the messages and the events.
     */
    void clear();

    void addPropertyChangeListener(PropertyChangeListener propertyChangeListener);
    
    default FieldHighlighter getFieldHighlighter() {
        return FieldHighlighter.getDefaultInstance();
    }
    
    default boolean isShowFilterPanel() {
        return true;
    }
}
