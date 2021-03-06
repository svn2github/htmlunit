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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebTestCase;

/**
 * Tests for IFrame.
 *
 * @version  $Revision$
 * @author Marc Guillemot
 */
public class IFrameTest extends WebTestCase {

    /**
     * Create an instance
     * @param name The name of the test
     */
    public IFrameTest( final String name ) {
        super(name);
    }


    /**
     * @throws Exception if the test fails
     */
    public void testStyle() throws Exception {
        final String content
            = "<html><head><title>First</title><script>\n"
            + "function doTest() {\n"
            + "    alert(document.getElementById('myIFrame').style == undefined);\n"
            + "}\n</script></head>"
            + "<body onload='doTest()'>"
            + "<iframe id='myIFrame' src='about:blank'></iframe></body></html>";

        final List collectedAlerts = new ArrayList();
        loadPage(content, collectedAlerts);
        final List expectedAlerts = Arrays.asList( new String[]{"false"} );
        createTestPageForRealBrowserIfNeeded(content, expectedAlerts);
        assertEquals( expectedAlerts, collectedAlerts );
    }

    /**
     * @throws Exception if the test fails
     */
    public void testReferenceFromJavaScript() throws Exception {
        final String content
            = "<html><head><title>First</title><script>\n"
            + "function doTest() {\n"
            + "    alert(window.frames.length);\n"
            + "    alert(window.frames['myIFrame'].name);\n"
            + "}\n</script></head>"
            + "<body onload='doTest()'>"
            + "<iframe name='myIFrame' src='about:blank'></iframe></body></html>";

        final List collectedAlerts = new ArrayList();
        loadPage(content, collectedAlerts);
        final List expectedAlerts = Arrays.asList( new String[]{"1", "myIFrame"} );
        createTestPageForRealBrowserIfNeeded(content, expectedAlerts);
        assertEquals( expectedAlerts, collectedAlerts );
    }

    /**
     * Regression test for bug 1562872
     * @throws Exception if the test fails
     */
    public void testDirectAccessPerName() throws Exception {
        final String content
            = "<html><head><title>First</title><script>\n"
            + "function doTest() {\n"
            + "    alert(myIFrame.location);\n"
            + "    alert(Frame.location);\n"
            + "}\n</script></head>"
            + "<body onload='doTest()'>"
            + "<iframe name='myIFrame' src='about:blank'></iframe>"
            + "<iframe name='Frame' src='about:blank'></iframe>"
            + "</body></html>";

        final List collectedAlerts = new ArrayList();
        final String[] expectedAlerts = {"about:blank", "about:blank"};
        createTestPageForRealBrowserIfNeeded(content, expectedAlerts);
        loadPage(content, collectedAlerts);
        assertEquals( expectedAlerts, collectedAlerts );
    }


    /**
     * Tests that the <iframe> node is visible from the contained page when it is loaded
     * @throws Exception if the test fails
     */
    public void testOnLoadGetsIFrameElementByIdInParent() throws Exception {
        final String firstContent
            = "<html><head><title>First</title></head>"
            + "<body>"
            + "<iframe id='myIFrame' src='frame.html'></iframe></body></html>";

        final String frameContent
            = "<html><head><title>Frame</title><script>\n"
            + "function doTest() {\n"
            + "    alert(parent.document.getElementById('myIFrame').tagName);\n"
            + "}\n</script></head>"
            + "<body onload='doTest()'>"
            + "</body></html>";
              
        final WebClient webClient = new WebClient();
        final MockWebConnection webConnection =
            new MockWebConnection(webClient);

        webConnection.setDefaultResponse(frameContent);
        webConnection.setResponse(URL_FIRST, firstContent);
        webClient.setWebConnection(webConnection);

        final List collectedAlerts = new ArrayList();
        webClient.setAlertHandler(new CollectingAlertHandler(collectedAlerts));

        final List expectedAlerts = Arrays.asList( new String[]{"IFRAME"} );
        webClient.getPage(URL_FIRST);
        assertEquals( expectedAlerts, collectedAlerts );
    }

    /**
     * @throws Exception if the test fails
     */
    public void testContentDocument() throws Exception {
        final String content
            = "<html><head><title>first</title>"
                + "<script>"
                + "function test()\n"
                + "{\n"
                + "  alert(document.getElementById('myFrame').contentDocument == frames.foo.document);\n"
                + "}\n"
                + "</script></head>"
                + "<body onload='test()'>"
                + "<iframe name='foo' id='myFrame' src='about:blank'></iframe>"
                + "</body></html>";
        final List expectedAlerts = Arrays.asList( new String[]{"true"} );
        createTestPageForRealBrowserIfNeeded(content, expectedAlerts);

        final List collectedAlerts = new ArrayList();
        loadPage(BrowserVersion.MOZILLA_1_0, content, collectedAlerts);

        assertEquals(expectedAlerts, collectedAlerts);
    }


    /**
     * @throws Exception if the test fails
     */
    public void testFrameElement() throws Exception {
        final String content
            = "<html><head><title>first</title>"
                + "<script>"
                + "function test()\n"
                + "{\n"
                + "  alert(document.getElementById('myFrame') == frames.foo.frameElement);\n"
                + "}\n"
                + "</script></head>"
                + "<body onload='test()'>"
                + "<iframe name='foo' id='myFrame' src='about:blank'></iframe>"
                + "</body></html>";
        final List expectedAlerts = Arrays.asList( new String[]{"true"} );
        createTestPageForRealBrowserIfNeeded(content, expectedAlerts);

        final List collectedAlerts = new ArrayList();
        loadPage(content, collectedAlerts);

        assertEquals(expectedAlerts, collectedAlerts);
    }
}
