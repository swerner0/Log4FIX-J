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

import org.opentradingsolutions.log4fix.ui.fields.FieldHighlighter;
import junit.framework.TestCase;
import quickfix.SessionID;

/**
 * @author Brian M. Coyner
 */
public class GlazedListsMemoryLogModelTest extends TestCase {

    private SessionID sessionId;
    private GlazedListsMemoryLogModel model;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        sessionId = new SessionID("FIX.4.2", "sender", "target");
        model = new GlazedListsMemoryLogModel(sessionId);
    }

    public void testGlazedListsMemoryLogModelExtendsMemoryLogModel() {
        assertTrue(MemoryLogModel.class.isAssignableFrom(model.getClass()));
    }

    public void testGetSessionId() {
        assertSame(sessionId, model.getSessionId());
    }

    public void testHighlighter() {
        GlazedListsMemoryLogModel model = new GlazedListsMemoryLogModel(sessionId);
        assertSame(model.getFieldHighlighter(), FieldHighlighter.getDefaultInstance());
    }

    public void testShowFilterPanel() {
        GlazedListsMemoryLogModel model = new GlazedListsMemoryLogModel(sessionId);
        assertTrue(model.isShowFilterPanel());
        model.setShowFilterPanel(false);
        assertFalse(model.isShowFilterPanel());
    }
}
