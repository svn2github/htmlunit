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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.NameValuePair;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.NodeList;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.ImmediateRefreshHandler;
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.OnbeforeunloadHandler;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.SubmitMethod;
import com.gargoylesoftware.htmlunit.TextUtil;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequestSettings;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.WebTestCase;
import com.gargoylesoftware.htmlunit.html.HtmlElementTest.HtmlAttributeChangeListenerTestImpl;

/**
 * Tests for {@link HtmlPage}.
 *
 * @version $Revision$
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author Noboru Sinohara
 * @author David K. Taylor
 * @author Andreas Hangler
 * @author <a href="mailto:cse@dynabean.de">Christian Sell</a>
 * @author Marc Guillemot
 * @author Ahmed Ashour
 */
public class HtmlPageTest extends WebTestCase {

    /**
     * @exception Exception If the test fails
     */
    @Test
    public void testConstructor() throws Exception {
        final String htmlContent = "<html>\n"
            + "<head><title>foo</title></head>\n"
            + "<body>\n"
            + "<p>hello world</p>\n"
            + "<form id='form1' action='/formSubmit' method='post'>\n"
            + "<input type='text' NAME='textInput1' value='textInput1'/>\n"
            + "<input type='text' name='textInput2' value='textInput2'/>\n"
            + "<input type='hidden' name='hidden1' value='hidden1'/>\n"
            + "<input type='submit' name='submitInput1' value='push me'/>\n"
            + "</form>\n"
            + "</body></html>";

        final HtmlPage page = loadPage(htmlContent);
        assertEquals("foo", page.getTitleText());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testGetInputByName() throws Exception {
        final String htmlContent = "<html>\n"
            + "<head><title>foo</title></head>\n"
            + "<body>\n"
            + "<p>hello world</p>\n"
            + "<form id='form1' action='/formSubmit' method='post'>\n"
            + "<input type='text' NAME='textInput1' value='textInput1'/>\n"
            + "<input type='text' name='textInput2' value='textInput2'/>\n"
            + "<input type='hidden' name='hidden1' value='hidden1'/>\n"
            + "<input type='submit' name='submitInput1' value='push me'/>\n"
            + "</form>\n"
            + "</body></html>";
        final HtmlPage page = loadPage(htmlContent);

        final HtmlForm form = (HtmlForm) page.getHtmlElementById("form1");
        final HtmlInput input = form.getInputByName("textInput1");
        Assert.assertEquals("name", "textInput1", input.getNameAttribute());

        Assert.assertEquals("value", "textInput1", input.getValueAttribute());
        Assert.assertEquals("type", "text", input.getTypeAttribute());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testFormSubmit() throws Exception {
        final String htmlContent = "<html>\n"
            + "<head><title>foo</title></head>\n"
            + "<body>\n"
            + "<p>hello world</p>\n"
            + "<form id='form1' action='/formSubmit' method='PoSt'>\n"
            + "<input type='text' NAME='textInput1' value='textInput1'/>\n"
            + "<input type='text' name='textInput2' value='textInput2'/>\n"
            + "<input type='hidden' name='hidden1' value='hidden1'/>\n"
            + "<input type='submit' name='submitInput1' value='push me'/>\n"
            + "</form>\n"
            + "</body></html>";
        final HtmlPage page = loadPage(htmlContent);
        final MockWebConnection webConnection = getMockConnection(page);

        final HtmlForm form = (HtmlForm) page.getHtmlElementById("form1");
        final HtmlInput textInput = form.getInputByName("textInput1");
        textInput.setValueAttribute("foo");

        final HtmlSubmitInput button = (HtmlSubmitInput) form.getInputByName("submitInput1");
        final HtmlPage secondPage = (HtmlPage) button.click();

        final List<NameValuePair> expectedParameters = new ArrayList<NameValuePair>();
        expectedParameters.add(new NameValuePair("textInput1", "foo"));
        expectedParameters.add(new NameValuePair("textInput2", "textInput2"));
        expectedParameters.add(new NameValuePair("hidden1", "hidden1"));
        expectedParameters.add(new NameValuePair("submitInput1", "push me"));

        final URL expectedUrl = new URL("http://www.gargoylesoftware.com/formSubmit");
        final URL actualUrl = secondPage.getWebResponse().getUrl();
        assertEquals("url", expectedUrl, actualUrl);
        Assert.assertEquals("method", SubmitMethod.POST, webConnection.getLastMethod());
        Assert.assertEquals("parameters", expectedParameters, webConnection.getLastParameters());
        assertNotNull(secondPage);
    }

    /**
     * Tests getHtmlElement() for all elements that can be loaded.
     * @throws Exception if the test fails
     */
    @Test
    public void testGetHtmlElement() throws Exception {
        final String htmlContent = "<html>\n"
            + "<head><title>foo</title></head>\n"
            + "<body>\n"
            + "    <p>hello world</p>\n"
            + "    <form id='form1' id='form1' action='/formSubmit' method='post'>\n"
            + "    <input type='text' NAME='textInput1' value='textInput1'/>\n"
            + "    <button type='submit' name='button1'>foobar</button>\n"
            + "    <select name='select1'>\n"
            + "        <option value='option1'>Option1</option>\n"
            + "    </select>\n"
            + "    <textarea name='textArea1'>foobar</textarea>\n"
            + "    </form>\n"
            + "    <a href='http://www.foo.com' name='anchor1'>foo.com</a>\n"
            + "    <table id='table1'>\n"
            + "        <tr>\n"
            + "            <th id='header1'>Header</th>\n"
            + "            <td id='data1'>Data</td>\n"
            + "        </tr>\n"
            + "    </table>\n"
            + "</body></html>";
        final HtmlPage page = loadPage(htmlContent);

        final HtmlForm form = (HtmlForm) page.getHtmlElementById("form1");
        assertSame("form1", form, page.getHtmlElementById("form1")); //huh??

        final HtmlInput input = form.getInputByName("textInput1");
        assertSame("input1", input, form.getInputByName("textInput1")); //??

        final HtmlButton button = form.getButtonByName("button1");
        assertSame("button1", button, form.getButtonByName("button1"));

        final HtmlSelect select = (HtmlSelect) form.getSelectsByName("select1").get(0);
        assertSame("select1", select, form.getSelectsByName("select1").get(0));

        final HtmlOption option = select.getOptionByValue("option1");
        assertSame("option1", option, select.getOptionByValue("option1"));

        final HtmlTable table = (HtmlTable) page.getHtmlElementById("table1");
        assertSame("table1", table, page.getHtmlElementById("table1"));

        final HtmlAnchor anchor = page.getAnchorByName("anchor1");
        assertSame("anchor1", anchor, page.getAnchorByName("anchor1"));
        assertSame("anchor3", anchor, page.getAnchorByHref("http://www.foo.com"));
        assertSame("anchor4", anchor, page.getFirstAnchorByText("foo.com"));

        final HtmlTableRow tableRow = table.getRow(0);
        assertSame("tableRow1", tableRow, table.getRow(0));

        final HtmlTableHeaderCell tableHeaderCell = (HtmlTableHeaderCell) tableRow.getCell(0);
        assertSame("tableHeaderCell1", tableHeaderCell, tableRow.getCell(0));
        assertSame("tableHeaderCell2", tableHeaderCell, page.getHtmlElementById("header1"));

        final HtmlTableDataCell tableDataCell = (HtmlTableDataCell) tableRow.getCell(1);
        assertSame("tableDataCell1", tableDataCell, tableRow.getCell(1));
        assertSame("tableDataCell2", tableDataCell, page.getHtmlElementById("data1"));

        final HtmlTextArea textArea = form.getTextAreaByName("textArea1");
        assertSame("textArea1", textArea, form.getTextAreaByName("textArea1"));
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testGetTabbableElements_None() throws Exception {
        final String htmlContent = "<html>\n"
            + "<head><title>foo</title></head>\n"
            + "<body>\n"
            + "<p>hello world</p>\n"
            + "<table><tr><td>foo</td></tr></table>\n"
            + "</body></html>";

        final HtmlPage page = loadPage(htmlContent);

        assertEquals(Collections.EMPTY_LIST, page.getTabbableElements());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testGetTabbableElements_OneEnabled_OneDisabled() throws Exception {
        final String htmlContent = "<html>\n"
            + "<head><title>foo</title></head>\n"
            + "<body>\n"
            + "<form><p>hello world</p>\n"
            + "<input name='foo' type='submit' disabled='disabled' id='foo'/>\n"
            + "<input name='bar' type='submit' id='bar'/>\n"
            + "</form></body></html>";
        final HtmlPage page = loadPage(htmlContent);

        final List<HtmlElement> expectedElements = new ArrayList<HtmlElement>();
        expectedElements.add(page.getHtmlElementById("bar"));

        assertEquals(expectedElements, page.getTabbableElements());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testGetTabbableElements() throws Exception {
        final String htmlContent = "<html>\n"
            + "<head><title>foo</title></head>\n"
            + "<body>\n"
            + "<a id='a' tabindex='1'>foo</a>\n"
            + "<a id='b'>foo</a>\n"
            + "<form>\n"
            + "<a id='c' tabindex='3'>foo</a>\n"
            + "<a id='d' tabindex='2'>foo</a>\n"
            + "<a id='e' tabindex='0'>foo</a>\n"
            + "</form>\n"
            + "<a id='f' tabindex='3'>foo</a>\n"
            + "<a id='g' tabindex='1'>foo</a>\n"
            + "<a id='q' tabindex='-1'>foo</a>\n"
            + "<form><p>hello world</p>\n"
            + "<input name='foo' type='submit' disabled='disabled' id='foo'/>\n"
            + "<input name='bar' type='submit' id='bar'/>\n"
            + "</form></body></html>";
        final HtmlPage page = loadPage(htmlContent);

        final List<HtmlElement> expectedElements = Arrays.asList(new HtmlElement[] {page.getHtmlElementById("a"),
                page.getHtmlElementById("g"), page.getHtmlElementById("d"),
                page.getHtmlElementById("c"), page.getHtmlElementById("f"),
                page.getHtmlElementById("e"), page.getHtmlElementById("b"),
                page.getHtmlElementById("bar")});

        assertEquals(expectedElements, page.getTabbableElements());

        final String[] expectedIds = {"a", "g", "d", "c", "f", "e", "b", "bar"};
        assertEquals(expectedIds, page.getTabbableElementIds());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testGetHtmlElementByAccessKey() throws Exception {
        final String htmlContent = "<html>\n"
            + "<head><title>foo</title></head>\n"
            + "<body>\n"
            + "<a id='a' accesskey='a'>foo</a>\n"
            + "<a id='b'>foo</a>\n"
            + "<form>\n"
            + "<a id='c' accesskey='c'>foo</a>\n"
            + "</form>\n"
            + "<form><p>hello world</p>\n"
            + "<input name='foo' type='submit' disabled='disabled' id='foo' accesskey='f'/>\n"
            + "<input name='bar' type='submit' id='bar'/>\n"
            + "</form></body></html>";
        final HtmlPage page = loadPage(htmlContent);

        assertEquals(page.getHtmlElementById("a"), page.getHtmlElementByAccessKey('A'));
        assertEquals(page.getHtmlElementById("c"), page.getHtmlElementByAccessKey('c'));
        assertNull(page.getHtmlElementByAccessKey('z'));
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testGetHtmlElementsByAccessKey() throws Exception {
        final String htmlContent = "<html>\n"
            + "<head><title>foo</title></head><body>\n"
            + "<a id='a' accesskey='a'>foo</a>\n"
            + "<a id='b' accesskey='a'>foo</a>\n"
            + "<form>\n"
            + "<a id='c' accesskey='c'>foo</a>\n"
            + "</form></body></html>";
        final HtmlPage page = loadPage(htmlContent);

        final List<HtmlElement> expectedElements = Arrays.asList(new HtmlElement[] {page.getHtmlElementById("a"),
                page.getHtmlElementById("b")});
        final List<HtmlElement> collectedElements = page.getHtmlElementsByAccessKey('a');
        assertEquals(expectedElements, collectedElements);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testGetFullQualifiedUrl_NoBaseSpecified() throws Exception {
        final String htmlContent = "<html><head><title>foo</title></head><body>\n"
            + "<form id='form1'>\n"
            + "<table><tr><td><input type='text' id='foo'/></td></tr></table>\n"
            + "</form></body></html>";
        final WebClient client = new WebClient();

        final MockWebConnection webConnection = new MockWebConnection(client);
        webConnection.setDefaultResponse(htmlContent);
        client.setWebConnection(webConnection);

        final String urlString = URL_GARGOYLE.toExternalForm();
        final HtmlPage page = (HtmlPage) client.getPage(URL_GARGOYLE);

        assertEquals(urlString, page.getFullyQualifiedUrl(""));
        assertEquals(urlString + "foo", page.getFullyQualifiedUrl("foo"));
        assertEquals("http://foo.com/bar", page.getFullyQualifiedUrl("http://foo.com/bar"));
        assertEquals("mailto:me@foo.com", page.getFullyQualifiedUrl("mailto:me@foo.com"));

        assertEquals(urlString + "foo", page.getFullyQualifiedUrl("foo"));
        assertEquals(urlString + "bbb", page.getFullyQualifiedUrl("aaa/../bbb"));
        assertEquals(urlString + "c/d", page.getFullyQualifiedUrl("c/./d"));

        final HtmlPage secondPage = (HtmlPage) client.getPage(urlString + "foo/bar?a=b&c=d");
        assertEquals(urlString + "foo/bar?a=b&c=d", secondPage.getFullyQualifiedUrl(""));
        assertEquals(urlString + "foo/one", secondPage.getFullyQualifiedUrl("one"));
        assertEquals(urlString + "two", secondPage.getFullyQualifiedUrl("/two"));
        assertEquals(urlString + "foo/two?a=b", secondPage.getFullyQualifiedUrl("two?a=b"));

        final HtmlPage thirdPage = (HtmlPage) client.getPage("http://foo.com/dog/cat/one.html");
        assertEquals("http://foo.com/dog/cat/one.html", thirdPage.getFullyQualifiedUrl(""));
        assertEquals("http://foo.com/dog/cat/two.html", thirdPage.getFullyQualifiedUrl("two.html"));
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testGetFullQualifiedUrl_WithBase() throws Exception {
        testGetFullQualifiedUrl_WithBase("http", "");
        testGetFullQualifiedUrl_WithBase("http", ":8080");
        testGetFullQualifiedUrl_WithBase("https", "");
        testGetFullQualifiedUrl_WithBase("https", ":2005");
    }

    /**
     * @throws Exception if the test fails
     */
    private void testGetFullQualifiedUrl_WithBase(final String baseProtocol, final String basePortPart)
        throws Exception {

        final String baseUrl = baseProtocol + "://second" + basePortPart;
        final String htmlContent = "<html><head><title>foo</title>\n"
            + "<base href='" + baseUrl + "'>\n"
            + "</head><body>\n"
            + "<form id='form1'>\n"
            + "<table><tr><td><input type='text' id='foo'/></td></tr></table>\n"
            + "</form></body></html>";
        final HtmlPage page = loadPage(htmlContent);

        assertEquals(baseUrl, page.getFullyQualifiedUrl(""));
        assertEquals(baseUrl + "/foo", page.getFullyQualifiedUrl("foo"));
        assertEquals(baseUrl + "/foo.js", page.getFullyQualifiedUrl("/foo.js"));
        assertEquals("http://foo.com/bar", page.getFullyQualifiedUrl("http://foo.com/bar"));
        assertEquals("mailto:me@foo.com", page.getFullyQualifiedUrl("mailto:me@foo.com"));

        assertEquals(baseUrl + "/bbb", page.getFullyQualifiedUrl("aaa/../bbb"));
        assertEquals(baseUrl + "/c/d", page.getFullyQualifiedUrl("c/./d"));
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testGetFullQualifiedUrl_WithInvalidBase() throws Exception {
        final String htmlContent = "<html><head><base href='---****://=='/></head></html>";
        final HtmlPage page = loadPage(htmlContent);

        // invalid base URL should be ignored
        assertEquals("http://somewhere.com/", page.getFullyQualifiedUrl("http://somewhere.com/"));
        assertEquals(page.getWebResponse().getUrl().toExternalForm() + "foo.html",
                page.getFullyQualifiedUrl("foo.html"));
    }

    /**
     * @throws Exception If an error occurs.
     */
    @Test
    public void testBase_Multiple() throws Exception {
        final String html = "<html>\n"
            + "<head>\n"
            + "<base href='" + URL_SECOND + "'>\n"
            + "<base href='" + URL_THIRD + "'>\n"
            + "</head>\n"
            + "<body>\n"
            + "  <a href='somepage.html'>\n"
            + "</body></html>";

        final WebClient webClient = new WebClient();
        final List<String> collectedIncorrectness = new ArrayList<String>();
        final IncorrectnessListener listener = new IncorrectnessListener() {
            @Test
            public void notify(final String message, final Object origin) {
                collectedIncorrectness.add(message);
            }
        };
        webClient.setIncorrectnessListener(listener);
        
        final MockWebConnection webConnection = new MockWebConnection(webClient);
        webClient.setWebConnection(webConnection);
        webConnection.setDefaultResponse(html);
        final HtmlPage page = (HtmlPage) webClient.getPage(URL_FIRST);
        page.getAnchors().get(0).click();
        
        final String[] expectedIncorrectness = {"Multiple 'base' detected, only the first is used."};
        assertEquals(expectedIncorrectness, collectedIncorrectness);
    }

    /**
     * @throws Exception If an error occurs.
     */
    @Test
    public void testBase_InsideBody() throws Exception {
        final String html = "<html>\n"
            + "<head>\n"
            + "</head>\n"
            + "<body>\n"
            + "  <base href='" + URL_SECOND + "'>\n"
            + "  <a href='somepage.html'>\n"
            + "</body></html>";

        final WebClient webClient = new WebClient();
        final List<String> collectedIncorrectness = new ArrayList<String>();
        final IncorrectnessListener listener = new IncorrectnessListener() {
            @Test
            public void notify(final String message, final Object origin) {
                collectedIncorrectness.add(message);
            }
        };
        webClient.setIncorrectnessListener(listener);
        
        final MockWebConnection webConnection = new MockWebConnection(webClient);
        webClient.setWebConnection(webConnection);
        webConnection.setDefaultResponse(html);
        final HtmlPage page = (HtmlPage) webClient.getPage(URL_FIRST);
        final HtmlAnchor anchor = (HtmlAnchor) page.getAnchors().get(0);
        final HtmlPage secondPage = (HtmlPage) anchor.click();
        
        final String[] expectedIncorrectness = {
            "Element 'base' must appear in <head>, it is ignored."
        };
        assertEquals(expectedIncorrectness, collectedIncorrectness);
        assertEquals(URL_FIRST + "/somepage.html", secondPage.getWebResponse().getUrl());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testOnLoadHandler_BodyStatement() throws Exception {
        final String htmlContent = "<html><head><title>foo</title>\n"
            + "</head><body onLoad='alert(\"foo\")'>\n"
            + "</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(htmlContent, collectedAlerts);
        assertEquals("foo", page.getTitleText());

        final String[] expectedAlerts = {"foo"};
        assertEquals(expectedAlerts, collectedAlerts);
    }

    /**
     * If the onload handler contains two statements then only the first would execute.
     * @throws Exception if the test fails
     */
    @Test
    public void testOnLoadHandler_TwoBodyStatements() throws Exception {
        final String htmlContent = "<html><head><title>foo</title>\n"
            + "</head><body onLoad='alert(\"foo\");alert(\"bar\")'>\n"
            + "</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(htmlContent, collectedAlerts);
        assertEquals("foo", page.getTitleText());

        final String[] expectedAlerts = {"foo", "bar"};
        assertEquals(expectedAlerts, collectedAlerts);
    }

    /**
     * Regression test for bug 713646.
     * @throws Exception if the test fails
     */
    @Test
    public void testOnLoadHandler_BodyName() throws Exception {
        final String htmlContent = "<html><head><title>foo</title>\n"
            + "<script type='text/javascript'>\n"
            + "window.onload=function(){alert('foo')}</script>\n"
            + "</head><body></body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(htmlContent, collectedAlerts);
        assertEquals("foo", page.getTitleText());

        final String[] expectedAlerts = {"foo"};
        assertEquals(expectedAlerts, collectedAlerts);
    }

    /**
     * Regression test for bug 713646.
     * @throws Exception if the test fails
     */
    @Test
    public void testOnLoadHandler_BodyName_NotAFunction() throws Exception {
        final String htmlContent = "<html><head><title>foo</title></head>\n"
            + "<body onLoad='foo=4711'>\n"
            + "<a name='alert' href='javascript:alert(foo)'/>\n"
            + "</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(htmlContent, collectedAlerts);
        assertEquals("foo", page.getTitleText());

        page.getAnchorByName("alert").click();

        final String[] expectedAlerts = {"4711"};
        assertEquals(expectedAlerts, collectedAlerts);
    }

    /**
     * Regression test for window.onload property.
     * @throws Exception if the test fails
     */
    @Test
    public void testOnLoadHandler_ScriptName() throws Exception {
        final String htmlContent = "<html><head><title>foo</title>\n"
            + "<script type='text/javascript'>\n"
            + "load=function(){alert('foo')};\n"
            + "onload=load\n"
            + "</script></head><body></body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(htmlContent, collectedAlerts);
        assertEquals("foo", page.getTitleText());

        final String[] expectedAlerts = {"foo"};
        assertEquals(expectedAlerts, collectedAlerts);
    }

    /**
     * Regression test for window.onload property.
     * @throws Exception if the test fails
     */
    @Test
    public void testOnLoadHandler_ScriptNameRead() throws Exception {
        final String htmlContent = "<html><head><title>foo</title>\n"
            + "<script type='text/javascript'>\n"
            + "load=function(){};\n"
            + "onload=load;\n"
            + "alert(onload);\n"
            + "</script></head><body></body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(htmlContent, collectedAlerts);
        assertEquals("foo", page.getTitleText());

        final String[] expectedAlerts = {"\nfunction () {\n}\n"};
        assertEquals(expectedAlerts, collectedAlerts);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testEmbeddedMetaTag_Regression() throws Exception {
        final String htmlContent = "<html><head><title>foo</title>\n"
            + "</head><body>\n"
            + "<table><tr><td>\n"
            + "<meta name=vs_targetSchema content=\"http://schemas.microsoft.com/intellisense/ie5\">\n"
            + "<form name='form1'>\n"
            + "    <input type='text' name='textfield1' id='textfield1' value='foo' />\n"
            + "    <input type='text' name='textfield2' id='textfield2'/>\n"
            + "</form>\n"
            + "</td></tr></table>\n"
            + "</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();

        // This used to blow up on page load
        final HtmlPage page = loadPage(htmlContent, collectedAlerts);
        assertEquals("foo", page.getTitleText());

        final List< ? > expectedAlerts = Collections.EMPTY_LIST;
        assertEquals(expectedAlerts, collectedAlerts);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testGetPageEncoding() throws Exception {
        final String htmlContent = "<html><head>\n"
            + "<title>foo</title>\n"
            + "<meta http-equiv='Content-Type' content='text/html ;charset=Shift_JIS'>\n"
            + "</head><body>\n"
            + "<table><tr><td>\n"
            + "<meta name=vs_targetSchema content=\"http://schemas.microsoft.com/intellisense/ie5\">\n"
            + "<form name='form1'>\n"
            + "    <input type='text' name='textfield1' id='textfield1' value='foo' />\n"
            + "    <input type='text' name='textfield2' id='textfield2'/>\n"
            + "</form>\n"
            + "</td></tr></table>\n"
            + "</body></html>";
        final HtmlPage page = loadPage(htmlContent);

        assertEquals("Shift_JIS", page.getPageEncoding());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testGetForms() throws Exception {
        final String htmlContent = "<html>\n"
            + "<head><title>foo</title></head>\n"
            + "<body>\n"
            + "<form name='one'>\n"
            + "<a id='c' accesskey='c'>foo</a>\n"
            + "</form>\n"
            + "<form name='two'>\n"
            + "<a id='c' accesskey='c'>foo</a>\n"
            + "</form>\n"
            + "<input name='foo' type='submit' disabled='disabled' id='foo' accesskey='f'/>\n"
            + "<input name='bar' type='submit' id='bar'/>\n"
            + "</form></body></html>";

        final HtmlPage page = loadPage(htmlContent);

        final List<HtmlForm> expectedForms = Arrays.asList(new HtmlForm[] {page.getFormByName("one"),
                page.getFormByName("two")});
        assertEquals(expectedForms, page.getForms());
    }

    /**
     * Test auto-refresh from a meta tag.
     * @throws Exception if the test fails
     */
    @Test
    public void testRefresh_MetaTag_DefaultRefreshHandler() throws Exception {
        final String firstContent = "<html><head><title>first</title>\n"
            + "<META HTTP-EQUIV=\"Refresh\" CONTENT=\"3;URL=" + URL_SECOND + "\">\n"
            + "</head><body></body></html>";
        final String secondContent = "<html><head><title>second</title></head><body></body></html>";

        final WebClient client = new WebClient();

        final MockWebConnection webConnection = new MockWebConnection(client);
        webConnection.setResponse(URL_FIRST, firstContent);
        webConnection.setResponse(URL_SECOND, secondContent);
        client.setWebConnection(webConnection);

        final HtmlPage page = (HtmlPage) client.getPage(URL_FIRST);

        assertEquals("second", page.getTitleText());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testRefresh_MetaTag_caseSensitivity() throws Exception {
        final String firstContent = "<html><head><title>first</title>\n"
            + "<META HTTP-EQUIV=\"Refresh\" CONTENT=\"3;Url=" + URL_SECOND + "\">\n"
            + "</head><body></body></html>";
        final String secondContent = "<html><head><title>second</title></head><body></body></html>";

        final WebClient client = new WebClient();

        final MockWebConnection webConnection = new MockWebConnection(client);
        webConnection.setResponse(URL_FIRST, firstContent);
        webConnection.setResponse(URL_SECOND, secondContent);
        client.setWebConnection(webConnection);

        final HtmlPage page = (HtmlPage) client.getPage(URL_FIRST);

        assertEquals("second", page.getTitleText());
    }

    /**
     * Test auto-refresh from a meta tag with no URL.
     * @throws Exception if the test fails
     */
    @Test
    public void testRefresh_MetaTag_NoUrl() throws Exception {
        final String firstContent = "<html><head><title>first</title>\n"
            + "<META HTTP-EQUIV=\"Refresh\" CONTENT=\"1\">\n"
            + "</head><body></body></html>";

        final WebClient client = new WebClient();
        final List<Object> collectedItems = new ArrayList<Object>();
        client.setRefreshHandler(new LoggingRefreshHandler(collectedItems));

        final MockWebConnection webConnection = new MockWebConnection(client);
        webConnection.setResponse(URL_FIRST, firstContent);
        client.setWebConnection(webConnection);

        client.getPage(URL_FIRST);

        // avoid using equals() on URL because it takes to much time (due to ip resolution)
        assertEquals("first", collectedItems.get(0));
        assertEquals(URL_FIRST, (URL) collectedItems.get(1));
        assertEquals(new Integer(1), collectedItems.get(2));
    }

    /**
     * Ensures that if a page is supposed to refresh itself every certain amount of
     * time, and the ImmediateRefreshHandler is being used, an OOME is avoided by
     * not performing the refresh.
     * @throws Exception if the test fails
     */
    @Test
    public void testRefresh_ImmediateRefresh_AvoidOOME() throws Exception {
        final String firstContent = "<html><head><title>first</title>\n"
            + "<META HTTP-EQUIV=\"Refresh\" CONTENT=\"1\">\n"
            + "</head><body></body></html>";

        final WebClient client = new WebClient();
        assertTrue(ImmediateRefreshHandler.class.isInstance(client.getRefreshHandler()));
        try {
            loadPage(firstContent);
            fail("should have thrown");
        }
        catch (final RuntimeException e) {
            assertTrue(e.getMessage().indexOf("could have caused an OutOfMemoryError") > -1);
        }
        Thread.sleep(1000);
    }

    /**
     * Test auto-refresh from a meta tag with URL quoted.
     * @throws Exception if the test fails
     */
    @Test
    public void testRefresh_MetaTagQuoted() throws Exception {
        final String firstContent = "<html><head><title>first</title>\n"
            + "<META HTTP-EQUIV='Refresh' CONTENT='0;URL=\"" + URL_SECOND + "\"'>\n"
            + "</head><body></body></html>";
        final String secondContent = "<html><head><title>second</title></head><body></body></html>";

        final WebClient client = new WebClient();

        final MockWebConnection webConnection = new MockWebConnection(client);
        webConnection.setResponse(URL_FIRST, firstContent);
        webConnection.setResponse(URL_SECOND, secondContent);
        client.setWebConnection(webConnection);

        final HtmlPage page = (HtmlPage) client.getPage(URL_FIRST);

        assertEquals("second", page.getTitleText());
    }

    /**
     * Test auto-refresh from a meta tag with URL partly quoted.
     * @throws Exception if the test fails
     */
    @Test
    public void testRefresh_MetaTagPartlyQuoted() throws Exception {
        final String firstContent = "<html><head><title>first</title>\n"
            + "<META HTTP-EQUIV='Refresh' CONTENT=\"0;URL='" + URL_SECOND + "\">\n"
            + "</head><body></body></html>";
        final String secondContent = "<html><head><title>second</title></head><body></body></html>";

        final WebClient client = new WebClient();

        final MockWebConnection webConnection = new MockWebConnection(client);
        webConnection.setResponse(URL_FIRST, firstContent);
        webConnection.setResponse(URL_SECOND, secondContent);
        client.setWebConnection(webConnection);

        final HtmlPage page = (HtmlPage) client.getPage(URL_FIRST);

        assertEquals("second", page.getTitleText());
    }

    /**
     * Test auto-refresh from a meta tag inside noscript.
     * @throws Exception if the test fails
     */
    @Test
    public void testRefresh_MetaTagNoScript() throws Exception {
        final String firstContent = "<html><head><title>first</title>\n"
            + "<noscript>\n"
            + "<META HTTP-EQUIV=\"Refresh\" CONTENT=\"0;URL=" + URL_SECOND + "\">\n"
            + "</noscript>\n"
            + "</head><body></body></html>";
        final String secondContent = "<html><head><title>second</title></head><body></body></html>";

        final WebClient client = new WebClient();

        final MockWebConnection webConnection = new MockWebConnection(client);
        webConnection.setResponse(URL_FIRST, firstContent);
        webConnection.setResponse(URL_SECOND, secondContent);
        client.setWebConnection(webConnection);

        HtmlPage page = (HtmlPage) client.getPage(URL_FIRST);
        assertEquals("first", page.getTitleText());

        client.setJavaScriptEnabled(false);
        page = (HtmlPage) client.getPage(URL_FIRST);
        assertEquals("second", page.getTitleText());
    }

    /**
     * Test auto-refresh from a meta tag with a refresh handler that doesn't refresh.
     * @throws Exception if the test fails
     */
    @Test
    public void testRefresh_MetaTag_CustomRefreshHandler() throws Exception {
        final String firstContent = "<html><head><title>first</title>\n"
            + "<META HTTP-EQUIV=\"Refresh\" CONTENT=\"3;URL=" + URL_SECOND + "\">\n"
            + "</head><body></body></html>";
        final String secondContent = "<html><head><title>second</title></head><body></body></html>";

        final WebClient client = new WebClient();
        final List<Object> collectedItems = new ArrayList<Object>();
        client.setRefreshHandler(new LoggingRefreshHandler(collectedItems));

        final MockWebConnection webConnection = new MockWebConnection(client);
        webConnection.setResponse(URL_FIRST, firstContent);
        webConnection.setResponse(URL_SECOND, secondContent);
        client.setWebConnection(webConnection);

        final HtmlPage page = (HtmlPage) client.getPage(URL_FIRST);

        assertEquals("first", page.getTitleText());

        // avoid using equals() on URL because it takes to much time (due to ip resolution)
        assertEquals("first", collectedItems.get(0));
        assertEquals(URL_SECOND, ((URL) collectedItems.get(1)));
        assertEquals(new Integer(3), collectedItems.get(2));
    }

    /**
     * Test that whitespace before and after ';' is permitted.
     *
     * @throws Exception if the test fails
     */
    @Test
    public void testRefresh_MetaTag_Whitespace() throws Exception {
        final String firstContent = "<html><head><title>first</title>\n"
            + "<META HTTP-EQUIV='Refresh' CONTENT='0  ;  URL=" + URL_SECOND + "'>\n"
            + "</head><body></body></html>";
        final String secondContent = "<html><head><title>second</title></head><body></body></html>";

        final WebClient client = new WebClient();

        final MockWebConnection webConnection = new MockWebConnection(client);
        webConnection.setResponse(URL_FIRST, firstContent);
        webConnection.setResponse(URL_SECOND, secondContent);
        client.setWebConnection(webConnection);

        final HtmlPage page = (HtmlPage) client.getPage(URL_FIRST);

        assertEquals("second", page.getTitleText());
    }

    /**
     * Test auto-refresh from a response header.
     * @throws Exception if the test fails
     */
    @Test
    public void testRefresh_HttpResponseHeader() throws Exception {
        final String firstContent = "<html><head><title>first</title>\n"
            + "</head><body></body></html>";
        final String secondContent = "<html><head><title>second</title></head><body></body></html>";

        final WebClient client = new WebClient();

        final MockWebConnection webConnection = new MockWebConnection(client);
        webConnection.setResponse(URL_FIRST, firstContent, 200, "OK", "text/html", Collections
                .singletonList(new NameValuePair("Refresh", "3;URL=" + URL_SECOND + "")));
        webConnection.setResponse(URL_SECOND, secondContent);
        client.setWebConnection(webConnection);

        final HtmlPage page = (HtmlPage) client.getPage(URL_FIRST);

        assertEquals("second", page.getTitleText());
    }

    /**
     * Test that the parent of the DOM Document (HtmlPage) is null.
     * @throws Exception if the test fails
     */
    @Test
    public void testDocumentParentIsNull() throws Exception {
        final String htmlContent = "<html>\n"
            + "<head><title>foo</title></head>\n"
            + "<body>\n"
            + "</body></html>";
        final HtmlPage page = loadPage(htmlContent);

        assertNotNull(page);
        assertNull(page.getParentDomNode());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testDocumentElement() throws Exception {
        final String htmlContent = "<html>\n"
            + "<head><title>foo</title></head>\n"
            + "<body>\n"
            + "</body></html>";
        final HtmlPage page = loadPage(htmlContent);

        final HtmlElement root = page.getDocumentHtmlElement();

        assertNotNull(root);
        assertEquals("html", root.getTagName());
        assertSame(page, root.getParentDomNode());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testDocumentNodeType() throws Exception {
        final String htmlContent = "<html>\n"
            + "<head><title>foo</title></head>\n"
            + "<body>\n"
            + "</body></html>";
        final HtmlPage page = loadPage(htmlContent);

        final HtmlElement root = page.getDocumentHtmlElement();

        assertEquals(org.w3c.dom.Node.DOCUMENT_NODE, page.getNodeType());
        assertEquals(org.w3c.dom.Node.ELEMENT_NODE, root.getNodeType());
        assertEquals("#document", page.getNodeName());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testDeregisterFrameWithoutSrc() throws Exception {
        final String htmlContent = "<html>\n"
            + "<head><title>foo</title></head>\n"
            + "<body>\n"
            + "<iframe />\n"
            + "<a href='about:blank'>link</a>\n"
            + "</body></html>";

        final HtmlPage page = loadPage(htmlContent);
        final HtmlAnchor link = (HtmlAnchor) page.getAnchors().get(0);
        link.click();
    }

    /**
     * Tests that a return statement in onload doesn't throw any exception.
     * @throws Exception if the test fails
     */
    @Test
    public void testOnLoadReturn() throws Exception {
        final String htmlContent = "<html><head><title>foo</title></head>\n"
            + "<body onload='return true'>\n"
            + "</body></html>";

        loadPage(htmlContent);
    }

    /**
     * Tests that wrong formed HTML code is parsed like browsers do.
     * @throws Exception if the test fails
     */
    @Test
    public void testWrongHtml_TagBeforeHtml() throws Exception {
        if (notYetImplemented()) {
            return;
        }

        final String htmlContent = "<div>\n"
            + "<html>\n"
            + "<head><title>foo</title>\n"
            + "<script>\n"
            + "var toto = 12345;\n"
            + "</script>\n"
            + "</head>\n"
            + "<body onload='alert(toto)'>\n"
            + "blabla"
            + "</body>\n"
            + "</html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(htmlContent, collectedAlerts);
        final String[] expectedAlerts = {"12345"};
        createTestPageForRealBrowserIfNeeded(htmlContent, expectedAlerts);

        assertEquals("foo", page.getTitleText());

        assertEquals(expectedAlerts, collectedAlerts);
    }

    /**
     * @exception Exception If the test fails
     */
    @Test
    public void testAsXml() throws Exception {
        final String htmlContent = "<html><head><title>foo</title></head>"
            + "<body><p>helloworld</p></body>"
            + "</html>";

        final HtmlPage page = loadPage(htmlContent);
        assertEquals(htmlContent, page.asXml().replaceAll("\\s", ""));
    }
        
    /**
     * Tests that the generated XML is valid as HTML code too.
     * @exception Exception If the test fails
     */
    @Test
    public void testAsXmlValidHtmlOutput() throws Exception {
        final String html =
            "<html><head><title>foo</title>"
            + "<script src='script.js'></script></head>"
            + "<body><div></div><iframe src='about:blank'></iframe></body>"
            + "</html>";

        final WebClient client = new WebClient();
        final MockWebConnection webConnection = new MockWebConnection(client);
        webConnection.setDefaultResponse(html);
        webConnection.setResponse(new URL(URL_FIRST, "script.js"), "", "text/javascript");
        client.setWebConnection(webConnection);

        final HtmlPage page = (HtmlPage) client.getPage(URL_FIRST);

        assertEquals(html, page.asXml()
                .replaceAll("[\\n\\r]", "")
                .replaceAll("\\s\\s+", "")
                .replaceAll("\"", "'"));
    }

    /**
     * @exception Exception If the test fails
     */
    @Test
    public void testAsXml2() throws Exception {
        final String htmlContent = "<html><head><title>foo</title>\n"
            + "<script>var test = 15 < 16;</script></head>\n"
            + "</head>\n"
            + "<body onload='test=(1 > 2) && (45 < 78)'><p>helloworld &amp;amp; helloall</p></body>\n"
            + "</html>";

        final HtmlPage page = loadPage(htmlContent);
        assertNotNull("xml document could not be parsed", page.asXml());
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        builder.parse(TextUtil.toInputStream(page.asXml()));
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testAsXml_unicode() throws Exception {
        final String unicodeString = "\u064A\u0627 \u0644\u064A\u064A\u0644";
        final String html = "<html>\n"
            + "<head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'></head>\n"
            + "<body><span id='foo'>" + unicodeString + "</span></body></html>";

        final WebClient client = new WebClient(BrowserVersion.getDefault());
        final MockWebConnection webConnection = new MockWebConnection(client);
        
        webConnection.setDefaultResponse(TextUtil.stringToByteArray(html, "UTF-8"), 200, "OK", "text/html");
        client.setWebConnection(webConnection);

        final HtmlPage page = (HtmlPage) client.getPage(URL_GARGOYLE);
        final String xml = page.asXml();
        assertTrue(xml.contains("<?xml "));
        assertTrue(xml.contains(unicodeString));
    }

    /**
     * Regression test for bug 1204637.
     * @exception Exception If the test fails
     */
    @Test
    public void testIsJavascript() throws Exception {
        assertTrue(HtmlPage.isJavaScript("text/javascript", null));
        assertTrue(HtmlPage.isJavaScript("text/JavaScript", null));
    }

    /**
     * @exception Exception if the test fails
     */
    @Test
    public void testGetHtmlElementsByName() throws Exception {
        final String html = "<html><body><div name='a'>foo</div><div name='b'/><div name='b'/></body></html>";
        final HtmlPage page = loadPage(html);
        assertEquals(1, page.getHtmlElementsByName("a").size());
        assertEquals(2, page.getHtmlElementsByName("b").size());
        assertEquals(0, page.getHtmlElementsByName("c").size());

        final HtmlElement a = page.getHtmlElementsByName("a").get(0);
        a.remove();
        assertEquals(0, page.getHtmlElementsByName("a").size());

        final HtmlElement b1 = page.getHtmlElementsByName("b").get(0);
        b1.appendDomChild(a);
        assertEquals(1, page.getHtmlElementsByName("a").size());
    }

    /**
     * Regression test for bug 1233519.
     * @exception Exception If the test fails
     */
    @Test
    public void testGetHtmlElementByIdAfterRemove() throws Exception {
        final String htmlContent
            = "<html><head><title>foo</title></head>\n"
            + "<body>\n"
            + "<div id='div1'>\n"
            + "<div id='div2'>\n"
            + "</div>\n"
            + "</div>\n"
            + "</body>\n"
            + "</html>";

        final HtmlPage page = loadPage(htmlContent);
        final HtmlElement div1 = page.getHtmlElementById("div1");
        page.getHtmlElementById("div2"); // would throw if not found
        div1.remove();
        try {
            page.getHtmlElementById("div1"); // throws if not found
            fail("div1 should have been removed");
        }
        catch (final ElementNotFoundException e) {
            // nothing
        }

        try {
            page.getHtmlElementById("div2"); // throws if not found
            fail("div2 should have been removed");
        }
        catch (final ElementNotFoundException e) {
            // nothing
        }
    }

    /**
     * Test getHtmlElementById() when 2 elements have the same id and the first one
     * is removed.
     * @exception Exception If the test fails
     */
    @Test
    public void testGetHtmlElementById_idTwice() throws Exception {
        final String htmlContent
            = "<html><head><title>foo</title></head>\n"
            + "<body>\n"
            + "<div id='id1'>foo</div>\n"
            + "<span id='id1'>bla</span>\n"
            + "</body>\n"
            + "</html>";

        final HtmlPage page = loadPage(htmlContent);
        final HtmlElement elt1 = page.getHtmlElementById("id1");
        assertEquals("div", elt1.getNodeName());
        elt1.remove();
        assertEquals("span", page.getHtmlElementById("id1").getNodeName());
    }

    /**
     * Test the "set-cookie" meta tag.
     * @throws Exception if the test fails
     */
    @Test
    public void testSetCookieMetaTag() throws Exception {
        final String content = "<html><head><title>first</title>\n"
            + "<meta http-equiv='set-cookie' content='webm=none; path=/;'>\n"
            + "</head><body>\n"
            + "<script>alert(document.cookie)</script>\n"
            + "</body></html>";

        final String[] expectedAlerts = {"webm=none"};
        createTestPageForRealBrowserIfNeeded(content, expectedAlerts);

        final List<String> collectedAlerts = new ArrayList<String>();
        final HtmlPage page = loadPage(content, collectedAlerts);
        assertEquals(expectedAlerts, collectedAlerts);
        
        final Cookie[] cookies = page.getWebClient().getWebConnection().getState().getCookies();
        assertEquals(1, cookies.length);
        final Cookie cookie = cookies[0];
        assertEquals(page.getWebResponse().getUrl().getHost(), cookie.getDomain());
        assertEquals("webm", cookie.getName());
        assertEquals("none", cookie.getValue());
        assertEquals("/", cookie.getPath());
    }
    
    /**
     * Regression test for bug 1658273.
     * @throws Exception if the test fails
     */
    @Test
    public void testOnLoadHandler_idChange() throws Exception {
        final String content = "<html><head><title>foo</title>\n"
            + "<div id='id1' class='cl1'><div id='id2' class='cl2'></div></div>'"
            + "<script type='text/javascript'>\n"
            + "document.getElementById('id1').id = 'id3';\n"
            + "alert(document.getElementById('id2').className);\n"
            + "alert(document.getElementById('id3').className);\n"
            + "</script>\n"
            + "</head><body></body></html>";
        
        final String[] expectedAlerts = {"cl2", "cl1"};
        createTestPageForRealBrowserIfNeeded(content, expectedAlerts);

        final List<String> collectedAlerts = new ArrayList<String>();
        loadPage(content, collectedAlerts);

        assertEquals(expectedAlerts, collectedAlerts);
    }
    
    /**
     * Test for bug 1714767.
     * @throws Exception if the test fails
     */
    @Test
    public void testNoSlashURL() throws Exception {
        testNoSlashURL("http:/second");
        testNoSlashURL("http:second");
    }

    private void testNoSlashURL(final String url) throws Exception {
        final String firstContent
            = "<html><body>\n"
            + "<iframe id='myIFrame' src='" + url + "'></iframe>\n"
            + "</body></html>";
    
        final String secondContent
            = "<html><body></body></html>";
        final WebClient client = new WebClient(BrowserVersion.FIREFOX_2);

        final MockWebConnection webConnection = new MockWebConnection(client);
        webConnection.setResponse(URL_FIRST, firstContent);
        webConnection.setResponse(URL_SECOND, secondContent);

        client.setWebConnection(webConnection);
    
        final HtmlPage firstPage = (HtmlPage) client.getPage(URL_FIRST);
        final HtmlInlineFrame iframe = (HtmlInlineFrame) firstPage.getHtmlElementById("myIFrame");
        
        assertEquals(URL_SECOND, iframe.getEnclosedPage().getWebResponse().getUrl());
    }

    /**
     * @throws Exception failure
     */
    @Test
    public void testMetaTagWithEmptyURL() throws Exception {
        final WebClient client = new WebClient();
        client.setRefreshHandler(new ImmediateRefreshHandler());
        
        // connection will return a page with <meta ... refresh> for the first call
        // and the same page without it for the other calls
        final MockWebConnection webConnection = new MockWebConnection(client) {
            private int nbCalls_ = 0;
            @Override
            public WebResponse getResponse(final WebRequestSettings settings) throws IOException {
                String content = "<html><head>\n";
                if (nbCalls_ == 0) {
                    content += "<meta http-equiv='refresh' content='1; URL='>\n";
                }
                content += "</head><body></body></html>";
                nbCalls_++;
                return new StringWebResponse(content, settings.getURL()) {
                    private static final long serialVersionUID = 4945986137562358686L;

                    @Override
                    public SubmitMethod getRequestMethod() {
                        return settings.getSubmitMethod();
                    }
                };
            }
        };
        client.setWebConnection(webConnection);
        
        final WebRequestSettings settings = new WebRequestSettings(URL_GARGOYLE);
        settings.setSubmitMethod(SubmitMethod.POST);
        client.getPage(settings);
    }

    /**
     * @throws Exception If the test fails
     */
    @Test
    public void testSerialization() throws Exception {
        final String content = "<html><body>\n"
            + "<div id='myId'>Hello there!</div>\n"
            + "</body></html>";

        final HtmlPage page1 = loadPage(content);

        final ByteArrayOutputStream byteOS = new ByteArrayOutputStream();
        final ObjectOutputStream objectOS = new ObjectOutputStream(byteOS);
        objectOS.writeObject(page1);
        
        final ByteArrayInputStream byteIS = new ByteArrayInputStream(byteOS.toByteArray());
        final ObjectInputStream objectIS = new ObjectInputStream(byteIS);
        final HtmlPage page2 = (HtmlPage) objectIS.readObject();
        
        final Iterator<HtmlElement> iterator1 = page1.getAllHtmlChildElements().iterator();
        final Iterator<HtmlElement> iterator2 = page2.getAllHtmlChildElements().iterator();
        while (iterator1.hasNext()) {
            assertTrue(iterator2.hasNext());
            final HtmlElement element1 = iterator1.next();
            final HtmlElement element2 = iterator2.next();
            assertEquals(element1.getNodeName(), element2.getNodeName());
        }
        assertFalse(iterator2.hasNext());
        assertEquals("Hello there!", page2.getHtmlElementById("myId").getFirstDomChild().getNodeValue());
    }

    /**
     * Verifies that a cloned HtmlPage has its own "idMap_".
     * @throws Exception if the test fails
     */
    @Test
    public void testClonedPageHasOwnIdMap() throws Exception {
        final String content = "<html><head><title>foo</title>"
            + "<body>"
            + "<div id='id1' class='cl1'><div id='id2' class='cl2'></div></div>"
            + "</body></html>";
        
        final HtmlPage page = loadPage(content);
        final HtmlElement id1 = (HtmlElement) page.getDocumentHtmlElement().getLastDomChild().getLastDomChild();
        assertEquals("id1", id1.getId());
        assertSame(id1, page.getHtmlElementById("id1"));
        final HtmlPage clone = (HtmlPage) page.cloneDomNode(true);
        assertSame(id1, page.getHtmlElementById("id1"));
        final HtmlElement id1clone = (HtmlElement) clone.getDocumentHtmlElement().getLastDomChild().getLastDomChild();
        assertNotSame(id1, id1clone);
        assertEquals("id1", id1clone.getId());
        assertSame(id1clone, clone.getHtmlElementById("id1"));
        assertNotSame(id1clone, page.getHtmlElementById("id1"));
        assertNotSame(id1, clone.getHtmlElementById("id1"));

        page.getHtmlElementById("id2").remove();
        try {
            page.getHtmlElementById("id2");
            fail("should have thrown ElementNotFoundException");
        }
        catch (final ElementNotFoundException enfe) {
            // expected
        }
        assertNotNull(clone.getHtmlElementById("id2"));
    }

    /**
     * Verifies that a cloned HtmlPage has its own "documentElement".
     * @throws Exception if the test fails
     */
    @Test
    public void testClonedPageHasOwnDocumentElement() throws Exception {
        final String content = "<html><head><title>foo</title>\n"
            + "<body>\n"
            + "<div id='id1' class='cl1'><div id='id2' class='cl2'></div></div>\n"
            + "</body></html>";
        
        final HtmlPage page = loadPage(content);
        final HtmlPage clone = (HtmlPage) page.cloneDomNode(true);
        assertTrue(page != clone);
        final HtmlElement doc = page.getDocumentHtmlElement();
        final HtmlElement docclone = clone.getDocumentHtmlElement();
        assertTrue(doc != docclone);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testHtmlAttributeChangeListener_AddAttribute() throws Exception {
        final String htmlContent
            = "<html><head><title>foo</title>\n"
            + "<script>\n"
            + "  function clickMe() {\n"
            + "    var p1 = document.getElementById('p1');\n"
            + "    p1.setAttribute('title', 'myTitle');\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body>\n"
            + "<p id='p1'></p>\n"
            + "<input id='myButton' type='button' onclick='clickMe()'>\n"
            + "</body></html>";

        final String[] expectedValues = {"attributeAdded: p,title,myTitle"};
        final HtmlPage page = loadPage(htmlContent);
        final HtmlAttributeChangeListenerTestImpl listenerImpl = new HtmlAttributeChangeListenerTestImpl();
        page.addHtmlAttributeChangeListener(listenerImpl);
        final HtmlButtonInput myButton = (HtmlButtonInput) page.getHtmlElementById("myButton");
        
        myButton.click();
        assertEquals(expectedValues, listenerImpl.getCollectedValues());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testHtmlAttributeChangeListener_ReplaceAttribute() throws Exception {
        final String htmlContent
            = "<html><head><title>foo</title>\n"
            + "<script>\n"
            + "  function clickMe() {\n"
            + "    var p1 = document.getElementById('p1');\n"
            + "    p1.setAttribute('title', p1.getAttribute('title') + 'a');\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body>\n"
            + "<p id='p1' title='myTitle'></p>\n"
            + "<input id='myButton' type='button' onclick='clickMe()'>\n"
            + "</body></html>";
        
        final String[] expectedValues = {"attributeReplaced: p,title,myTitle"};
        final HtmlPage page = loadPage(htmlContent);
        final HtmlAttributeChangeListenerTestImpl listenerImpl = new HtmlAttributeChangeListenerTestImpl();
        page.addHtmlAttributeChangeListener(listenerImpl);
        final HtmlButtonInput myButton = (HtmlButtonInput) page.getHtmlElementById("myButton");
        
        myButton.click();
        assertEquals(expectedValues, listenerImpl.getCollectedValues());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testHtmlAttributeChangeListener_RemoveAttribute() throws Exception {
        final String htmlContent
            = "<html><head><title>foo</title>\n"
            + "<script>\n"
            + "  function clickMe() {\n"
            + "    var p1 = document.getElementById('p1');\n"
            + "    p1.removeAttribute('title');\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body>\n"
            + "<p id='p1' title='myTitle'></p>\n"
            + "<input id='myButton' type='button' onclick='clickMe()'>\n"
            + "</body></html>";
        
        final String[] expectedValues = {"attributeRemoved: p,title,myTitle"};
        final HtmlPage page = loadPage(htmlContent);
        final HtmlAttributeChangeListenerTestImpl listenerImpl = new HtmlAttributeChangeListenerTestImpl();
        page.addHtmlAttributeChangeListener(listenerImpl);
        final HtmlButtonInput myButton = (HtmlButtonInput) page.getHtmlElementById("myButton");
        
        myButton.click();
        assertEquals(expectedValues, listenerImpl.getCollectedValues());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testHtmlAttributeChangeListener_RemoveListener() throws Exception {
        final String htmlContent
            = "<html><head><title>foo</title>\n"
            + "<script>\n"
            + "  function clickMe() {\n"
            + "    var p1 = document.getElementById('p1');\n"
            + "    p1.setAttribute('title', p1.getAttribute('title') + 'a');\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body>\n"
            + "<p id='p1' title='myTitle'></p>\n"
            + "<input id='myButton' type='button' onclick='clickMe()'>\n"
            + "</body></html>";
        
        final String[] expectedValues = {"attributeReplaced: p,title,myTitle"};
        final HtmlPage page = loadPage(htmlContent);
        final HtmlAttributeChangeListenerTestImpl listenerImpl = new HtmlAttributeChangeListenerTestImpl();
        page.addHtmlAttributeChangeListener(listenerImpl);
        final HtmlButtonInput myButton = (HtmlButtonInput) page.getHtmlElementById("myButton");
        
        myButton.click();
        page.removeHtmlAttributeChangeListener(listenerImpl);
        myButton.click();
        assertEquals(expectedValues, listenerImpl.getCollectedValues());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testHtmlAttributeChangeListener_ListenerRegistersNewListener() throws Exception {
        final String htmlContent
            = "<html><head><title>foo</title>\n"
            + "</head>\n"
            + "<body>\n"
            + "<p id='p1' title='myTitle'></p>\n"
            + "</body></html>";

        final HtmlPage page = loadPage(htmlContent);

        final List<String> collector = new ArrayList<String>();
        final HtmlAttributeChangeListener listener2 = new HtmlAttributeChangeListenerTestImpl() {
            @Override
            @Test
            public void attributeReplaced(final HtmlAttributeChangeEvent event) {
                collector.add("in listener 2");
            }
        };
        final HtmlAttributeChangeListener listener1 = new HtmlAttributeChangeListenerTestImpl() {
            @Override
            @Test
            public void attributeReplaced(final HtmlAttributeChangeEvent event) {
                collector.add("in listener 1");
                page.addHtmlAttributeChangeListener(listener2);
            }
        };

        page.addHtmlAttributeChangeListener(listener1);

        final HtmlElement p = page.getHtmlElementById("p1");
        p.setAttributeValue("title", "new title");

        final String[] expectedValues = {"in listener 1"};
        assertEquals(expectedValues, collector);
        collector.clear();

        p.setAttributeValue("title", "new new title");
        final String[] expectedValues2 = {"in listener 1", "in listener 2"};
        assertEquals(expectedValues2, collector);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testCaseInsensitiveRegexReplacement() throws Exception {
        final String html = "<html><body><script>\n"
            + "var r = /^([#.]?)([a-z0-9\\*_-]*)/i;\n"
            + "var s = '#userAgent';\n"
            + "s = s.replace(r, '');\n"
            + "alert(s.length);\n"
            + "</script></body></html>";
        final String[] expectedAlerts = {"0"};
        final List<String> actualAlerts = new ArrayList<String>();
        loadPage(html, actualAlerts);
        assertEquals(expectedAlerts, actualAlerts);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testRegexReplacementWithFunction() throws Exception {
        final String html = "<html><body><script>\n"
            + "var r = /-([a-z])/ig;\n"
            + "var s = 'font-size';\n"
            + "s = s.replace(r, function(z,b){return b.toUpperCase();});\n"
            + "alert(s);\n"
            + "</script></body></html>";
        final String[] expectedAlerts = {"fontSize"};
        final List<String> collectedAlerts = new ArrayList<String>();
        loadPage(html, collectedAlerts);
        assertEquals(expectedAlerts, collectedAlerts);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testTitle_EmptyXmlTagExpanded() throws Exception {
        final String content = "<html><head><title/></head>\n"
            + "<body>Hello World!</body></html>";
        final HtmlPage page = loadPage(content);
        assertTrue(page.asXml().indexOf("</title>") != -1);
    }

    /**
     * Tests getElementById() of child element after appendChild(), removeChild(), then appendChild()
     * of the parent element.
     *
     * @throws Exception if the test fails
     */
    @Test
    public void testGetElementById_AfterAppendRemoveAppendChild() throws Exception {
        final String content = "<html><head><title>foo</title><script>\n"
            + "  function test() {\n"
            + "    var table = document.createElement('table');\n"
            + "    var tr = document.createElement('tr');\n"
            + "    tr.id='myTR';\n"
            + "    table.appendChild(tr);\n"
            + "    document.body.appendChild(table);\n"
            + "    document.body.removeChild(table);\n"
            + "    document.body.appendChild(table);\n"
            + "    alert(document.getElementById('myTR'));\n"
            + "  }\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        loadPage(content, collectedAlerts);
        assertFalse(collectedAlerts.get(0).equals("null"));
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testGetElementById_AfterAppendingToNewlyCreatedElement() throws Exception {
        final String content = "<html><head><title>foo</title><script>\n"
            + "  function test() {\n"
            + "    var table = document.createElement('table');\n"
            + "    var tr = document.createElement('tr');\n"
            + "    tr.id='myTR';\n"
            + "    table.appendChild(tr);\n"
            + "    alert(document.getElementById('myTR'));\n"
            + "  }\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";
        final List<String> collectedAlerts = new ArrayList<String>();
        loadPage(content, collectedAlerts);
        assertTrue(collectedAlerts.get(0).equals("null"));
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testOnunLoadHandler() throws Exception {
        final String htmlContent = "<html><head><title>foo</title>\n"
            + "</head><body onunload='alert(\"foo\");alert(\"bar\")'>\n"
            + "</body></html>";

        final String[] expectedAlerts = {"foo", "bar"};
        final List<String> collectedAlerts = new ArrayList<String>();
        final WebClient client = new WebClient();
        client.setAlertHandler(new CollectingAlertHandler(collectedAlerts));
        final MockWebConnection conn = new MockWebConnection(client);
        conn.setResponse(URL_FIRST, htmlContent);
        client.setWebConnection(conn);
        
        client.getPage(URL_FIRST);
        client.getPage(URL_FIRST);

        assertEquals(expectedAlerts, collectedAlerts);
    }

    /**
     * @throws Exception If the test fails
     */
    @Test
    public void testOnbeforeunloadHandler() throws Exception {
        testOnbeforeunloadHandler(BrowserVersion.INTERNET_EXPLORER_7_0, false, "first");
        testOnbeforeunloadHandler(BrowserVersion.INTERNET_EXPLORER_7_0, true, "second");
        testOnbeforeunloadHandler(BrowserVersion.FIREFOX_2, false, "first");
        testOnbeforeunloadHandler(BrowserVersion.FIREFOX_2, true, "second");
    }

    /**
     * @param browserVersion BrowserVersion to use
     * @param handlerOk whether OnbeforeunloadHandler.handleEvent will return true of false.
     * @param expectedPageTitle the expected title of the page after clicking.
     */
    private void testOnbeforeunloadHandler(final BrowserVersion browserVersion, final boolean handlerOk,
        final String expectedPageTitle) throws Exception {
        final WebClient webClient = new WebClient(browserVersion);
        final MockWebConnection webConnection = new MockWebConnection(webClient);
        final List<String> collectedConfirms = new ArrayList<String>();

        webClient.setOnbeforeunloadHandler(new OnbeforeunloadHandler() {
            public boolean handleEvent(final Page page, final String message) {
                collectedConfirms.add(message);
                return handlerOk;
            }
        });

        final String expectedMessage = "Any string value here forces a dialog box to appear before closing the window.";
        final String firstContent = "<html><head><title>first</title>\n"
            + "<script>\n"
            + "  function closeIt(event) {\n"
            + "     event.returnValue = '" + expectedMessage + "';\n"
            + "  }\n"
            + "</script>\n"
            + "</head><body onbeforeunload='closeIt(event)'>\n"
            + "  <a href='" + URL_SECOND + "'>Second page</a>\n"
            + "</body></html>";

        final String secondContent = "<html><head><title>second</title>\n"
            + "</head><body>\n"
            + "</body></html>";

        webConnection.setResponse(URL_FIRST, firstContent);
        webConnection.setResponse(URL_SECOND, secondContent);
        webClient.setWebConnection(webConnection);

        final HtmlPage page = (HtmlPage) webClient.getPage(URL_FIRST);
        final HtmlAnchor anchor = (HtmlAnchor) page.getAnchors().get(0);
        final HtmlPage secondPage = (HtmlPage) anchor.click();
        
        assertEquals(new String[] {expectedMessage}, collectedConfirms);
        assertEquals(expectedPageTitle, secondPage.getTitleText());
    }

    /**
     * @throws Exception If an error occurs.
     */
    @Test
    public void testSrcJavaScript() throws Exception {
        final String htmlContent
            = "<html><head><title>foo</title></head><body>\n"
            + "<script id='ie_ready' src='javascript:void(0)'></script>\n"
            + "</body></html>";

        loadPage(htmlContent);
    }

    /**
     * Regression test for asText() which would blow up.
     *
     * @exception Exception If the test fails
     */
    @Test
    public void testAsText() throws Exception {
        final String htmlContent
            = "<html><head><title>test</title></head>\n"
            + "<body><table>\n"
            + "<tr><form><td>a</td></form></tr>\n"
            + "</table></body></html>";

        final HtmlPage page = loadPage(htmlContent);
        page.asText();
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void getElementsByTagName() throws Exception {
        final String firstContent
            = "<html><head><title>First</title></head>\n"
            + "<body>\n"
            + "<form><input type='button' name='button1' value='pushme'></form>\n"
            + "<div>a</div> <div>b</div> <div>c</div>\n"
            + "</body></html>";

        final HtmlPage page = loadPage(firstContent);

        final NodeList inputs = page.getElementsByTagName("input");
        assertEquals(1, inputs.getLength());
        assertEquals("button", inputs.item(0).getAttributes().getNamedItem("type").getNodeValue());

        final NodeList divs = page.getElementsByTagName("div");
        assertEquals(3, divs.getLength());

        final HtmlDivision newDiv = new HtmlDivision(null, HtmlDivision.TAG_NAME, page, null);
        page.getBody().appendChild(newDiv);
        assertEquals(4, divs.getLength());
    }

}
