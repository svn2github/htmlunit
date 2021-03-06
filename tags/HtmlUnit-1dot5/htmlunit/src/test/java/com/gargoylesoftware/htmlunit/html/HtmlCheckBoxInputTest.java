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
package com.gargoylesoftware.htmlunit.html;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebTestCase;

/**
 *  Tests for HtmlCheckBox
 *
 * @version $Revision$
 * @author Mike Bresnahan
 * @author Marc Guillemot
 */
public class HtmlCheckBoxInputTest extends WebTestCase {
    /**
     *  Create an instance
     *
     * @param  name The name of the test
     */
    public HtmlCheckBoxInputTest( final String name ) {
        super( name );
    }

    /**
     * Verifies that a HtmlCheckBox is unchecked by default.
     * The onClick tests make this assumption.
     * @throws Exception if the test fails
     */
    public void test_defaultState() throws Exception {
        final String htmlContent
            = "<html><head><title>foo</title></head><body>"
            + "<form id='form1'>"
            + "    <input type='checkbox' name='checkbox' id='checkbox'>Check me</input>"
            + "</form></body></html>";
        final HtmlPage page = loadPage(htmlContent);
        final HtmlCheckBoxInput checkBox = ( HtmlCheckBoxInput )page.getHtmlElementById( "checkbox" );

        assertFalse( checkBox.isChecked());
    }

    /**
     * Given a onclick handler that does not cause the form to submit, this test 
     * verifies that HtmlCheckBix.click()
     * <ul>
     *   <li>sets the checkbox to the "checked" state</li>
     *   <li>returns the same page</li>
     * </ul>
     * @throws Exception if the test fails
     */
    public void test_onClick() throws Exception {
        final String htmlContent
            = "<html><head><title>foo</title></head><body>"
            + "<form id='form1' onSubmit='alert(\"bar\")' onReset='alert(\"reset\")'>"
            + "    <input type='checkbox' name='checkbox' id='checkbox' "
            + "onClick='alert(\"foo\")'>Check me</input>"
            + "</form></body></html>";
        final List collectedAlerts = new ArrayList();
        final HtmlPage page = loadPage(htmlContent, collectedAlerts);
        final HtmlCheckBoxInput checkBox = ( HtmlCheckBoxInput )page.getHtmlElementById( "checkbox" );

        final HtmlPage secondPage = (HtmlPage)checkBox.click();

        final List expectedAlerts = Arrays.asList( new String[]{"foo"} );
        assertEquals( expectedAlerts, collectedAlerts );

        assertSame( page, secondPage );
        assertTrue( checkBox.isChecked());
    }

    /**
     * Given a onclick handler that causes the form to submit, this test verifies that HtmlCheckBix.click()
     * <ul>
     *   <li>sets the checkbox to the "checked" state</li>
     *   <li>returns the new page</li>
     * </ul>
     * @throws Exception if the test fails
     */
    public void test_onClickThatSubmitsForm() throws Exception {
        final String htmlContent
            = "<html><head><title>foo</title></head><body>"
            + "<form id='form1' name='form1'>"
            + "    <input type='checkbox' name='checkbox' id='checkbox' "
            + "onClick='document.form1.submit()'>Check me</input>"
            + "</form></body></html>";
        final HtmlPage page = loadPage(htmlContent);
        final HtmlCheckBoxInput checkBox = ( HtmlCheckBoxInput )page.getHtmlElementById( "checkbox" );

        final HtmlPage secondPage = (HtmlPage)checkBox.click();

        assertNotSame( page, secondPage );
        assertTrue( checkBox.isChecked());
    }

    /**
     * Verifies that a asText() returns "checked" or "unckecked" according to the state of the checkbox.
     * @throws Exception if the test fails
     */
    public void testAsText() throws Exception {
        final String htmlContent
            = "<html><head><title>foo</title></head><body>"
            + "<form id='form1'>"
            + "    <input type='checkbox' name='checkbox' id='checkbox'>Check me</input>"
            + "</form></body></html>";

        final HtmlPage page = loadPage(htmlContent);

        final HtmlCheckBoxInput checkBox = ( HtmlCheckBoxInput )page.getHtmlElementById( "checkbox" );
        assertEquals("unchecked", checkBox.asText());
        checkBox.setChecked(true);
        assertEquals("checked", checkBox.asText());
    }
}
