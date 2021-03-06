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
package com.gargoylesoftware.htmlunit.javascript;

import org.apache.commons.logging.Log;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebAssert;
import com.gargoylesoftware.htmlunit.javascript.regexp.HtmlUnitRegExpProxy;

/**
 * ContextFactory that supports termination of scripts if they exceed a timeout. Based on example from
 * <a href="http://www.mozilla.org/rhino/apidocs/org/mozilla/javascript/ContextFactory.html">ContextFactory</a>.
 *
 * @version $Revision$
 * @author Andre Soereng
 * @author Ahmed Ashour
 */
public class HtmlUnitContextFactory extends ContextFactory {

    private final Log log_;
    private static final int INSTRUCTION_COUNT_THRESHOLD = 10000;
    private static long Timeout_ = 0;
    private static boolean DebuggerEnabled_;
    
    private static final ThreadLocal<BrowserVersion> browserVersion_ = new ThreadLocal<BrowserVersion>();

    /**
     * Creates a new instance of HtmlUnitContextFactory.
     *
     * @param log the log that the error reporter should use
     */
    public HtmlUnitContextFactory(final Log log) {
        WebAssert.notNull("log", log);
        log_ = log;
    }

    /**
     * Sets the number of milliseconds a script is allowed to execute before
     * being terminated. A value of 0 or less means no timeout.
     *
     * @param timeout the timeout value
     */
    public static void setTimeout(final long timeout) {
        Timeout_ = timeout;
    }

    /**
     * Returns the number of milliseconds a script is allowed to execute before
     * being terminated. A value of 0 or less means no timeout.
     *
     * @return the timeout value. Default value is 0
     */
    public static long getTimeout() {
        return Timeout_;
    }

    /**
     * Enables or disables the debugger, which logs stack entries and exceptions. Enabling the
     * debugger may be useful if HtmlUnit is having trouble with JavaScript, especially if you are
     * using some of the more advanced libraries like Dojo, Prototype or jQuery.
     *
     * @param enabled whether or not the debugger should be enabled
     * @see DebuggerImpl
     * @see DebugFrameImpl
     */
    public static void setDebuggerEnabled(final boolean enabled) {
        DebuggerEnabled_ = enabled;
    }

    /**
     * Returns <tt>true</tt> if the debugger is enabled, <tt>false</tt> otherwise.
     *
     * @return <tt>true</tt> if the debugger is enabled, <tt>false</tt> otherwise
     * @see DebuggerImpl
     * @see DebugFrameImpl
     */
    public static boolean getDebuggerEnabled() {
        return DebuggerEnabled_;
    }

    // Custom Context to store execution time.
    private static class TimeoutContext extends Context {
        private long startTime_;

        public void startClock() {
            startTime_ = System.currentTimeMillis();
        }

        public void terminateScriptIfNecessary() {
            if (Timeout_ > 0) {
                final long currentTime = System.currentTimeMillis();
                if (currentTime - startTime_ > Timeout_) {
                    // Terminate script by throwing an Error instance to ensure that the
                    // script will never get control back through catch or finally.
                    throw new TimeoutError(Timeout_, currentTime - startTime_);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Context makeContext() {
        final TimeoutContext cx = new TimeoutContext();

        // Use pure interpreter mode to get observeInstructionCount() callbacks.
        cx.setOptimizationLevel(-1);

        // Set threshold on how often we want to receive the callbacks
        cx.setInstructionObserverThreshold(INSTRUCTION_COUNT_THRESHOLD);

        cx.setErrorReporter(new StrictErrorReporter(log_));
        cx.setWrapFactory(new HtmlUnitWrapFactory());

        if (DebuggerEnabled_) {
            cx.setDebugger(new DebuggerImpl(), null);
        }

        // register custom RegExp processing
        ScriptRuntime.setRegExpProxy(cx, new HtmlUnitRegExpProxy(ScriptRuntime.getRegExpProxy(cx)));

        return cx;
    }

    /**
     * Run-time calls this when instruction counting is enabled and the counter
     * reaches limit set by setInstructionObserverThreshold(). A script can be
     * terminated by throwing an Error instance here.
     *
     * @param cx the context calling us
     * @param instructionCount amount of script instruction executed since last call to observeInstructionCount
     */
    @Override
    protected void observeInstructionCount(final Context cx, final int instructionCount) {
        final TimeoutContext tcx = (TimeoutContext) cx;
        tcx.terminateScriptIfNecessary();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object doTopCall(final Callable callable,
            final Context cx, final Scriptable scope,
            final Scriptable thisObj, final Object[] args) {

        final TimeoutContext tcx = (TimeoutContext) cx;
        tcx.startClock();
        return super.doTopCall(callable, cx, scope, thisObj, args);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean hasFeature(final Context cx, final int featureIndex) {
        if (Context.FEATURE_RESERVED_KEYWORD_AS_IDENTIFIER == featureIndex) {
            return true;
        }
        else if (Context.FEATURE_PARENT_PROTO_PROPERTIES == featureIndex) {
            return !browserVersion_.get().isIE();
        }
        else {
            return super.hasFeature(cx, featureIndex);
        }
    }
    /**
     * Puts the specified {@link BrowserVersion} as a thread local variable.
     * @param browserVersion the BrowserVersion that is currently used
     */
    public static void putThreadLocal(final BrowserVersion browserVersion) {
        browserVersion_.set(browserVersion);
    }
}
