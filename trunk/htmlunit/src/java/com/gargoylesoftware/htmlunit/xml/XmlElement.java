/*
 * Copyright (c) 2002-2007 Gargoyle Software Inc. All rights reserved.
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
package com.gargoylesoftware.htmlunit.xml;

import java.util.Iterator;
import java.util.Map;

import com.gargoylesoftware.htmlunit.html.DomNamespaceNode;

/**
 * An XML element.
 *
 * @version $Revision$
 * @author Ahmed Ashour
 */
public class XmlElement extends DomNamespaceNode {

    /** Constant meaning that the specified attribute was not defined. */
    public static final String ATTRIBUTE_NOT_DEFINED = new String("");

    /** The map holding the attributes, keyed by name. */
    private Map/* String, XmlAttr*/ attributes_;

    /**
     * Create an instance of a DOM node that can have a namespace.
     *
     * @param namespaceURI the URI that identifies an XML namespace.
     * @param qualifiedName The qualified name of the element type to instantiate.
     * @param xmlPage The page that contains this element.
     * @param attributes The attributes of this element.
     */
    protected XmlElement(final String namespaceURI, final String qualifiedName, final XmlPage xmlPage,
            final Map/* String, XmlAttr*/ attributes) {
        super(namespaceURI, qualifiedName, xmlPage);
        attributes_ = attributes;
        for (final Iterator values = attributes.values().iterator(); values.hasNext();) {
            final XmlAttr attr = (XmlAttr) values.next();
            attr.setParentNode(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    public short getNodeType() {
        return org.w3c.dom.Node.ELEMENT_NODE;
    }

    /**
     * @return The same value as returned by {@link #getTagName()},
     */
    public String getNodeName() {
        return getTagName();
    }

    /**
     * Return the tag name of this element.
     * @return the tag name of this element.
     */
    public String getTagName() {
        if (getNamespaceURI() == null) {
            return getLocalName();
        }
        else {
            return getQualifiedName();
        }
    }

    /**
     * Return the value of the specified attribute or an empty string.  If the
     * result is an empty string then it will be {@link #ATTRIBUTE_NOT_DEFINED}
     *
     * @param attributeName the name of the attribute
     * @return The value of the attribute or {@link #ATTRIBUTE_NOT_DEFINED}
     */
    public final String getAttributeValue(final String attributeName) {
        final XmlAttr attr = (XmlAttr) attributes_.get(attributeName);

        if (attr != null) {
            return attr.getNodeValue();
        }
        else {
            return ATTRIBUTE_NOT_DEFINED;
        }
    }

    /**
     * Returns the map holding the attributes, keyed by name.
     * @return the attributes map.
     */
    public Map getAttributes() {
        return attributes_;
    }
}
