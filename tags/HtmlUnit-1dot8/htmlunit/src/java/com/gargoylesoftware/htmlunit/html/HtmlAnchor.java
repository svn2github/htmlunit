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
package com.gargoylesoftware.htmlunit.html;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.TextUtil;
import com.gargoylesoftware.htmlunit.WebRequestSettings;
import com.gargoylesoftware.htmlunit.WebWindow;

/**
 *  Wrapper for the html element "a"
 *
 * @version  $Revision$
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author David K. Taylor
 * @author <a href="mailto:cse@dynabean.de">Christian Sell</a>
 */
public class HtmlAnchor extends FocusableElement {

    /** the HTML tag represented by this element */
    public static final String TAG_NAME = "a";

    /**
     *  Create an instance
     *
     * @param  page The page that contains this element
     * @param attributes the initial attributes
     */
    public HtmlAnchor(final HtmlPage page, final Map attributes) {
        super( page, attributes );
    }

    /**
     * @return the HTML tag name
     */
    public String getTagName() {
        return TAG_NAME;
    }

    /**
     * This method will be called if there either wasn't an onclick handler or
     * there was but the result of that handler was true.  This is the default
     * behaviour of clicking the element.  For this anchor element, the default
     * behavior is to open the HREF page, or execute the HREF if it is a
     * javascript: URL.
     *
     * @param defaultPage The default page to return if the action does not
     * load a new page.
     * @return The page that is currently loaded after execution of this method
     * @throws IOException If an IO error occured
     */
    protected Page doClickAction(final Page defaultPage) throws IOException {


        final String href = getHrefAttribute();

        getLog().debug(
            "do click action in window '"
                    + defaultPage.getEnclosingWindow().getName()
                    + "', using href '" + href + "'");

        if( href != null && href.length() > 0 && !href.startsWith("#")) {
            final HtmlPage page = getPage();
            if( TextUtil.startsWithIgnoreCase(href, "javascript:") ) {
                return page.executeJavaScriptIfPossible(
                    href, "javascript url", true, this ).getNewPage();
            }
            else {
                final URL url = page.getFullyQualifiedUrl(href);
                getLog().debug(
                    "Getting page for " + url.toExternalForm()
                            + ", derived from href '" + href
                            + "', using the originating url "
                            + page.getWebResponse().getUrl());
                return page.getWebClient().getPage(
                        page.getEnclosingWindow(),
                        page.getResolvedTarget(getTargetAttribute()),
                        new WebRequestSettings(url));
            }
        }
        else {
            return defaultPage;
        }
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
     * Return the value of the attribute "name".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "name"
     * or an empty string if that attribute isn't defined.
     */
    public final String getNameAttribute() {
        return getAttributeValue("name");
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
     * Return the value of the attribute "accesskey".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "accesskey"
     * or an empty string if that attribute isn't defined.
     */
    public final String getAccessKeyAttribute() {
        return getAttributeValue("accesskey");
    }


    /**
     * Return the value of the attribute "shape".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "shape"
     * or an empty string if that attribute isn't defined.
     */
    public final String getShapeAttribute() {
        return getAttributeValue("shape");
    }


    /**
     * Return the value of the attribute "coords".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "coords"
     * or an empty string if that attribute isn't defined.
     */
    public final String getCoordsAttribute() {
        return getAttributeValue("coords");
    }


    /**
     * Return the value of the attribute "tabindex".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "tabindex"
     * or an empty string if that attribute isn't defined.
     */
    public final String getTabIndexAttribute() {
        return getAttributeValue("tabindex");
    }


    /**
     * Return the value of the attribute "onfocus".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "onfocus"
     * or an empty string if that attribute isn't defined.
     */
    public final String getOnFocusAttribute() {
        return getAttributeValue("onfocus");
    }


    /**
     * Return the value of the attribute "onblur".  Refer to the
     * <a href='http://www.w3.org/TR/html401/'>HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return The value of the attribute "onblur"
     * or an empty string if that attribute isn't defined.
     */
    public final String getOnBlurAttribute() {
        return getAttributeValue("onblur");
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
    
    /**
     * Open this link in a new window, much as web browsers do when you
     * shift-click a link or use the context menu to open in a new window.
     * 
     * It should be noted that even web browsers will sometimes not give the
     * expected result when using this method of following links. Links that
     * have no real href and rely on javascript to do their work will fail.
     * 
     * @return The Page opened by this link, nested in a new TopLevelWindow
     * @throws MalformedURLException if the href could not be converted to
     *         a valid URL
     */
    public final Page openLinkInNewWindow() throws MalformedURLException {
        final URL target = getPage().getFullyQualifiedUrl(getHrefAttribute());
        final WebWindow newWindow = getPage().getWebClient().openWindow(
                target,
                "HtmlAnchor.openLinkInNewWindow() target");
        return newWindow.getEnclosedPage();
    }
}
