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
package com.gargoylesoftware.htmlunit.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.httpclient.HttpState;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gargoylesoftware.htmlunit.SubmitMethod;
import com.gargoylesoftware.htmlunit.WebConnection;
import com.gargoylesoftware.htmlunit.WebRequestSettings;
import com.gargoylesoftware.htmlunit.WebResponse;

/**
 * Wrapper around a "real" WebConnection that will use the wrapped web connection 
 * to do the real job and save all received responses
 * in the temp directory with an overview page.<br>
 * <br>
 * This may be usefull at conception time to understand what is "browsed".<br>
 * <br>
 * Example:
 * <pre>
 * final WebClient client = new WebClient();
 * final WebConnection connection = new DebuggingWebConnection(client.getWebConnection(), "myTest");
 * client.setWebConnection(connection);
 * </pre>
 * In this example an overview page will be generated under the name myTest.html in the temp directory.<br>
 * <br>
 * <em>This class is only intended as an help during the conception.</em>
 * @version  $Revision$
 * @author Marc Guillemot
 */
public class DebuggingWebConnection extends WebConnection {
    private static final Log LOG = LogFactory.getLog(DebuggingWebConnection.class);

    private int counter_ = 0;
    private final WebConnection wrappedWebConnection_;
    private final String reportBaseName_;
    private final File javaScriptFile_;

    /**
     * Wraps a web connection to have a report generated of the received responses.
     * @param webConnection the webConnection that do the real work
     * @param reportBaseName the base name to use for the generated files. 
     * The report will be reportBaseName + ".html" in the temp file.
     * @throws IOException in case of problems writing the files.
     */
    public DebuggingWebConnection(final WebConnection webConnection, 
            final String reportBaseName) throws IOException {

        super(webConnection.getWebClient());

        wrappedWebConnection_ = webConnection;
        reportBaseName_ = reportBaseName;
        javaScriptFile_ = File.createTempFile(reportBaseName_, ".js");
        createOverview();
    }
    
    
    /**
     * Calls the wrapped webconnection and save the received response.
     * @see com.gargoylesoftware.htmlunit.WebConnection#getResponse(WebRequestSettings)
     */
    public WebResponse getResponse(final WebRequestSettings webRequestSettings) throws IOException {
        final WebResponse response = wrappedWebConnection_.getResponse(webRequestSettings);
        saveResponse(response, webRequestSettings.getSubmitMethod());
        return response;
    }

    /**
     * Calls the wrapped webConnection
     * @see com.gargoylesoftware.htmlunit.WebConnection#getStateForUrl(java.net.URL)
     */
    public HttpState getStateForUrl(final URL url) {
        return wrappedWebConnection_.getStateForUrl(url);
    }

    
    /**
     * Save the response content in the temp dir and add it to the summary page
     * @param response the response to save
     * @param submitMethod the method used to get the response
     * @throws IOException if a problem occurs writing the file
     */
    private void saveResponse(final WebResponse response, final SubmitMethod submitMethod)
        throws IOException {
        counter_++;
        final String extension;
        if ("application/x-javascript".equals(response.getContentType())) {
            extension = ".js";
        }
        else if ("text/html".equals(response.getContentType())) {
            extension = ".html";
        }
        else {
            extension = ".txt";
        }
        final File f = File.createTempFile(reportBaseName_ + counter_ + "-", extension);
        final String content = response.getContentAsString();
        FileUtils.writeStringToFile(f, content, response.getContentCharSet());
        LOG.info("Created file " + f.getAbsolutePath()
                + " for response " + counter_ + ": " + response.getUrl());
        
        final FileWriter jsFileWriter = new FileWriter(javaScriptFile_, true);
        jsFileWriter.write("tab[tab.length] = {code: " + response.getStatusCode() + ", " 
                + "fileName: '" + f.getName() + "', "
                + "contentType: '" + response.getContentType() + "', "
                + "method: '" + submitMethod.getName() + "', "
                + "url: '" + response.getUrl() + "'}\n");
        jsFileWriter.close();
    }
    
    /**
     * Creates the summary file and the javascript file that will be updated for each received response
     * @throws IOException if a problem occurs writing the file
     */
    private void createOverview() throws IOException {
        
        FileUtils.writeStringToFile(javaScriptFile_, "var tab = [];\n", "ISO-8859-1");

        final File summary = new File(javaScriptFile_.getParentFile(), reportBaseName_ + ".html");
        final String content = "<html><head><title>Summary for " + reportBaseName_ + "</title>\n"
            + "<h1>Received responses</h1>\n"
            + "<script src='" + javaScriptFile_.getName() + "' type='text/javascript'></script>\n"
            + "</head>\n"
            + "<body>"
            + "<ol>\n"
            + "<script>\n"
            + "for (var i=0; i<tab.length; ++i) {\n"
            + "  var curRes = tab[i];\n"
            + "  document.writeln('<li>'"
            + " + curRes.code + ' ' + curRes.method + ' ' "
            + " + '<a href=\"' + curRes.fileName + '\" target=_blank>' + curRes.url + '</a> "
            + " (' + curRes.contentType + ')</li>');\n"
            + "}\n"
            + "</script>\n"
            + "</ol>"
            + "</body></html>";
        
        FileUtils.writeStringToFile(summary, content, "ISO-8859-1");
        LOG.info("Summary will be in " + summary.getAbsolutePath());
    }
}
