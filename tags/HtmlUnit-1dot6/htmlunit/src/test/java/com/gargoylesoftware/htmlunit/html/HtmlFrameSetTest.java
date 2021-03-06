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

import java.net.URL;

import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebTestCase;
import com.gargoylesoftware.htmlunit.WebWindow;

/**
 *  Tests for HtmlFrameSet
 *
 * @version  $Revision$
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author Marc Guillemot
 * @author Hans Donner
 */
public class HtmlFrameSetTest extends WebTestCase {

    /**
     *  Create an instance
     *
     * @param  name Name of the test
     */
    public HtmlFrameSetTest( final String name ) {
        super( name );
    }


    /**
     * @throws Exception if the test fails
     */
    public void testLoadingFrameSet()
        throws Exception {

        final String firstContent
            = "<html><head><title>First</title></head>"
            + "<frameset cols='130,*'>"
            + "  <frame scrolling='no' name='left' src='http://second' frameborder='1' />"
            + "  <frame scrolling='auto' name='right' src='http://third' frameborder='1' />"
            + "  <noframes>"
            + "    <body>Frames not supported</body>"
            + "  </noframes>"
            + "</frameset>"
            + "</html>";
        final String secondContent = "<html><head><title>Second</title></head><body></body></html>";
        final String thirdContent  = "<html><head><title>Third</title></head><body></body></html>";

        final WebClient webClient = new WebClient();

        final MockWebConnection webConnection = new MockWebConnection( webClient );
        webConnection.setResponse(URL_FIRST, firstContent);
        webConnection.setResponse(URL_SECOND, secondContent);
        webConnection.setResponse(URL_THIRD, thirdContent);

        webClient.setWebConnection( webConnection );

        final HtmlPage firstPage = (HtmlPage) webClient.getPage(URL_FIRST);
        assertEquals( "First", firstPage.getTitleText() );

        final WebWindow secondWebWindow = webClient.getWebWindowByName("left");
        assertSame( firstPage, ((BaseFrame.FrameWindow) secondWebWindow).getEnclosingPage() );
        assertEquals( "Second", ((HtmlPage) secondWebWindow.getEnclosedPage()).getTitleText() );

        final WebWindow thirdWebWindow = webClient.getWebWindowByName("right");
        assertInstanceOf( thirdWebWindow, BaseFrame.FrameWindow.class );
        assertSame( firstPage, ((BaseFrame.FrameWindow) thirdWebWindow).getEnclosingPage() );
        assertEquals( "Third", ((HtmlPage)thirdWebWindow.getEnclosedPage()).getTitleText() );
    }


    /**
     * @throws Exception if the test fails
     */
    public void testLoadingIFrames()
        throws Exception {

        final String firstContent
            = "<html><head><title>First</title></head>"
            + "<body>"
            + "  <iframe name='left' src='http://second' />"
            + "  some stuff"
            + "</html>";
        final String secondContent = "<html><head><title>Second</title></head><body></body></html>";

        final WebClient webClient = new WebClient();

        final MockWebConnection webConnection = new MockWebConnection( webClient );
        webConnection.setResponse(URL_FIRST, firstContent);
        webConnection.setResponse(URL_SECOND, secondContent);

        webClient.setWebConnection( webConnection );

        final HtmlPage firstPage = (HtmlPage) webClient.getPage(URL_FIRST);
        assertEquals( "First", firstPage.getTitleText() );

        final WebWindow secondWebWindow = webClient.getWebWindowByName("left");
        assertInstanceOf(secondWebWindow, BaseFrame.FrameWindow.class);
        assertSame( firstPage, ((BaseFrame.FrameWindow) secondWebWindow).getEnclosingPage() );
        assertEquals( "Second", ((HtmlPage)secondWebWindow.getEnclosedPage()).getTitleText() );
    }

    /**
     * <a href="http://sourceforge.net/tracker/index.php?func=detail&aid=1101525&group_id=47038&atid=448266">
     * Bug report 1101525 </a>
     * 
     * @throws Exception if the test fails
     */
    public void testLoadingFrameSetWithRelativePaths()
        throws Exception {

        final String framesContent 
            = "<html><head><title>Frames</title></head>"
            + "<frameset rows='110,*'>"
            + "  <frame src='subdir1/menu.html' name='menu' scrolling='no' border='0' noresize>"
            + "  <frame src='subdir2/first.html' name='test' border='0' auto>"
            + "</frameset>" 
            + "<noframes>"
            + "  <body>Frames not supported</body>" 
            + "</noframes>"
            + "</html>";
        final String menuContent 
            = "<html><head><title>Menu</title></head>"
            + "<body>"
            + "  <script language='javascript'>"
            + "    function changeEditPage() {parent.test.location='../second.html';}"
            + "  </script>"
            + "  <a name ='changePage' onClick='javascript:changeEditPage();' href='#'>Click</a>."
            + "</body>"
            + "</html>";
        final String firstContent
            = "<html><head><title>First</title></head>"
            + "<body>First/body>" 
            + "</html>";
        final String secondContent
            = "<html><head><title>Second</title></head>"
            + "<body>Second</body>" 
            + "</html>";
        final String baseUrl = "http://framestest";
        
        final URL framesURL = new URL(baseUrl + "/frames.html");
        final URL menuURL = new URL(baseUrl + "/subdir1/menu.html");
        final URL firstURL = new URL(baseUrl + "/subdir2/first.html");
        final URL secondURL = new URL(baseUrl + "/second.html");

        final WebClient webClient = new WebClient();

        final MockWebConnection webConnection = new MockWebConnection( webClient );
        webConnection.setResponse(framesURL, framesContent);
        webConnection.setResponse(menuURL, menuContent);
        webConnection.setResponse(firstURL, firstContent);
        webConnection.setResponse(secondURL, secondContent);

        webClient.setWebConnection( webConnection );

        final HtmlPage framesPage = (HtmlPage) webClient.getPage(framesURL);
        assertEquals( "Frames", framesPage.getTitleText() );
      
        final WebWindow menuWebWindow = webClient.getWebWindowByName("menu");
        final HtmlPage menuPage = (HtmlPage) menuWebWindow.getEnclosedPage();
        assertEquals( "Menu", menuPage.getTitleText() );

        final WebWindow testWebWindow = webClient.getWebWindowByName("test");
        assertEquals( "First", ((HtmlPage) testWebWindow.getEnclosedPage()).getTitleText() );

        final HtmlAnchor changePage = menuPage.getAnchorByName("changePage");
        changePage.click();
        assertEquals( "Second", ((HtmlPage) testWebWindow.getEnclosedPage()).getTitleText() );
        
    }

}
