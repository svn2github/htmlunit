/*
 * Copyright (c) 2002-2008 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gargoylesoftware.htmlunit.html;

import java.io.IOException;
import java.util.Map;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.SgmlPage;

/**
 * Wrapper for the HTML element "input".
 *
 * @version $Revision$
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author David K. Taylor
 * @author <a href="mailto:cse@dynabean.de">Christian Sell</a>
 * @author Daniel Gredler
 * @author Ahmed Ashour
 */
public class HtmlPasswordInput extends HtmlInput {

    private static final long serialVersionUID = -1074283471317076942L;

    /**
     * Create an instance.
     * @param namespaceURI the URI that identifies an XML namespace
     * @param qualifiedName the qualified name of the element type to instantiate
     * @param page the page that contains this element
     * @param attributes the initial attributes
     */
    HtmlPasswordInput(final String namespaceURI, final String qualifiedName, final SgmlPage page,
            final Map<String, DomAttr> attributes) {
        super(namespaceURI, qualifiedName, page, attributes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isSubmittableByEnter() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page type(final char c, final boolean shiftKey, final boolean ctrlKey, final boolean altKey)
        throws IOException {
        if (isDisabled()) {
            return getPage();
        }
        return super.type(c, shiftKey, ctrlKey, altKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doType(final char c, final boolean shiftKey, final boolean ctrlKey, final boolean altKey) {
        //TODO: HtmlTextInput, HtmlPasswordArea, and HtmlTextArea should have synchronized logic (helper class?)
        //TODO: Also, what about adding set/getCursor(int index)
        final String value = getValueAttribute();
        if (c == '\b') {
            if (value.length() > 0) {
                setValueAttribute(value.substring(0, value.length() - 1));
            }
        }
        else if ((c == ' ' || !Character.isWhitespace(c))) {
            setValueAttribute(value + c);
        }
    }
}
