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

import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mortbay.jetty.Server;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.BrowserRunner;
import com.gargoylesoftware.htmlunit.HttpWebConnectionTest;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebTestCase;
import com.gargoylesoftware.htmlunit.BrowserRunner.Browser;
import com.gargoylesoftware.htmlunit.BrowserRunner.Browsers;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Tests for 0.9.9.3 version of <a href="http://sarissa.sourceforge.net">Sarissa</a>.
 *
 * @version $Revision$
 * @author Ahmed Ashour
 */
@RunWith(BrowserRunner.class)
public class Sarissa0993Test extends WebTestCase {

    /** Number of browsers this case will use. */
    private static final int BROWSERS_COUNTER = 3;
    private static int Counter_;

    private static Server Server_;
    private static HtmlPage Page_;
    
    /**
     * Returns the Sarissa version being tested.
     *
     * @return the Sarissa version being tested
     */
    protected String getVersion() {
        return "0.9.9.3";
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    public void sarissa() throws Exception {
        test("SarissaTestCase");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    public void xmlHttpRequest() throws Exception {
        test("XmlHttpRequestTestCase");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    public void xmlSerializer() throws Exception {
        test("XMLSerializerTestCase");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    public void domParser() throws Exception {
        test("DOMParserTestCase");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    public void xmlDocument() throws Exception {
        test("XMLDocumentTestCase");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    public void xmlElement() throws Exception {
        test("XMLElementTestCase");
    }

    /**
     * @throws Exception if an error occurs
     */
    @Test
    public void xsltProcessor() throws Exception {
        //should be ++++F+++ (when property order rhino issue is fixed)
        test("XSLTProcessorTestCase", "+++++++F");
    }

    private void test(final String testName) throws Exception {
        final List< ? > divList =
            Page_.getByXPath("//div[@class='placeholder']/a[@name='#" + testName + "']/../div[last()]");
        assertEquals(1, divList.size());
        final HtmlDivision div = (HtmlDivision) divList.get(0);
        assertEquals("OK!", div.asText());
    }

    /**
     * This is used in case a failing test is expected to happen.
     *
     * @param expectedResult in the form of "+++F+++" (see the results in a real browser)
     */
    private void test(final String testName, final String expectedResult) throws Exception {
        final HtmlAnchor anchor =
            (HtmlAnchor) Page_.getFirstByXPath("//div[@class='placeholder']/a[@name='#" + testName + "']");
        final StringBuilder builder = new StringBuilder();
        for (Node node = anchor.getNextSibling().getNextSibling(); node instanceof DomText;
            node = node.getNextSibling()) {
            builder.append(((DomText) node).asText());
        }
        assertEquals(expectedResult, builder.toString());
    }

    /**
     * @throws Exception if an error occurs
     */
    @Before
    public void init() throws Exception {
        if (Counter_ < BROWSERS_COUNTER) {
            if (Server_ == null) {
                Server_ = HttpWebConnectionTest.startWebServer("src/test/resources/sarissa/" + getVersion());
            }
            if (Page_ == null) {
                final WebClient client = getWebClient();
                final String url = "http://localhost:" + HttpWebConnectionTest.PORT + "/test/testsarissa.html";
                Page_ = (HtmlPage) client.getPage(url);
                ((HtmlButton) Page_.getFirstByXPath("//button")).click();
            }
        }
    }

    /**
     * Performs deconstruction.
     * @throws Exception if an error occurs
     */
    @AfterClass
    public static void clean() throws Exception {
        if (++Counter_ == BROWSERS_COUNTER) {
            HttpWebConnectionTest.stopWebServer(Server_);
            Server_ = null;
        }
        Page_ = null;
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Browsers(Browser.NONE)
    public void xslt() throws Exception {
        final String input = "<root><element attribute=\"value\"/></root>";
        final String style = "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
            + "<xsl:output method=\"xml\" omit-xml-declaration=\"yes\"/>\n"
            + "<xsl:param select=\"'anonymous'\" name=\"user\"/>\n"
            + "<xsl:template match=\"/\">\n"
            + "<p id=\"user\">User: <xsl:value-of select=\"$user\"/>\n"
            + "</p>\n"
            + "<xsl:apply-templates/>\n"
            + "<hr/>\n"
            + "</xsl:template>\n"
            + "<xsl:template match=\"greeting\">\n"
            + "<p>\n"
            + "<xsl:apply-templates/>\n"
            + "</p>\n"
            + "</xsl:template>\n"
            + "</xsl:stylesheet>";

        final Source xmlSource = new StreamSource(new StringReader(input));
        final Source xsltSource = new StreamSource(new StringReader(style));

        final Document containerDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        final Element containerElement = containerDocument.createElement("container");
        containerDocument.appendChild(containerElement);

        final DOMResult result = new DOMResult(containerElement);

        final Transformer transformer = TransformerFactory.newInstance().newTransformer(xsltSource);
        transformer.transform(xmlSource, result);
    }
}
