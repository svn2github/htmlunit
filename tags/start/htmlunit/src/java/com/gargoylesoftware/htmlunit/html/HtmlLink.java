/*
 *  Copyright (C) 2002 Gargoyle Software. All rights reserved.
 *
 *  This file is part of HtmlUnit. For details on use and redistribution
 *  please refer to the license.html file included with these sources.
 */
package com.gargoylesoftware.htmlunit.html;

import org.w3c.dom.Element;

/**
 * Wrapper for the html element "link".  <b>Note:</b> This is not a clickable link,
 * that one is an HtmlAnchor
 *
 * @version  $Revision$
 * @author  <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 */
public class HtmlLink extends HtmlElement {

    /**
     * Create an instance of HtmlLink
     *
     * @param page The HtmlPage that contains this element.
     * @param xmlElement The actual html element that we are wrapping.
     */
    HtmlLink( final HtmlPage page, final Element xmlElement ) {
        super(page, xmlElement);
    }


    /**
     * Return the value of the attribute "id".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "id"
     * or an empty string if that attribute isn't defined.
     */
    public final String getIdAttribute() {
        return getAttributeValue("id");
    }


    /**
     * Return the value of the attribute "class".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "class"
     * or an empty string if that attribute isn't defined.
     */
    public final String getClassAttribute() {
        return getAttributeValue("class");
    }


    /**
     * Return the value of the attribute "style".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "style"
     * or an empty string if that attribute isn't defined.
     */
    public final String getStyleAttribute() {
        return getAttributeValue("style");
    }


    /**
     * Return the value of the attribute "title".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "title"
     * or an empty string if that attribute isn't defined.
     */
    public final String getTitleAttribute() {
        return getAttributeValue("title");
    }


    /**
     * Return the value of the attribute "lang".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "lang"
     * or an empty string if that attribute isn't defined.
     */
    public final String getLangAttribute() {
        return getAttributeValue("lang");
    }


    /**
     * Return the value of the attribute "xml:lang".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "xml:lang"
     * or an empty string if that attribute isn't defined.
     */
    public final String getXmlLangAttribute() {
        return getAttributeValue("xml:lang");
    }


    /**
     * Return the value of the attribute "dir".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "dir"
     * or an empty string if that attribute isn't defined.
     */
    public final String getTextDirectionAttribute() {
        return getAttributeValue("dir");
    }


    /**
     * Return the value of the attribute "onclick".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "onclick"
     * or an empty string if that attribute isn't defined.
     */
    public final String getOnClickAttribute() {
        return getAttributeValue("onclick");
    }


    /**
     * Return the value of the attribute "ondblclick".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "ondblclick"
     * or an empty string if that attribute isn't defined.
     */
    public final String getOnDblClickAttribute() {
        return getAttributeValue("ondblclick");
    }


    /**
     * Return the value of the attribute "onmousedown".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "onmousedown"
     * or an empty string if that attribute isn't defined.
     */
    public final String getOnMouseDownAttribute() {
        return getAttributeValue("onmousedown");
    }


    /**
     * Return the value of the attribute "onmouseup".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "onmouseup"
     * or an empty string if that attribute isn't defined.
     */
    public final String getOnMouseUpAttribute() {
        return getAttributeValue("onmouseup");
    }


    /**
     * Return the value of the attribute "onmouseover".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "onmouseover"
     * or an empty string if that attribute isn't defined.
     */
    public final String getOnMouseOverAttribute() {
        return getAttributeValue("onmouseover");
    }


    /**
     * Return the value of the attribute "onmousemove".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "onmousemove"
     * or an empty string if that attribute isn't defined.
     */
    public final String getOnMouseMoveAttribute() {
        return getAttributeValue("onmousemove");
    }


    /**
     * Return the value of the attribute "onmouseout".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "onmouseout"
     * or an empty string if that attribute isn't defined.
     */
    public final String getOnMouseOutAttribute() {
        return getAttributeValue("onmouseout");
    }


    /**
     * Return the value of the attribute "onkeypress".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "onkeypress"
     * or an empty string if that attribute isn't defined.
     */
    public final String getOnKeyPressAttribute() {
        return getAttributeValue("onkeypress");
    }


    /**
     * Return the value of the attribute "onkeydown".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "onkeydown"
     * or an empty string if that attribute isn't defined.
     */
    public final String getOnKeyDownAttribute() {
        return getAttributeValue("onkeydown");
    }


    /**
     * Return the value of the attribute "onkeyup".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "onkeyup"
     * or an empty string if that attribute isn't defined.
     */
    public final String getOnKeyUpAttribute() {
        return getAttributeValue("onkeyup");
    }


    /**
     * Return the value of the attribute "charset".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "charset"
     * or an empty string if that attribute isn't defined.
     */
    public final String getCharsetAttribute() {
        return getAttributeValue("charset");
    }


    /**
     * Return the value of the attribute "href".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "href"
     * or an empty string if that attribute isn't defined.
     */
    public final String getHrefAttribute() {
        return getAttributeValue("href");
    }


    /**
     * Return the value of the attribute "hreflang".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "hreflang"
     * or an empty string if that attribute isn't defined.
     */
    public final String getHrefLangAttribute() {
        return getAttributeValue("hreflang");
    }


    /**
     * Return the value of the attribute "type".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "type"
     * or an empty string if that attribute isn't defined.
     */
    public final String getTypeAttribute() {
        return getAttributeValue("type");
    }


    /**
     * Return the value of the attribute "rel".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "rel"
     * or an empty string if that attribute isn't defined.
     */
    public final String getRelAttribute() {
        return getAttributeValue("rel");
    }


    /**
     * Return the value of the attribute "rev".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "rev"
     * or an empty string if that attribute isn't defined.
     */
    public final String getRevAttribute() {
        return getAttributeValue("rev");
    }


    /**
     * Return the value of the attribute "media".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "media"
     * or an empty string if that attribute isn't defined.
     */
    public final String getMediaAttribute() {
        return getAttributeValue("media");
    }


    /**
     * Return the value of the attribute "target".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "target"
     * or an empty string if that attribute isn't defined.
     */
    public final String getTargetAttribute() {
        return getAttributeValue("target");
    }
}
