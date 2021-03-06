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
package com.gargoylesoftware.htmlunit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Tests that have to do with when the scripting engine is called.  Javascript
 * specific tests will be found elsewhere.
 *
 * @version  $Revision$
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author Marc Guillemot
 */
public class ScriptEngineTest extends WebTestCase {
    /**
     * Create an instance
     * @param name The name of the test
     */
    public ScriptEngineTest( final String name ) {
        super(name);
    }


    /**
     * Test that code in script tags is executed on page load.  Try different combinations
     * of the script tag except for the case where a remote javascript page is loaded.  That
     * one will be tested seperately.
     * @throws Exception If something goes wrong.
     */
    public void testScriptTags_AllLocalContent() throws Exception {
        final String content
            = "<html>"
            + "<head><title>foo</title>"
            + "<script>One</script>" // no language specifed - assume javascript
            + "<script language='javascript'>Two</script>"
            + "<script type='text/javascript'>Three</script>"
            + "<script type='text/perl'>Four</script>" // type is unsupported language
            + "</head>"
            + "<body>"
            + "<p>hello world</p>"
            + "</body></html>";
        final List collectedScripts = new ArrayList();
        loadPageAndCollectScripts(content, collectedScripts);

        // NO MORE: The last expected is the dummy stub that is needed to initialize the javascript engine
        final List expectedScripts = Arrays.asList( new String[]{
            "One", "Two", "Three"} );

        assertEquals( expectedScripts, collectedScripts );
    }


    private HtmlPage loadPageAndCollectScripts(
            final String html, final List collectedScripts )
        throws Exception {

        final WebClient client = new WebClient();
        client.setScriptEngine( new ScriptEngine(client) {
            public void initialize(final HtmlPage page) {
            }
            public Object execute(
                    final HtmlPage htmlPage, final String sourceCode,
                    final String sourceName, final HtmlElement htmlElement ) {
                collectedScripts.add( sourceCode );
                return null;
            }
            public Object callFunction(
                    final HtmlPage htmlPage, final Object javaScriptFunction,
                    final Object thisObject, final Object [] args,
                    final HtmlElement htmlElement ) {
                return null;
            }
            public boolean isScriptRunning() {
                return false;
            }
        } );

        final MockWebConnection webConnection = new MockWebConnection( client );

        webConnection.setDefaultResponse( html );
        client.setWebConnection( webConnection );

        final HtmlPage page = (HtmlPage) client.getPage(new WebRequestSettings(URL_GARGOYLE, SubmitMethod.POST));
        return page;
    }
}
