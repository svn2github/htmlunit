/*
 * Copyright (c) 2002, 2004 Gargoyle Software Inc. All rights reserved.
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
package com.gargoylesoftware.htmlunit.javascript;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebTestCase;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


/**
 * Tests for {@link HtmlElement} attributes.
 * 
 * @version  $Revision$
 * @author David D. Kilzer
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 */
public class AttributeCaseTest extends WebTestCase {

    private static final String ATTRIBUTE_NAME = "randomAttribute";
    private static final String ATTRIBUTE_VALUE = "someValue";
    private static final String ATTRIBUTE_VALUE_NEW = "newValue";

    private HtmlElement element_;
    private HtmlPage page_;


    /**
     * Create an instance
     *
     * @param name The name of the test
     */
    public AttributeCaseTest(final String name) {
        super(name);
    }

    /** 
     * Test {@link HtmlElement#getAttributeValue(String)} with a lower case name 
     * @throws IOException If the test fails
     */
    public void testGetAttributeLowerCase() throws IOException {
        setupGetAttributeTest(ATTRIBUTE_NAME, ATTRIBUTE_VALUE);
        assertEquals(page_.asXml(), ATTRIBUTE_VALUE, element_.getAttributeValue(ATTRIBUTE_NAME.toLowerCase()));
    }

    /** 
     * Test {@link HtmlElement#getAttributeValue(String)} with a mixed case name 
     * @throws IOException If the test fails
     */
    public void testGetAttributeMixedCase() throws IOException {
        setupGetAttributeTest(ATTRIBUTE_NAME, ATTRIBUTE_VALUE);
        assertEquals(page_.asXml(), ATTRIBUTE_VALUE, element_.getAttributeValue(ATTRIBUTE_NAME));
    }

    /** 
     * Test {@link HtmlElement#getAttributeValue(String)} with an upper case name 
     * @throws IOException If the test fails
     */
    public void testGetAttributeUpperCase() throws IOException {
        setupGetAttributeTest(ATTRIBUTE_NAME, ATTRIBUTE_VALUE);
        assertEquals(page_.asXml(), ATTRIBUTE_VALUE, element_.getAttributeValue(ATTRIBUTE_NAME.toUpperCase()));
    }

    /** 
     * Test {@link HtmlElement#setAttributeValue(String,String)} with a lower case name 
     * @throws IOException If the test fails
     */
    public void testSetAttributeLowerCase() throws IOException {
        setupSetAttributeTest(ATTRIBUTE_NAME.toLowerCase(), ATTRIBUTE_VALUE, ATTRIBUTE_VALUE_NEW);
        assertEquals(page_.asXml(), ATTRIBUTE_VALUE_NEW, element_.getAttributeValue(ATTRIBUTE_NAME.toLowerCase()));
    }

    /** 
     * Test {@link HtmlElement#setAttributeValue(String,String)} with a mixed case name 
     * @throws IOException If the test fails
     */
    public void testSetAttributeMixedCase() throws IOException {
        setupSetAttributeTest(ATTRIBUTE_NAME, ATTRIBUTE_VALUE, ATTRIBUTE_VALUE_NEW);
        assertEquals(page_.asXml(), ATTRIBUTE_VALUE_NEW, element_.getAttributeValue(ATTRIBUTE_NAME.toLowerCase()));
    }

    /** 
     * Test {@link HtmlElement#setAttributeValue(String,String)} with an upper case name 
     * @throws IOException If the test fails
     */
    public void testSetAttributeUpperCase() throws IOException {
        setupSetAttributeTest(ATTRIBUTE_NAME.toUpperCase(), ATTRIBUTE_VALUE, ATTRIBUTE_VALUE_NEW);
        assertEquals(page_.asXml(), ATTRIBUTE_VALUE_NEW, element_.getAttributeValue(ATTRIBUTE_NAME.toLowerCase()));
    }

    /** 
     * Test {@link HtmlElement#removeAttribute(String)} with a lower case name 
     * @throws IOException If the test fails
     */
    public void testRemoveAttributeLowerCase() throws IOException {
        setupGetAttributeTest(ATTRIBUTE_NAME, ATTRIBUTE_VALUE);
        element_.removeAttribute(ATTRIBUTE_NAME.toLowerCase());
        assertEquals(page_.asXml(), "", element_.getAttributeValue(ATTRIBUTE_NAME.toLowerCase()));
    }

    /** 
     * Test {@link HtmlElement#removeAttribute(String)} with a mixed case name 
     * @throws IOException If the test fails
     */
    public void testRemoveAttributeMixedCase() throws IOException {
        setupGetAttributeTest(ATTRIBUTE_NAME, ATTRIBUTE_VALUE);
        element_.removeAttribute(ATTRIBUTE_NAME);
        assertEquals(page_.asXml(), "", element_.getAttributeValue(ATTRIBUTE_NAME.toLowerCase()));
    }

    /** 
     * Test {@link HtmlElement#removeAttribute(String)} with an upper case name 
     * @throws IOException If the test fails
     */
    public void testRemoveAttributeUpperCase() throws IOException {
        setupGetAttributeTest(ATTRIBUTE_NAME, ATTRIBUTE_VALUE);
        element_.removeAttribute(ATTRIBUTE_NAME.toUpperCase());
        assertEquals(page_.asXml(), "", element_.getAttributeValue(ATTRIBUTE_NAME.toLowerCase()));
    }

    /** 
     * Test {@link HtmlElement#isAttributeDefined(String)} with a lower case name 
     * @throws IOException If the test fails
     */
    public void testIsAttributeDefinedLowerCase() throws IOException {
        setupGetAttributeTest(ATTRIBUTE_NAME, ATTRIBUTE_VALUE);
        assertTrue(page_.asXml(), element_.isAttributeDefined(ATTRIBUTE_NAME.toLowerCase()));
    }

    /** 
     * Test {@link HtmlElement#isAttributeDefined(String)} with a mixed case name 
     * @throws IOException If the test fails
     */
    public void testIsAttributeDefinedMixedCase() throws IOException {
        setupGetAttributeTest(ATTRIBUTE_NAME, ATTRIBUTE_VALUE);
        assertTrue(page_.asXml(), element_.isAttributeDefined(ATTRIBUTE_NAME));
    }

    /** 
     * Test {@link HtmlElement#isAttributeDefined(String)} with an upper case name 
     * @throws IOException If the test fails
     */
    public void testIsAttributeDefinedUpperCase() throws IOException {
        setupGetAttributeTest(ATTRIBUTE_NAME, ATTRIBUTE_VALUE);
        assertTrue(page_.asXml(), element_.isAttributeDefined(ATTRIBUTE_NAME.toUpperCase()));
    }


    private void setupAttributeTest(String content, String elementId) throws IOException {

        final WebClient client = new WebClient();
        final MockWebConnection webConnection = new MockWebConnection(client);

        webConnection.setDefaultResponse(content);
        client.setWebConnection(webConnection);

        page_ = (HtmlPage) client.getPage(URL_GARGOYLE);

        element_ = page_.getHtmlElementById(elementId);
    }


    private void setupGetAttributeTest(String attributeName, String attributeValue) throws IOException {

        final String elementId = "p-id";
        final String content = "<html><head><title>AttributeCaseTest</title></head><body>\n"
                          + "<p id=\"" + elementId + "\" " + attributeName + "=\"" + attributeValue + "\">\n"
                          + "</body></html>";

        setupAttributeTest(content, elementId);
    }


    private void setupSetAttributeTest(String attributeName, String attributeValue, String newAttributeValue)
            throws IOException {

        final String elementId = "p-id";
        final String content
                = "<html><head><title>AttributeCaseTest</title></head><body>\n"
                  + "<p id=\"" + elementId + "\" " + attributeName + "=\"" + attributeValue + "\">\n"
                  + "<script language=\"javascript\" type=\"text/javascript\">\n<!--\n"
                  + "  document.getElementById(\"" + elementId + "\").setAttribute(\"" + attributeName + "\", \"" +
                  newAttributeValue + "\");\n"
                  + "\n// -->\n</script>\n"
                  + "</body></html>";

        setupAttributeTest(content, elementId);
    }
}
