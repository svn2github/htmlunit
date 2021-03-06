/*
 * Copyright (c) 2002-2006 Gargoyle Software Inc. All rights reserved.
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
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


/**
 * Tests for Images
 *
 * @version  $Revision$
 * @author  <a href="mailto:george@murnock.com">George Murnock</a>
 */
public class ImageTest extends WebTestCase {
    /**
     * Create an instance
     * @param name The name of the test.
     */
    public ImageTest( final String name ) {
        super(name);
    }
    
    /**
     * This test verifies that JavaScript can be used to get the src
     * attribute of an <img> tag.
     * @throws Exception if the test fails
     */
    public void test_getSrc() throws Exception {
        final String content
            = "<html><head><title></title><script>"
            + "function doTest(){\n"
            + "    alert(document.getElementById('anImage').src);\n"
            +"}\n"
            + "</script></head><body onload='doTest()'>"
            + "<img src='foo.gif' id='anImage'/>"
            + "</body></html>";

        final List collectedAlerts = new ArrayList();
        loadPage(content, collectedAlerts);

        final List expectedAlerts = Arrays.asList( new String[]{
            "http://www.gargoylesoftware.com/foo.gif"
        } );

        createTestPageForRealBrowserIfNeeded(content, expectedAlerts);
        assertEquals( expectedAlerts, collectedAlerts );
    }    

    /**
     * This test verifies that when JavaScript is used to modify the src
     * attribute, the value is persisted to the corresponding <img> node
     * in the DOM tree.
     * @throws Exception if the test fails
     */
    public void test_setSrc() throws Exception {
        final String content
            = "<html><head><title></title><script>"
            + "function doTest(){\n"
            + "    document.getElementById('anImage').src = 'bar.gif';\n"
            +"}\n"
            + "</script></head><body onload='doTest()'>"
            + "<img src='foo.gif' id='anImage'/>"
            + "</body></html>";

        final HtmlPage page = loadPage(content, null);
        final HtmlImage image = (HtmlImage) page.getHtmlElementById("anImage");
        assertEquals("bar.gif", image.getSrcAttribute());
    }
    
    /**
     * JavaScript can be used to preload images, as follows:
     * <code>var newImage = new Image(); newImage.src = 'foo.gif';</code>.
     * When <code>new Image()</code> is called, HtmlUnit creates a new JavaScript 
     * Image object. However, no corresponding DOM node is created, which is
     * just as well, since browers don't create one either.
     * This test verifies that the above JavaScript code can be invoked without
     * throwing an "IllegalStateException: DomNode has not been set for this 
     * SimpleScriptable."
     * @throws Exception if the test fails
     */
    public void test_setSrc_newImage() throws Exception {
        final String content
            = "<html><head><title></title><script>"
            + "function doTest(){\n"
            + "    var preloadImage = new Image();\n"
            + "    preloadImage.src = 'bar.gif';\n"
            + "    alert(preloadImage.src);\n"
            +"}\n"
            + "</script></head><body onload='doTest()'>"
            + "</body></html>";

        final List collectedAlerts = new ArrayList();
        loadPage(content, collectedAlerts);

        final List expectedAlerts = Arrays.asList( new String[]{
            "http://www.gargoylesoftware.com/bar.gif"
        } );
        
        createTestPageForRealBrowserIfNeeded(content, expectedAlerts);
        assertEquals( expectedAlerts, collectedAlerts );
    }

    /**
     * @throws Exception if the test fails
     */
    public void test_AttributeName() throws Exception {
        final String content
            = "<html><head><title></title><script>"
            + "function test()\n"
            + "{\n"
            + "  var oImg = document.getElementById('myImage');\n"
            + "  oImg.name = 'foo';\n"
            + "  alert(oImg.name);\n"
            +"}\n"
            + "</script></head><body onload='test()'>"
            + "<img src='foo.png' id='myImage'>"
            + "</body></html>";

        final List expectedAlerts = Arrays.asList(new String[]{"foo"});
        createTestPageForRealBrowserIfNeeded(content, expectedAlerts);

        final List collectedAlerts = new ArrayList();
        loadPage(content, collectedAlerts);

        assertEquals( expectedAlerts, collectedAlerts );
    }
}
