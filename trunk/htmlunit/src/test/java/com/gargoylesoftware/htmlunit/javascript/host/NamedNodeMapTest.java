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
package com.gargoylesoftware.htmlunit.javascript.host;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebTestCase;

/**
 * Tests for {@link com.gargoylesoftware.htmlunit.javascript.NamedNodeMap}.
 *
 * @version $Revision$
 * @author Marc Guillemot
 * @author Daniel Gredler
 */
public class NamedNodeMapTest extends WebTestCase {

    /**
     * @throws Exception if an error occurs
     */
    @Test
    public void testAttributes() throws Exception {
        final String html =
              "<html>\n"
            + "<head>\n"
            + "<script>\n"
            + "  function test() {\n"
            + "    var f = document.getElementById('f');\n"
            + "    for(var i = 0; i < f.attributes.length; i++) {\n"
            + "      alert(f.attributes[i].name + '=' + f.attributes[i].value);\n"
            + "    }\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body onload='test()'>\n"
            + "<form name='f' id='f' foo='bar' baz='blah'></form>\n"
            + "</body>\n"
            + "</html>";
        final List<String> actual = new ArrayList<String>();
        loadPage(html, actual);
        final String[] expected = {"name=f", "id=f", "foo=bar", "baz=blah"};
        assertEquals(expected, actual);
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    public void testGetNamedItem_HTML() throws Exception {
        final String html =
              "<html>\n"
            + "<head>\n"
            + "<script>\n"
            + "  function test() {\n"
            + "    var f = document.getElementById('f');\n"
            + "    alert(f.attributes.getNamedItem('name').nodeName);\n"
            + "    alert(f.attributes.getNamedItem('name').nodeValue);\n"
            + "    alert(f.attributes.getNamedItem('NaMe').nodeName);\n"
            + "    alert(f.attributes.getNamedItem('nAmE').nodeValue);\n"
            + "    alert(f.attributes.name.nodeName);\n"
            + "    alert(f.attributes.name.nodeValue);\n"
            + "    alert(f.attributes.NaMe.nodeName);\n"
            + "    alert(f.attributes.nAmE.nodeValue);\n"
            + "    alert(f.attributes.getNamedItem('notExisting'));\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body onload='test()'>\n"
            + "<form name='f' id='f' foo='bar' baz='blah'></form>\n"
            + "</body>\n"
            + "</html>";
        final String[] expected = {"name", "f", "name", "f", "name", "f", "name", "f", "null"};
        createTestPageForRealBrowserIfNeeded(html, expected);

        final List<String> actual = new ArrayList<String>();
        loadPage(html, actual);
        assertEquals(expected, actual);
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    public void testGetNamedItem_XML() throws Exception {
        final URL firstURL = new URL("http://htmlunit/first.html");
        final URL secondURL = new URL("http://htmlunit/second.xml");

        final String html = "<html><head><title>foo</title><script>\n"
            + "  function test() {\n"
            + "    var doc = createXmlDocument();\n"
            + "    doc.async = false;\n"
            + "    doc.load('" + "second.xml" + "');\n"
            + "    alert(doc.documentElement.attributes.getNamedItem('name').nodeName);\n"
            + "    alert(doc.documentElement.attributes.getNamedItem('name').nodeValue);\n"
            + "    alert(doc.documentElement.attributes.name.nodeName);\n"
            + "    alert(doc.documentElement.attributes.name.nodeValue);\n"
            + "    alert(doc.documentElement.attributes.getNamedItem('NaMe'));\n"
            + "    alert(doc.documentElement.attributes.NaMe);\n"
            + "    alert(doc.documentElement.attributes.getNamedItem('nonExistent'));\n"
            + "  }\n"
            + "  function createXmlDocument() {\n"
            + "    if (document.implementation && document.implementation.createDocument)\n"
            + "      return document.implementation.createDocument('', '', null);\n"
            + "    else if (window.ActiveXObject)\n"
            + "      return new ActiveXObject('Microsoft.XMLDOM');\n"
            + "  }\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";

        final String xml = "<blah name='y'></blah>";

        final String[] expectedAlerts = new String[] {"name", "y", "name", "y", "null", "undefined", "null"};
        final List<String> collectedAlerts = new ArrayList<String>();
        final WebClient client = new WebClient(BrowserVersion.FIREFOX_2);
        client.setAlertHandler(new CollectingAlertHandler(collectedAlerts));
        final MockWebConnection conn = new MockWebConnection(client);
        conn.setResponse(firstURL, html);
        conn.setResponse(secondURL, xml, "text/xml");
        client.setWebConnection(conn);

        client.getPage(firstURL);
        assertEquals(expectedAlerts, collectedAlerts);
    }

}
