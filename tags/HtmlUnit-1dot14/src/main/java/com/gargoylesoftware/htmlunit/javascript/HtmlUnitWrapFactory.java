/*
 * Copyright (c) 2002-2008 Gargoyle Software Inc. All rights reserved.
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
package com.gargoylesoftware.htmlunit.javascript;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.WrapFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 * Called by Rhino to Wrap Object as {@link Scriptable}.
 *
 * @version $Revision$
 * @author Marc Guillemot
 */
public class HtmlUnitWrapFactory extends WrapFactory {

    /**
     * Constructor
     */
    public HtmlUnitWrapFactory() {
        setJavaPrimitiveWrap(false); // we don't want to wrap String and Co
    }

    /**
     * Wraps Objects used by htmlunit (like {@link NodeList}) or delegates to
     * parent class.
     * {@inheritDoc}
     * @see org.mozilla.javascript.WrapFactory#wrapAsJavaObject(org.mozilla.javascript.Context,
     * org.mozilla.javascript.Scriptable, java.lang.Object, java.lang.Class)
     */
    public Scriptable wrapAsJavaObject(final Context context,
            final Scriptable scope, final Object javaObject,
            final Class staticType) {

        // TODO: should depend from the js configuration file
        final Scriptable resp;
        if (NodeList.class.equals(staticType)
                || NamedNodeMap.class.equals(staticType)) {
            resp = new ScriptableWrapper(scope, javaObject, staticType);
        }
        else {
            resp = super.wrapAsJavaObject(context, scope, javaObject,
                    staticType);
        }
        return resp;
    }
}
