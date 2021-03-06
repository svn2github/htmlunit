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
package com.gargoylesoftware.htmlunit.html;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebTestCase;

/**
 * Tests for {@link HtmlScript}.
 *
 * @version $Revision$
 * @author Marc Guillemot
 * @author Daniel Gredler
 */
public class HtmlScriptTest extends WebTestCase {

    /**
     * Creates an instance.
     * @param name The name of the test.
     */
    public HtmlScriptTest(final String name) {
        super(name);
    }

    /**
     * Verifies that a failing HTTP status code for a JavaScript file request (like a 404 response)
     * results in a {@link FailingHttpStatusCodeException}, depending on how the client has been
     * configured.
     *
     * @see HtmlPage#loadJavaScriptFromUrl(java.net.URL, String)
     * @see WebClient#isThrowExceptionOnFailingStatusCode()
     * @throws Exception if an error occurs
     */
    public void testBadExternalScriptReference() throws Exception {

        final String html = "<html><head><title>foo</title>\n"
                + "<script src='inexistent.js'></script>\n"
                + "</head><body></body></html>";

        final WebClient client = new WebClient();

        final MockWebConnection webConnection = new MockWebConnection(client);
        webConnection.setDefaultResponse("inexistent", 404, "Not Found", "text/html");
        webConnection.setResponse(URL_FIRST, html);
        client.setWebConnection(webConnection);

        try {
            client.getPage(URL_FIRST);
            fail("Should throw.");
        }
        catch (final FailingHttpStatusCodeException e) {
            final String url = URL_FIRST.toExternalForm();
            assertTrue("exception contains url of failing script", e.getMessage().indexOf(url) > -1);
            assertEquals(404, e.getStatusCode());
            assertEquals("Not Found", e.getStatusMessage());
        }

        client.setThrowExceptionOnFailingStatusCode(false);

        try {
            client.getPage(URL_FIRST);
        }
        catch (final FailingHttpStatusCodeException e) {
            fail("Should not throw.");
        }
    }

    /**
     * @throws Exception If an error occurs.
     */
    public void testAsText() throws Exception {
        final String htmlContent
            = "<html><head><title>foo</title></head><body>\n"
            + "<script id='script1'>\n"
            + "    var foo = 132;\n"
            + "</script></body></html>";

        final HtmlPage page = loadPage(htmlContent);

        final HtmlScript script = (HtmlScript) page.getHtmlElementById("script1");
        assertEquals("", script.asText());
    }

    /**
     * Verifies that the weird script src attribute used by the jQuery JavaScript library is
     * ignored silently (bug 1695279).
     * @throws Exception If the test fails.
     */
    public void testInvalidJQuerySrcAttribute() throws Exception {
        loadPage("<html><body><script src='//:'></script></body></html>");
    }

    /**
     * Verifies that if a script element executes "window.location.href=someotherpage", then subsequent
     * script tags, and any onload handler for the original page do not run.
     * @throws Exception If the test fails
     */
    public void testChangingLocationSkipsFurtherScriptsOnPage() throws Exception {
        final String firstPage
            = "<html><head></head>\n"
            + "<body onload='alert(\"body onload executing but should be skipped\")'>\n"
            + "<script>alert('First script executes')</script>\n"
            + "<script>window.location.href='" + URL_SECOND + "'</script>\n"
            + "<script>alert('Third script executing but should be skipped')</script>\n"
            + "</body></html>";

        final String secondPage
            = "<html><head></head><body>\n"
            + "<script>alert('Second page loading')</script>\n"
            + "</body></html>";

        final WebClient client = new WebClient();

        final MockWebConnection webConnection = new MockWebConnection(client);
        webConnection.setResponse(URL_FIRST, firstPage);
        webConnection.setResponse(URL_SECOND, secondPage);
        client.setWebConnection(webConnection);

        final List collectedAlerts = new ArrayList();
        client.setAlertHandler(new CollectingAlertHandler(collectedAlerts));

        client.getPage(URL_FIRST);
        final String[] expectedAlerts = {"First script executes", "Second page loading"};
        assertEquals(expectedAlerts, collectedAlerts);
    }

    /**
     * Verifies that a script element is not run when it is cloned.
     * See bug 1707788.
     * @throws Exception If an error occurs.
     */
    public void testScriptIsNotRunWhenCloned() throws Exception {
        final String html = "<html><body onload='document.body.cloneNode(true)'>\n"
            + "<script>alert('a')</script></body></html>";
        final List collectedAlerts = new ArrayList();
        loadPage(html, collectedAlerts);

        final String[] expectedAlerts = {"a"};
        assertEquals(expectedAlerts, collectedAlerts);
    }

    /**
     * @throws Exception if an error occurs
     */
    public void testDefer() throws Exception {
        final String html = "<html><head>\n"
            + "<script defer>alert('deferred')</script>\n"
            + "<script>alert('normal')</script>\n"
            + "</head>\n"
            + "<body onload='alert(\"onload\")'>test</body>\n"
            + "</html>";

        final List actualFF = new ArrayList();
        loadPage(BrowserVersion.FIREFOX_2, html, actualFF);
        final String[] expectedFF = new String[] {"deferred", "normal", "onload"};
        assertEquals(expectedFF, actualFF);

        final List actualIE = new ArrayList();
        loadPage(BrowserVersion.INTERNET_EXPLORER_7_0, html, actualIE);
        final String[] expectedIE = new String[] {"normal", "deferred", "onload"};
        assertEquals(expectedIE, actualIE);
    }
}
