/*
 * Copyright (c) 2002-2009 Gargoyle Software Inc.
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

import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.BrowserRunner;
import com.gargoylesoftware.htmlunit.WebTestCase;
import com.gargoylesoftware.htmlunit.BrowserRunner.Alerts;
import com.gargoylesoftware.htmlunit.BrowserRunner.Browser;
import com.gargoylesoftware.htmlunit.BrowserRunner.Browsers;

/**
 * Unit tests for {@link HTMLHtmlElement}.
 *
 * @version $Revision$
 * @author Daniel Gredler
 * @author Marc Guillemot
 */
@RunWith(BrowserRunner.class)
public class HTMLHtmlElementTest extends WebTestCase {

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Browsers(Browser.FF)
    @Alerts(FF2 = { "[object HTMLHtmlElement]", "[HTMLHtmlElement]" },
            FF3 = { "[object HTMLHtmlElement]", "[object HTMLHtmlElement]" })
    public void HTMLHtmlElement_toString() throws Exception {
        final String html = "<html id='myId'><head><title>foo</title><script>\n"
            + "  function test() {\n"
            + "    alert(document.getElementById('myId'));\n"
            + "    alert(HTMLHtmlElement);\n"
            + "  }\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";
        loadPageWithAlerts(html);
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @Alerts({ "2", "HEAD", "BODY", "null", "null" })
    public void childNodes_1() throws Exception {
        // The whitespace in this HTML is very important, because we're verifying
        // that it doesn't get included in the childNodes collection.
        final String html = "<html> \n <body> \n <script>\n"
            + "var nodes = document.documentElement.childNodes;\n"
            + "alert(nodes.length);\n"
            + "alert(nodes[0].nodeName);\n"
            + "alert(nodes[1].nodeName);\n"
            + "alert(nodes[0].previousSibling);\n"
            + "alert(nodes[1].nextSibling);\n"
            + "</script> \n </body> \n </html>";
        loadPageWithAlerts(html);
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @Alerts({ "1", "HEAD" })
    public void childNodes_2() throws Exception {
        // The whitespace in this HTML is very important, because we're verifying
        // that it doesn't get included in the childNodes collection.
        final String html = "<html> \n <head> \n <script>\n"
            + "var nodes = document.documentElement.childNodes;\n"
            + "alert(nodes.length);\n"
            + "alert(nodes[0].nodeName);\n"
            + "</script> \n </head> \n </html>";
        loadPageWithAlerts(html);
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @Alerts(FF = { "true", "true", "true", "true" },
            IE = { "true", "false", "true", "false" })
    public void clientWidth() throws Exception {
        final String html = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'"
            + " 'http://www.w3.org/TR/html4/loose.dtd'>" // important for IE6!!!
            + "<html><head>\n"
            + "<script>\n"
            + "function test() {\n"
            + "  var elt = document.body.parentNode;\n"
            + "  alert(elt.clientWidth > 0);\n"
            + "  alert(elt.clientWidth == window.innerWidth);\n"
            + "  alert(elt.clientHeight > 0);\n"
            + "  alert(elt.clientHeight == window.innerHeight);\n"
            + "}\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";

        loadPageWithAlerts(html);
    }
}
