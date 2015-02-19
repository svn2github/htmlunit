/*
 * Copyright (c) 2002-2015 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gargoylesoftware.htmlunit;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.BrowserRunner.Alerts;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

/**
 * Unit tests for {@link CookieManager}.
 * These test requirering a special setup in you etc/hosts
 * 127.0.0.1       host1.htmlunit.org
 * 127.0.0.1       host2.htmlunit.org
 *
 * @version $Revision$
 * @author Ronald Brill
 */
@RunWith(BrowserRunner.class)
@Ignore("Requires a special configuration; see class comment for more")
public class CookieManager4Test extends WebDriverTestCase {

    private static final String DOMAIN = "htmlunit.org";
    private static final String HOST1 = "http://host1." + DOMAIN + ":" + PORT;
    private static final String HOST2 = "http://host2." + DOMAIN + ":" + PORT;

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts(DEFAULT = "cross-domain=1")
    public void differentHostsSameDomain() throws Exception {
        final List<NameValuePair> responseHeader1 = new ArrayList<>();
        responseHeader1.add(new NameValuePair("Set-Cookie", "cross-domain=1; Domain=.htmlunit.org; Path=/"));

        final String html = "<html>\n"
            + "<head></head>\n"
            + "<body>\n"
            + "<p>Cookie Domain Test</p>\n"
            + "<script>\n"
            + "  location.replace('" + HOST2 + "/');\n"
            + "</script>\n"
            + "</body>\n"
            + "</html>";

        getMockWebConnection().setDefaultResponse(CookieManagerTest.HTML_ALERT_COOKIE);
        final URL firstUrl = new URL(HOST1 + "/");
        getMockWebConnection().setResponse(firstUrl, html, 200, "Ok", "text/html", responseHeader1);

        loadPageWithAlerts2(firstUrl);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts(DEFAULT = "cross-domain=1")
    public void differentHostsSameDomainCookieFromJS() throws Exception {
        final String html = "<html>\n"
            + "<head>\n"
            + "</head>\n"
            + "<body>\n"
            + "<p>Cookie Domain Test</p>\n"
            + "<script>\n"
            + "  document.cookie='cross-domain=1; Domain=.htmlunit.org; Path=/';\n"
            + "  location.replace('" + HOST2 + "/');\n"
            + "</script>\n"
            + "</body>\n"
            + "</html>";

        getMockWebConnection().setDefaultResponse(CookieManagerTest.HTML_ALERT_COOKIE);
        final URL firstUrl = new URL(HOST1 + "/");
        getMockWebConnection().setResponse(firstUrl, html);

        loadPageWithAlerts2(firstUrl);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts(DEFAULT = "cross-domain=1")
    public void differentHostsSameDomainCookieFromMeta() throws Exception {
        final String html = "<html>\n"
            + "<head>\n"
            + "  <meta http-equiv='Set-Cookie', content='cross-domain=1; Domain=.htmlunit.org; Path=/'>"
            + "</head>\n"
            + "<body>\n"
            + "<p>Cookie Domain Test</p>\n"
            + "<script>\n"
            + "  location.replace('" + HOST2 + "/');\n"
            + "</script>\n"
            + "</body>\n"
            + "</html>";

        getMockWebConnection().setDefaultResponse(CookieManagerTest.HTML_ALERT_COOKIE);
        final URL firstUrl = new URL(HOST1 + "/");
        getMockWebConnection().setResponse(firstUrl, html);

        loadPageWithAlerts2(firstUrl);
    }
}
