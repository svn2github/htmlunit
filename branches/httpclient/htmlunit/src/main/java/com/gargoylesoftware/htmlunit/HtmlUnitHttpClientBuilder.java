/*
 * Copyright (c) 2002-2014 Gargoyle Software Inc.
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

import java.lang.reflect.Field;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.TextUtils;

/**
 * A helper class for configuring {@link HttpClientBuilder}.
 *
 * @version $Revision$
 * @author Ahmed Ashour
 */
class HtmlUnitHttpClientBuilder {

    public static void configureConnectionManager(final HttpClientBuilder builder) {
        final ConnectionSocketFactory socketFactory = new SocksConnectionSocketFactory();
        
        LayeredConnectionSocketFactory sslSocketFactory = (LayeredConnectionSocketFactory) getField(builder, "sslSocketFactory");
        final SocketConfig defaultSocketConfig = (SocketConfig) getField(builder, "defaultSocketConfig");
        final ConnectionConfig defaultConnectionConfig = (ConnectionConfig) getField(builder, "defaultConnectionConfig");
        final boolean systemProperties = (Boolean) getField(builder, "systemProperties");
        final int maxConnTotal = (Integer) getField(builder, "maxConnTotal");
        final int maxConnPerRoute = (Integer) getField(builder, "maxConnPerRoute");
        X509HostnameVerifier hostnameVerifier = (X509HostnameVerifier) getField(builder, "hostnameVerifier");
        final SSLContext sslcontext = (SSLContext) getField(builder, "sslcontext");

        if (sslSocketFactory == null) {
            final String[] supportedProtocols = systemProperties ? split(
                    System.getProperty("https.protocols")) : null;
            final String[] supportedCipherSuites = systemProperties ? split(
                    System.getProperty("https.cipherSuites")) : null;
            if (hostnameVerifier == null) {
                hostnameVerifier = SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
            }
            if (sslcontext != null) {
                sslSocketFactory = new SSLConnectionSocketFactory(
                        sslcontext, supportedProtocols, supportedCipherSuites, hostnameVerifier);
            } else {
                if (systemProperties) {
                    sslSocketFactory = new SSLConnectionSocketFactory(
                            (SSLSocketFactory) SSLSocketFactory.getDefault(),
                            supportedProtocols, supportedCipherSuites, hostnameVerifier);
                } else {
                    sslSocketFactory = new SSLConnectionSocketFactory(
                            SSLContexts.createDefault(),
                            hostnameVerifier);
                }
            }
        }

        final PoolingHttpClientConnectionManager poolingmgr = new PoolingHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", socketFactory)
                    .register("https", sslSocketFactory)
                    .build());
        if (defaultSocketConfig != null) {
            poolingmgr.setDefaultSocketConfig(defaultSocketConfig);
        }
        if (defaultConnectionConfig != null) {
            poolingmgr.setDefaultConnectionConfig(defaultConnectionConfig);
        }
        if (systemProperties) {
            String s = System.getProperty("http.keepAlive", "true");
            if ("true".equalsIgnoreCase(s)) {
                s = System.getProperty("http.maxConnections", "5");
                final int max = Integer.parseInt(s);
                poolingmgr.setDefaultMaxPerRoute(max);
                poolingmgr.setMaxTotal(2 * max);
            }
        }
        if (maxConnTotal > 0) {
            poolingmgr.setMaxTotal(maxConnTotal);
        }
        if (maxConnPerRoute > 0) {
            poolingmgr.setDefaultMaxPerRoute(maxConnPerRoute);
        }
        builder.setConnectionManager(poolingmgr);
    }

    private static String[] split(final String s) {
        if (TextUtils.isBlank(s)) {
            return null;
        }
        return s.split(" *, *");
    }

    private static Object getField(final HttpClientBuilder builder, final String fieldName) {
        try {
            final Field field = HttpClientBuilder.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(builder);
        }
        catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
