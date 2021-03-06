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
package com.gargoylesoftware.htmlunit.libraries;

import java.net.URL;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebTestCase;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Tests for compatibility with version 1.5.0-rc1 of
 * <a href="http://prototype.conio.net/">Prototype JavaScript library</a>.
 *
 * @version $Revision$
 * @author Daniel Gredler
 * @author Ahmed Ashour
 */
public class Prototype150rc1Test extends WebTestCase {

    /**
     * @param name The name of the test.
     */
    public Prototype150rc1Test(final String name) {
        super(name);
    }

    /**
     * @throws Exception If test fails.
     */
    public void testAjax() throws Exception {
        if (notYetImplemented()) {
            return;
        }
        final String filename = "ajax.html";
        test(BrowserVersion.INTERNET_EXPLORER_7_0, filename, 3, 5, 2, 2);
        test(BrowserVersion.FIREFOX_2, filename, 3, 11, 0, 0);
    }

    /**
     * @throws Exception If test fails.
     */
    public void testArray() throws Exception {
        final String filename = "array.html";
        test(BrowserVersion.INTERNET_EXPLORER_7_0, filename, 12, 49, 0, 0);
        test(BrowserVersion.FIREFOX_2, filename, 12, 49, 0, 0);
    }

    /**
     * @throws Exception If test fails.
     */
    public void testBase() throws Exception {
        final String filename = "base.html";
        test(BrowserVersion.INTERNET_EXPLORER_7_0, filename, 4, 48, 0, 0);
        test(BrowserVersion.FIREFOX_2, filename, 4, 48, 0, 0);
    }

    /**
     * @throws Exception If test fails.
     */
    public void testDom() throws Exception {
        if (notYetImplemented()) {
            return;
        }
        final String filename = "dom.html";
        test(BrowserVersion.INTERNET_EXPLORER_7_0, filename, 25, 253, 1, 0);
        test(BrowserVersion.FIREFOX_2, filename, 25, 254, 0, 0);
    }

    /**
     * @throws Exception If test fails.
     */
    public void testElementMixins() throws Exception {
        final String filename = "element_mixins.html";
        test(BrowserVersion.INTERNET_EXPLORER_7_0, filename, 4, 7, 0, 0);
        test(BrowserVersion.FIREFOX_2, filename, 4, 7, 0, 0);
    }

    /**
     * @throws Exception If test fails.
     */
    public void testEnumerable() throws Exception {
        final String filename = "enumerable.html";
        test(BrowserVersion.INTERNET_EXPLORER_7_0, filename,  23, 67, 0, 0);
        test(BrowserVersion.FIREFOX_2, filename,  23, 67, 0, 0);
    }

    /**
     * @throws Exception If test fails.
     */
    public void testForm() throws Exception {
        if (notYetImplemented()) {
            return;
        }
        final String filename = "form.html";
        test(BrowserVersion.INTERNET_EXPLORER_7_0, filename, 4, 21, 0, 0);
        test(BrowserVersion.FIREFOX_2, filename, 4, 21, 0, 0);
    }

    /**
     * Blocked by Rhino bug 370279
     * https://bugzilla.mozilla.org/show_bug.cgi?id=370279
     * @throws Exception If test fails.
     */
    public void testHash() throws Exception {
        final String filename = "hash.html";
        test(BrowserVersion.INTERNET_EXPLORER_7_0, filename, 5, 19, 0, 0);
        test(BrowserVersion.FIREFOX_2, filename, 5, 19, 0, 0);
    }

    /**
     * @throws Exception If test fails.
     */
    public void testPosition() throws Exception {
        if (notYetImplemented()) {
            return;
        }
        final String filename = "position.html";
        test(BrowserVersion.INTERNET_EXPLORER_7_0, filename, 5, 25, 3, 0);
        test(BrowserVersion.FIREFOX_2, filename, 5, 28, 0, 0);
    }

    /**
     * @throws Exception If test fails.
     */
    public void testRange() throws Exception {
        final String filename = "range.html";
        test(BrowserVersion.INTERNET_EXPLORER_7_0, filename, 6, 21, 0, 0);
        test(BrowserVersion.FIREFOX_2, filename, 6, 21, 0, 0);
    }

    /**
     * @throws Exception If test fails.
     */
    public void testSelector() throws Exception {
        final String filename = "selector.html";
        //HtmlUnit with IE succeeds for all :)
        // It should be test(BrowserVersion.INTERNET_EXPLORER_7_0, filename, 18, 35, 9, 1);
        test(BrowserVersion.INTERNET_EXPLORER_7_0, filename, 18, 46, 0, 0);
        test(BrowserVersion.FIREFOX_2, filename, 18, 46, 0, 0);
    }

    /**
     * Blocked by Rhino bug 369860
     * https://bugzilla.mozilla.org/show_bug.cgi?id=369860
     * @throws Exception If test fails.
     */
    public void testString() throws Exception {
        if (notYetImplemented()) {
            return;
        }
        final String filename = "string.html";
        test(BrowserVersion.INTERNET_EXPLORER_7_0, filename, 19, 76, 0, 0);
        test(BrowserVersion.FIREFOX_2, filename, 19, 76, 0, 0);
    }

    private void test(final BrowserVersion browserVersion, final String filename, final int tests,
            final int assertions, final int failures, final int errors) throws Exception {
        final WebClient client = new WebClient(browserVersion);
        final URL url = getClass().getClassLoader().getResource("prototype/1.5.0-rc1/test/unit/" + filename);
        assertNotNull(url);

        final HtmlPage page = (HtmlPage) client.getPage(url);
        page.getEnclosingWindow().getThreadManager().joinAll(10000);

        final String summary = page.getHtmlElementById("logsummary").asText();
        final String expected = tests + " tests, " + assertions + " assertions, " + failures + " failures, "
             + errors + " errors";
        assertEquals(expected, summary);
    }

}
