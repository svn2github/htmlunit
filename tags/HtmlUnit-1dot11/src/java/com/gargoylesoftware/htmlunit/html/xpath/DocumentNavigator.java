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
package com.gargoylesoftware.htmlunit.html.xpath;

import java.util.Iterator;
import java.util.Map;
import java.util.Collections;

import org.jaxen.DefaultNavigator;
import org.jaxen.XPath;
import org.jaxen.JaxenException;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.DomComment;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.Util;

/**
 * Jaxen Navigator implementation for navigating around the HtmlUnit DOM object model
 * in the context of XPath evaluation. The implementation is closely modeled after the
 * W3C DOM Navigator that comes with Jaxen.
 * <p>
 * <em>This class is not intended for direct usage, but is used by the Jaxen engine
 * during evaluation.
 * </em>
 *
 * @version  $Revision$
 * @author <a href="mailto:cse@dynabean.de">Christian Sell</a>
 * @author Mike Bowler
 * @see HtmlUnitXPath
 */
public class DocumentNavigator extends DefaultNavigator {
    private static final long serialVersionUID = -5323715453687261210L;
    
    /**
     * Constant: singleton navigator.
     */
    public static final DocumentNavigator instance = new DocumentNavigator();

    /**
     * Get an iterator over all of this node's children.
     *
     * @param contextNode The context node for the child axis.
     * @return A possibly-empty iterator (not null).
     */
    public Iterator getChildAxisIterator (final Object contextNode) {
        return ((DomNode)contextNode).getChildIterator();
    }

    /**
     * Get a (single-member) iterator over this node's parent.
     *
     * @param contextNode the context node for the parent axis.
     * @return A possibly-empty iterator (not null).
     */
    public Iterator getParentAxisIterator (final Object contextNode) {
        return new Iterator() {
            private DomNode parent_ = ((DomNode)contextNode).getParentNode();

            public boolean hasNext() {
                return parent_ != null;
            }
            public Object next() {
                final DomNode next = parent_;
                parent_ = null;
                return next;
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * Get an iterator over all following siblings.
     *
     * @param contextNode the context node for the sibling iterator.
     * @return A possibly-empty iterator (not null).
     */
    public Iterator getFollowingSiblingAxisIterator (final Object contextNode) {
        return Util.getFollowingSiblingAxisIterator((DomNode) contextNode);
    }

    /**
     * Get an iterator over all preceding siblings.
     *
     * @param contextNode The context node for the preceding sibling axis.
     * @return A possibly-empty iterator (not null).
     */
    public Iterator getPrecedingSiblingAxisIterator (final Object contextNode) {
        return Util.getPrecedingSiblingAxisIterator((DomNode)contextNode);
    }

    /**
     * Get an iterator over all following nodes, depth-first.
     *
     * @param contextNode The context node for the following axis.
     * @return A possibly-empty iterator (not null).
     */
    public Iterator getFollowingAxisIterator (final Object contextNode) {
        return Util.getFollowingAxisIterator((DomNode)contextNode);
    }

    /**
     * Get an iterator over all preceding nodes, depth-first.
     *
     * @param contextNode The context node for the preceding axis.
     * @return A possibly-empty iterator (not null).
     */
    public Iterator getPrecedingAxisIterator (final Object contextNode) {
        return Util.getPrecedingAxisIterator((DomNode)contextNode);
    }

    /**
     * Get an iterator over all attributes.
     *
     * @param contextNode The context node for the attribute axis.
     * @return A possibly-empty iterator (not null).
     */
    public Iterator getAttributeAxisIterator (final Object contextNode) {
        if(contextNode instanceof HtmlElement) {
            return ((HtmlElement)contextNode).getAttributeEntriesIterator();
        }
        else {
            return Collections.EMPTY_LIST.iterator();
        }
    }

    /**
     * @param xpath an xpath expression
     * @return a parsed form of the given xpath string, which will be suitable
     *  for queries on DOM documents.
     * @throws JaxenException if the expression could not be parsed
     */
    public XPath parseXPath (final String xpath) throws JaxenException {
        return new HtmlUnitXPath(xpath);
    }

    /**
     * Get the top-level document node.
     *
     * @param contextNode Any node in the document.
     * @return The root node.
     */
    public Object getDocumentNode (final Object contextNode) {
        return ((DomNode)contextNode).getPage();
    }

    /**
     * Get the Namespace URI of an element.
     *
     * @param object The target node.
     * @return A string (possibly empty) if the node is an element,
     * and null otherwise.
     */
    public String getElementNamespaceUri (final Object object) {

        //return object instanceof HtmlElement ? "" : null;
        if(object instanceof HtmlElement) {
            return "";
        }
        else {
            return null;
        }
    }

    /**
     * Get the local name of an element.
     *
     * @param object The target node.
     * @return A string representing the unqualified local name
     * if the node is an element, or null otherwise.
     */
    public String getElementName (final Object object) {
        return ((DomNode)object).getNodeName();
    }

    /**
     * Get the qualified name of an element.
     *
     * @param object The target node.
     * @return A string representing the qualified (i.e. possibly
     * prefixed) name if the node is an element, or null otherwise.
     */
    public String getElementQName (final Object object) {
        return ((DomNode)object).getNodeName();
    }

    /**
     * @param object The target node.
     * @return the Namespace URI of an attribute.
     */
    public String getAttributeNamespaceUri (final Object object) {
        return "";
    }

    /**
     * Get the local name of an attribute.
     *
     * @param object The target node.
     * @return A string representing the unqualified local name
     * if the node is an attribute, or null otherwise.
     */
    public String getAttributeName (final Object object) {
        return (String)((Map.Entry)object).getKey();
    }

    /**
     * Get the qualified name of an attribute.
     *
     * @param object The target node.
     * @return A string representing the qualified (i.e. possibly
     * prefixed) name if the node is an attribute, or null otherwise.
     */
    public String getAttributeQName (final Object object) {
        return (String)((Map.Entry)object).getKey();
    }

    /**
     * Test if a node is a top-level document.
     *
     * @param object The target node.
     * @return true if the node is the document root, false otherwise.
     */
    public boolean isDocument (final Object object) {
        return (object instanceof HtmlPage);
    }

    /**
     * Test if a node is a Namespace.
     *
     * @param object The target node.
     * @return true if the node is a Namespace, false otherwise.
     */
    public boolean isNamespace (final Object object) {
        return false;
    }

    /**
     * Test if a node is an element.
     *
     * @param object The target node.
     * @return true if the node is an element, false otherwise.
     */
    public boolean isElement (final Object object) {
        return (object instanceof HtmlElement);
    }

    /**
     * Test if a node is an attribute.
     *
     * @param object The target node.
     * @return true if the node is an attribute, false otherwise.
     */
    public boolean isAttribute (final Object object) {
        return (object instanceof Map.Entry);
    }

    /**
     * Test if a node is a comment.
     *
     * @param object The target node.
     * @return true if the node is a comment, false otherwise.
     */
    public boolean isComment (final Object object) {
        return (object instanceof DomComment);
    }

    /**
     * Test if a node is plain text.
     *
     * @param object The target node.
     * @return true if the node is a text node, false otherwise.
     */
    public boolean isText (final Object object) {
        return (object instanceof DomText);
    }

    /**
     * Test if a node is a processing instruction.
     *
     * @param object The target node.
     * @return true if the node is a processing instruction, false otherwise.
     */
    public boolean isProcessingInstruction (final Object object) {
        return false;
    }

    /**
     * Get the string value of an element node.
     *
     * @param object The target node.
     * @return The text inside the node and its descendants if the node
     * is an element, null otherwise.
     */
    public String getElementStringValue (final Object object) {
        return ((DomNode)object).asText();
    }

    /**
     * Get the string value of an attribute node.
     *
     * @param object The target node.
     * @return The text of the attribute value if the node is an
     * attribute, null otherwise.
     */
    public String getAttributeStringValue (final Object object) {
        return (String)((Map.Entry)object).getValue();
    }

    /**
     * Get the string value of text.
     *
     * @param object The target node.
     * @return The string of text if the node is text, null otherwise.
     */
    public String getTextStringValue (final Object object) {
        return ((DomText)object).asText();
    }

    /**
     * Get the string value of a comment node.
     *
     * @param object The target node.
     * @return The text of the comment if the node is a comment,
     * null otherwise.
     */
    public String getCommentStringValue (final Object object) {
        return null;
    }

    /**
     * Get the string value of a Namespace node.
     *
     * @param object The target node.
     * @return The Namespace URI as a (possibly empty) string if the
     * node is a namespace node, null otherwise.
     */
    public String getNamespaceStringValue (final Object object) {
        return null;
    }

    /**
     * Get the prefix value of a Namespace node.
     *
     * @param object The target node.
     * @return The Namespace prefix a (possibly empty) string if the
     * node is a namespace node, null otherwise.
     */
    public String getNamespacePrefix (final Object object) {
        return null;
    }

    /**
     *  Returns the element whose ID is given by elementId.
     *  If no such element exists, returns null.
     *  Attributes with the name "ID" are not of type ID unless so defined.
     *  Atribute types are only known if when the parser understands DTD's or
     *  schemas that declare attributes of type ID. When JAXP is used, you
     *  must call <code>setValidating(true)</code> on the
     *  DocumentBuilderFactory.
     *
     *  @param contextNode   a node from the document in which to look for the
     *                       id
     *  @param elementId   id to look for
     *
     *  @return   element whose ID is given by elementId, or null if no such
     *            element exists in the document or if the implementation
     *            does not know about attribute types
     *  @see   javax.xml.parsers.DocumentBuilderFactory
     */
    public Object getElementById(final Object contextNode, final String elementId) {
        final HtmlPage page = ((DomNode)contextNode).getPage();
        return page.getHtmlElementById(elementId);
    }

}
