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
import java.util.List;

/**
 * A simple alert handler that keeps track of alerts in a list.
 *
 * @version  $Revision$
 * @author  <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 */
public class CollectingAlertHandler implements AlertHandler {
    private final List collectedAlerts_;

    /**
     * Create an instance with an ArrayList
     */
    public CollectingAlertHandler() {
        this( new ArrayList() );
    }


    /**
     * Create an instance with the specified list.
     *
     * @param list The list to store alerts in.
     */
    public CollectingAlertHandler( final List list ) {
        Assert.notNull("list",list);
        collectedAlerts_ = list;
    }


    /**
     * Handle the alert.  This implementation will store the message in a list
     * for retrieval later.
     *
     * @param page The page that triggered the alert
     * @param message The message in the alert.
     */
    public void handleAlert( final Page page, final String message ) {
        collectedAlerts_.add(message);
    }


    /**
     * Return a list containing the message portion of any collected alerts.
     * @return a list of alert messages
     */
    public List getCollectedAlerts() {
        return collectedAlerts_;
    }
}
