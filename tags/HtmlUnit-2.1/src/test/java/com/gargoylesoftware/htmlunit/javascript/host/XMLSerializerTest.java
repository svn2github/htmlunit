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
package com.gargoylesoftware.htmlunit.javascript.host;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebTestCase;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;

/**
 * Tests for {@link XMLSerializer}.
 *
 * @version $Revision$
 * @author Ahmed Ashour
 */
public class XMLSerializerTest extends WebTestCase {

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void test() throws Exception {
        final String expectedStringIE =
            "<note>13109<to>Tove</to>13109<from>Jani</from>13109<body>Do32not32forget32me32this32weekend!</body>"
            + "13109<outer>131099<inner>Some32Value</inner></outer>1310</note>1310";
        test(BrowserVersion.INTERNET_EXPLORER_7_0, expectedStringIE);
        final String expectedStringFF =
            "<note>32<to>Tove</to>3210<from>Jani</from>321032<body>Do32not32forget32me32this32weekend!</body>"
            + "32<outer>10323232<inner>Some32Value</inner></outer>32</note>";
        test(BrowserVersion.FIREFOX_2, expectedStringFF);
    }
    
    private void test(final BrowserVersion browserVersion, final String expectedString)
        throws Exception {
        final String content = "<html><head><title>foo</title><script>\n"
            + "  function test() {\n"
            + "    var text='<note> ';\n"
            + "    text += '<to>Tove</to> \\n';\n"
            + "    text += '<from>Jani</from> \\n ';\n"
            + "    text += '<body>Do not forget me this weekend!</body> ';\n"
            + "    text += '<outer>\\n ';\n"
            + "    text += '  <inner>Some Value</inner>';\n"
            + "    text += '</outer> ';\n"
            + "    text += '</note>';\n"
            + "    if (window.ActiveXObject) {\n"
            + "      var doc=new ActiveXObject('Microsoft.XMLDOM');\n"
            + "      doc.async=false;\n"
            + "      doc.loadXML(text);\n"
            + "      var xml = doc.xml;\n"
            + "    } else {\n"
            + "      var parser=new DOMParser();\n"
            + "      var doc=parser.parseFromString(text,'text/xml');\n"
            + "      var serializer = new XMLSerializer();\n"
            + "      var xml = serializer.serializeToString(doc.documentElement);\n"
            + "    }\n"
            + "    var ta = document.getElementById('myTextArea');\n"
            + "    for (var i=0; i < xml.length; i++) {\n"
            + "      if (xml.charCodeAt(i) < 33)\n"
            + "        ta.value += xml.charCodeAt(i);\n"
            + "      else\n"
            + "        ta.value += xml.charAt(i);\n"
            + "    }\n"
            + "  }\n"
            + "</script></head><body onload='test()'>\n"
            + "  <textarea id='myTextArea' cols='80' rows='30'></textarea>\n"
            + "</body></html>";
        
        final HtmlPage page = loadPage(browserVersion, content, null);
        final HtmlTextArea textArea = (HtmlTextArea) page.getHtmlElementById("myTextArea");
        assertEquals(expectedString, textArea.getText());
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testNameSpaces() throws Exception {
        if (notYetImplemented()) {
            return;
        }
        final String expectedStringIE =
            "<?xml32version=\"1.0\"?>1310<xsl:stylesheet32version=\"1.0\"32"
            + "xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">13109<xsl:template32match=\"/\">131099<html>1310999"
            + "<body>1310999</body>131099</html>13109</xsl:template>1310</xsl:stylesheet>1310";
        testNameSpaces(BrowserVersion.INTERNET_EXPLORER_7_0, expectedStringIE);
        final String expectedStringFF =
            "<xsl:stylesheet32version=\"1.0\"32xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">103232"
            + "<xsl:template32match=\"/\">103232<html>1032323232<body>1032323232</body>103232</html>103232"
            + "</xsl:template>10</xsl:stylesheet>";
        testNameSpaces(BrowserVersion.FIREFOX_2, expectedStringFF);
    }
    
    private void testNameSpaces(final BrowserVersion browserVersion, final String expectedString) throws Exception {
        final String html = "<html><head><title>foo</title><script>\n"
            + "  function test() {\n"
            + "    var text='<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\\n';\n"
            + "    text += '<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\\n';\n"
            + "    text += '  <xsl:template match=\"/\">\\n';\n"
            + "    text += '  <html>\\n';\n"
            + "    text += '    <body>\\n';\n"
            + "    text += '    </body>\\n';\n"
            + "    text += '  </html>\\n';\n"
            + "    text += '  </xsl:template>\\n';\n"
            + "    text += '</xsl:stylesheet>';\n"
            + "    if (window.ActiveXObject) {\n"
            + "      var doc=new ActiveXObject('Microsoft.XMLDOM');\n"
            + "      doc.async=false;\n"
            + "      doc.loadXML(text);\n"
            + "      var xml = doc.xml;\n"
            + "    } else {\n"
            + "      var parser=new DOMParser();\n"
            + "      var doc=parser.parseFromString(text,'text/xml');\n"
            + "      var serializer = new XMLSerializer();\n"
            + "      var xml = serializer.serializeToString(doc.documentElement);\n"
            + "    }\n"
            + "    var ta = document.getElementById('myTextArea');\n"
            + "    for (var i=0; i < xml.length; i++) {\n"
            + "      if (xml.charCodeAt(i) < 33)\n"
            + "        ta.value += xml.charCodeAt(i);\n"
            + "      else\n"
            + "        ta.value += xml.charAt(i);\n"
            + "    }\n"
            + "  }\n"
            + "</script></head><body onload='test()'>\n"
            + "  <textarea id='myTextArea' cols='80' rows='30'></textarea>\n"
            + "</body></html>";

        final HtmlPage page = loadPage(browserVersion, html, null);
        final HtmlTextArea textArea = (HtmlTextArea) page.getHtmlElementById("myTextArea");
        assertEquals(expectedString, textArea.getText());
    }
}
