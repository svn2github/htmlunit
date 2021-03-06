/*
 * Copyright (c) 2002-2006 Gargoyle Software Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. The end-user documentation included with the redistribution, if any, must
 *    include the following acknowledgment:
 *
 *       "This product includes software developed by Gargoyle Software Inc.
 *        (http://www.GargoyleSoftware.com/)."
 *
 *    Alternately, this acknowledgment may appear in the software itself, if
 *    and wherever such third-party acknowledgments normally appear.
 * 4. The name "Gargoyle Software" must not be used to endorse or promote
 *    products derived from this software without prior written permission.
 *    For written permission, please contact info@GargoyleSoftware.com.
 * 5. Products derived from this software may not be called "HtmlUnit", nor may
 *    "HtmlUnit" appear in their name, without prior written permission of
 *    Gargoyle Software Inc.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL GARGOYLE
 * SOFTWARE INC. OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.gargoylesoftware.htmlunit.javascript.host;

import org.mozilla.javascript.Context;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.javascript.SimpleScriptable;

/**
 * JavaScript object representing an Event that is passed into Event Handlers
 * when they are invoked. For general information on which properties and functions
 * should be supported, see <a href="www.mozilla.org/docs/dom/domref/dom_event_ref.html">
 * the mozilla docs</a> or <a href="http://www.w3.org/TR/DOM-Level-2-Events/events.html#Events-Event">
 * the W3C DOM Level 2 Event Documentation</a>.
 *
 * @version $Revision$
 * @author <a hrer="mailto:chriseldredge@comcast.net">Chris Eldredge</a>
 * @author Mike Bowler
 * @author Chris Erskine
 * @author Marc Guillemot
 * @author Daniel Gredler
 * @author Brad Murray
 */
public class Event extends SimpleScriptable {

    private static final long serialVersionUID = 4050485607908455730L;
    private Object srcElement_;     // IE-only writeable equivalent of target.
    private Object target_;         // W3C standard read-only equivalent of srcElement.
    private Object currentTarget_;  // Changes during event capturing and bubbling.
    private Object keyCode_;        // Key code for a keypress

    /**
     * Creates a new event instance.
     * @param domNode The DOM node that triggered the event.
     */
    public Event(final DomNode domNode) {
        final Object target = domNode.getScriptObject();
        srcElement_ = target;
        target_ = target;
        currentTarget_ = target;
        keyCode_ = Context.getUndefinedValue();
        setParentScope((SimpleScriptable) target);
        setDomNode(domNode, false);
    }

    /**
     * Creates a new event instance for a keypress event.
     * @param domNode the DOM node that triggered the event.
     * @param keyCode The key code associated with the event.
     */
    public Event(final DomNode domNode, final int keyCode) {
        this(domNode);
        keyCode_ = new Integer (keyCode);
    }

    /**
     * Returns the object that fired the event. This is an IE-only property.
     * @return The object that fired the event.
     */
    public Object jsxGet_srcElement() {
        return srcElement_;
    }

    /**
     * Sets the object that fired the event. This is an IE-only property.
     * @param srcElement The object that fired the event.
     */
    public void jsxSet_srcElement(final Object srcElement) {
        srcElement_ = srcElement;
    }

    /**
     * Returns the event target to which the event was originally dispatched.
     * @return The event target to which the event was originally dispatched.
     */
    public Object jsxGet_target() {
        return target_;
    }

    /**
     * Returns the event target whose event listeners are currently being processed. This
     * is useful during event capturing and event bubbling.
     * @return The current event target.
     */
    public Object jsxGet_currentTarget() {
        return currentTarget_;
    }

    /**
     * Returns the key code associated with the event.
     * @return The key code associated with the event.
     */
    public Object jsxGet_keyCode () {
        return keyCode_;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        final StringBuffer buffer = new StringBuffer("Event: (");
        buffer.append("Current Target: ");
        buffer.append(currentTarget_.toString());
        buffer.append(");");
        return buffer.toString();
    }

}
