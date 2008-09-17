/*
 * Copyright (c) 2002-2008 Gargoyle Software Inc.
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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Class which centralizes proxy configuration, in an effort to reduce clutter in the {@link WebClient}
 * class. One instance of this class exists for each <tt>WebClient</tt> instance.
 *
 * @version $Revision$
 * @author Daniel Gredler
 * @see WebClient#getProxyConfig()
 */
public class ProxyConfig implements Serializable {

    private static final long serialVersionUID = -9164636437071690421L;

    private String proxyHost_;
    private int proxyPort_;
    private final Map<String, Pattern> proxyBypassHosts_;

    /**
     * Creates a new instance.
     */
    public ProxyConfig() {
        this(null, 0);
    }

    /**
     * Creates a new instance.
     * @param proxyHost the proxy host
     * @param proxyPort the proxy port
     */
    public ProxyConfig(final String proxyHost, final int proxyPort) {
        proxyHost_ = proxyHost;
        proxyPort_ = proxyPort;
        proxyBypassHosts_ = new HashMap<String, Pattern>();
    }

    /**
     * Returns the proxy host used to perform HTTP requests.
     * @return the proxy host used to perform HTTP requests
     */
    public String getProxyHost() {
        return proxyHost_;
    }

    /**
     * Sets the proxy host used to perform HTTP requests.
     * @param proxyHost the proxy host used to perform HTTP requests
     */
    public void setProxyHost(final String proxyHost) {
        proxyHost_ = proxyHost;
    }

    /**
     * Returns the proxy port used to perform HTTP requests.
     * @return the proxy port used to perform HTTP requests
     */
    public int getProxyPort() {
        return proxyPort_;
    }

    /**
     * Sets the proxy port used to perform HTTP requests.
     * @param proxyPort the proxy port used to perform HTTP requests
     */
    public void setProxyPort(final int proxyPort) {
        proxyPort_ = proxyPort;
    }

    /**
     * Any hosts matched by the specified regular expression pattern will bypass the configured proxy.
     * @param pattern a regular expression pattern that matches the hostnames of the hosts which should
     *                bypass the configured proxy.
     * @see Pattern
     */
    public void addHostsToProxyBypass(final String pattern) {
        proxyBypassHosts_.put(pattern, Pattern.compile(pattern));
    }

    /**
     * Any hosts matched by the specified regular expression pattern will no longer bypass the configured proxy.
     * @param pattern the previously added regular expression pattern
     * @see Pattern
     */
    public void removeHostsFromProxyBypass(final String pattern) {
        proxyBypassHosts_.remove(pattern);
    }

    /**
     * Returns <tt>true</tt> if the host with the specified hostname should be accessed bypassing the
     * configured proxy.
     * @param hostname the name of the host to check
     * @return <tt>true</tt> if the host with the specified hostname should be accessed bypassing the
     * configured proxy, <tt>false</tt> otherwise.
     */
    protected boolean shouldBypassProxy(final String hostname) {
        boolean bypass = false;
        for (final Pattern p : proxyBypassHosts_.values()) {
            if (p.matcher(hostname).find()) {
                bypass = true;
                break;
            }
        }
        return bypass;
    }

}
