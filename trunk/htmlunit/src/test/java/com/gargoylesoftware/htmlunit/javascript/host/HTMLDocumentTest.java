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
import com.gargoylesoftware.htmlunit.BrowserRunner.NotYetImplemented;

/**
 * Tests for {@link HTMLDocument}.
 *
 * @version $Revision$
 * @author Ahmed Ashour
 * @author Marc Guillemot
 */
@RunWith(BrowserRunner.class)
public class HTMLDocumentTest extends WebTestCase {

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts({ "2", "DIV", "2" })
    public void getElementsByTagName() throws Exception {
        final String html = "<html>\n"
            + "<head>\n"
            + "    <title>Test</title>\n"
            + "    <script>\n"
            + "    function test() {\n"
            + "      alert(document.getElementsByTagName('div').length);\n"
            + "      document.getElementById('myDiv').innerHTML = \"<P><DIV id='secondDiv'></DIV></P>\";\n"
            + "      alert(document.getElementById('secondDiv').nodeName);\n"
            + "      alert(document.getElementsByTagName('div').length);\n"
            + "    }\n"
            + "    </script>\n"
            + "</head>\n"
            + "<body onload='test()'>"
            + "<div id='myDiv'>\n"
            + "  <div></div>\n"
            + "</div>\n"
            + "</body>\n"
            + "</html>";

        loadPageWithAlerts(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts(FF3 = { "function", "div1", "span2", "span3", "2", "1", "1", "0", "0", "0" },
            FF2 = { "undefined", "exception" },
            IE = { "undefined", "exception" })
    public void getElementsByClassName() throws Exception {
        final String html
            = "<html><head><title>First</title><script>\n"
            + "function doTest() {\n"
            + "    alert(typeof document.getElementsByClassName);\n"
            + "    try {\n"
            + "      var elements = document.getElementsByClassName('foo');\n"
            + "      for (var i=0; i<elements.length; i++) {\n"
            + "        alert(elements[i].id);\n"
            + "      }\n"
            + "      alert(document.getElementsByClassName('red').length);\n"
            + "      alert(document.getElementsByClassName('foo red').length);\n"
            + "      alert(document.getElementsByClassName('red foo').length);\n"
            + "      alert(document.getElementsByClassName('blue foo').length);\n"
            + "      alert(document.getElementsByClassName('*').length);\n"
//            + "      alert(document.getElementsByClassName().length);\n" // exception in FF3
            + "      alert(document.getElementsByClassName(null).length);\n"
            + "    }\n"
            + "    catch (e) { alert('exception') }\n"
            + "}\n"
            + "</script></head><body onload='doTest()'>\n"
            + "<div class='foo' id='div1'><span class='c2'>hello</span>\n"
            + "  <span class='foo' id='span2'>World!</span></div>\n"
            + "<span class='foo red' id='span3'>again</span>\n"
            + "<span class='red' id='span4'>bye</span>\n"
            + "</body></html>";

        loadPageWithAlerts(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("BackCompat")
    public void compatMode() throws Exception {
        compatMode("");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("BackCompat")
    public void compatMode_no_url() throws Exception {
        compatMode("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("CSS1Compat")
    public void compatMode_strict() throws Exception {
        compatMode("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("CSS1Compat")
    public void compatMode_strict_40() throws Exception {
        compatMode("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts(IE = { "CSS1Compat" }, FF = { "BackCompat" })
    public void compatMode_loose_40() throws Exception {
        compatMode("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\" "
            + "\"http://www.w3.org/TR/html4/loose.dtd\">");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("CSS1Compat")
    public void compatMode_loose() throws Exception {
        compatMode("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" "
            + "\"http://www.w3.org/TR/html4/loose.dtd\">");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("CSS1Compat")
    public void compatMode_xhtml_traditional() throws Exception {
        compatMode("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" "
            + "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("CSS1Compat")
    public void compatMode_xhtml_strict() throws Exception {
        compatMode("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" "
            + "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
    }

    private void compatMode(final String doctype) throws Exception {
        final String html = doctype + "<html>\n"
            + "<head>\n"
            + "    <title>Test</title>\n"
            + "    <script>\n"
            + "    function test() {\n"
            + "      alert(document.compatMode);\n"
            + "    }\n"
            + "    </script>\n"
            + "</head>\n"
            + "<body onload='test()'>\n"
            + "</body>\n"
            + "</html>";

        loadPageWithAlerts(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts(IE = "true", FF = "false")
    public void uniqueID() throws Exception {
        final String html = "<html>\n"
            + "<head>\n"
            + "    <title>Test</title>\n"
            + "    <script>\n"
            + "    function test() {\n"
            + "      alert(document.uniqueID != undefined);\n"
            + "    }\n"
            + "    </script>\n"
            + "</head>\n"
            + "<body onload='test()'>\n"
            + "</body>\n"
            + "</html>";

        loadPageWithAlerts(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Browsers(Browser.FF)
    @Alerts({ "[object HTMLDivElement]", "[object HTMLUnknownElement]", "[object Element]" })
    public void createDocumentNS() throws Exception {
        final String html = "<html>\n"
            + "<head>\n"
            + "<title>Test</title>\n"
            + "<script>\n"
            + "function test() {\n"
            + "  var elt = document.createElementNS('http://www.w3.org/1999/xhtml', 'div');\n"
            + "  alert(elt);\n"
            + "  var elt = document.createElementNS('http://www.w3.org/1999/xhtml', 'foo');\n"
            + "  alert(elt);\n"
            + "  elt = document.createElementNS('blabla', 'div');\n"
            + "  alert(elt);\n"
            + "}\n"
            + "</script>\n"
            + "</head>\n"
            + "<body onload='test()'>"
            + "</body>\n"
            + "</html>";

        loadPageWithAlerts(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Browsers(Browser.FF)
    @Alerts("Hello")
    public void createDocumentNS_xul() throws Exception {
        final String html = "<html>\n"
            + "<head>\n"
            + "<title>Test</title>\n"
            + "<script>\n"
            + "function test() {\n"
            + "  var inner = document.createElementNS('http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul',"
            + "'label');\n"
            + "  inner.setAttribute('value', 'Hello');\n"
            + "  inner.style['fontFamily'] = 'inherit';\n"
            + "  document.body.appendChild(inner);\n"
            + "  alert(document.body.lastChild.value);\n"
            + "}\n"
            + "</script>\n"
            + "</head>\n"
            + "<body onload='test()'>"
            + "</body>\n"
            + "</html>";

        loadPageWithAlerts(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts(FF = { "[object HTMLCollection]", "0" }, IE = { "[object]", "0" })
    public void applets() throws Exception {
        final String html = "<html>\n"
            + "<head>\n"
            + "<title>Test</title>\n"
            + "<script>\n"
            + "function test() {\n"
            + "  alert(document.applets);\n"
            + "  alert(document.applets.length);\n"
            + "}\n"
            + "</script>\n"
            + "</head>\n"
            + "<body onload='test()'>"
            + "</body>\n"
            + "</html>";

        loadPageWithAlerts(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts(FF = { "imported: [object HTMLScriptElement]" }, IE = { "exception" })
    @NotYetImplemented(Browser.FF)
    public void importNode_script() throws Exception {
        final String html = "<html><head><title>foo</title><script>\n"
            + "function test() {\n"
            + " try {\n"
            + "  var d = document.implementation.createDocument(null, null, null);\n"
            + "  var xhtml = \"<html xmlns='http://www.w3.org/1999/xhtml'><sc\" "
            + "   + \"ript>alert('o'); _scriptEvaluated=true;</scr\" + \"ipt></html>\";\n"
            + "  var newDoc = (new DOMParser()).parseFromString(xhtml, 'text/xml');\n"
            + "  var theScript = newDoc.getElementsByTagName('script')[0];\n"
            + "  var importedScript = window.document.importNode(theScript, true);\n"
            + "  alert('imported: ' + importedScript);\n"
            + "  var theSpan = document.getElementById('s1');\n"
            + "  document.body.replaceChild(importedScript, theSpan);\n"
            + " } catch (e) { alert('exception') }\n"
            + "}\n"
            + "</script></head><body onload='test()'>\n"
            + "  <span id='s1'></span>\n"
            + "</body></html>";

        loadPageWithAlerts(html);
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @Browsers(Browser.FF)
    @Alerts("clicked")
    public void dispatchEvent() throws Exception {
        final String html =
            "<html>\n"
            + "<script>\n"
            + "  function doTest() {\n"
            + "    var e = document.createEvent('MouseEvents');\n"
            + "    e.initMouseEvent('click', true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);\n"
            + "    document.dispatchEvent(e);\n"
            + "  }\n"
            + "  function clickListener() {\n"
            + "    alert('clicked');\n"
            + "  }\n"
            + "  document.addEventListener('click', clickListener, true);\n"
            + "</script>\n"
            + "<body onload='doTest()'>foo</body>\n"
            + "</html>";
        loadPageWithAlerts(html);
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @Browsers(Browser.IE)
    @Alerts({ "0", "1", "f", "f", "urn:f", "urn:f", "true" })
    public void namespaces() throws Exception {
        final String html =
              "<body><script>\n"
            + "var ns = document.namespaces;\n"
            + "alert(ns.length);\n"
            + "ns.add('f', 'urn:f');\n"
            + "alert(ns.length);\n"
            + "alert(ns.item(0).name);\n"
            + "alert(ns[0].name);\n"
            + "alert(ns.item('f').urn);\n"
            + "alert(ns['f'].urn);\n"
            + "alert(ns == document.namespaces);\n"
            + "</script></body>";
        loadPageWithAlerts(html);
    }

}
