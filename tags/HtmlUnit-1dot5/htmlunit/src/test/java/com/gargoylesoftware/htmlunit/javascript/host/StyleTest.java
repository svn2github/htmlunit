/*
 * Copyright (c) 2002, 2005 Gargoyle Software Inc. All rights reserved.
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
import java.util.Collections;
import java.util.List;

import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebTestCase;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Tests for Style.
 *
 * @version  $Revision$
 * @author  <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 */
public class StyleTest extends WebTestCase {

    /**
     * Create an instance
     * @param name The name of the test
     */
    public StyleTest( final String name ) {
        super(name);
    }


    /**
     * @throws Exception if the test fails
     */
    public void testStyle_OneCssAttribute() throws Exception {
        final WebClient client = new WebClient();
        final MockWebConnection webConnection = new MockWebConnection( client );

        final String firstContent
            = "<html><head><title>First</title><script>\n"
            + "function doTest() {\n"
            + "    var style = document.getElementById('div1').style;\n"
            + "    alert(style.color);\n"
            + "    style.color = 'pink';\n"
            + "    alert(style.color);\n"
            + "}\n</script></head>"
            + "<body onload='doTest()'><div id='div1' style='color: black'>foo</div></body></html>";

        webConnection.setResponse(
            URL_FIRST, firstContent, 200, "OK", "text/html", Collections.EMPTY_LIST );
        client.setWebConnection( webConnection );

        final List collectedAlerts = new ArrayList();
        client.setAlertHandler( new CollectingAlertHandler(collectedAlerts) );

        final HtmlPage page = (HtmlPage)client.getPage(URL_FIRST);

        final List expectedAlerts = Arrays.asList( new String[]{"black", "pink"} );
        assertEquals( expectedAlerts, collectedAlerts );

        assertEquals("color: pink; ", page.getHtmlElementById("div1").getAttributeValue("style") );
    }


    /**
     * @throws Exception if the test fails
     */
    public void testStyle_MultipleCssAttributes() throws Exception {
        final WebClient client = new WebClient();
        final MockWebConnection webConnection = new MockWebConnection( client );

        final String firstContent
            = "<html><head><title>First</title><script>\n"
            + "function doTest() {\n"
            + "    var style = document.getElementById('div1').style;\n"
            + "    alert(style.color);\n"
            + "    style.color = 'pink';\n"
            + "    alert(style.color);\n"
            + "}\n</script></head>"
            + "<body onload='doTest()'>"
            + "<div id='div1' style='color: black;background:blue;foo:bar'>foo</div></body></html>";

        webConnection.setResponse(
            URL_FIRST, firstContent, 200, "OK", "text/html", Collections.EMPTY_LIST );
        client.setWebConnection( webConnection );

        final List collectedAlerts = new ArrayList();
        client.setAlertHandler( new CollectingAlertHandler(collectedAlerts) );

        final HtmlPage page = (HtmlPage)client.getPage(URL_FIRST);

        final List expectedAlerts = Arrays.asList( new String[]{"black", "pink"} );
        assertEquals( expectedAlerts, collectedAlerts );

        assertEquals(
            "background: blue; color: pink; foo: bar; ",
            page.getHtmlElementById("div1").getAttributeValue("style") );
    }

    /**
     * @throws Exception if the test fails
     */
    public void testStyle_OneUndefinedCssAttribute() throws Exception {
        final WebClient client = new WebClient();
        final MockWebConnection webConnection = new MockWebConnection( client );

        final String firstContent
            = "<html><head><title>First</title><script>\n"
            + "function doTest() {\n"
            + "    var style = document.getElementById('div1').style;\n"
            + "    alert(document.getElementById('nonexistingid'));\n"
            + "    alert(style.color);\n"
            + "    style.color = 'pink';\n"
            + "    alert(style.color);\n"
            + "}\n</script></head>"
            + "<body onload='doTest()'><div id='div1'>foo</div></body></html>";

        webConnection.setResponse(
                URL_FIRST, firstContent, 200, "OK", "text/html", Collections.EMPTY_LIST );
        client.setWebConnection( webConnection );

        final List collectedAlerts = new ArrayList();
        client.setAlertHandler( new CollectingAlertHandler(collectedAlerts) );

        final HtmlPage page = (HtmlPage)client.getPage(URL_FIRST);

        final List expectedAlerts = Arrays.asList( new String[]{"null", "", "pink"} );
        assertEquals( expectedAlerts, collectedAlerts );

        assertEquals("color: pink; ", page.getHtmlElementById("div1").getAttributeValue("style") );
    }
}
