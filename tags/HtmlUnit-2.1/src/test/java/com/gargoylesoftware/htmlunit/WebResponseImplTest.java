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
package com.gargoylesoftware.htmlunit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Tests for {@link WebResponseImpl}.
 *
 * @version $Revision$
 * @author Marc Guillemot
 * @author Ahmed Ashour
 */
public class WebResponseImplTest extends WebTestCase {

    /**
     * Verifies that when no encoding header is provided, encoding may be recognized with its Byte Order Mark.
     * @throws Exception if the test fails
     */
    @Test
    public void testRecognizeBOM() throws Exception {
        testRecognizeBOM("UTF-8",    new byte[] {(byte) 0xef, (byte) 0xbb, (byte) 0xbf});
        testRecognizeBOM("UTF-16BE", new byte[] {(byte) 0xfe, (byte) 0xff});
        testRecognizeBOM("UTF-16LE", new byte[] {(byte) 0xff, (byte) 0xfe});
    }

    private void testRecognizeBOM(final String encoding, final byte[] markerBytes) throws Exception {
        final WebClient webClient = new WebClient();

        final MockWebConnection webConnection = new MockWebConnection(webClient);

        final String html = "<html><head><script src='foo.js'></script></head><body></body></html>";

        // see http://en.wikipedia.org/wiki/Byte_Order_Mark
        final String[] expectedAlerts = {"\u6211\u662F\u6211\u7684 "
                + "\u064A\u0627 \u0623\u0647\u0644\u0627\u064B"};
        final byte[] script = ("alert('" + expectedAlerts[0]  + "');").getBytes(encoding);

        webConnection.setDefaultResponse(ArrayUtils.addAll(markerBytes, script), 200, "OK", "text/javascript");
        webConnection.setResponse(URL_FIRST, html);
        webClient.setWebConnection(webConnection);

        final List<String> collectedAlerts = new ArrayList<String>();
        webClient.setAlertHandler(new CollectingAlertHandler(collectedAlerts));

        webClient.getPage(URL_FIRST);

        assertEquals(expectedAlerts, collectedAlerts);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testEncoding() throws Exception {
        final String title = "\u6211\u662F\u6211\u7684FOCUS";
        final String content =
            "<html><head>\n"
            + "<title>" + title + "</title>\n"
            + "</head>\n"
            + "<body>\n"
            + "</body></html>";

        final WebClient client = new WebClient();

        final MockWebConnection webConnection = new MockWebConnection(client);
        final List< ? extends NameValuePair> emptyList = Collections.emptyList();
        webConnection.setResponse(URL_FIRST, content.getBytes("UTF-8"), 200, "OK", "text/html", emptyList);
        client.setWebConnection(webConnection);
        final WebRequestSettings settings = new WebRequestSettings(URL_FIRST);
        settings.setCharset("UTF-8");
        final HtmlPage page = (HtmlPage) client.getPage(settings);
        assertEquals(title, page.getTitleText());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testQuotedCharset() throws Exception {
        final String xml
            = "<books id='myId'>\n"
            + "  <book>\n"
            + "    <title>Immortality</title>\n"
            + "    <author>John Smith</author>\n"
            + "  </book>\n"
            + "</books>";

        final List<String> collectedAlerts = new ArrayList<String>();
        final WebClient client = new WebClient();
        client.setAlertHandler(new CollectingAlertHandler(collectedAlerts));
        final MockWebConnection conn = new MockWebConnection(client);
        final List< ? extends NameValuePair> emptyList = Collections.emptyList();
        conn.setResponse(URL_FIRST, xml, HttpStatus.SC_OK, "OK", "text/xml; charset=\"ISO-8859-1\"", emptyList);
        client.setWebConnection(conn);
        client.getPage(URL_FIRST);
    }
}
