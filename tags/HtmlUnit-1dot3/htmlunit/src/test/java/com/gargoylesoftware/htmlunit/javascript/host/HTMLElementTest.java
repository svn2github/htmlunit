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
package com.gargoylesoftware.htmlunit.javascript.host;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebTestCase;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Tests for NodeImpl
 * 
 * @author yourgod
 * @author Chris Erskine
 * @author David D. Kilzer
 * @version $Revision$
 *
 */
public class HTMLElementTest extends WebTestCase {

    /**
     * @param name The name of the test case
     */
    public HTMLElementTest(String name) {
        super(name);
    }

    /**
     * @throws Exception on test failure
     */
    public void test_getAttribute() throws Exception {
        final String content = "<html>\n" +
                "<head>\n" +
                "    <title>test</title>\n" +
                "    <script>\n" +
                "    function doTest(){\n" +
                "       var myNode = document.getElementById(\'myNode\');\n" +
                "       alert(myNode.title);\n" +
                "       alert(myNode.getAttribute('title'));\n" +
                "   }\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body onload=\'doTest()\'>\n" +
                "<p id=\'myNode\' title=\'a\'>\n" +
                "</p>\n" +
                "</body>\n" +
                "</html>\n" +
                "";
        final List collectedAlerts = new ArrayList();
        final HtmlPage page = loadPage(content, collectedAlerts);
        assertEquals("test", page.getTitleText());

        final List expectedAlerts = Arrays.asList(new String[]{
            "a", "a"
        });

        assertEquals(expectedAlerts, collectedAlerts);
    }

    /**
     * @throws Exception on test failure
     */
    public void test_setAttribute() throws Exception {
        final String content = "<html>\n" + 
                "<head>\n" + 
                "    <title>test</title>\n" + 
                "    <script>\n" + 
                "    function doTest(){\n" + 
                "       var myNode = document.getElementById(\'myNode\');\n" + 
                "       alert(myNode.title);\n" + 
                "       myNode.setAttribute(\'title\', \'b\');\n" + 
                "       alert(myNode.title);\n" + 
                "   }\n" + 
                "    </script>\n" + 
                "</head>\n" + 
                "<body onload=\'doTest()\'>\n" + 
                "<p id=\'myNode\' title=\'a\'>\n" + 
                "</p>\n" + 
                "</body>\n" + 
                "</html>\n" + 
                "";
        final List collectedAlerts = new ArrayList();
        final HtmlPage page = loadPage(content, collectedAlerts);
        assertEquals("test", page.getTitleText());

        final List expectedAlerts = Arrays.asList(new String[]{
            "a", "b"
        });

        assertEquals(expectedAlerts, collectedAlerts);
    }

    /**
     * Test for getElementsByTagName
     * @throws Exception if the test fails
     */
    public void testGetElementsByTagName() throws Exception {
        final String content
            = "<html><head><title>First</title><script>\n"
            + "function doTest() {\n"
            + "var a1 = document.getElementsByTagName('td');\n"
            + "alert('all = ' + a1.length);\n"
            + "var firstRow = document.getElementById('r1');\n"
            + "var rowOnly = firstRow.getElementsByTagName('td');\n"
            + "alert('row = ' + rowOnly.length);\n"
            + "}\n"
            + "</script></head><body onload='doTest()'>\n"
            + "<table>\n"
            + "<tr id='r1'><td>1</td><td>2</td></tr>\n"
            + "<tr id='r2'><td>3</td><td>4</td></tr>\n"
            + "</table>\n"
            + "</body></html>\n";
        final List collectedAlerts = new ArrayList();
        loadPage(content, collectedAlerts);

        final List expectedAlerts = Arrays.asList(new String[]{
            "all = 4", "row = 2"
        });

        assertEquals(expectedAlerts, collectedAlerts);
    }
    
    /**
     * Test getting the class for the element
     * @throws Exception if the test fails
     */
    public void testGetClassName() throws Exception {
        final String content
            = "<html><head><style>.x {  font: 8pt Arial bold;  }</style>\n"
            + "<script>\n"
            + "function doTest() {\n"
            + "    var ele = document.getElementById('pid');\n"
            + "    var aClass = ele.className;\n"
            + "    alert('the class is ' + aClass);\n"
            + "}\n"
            + "</script></head><body onload='doTest()'>\n"
            + "<p id='pid' class='x'>text</p>\n"
            + "</body></html>\n";
          
        final List collectedAlerts = new ArrayList();
        loadPage(content, collectedAlerts);

        final List expectedAlerts = Arrays.asList(new String[]{
            "the class is x"
        });

        assertEquals(expectedAlerts, collectedAlerts);
    }
    /**
     * Test getting the class for the element
     * @throws Exception if the test fails
     */
    public void testSetClassName() throws Exception {
        final String content
            = "<html><head><style>.x {  font: 8pt Arial bold;  }</style>\n"
            + "<script>\n"
            + "function doTest() {\n"
            + "    var ele = document.getElementById('pid');\n"
            + "    ele.className = 'z';"
            + "    var aClass = ele.className;\n"
            + "    alert('the class is ' + aClass);\n"
            + "}\n"
            + "</script></head><body onload='doTest()'>\n"
            + "<p id='pid' class='x'>text</p>\n"
            + "</body></html>\n";
          
        final List collectedAlerts = new ArrayList();
        loadPage(content, collectedAlerts);

        final List expectedAlerts = Arrays.asList(new String[]{
            "the class is z"
        });

        assertEquals(expectedAlerts, collectedAlerts);
    }
}
