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
package com.gargoylesoftware.htmlunit.html;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.gargoylesoftware.htmlunit.WebTestCase;

/**
 * <p>Tests for all the generated attribute accessors. This test case will
 * dynamically generate tests for all the various attributes. The code
 * is fairly complicated but doing it this way is much easier than writing
 * individual tests for all the attributes.</p>
 *
 * <p>With the new custom DOM, this test has somewhat lost its significance.
 * We simply set and get the attributes and compare the results.</p>
 *
 * @version $Revision$
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author Christian Sell
 * @author Marc Guillemot
 * @author Ahmed Ashour
 */
public class AttributesTest extends TestCase {

    private final Class< ? > classUnderTest_;
    private final Method method_;
    private final HtmlPage page_;
    private final String attributeName_;

    private static final List<String> EXCLUDED_METHODS = new ArrayList<String>();
    static {
        EXCLUDED_METHODS.add("getHtmlElementsByAttribute");
        EXCLUDED_METHODS.add("getOneHtmlElementByAttribute");
        EXCLUDED_METHODS.add("getAttribute");
    }

    /**
     * Returns a test suite containing a separate test for each attribute on each element.
     *
     * @return the test suite
     * @throws Exception if the tests cannot be created
     */
    public static Test suite() throws Exception {
        final HtmlPage page = WebTestCase.loadPage("<html><head><title>foo</title></head><body></body></html>");

        final TestSuite suite = new TestSuite();
        final String[] classesToTest = {
            "HtmlAddress", "HtmlAnchor", "HtmlApplet", "HtmlArea",
            "HtmlBase", "HtmlBaseFont", "HtmlBidirectionalOverride",
            "HtmlBlockQuote", "HtmlBody", "HtmlBreak", "HtmlButton",
            "HtmlButtonInput", "HtmlCaption", "HtmlCenter",
            "HtmlCheckBoxInput", "HtmlDefinitionDescription",
            "HtmlDefinitionList", "HtmlDefinitionTerm",
            "HtmlDeletedText", "HtmlDivision", "HtmlElement",
            "HtmlFieldSet", "HtmlFileInput", "HtmlFont", "HtmlForm",
            "HtmlFrame", "HtmlFrameSet", "HtmlHead", "HtmlHeading1",
            "HtmlHeading2", "HtmlHeading3", "HtmlHeading4", "HtmlHeading5",
            "HtmlHeading6", "HtmlHiddenInput", "HtmlHorizontalRule",
            "HtmlImage", "HtmlImageInput", "HtmlInlineFrame",
            "HtmlInlineQuotation",
            "HtmlInsertedText", "HtmlIsIndex", "HtmlLabel",
            "HtmlLegend", "HtmlLink", "HtmlListItem", "HtmlMap",
            "HtmlMenu", "HtmlMeta", "HtmlNoFrames", "HtmlNoScript",
            "HtmlObject", "HtmlOption", "HtmlOptionGroup", "HtmlOrderedList",
            /*"HtmlPage",*/ "HtmlParagraph", "HtmlParameter", "HtmlPasswordInput",
            "HtmlPreformattedText", "HtmlRadioButtonInput", "HtmlResetInput",
            "HtmlScript", "HtmlSelect", "HtmlSpan", "HtmlStyle", "HtmlSubmitInput",
            "HtmlTable", "HtmlTableBody", /*"HtmlTableCell",*/ "HtmlTableColumn",
            "HtmlTableColumnGroup", "HtmlTableDataCell",
            "HtmlTableFooter", "HtmlTableHeader", "HtmlTableHeaderCell",
            "HtmlTableRow", "HtmlTextArea", "HtmlDirectory", "HtmlTextInput",
            "HtmlTitle", "HtmlUnorderedList"
        };
        for (final String testClass : classesToTest) {
            final Class< ? > clazz = Class.forName("com.gargoylesoftware.htmlunit.html." + testClass);
            addTestsForClass(clazz, page, suite);
        }
        return suite;
    }

    /**
     * Adds all the tests for a given class.
     *
     * @param clazz the class to create tests for
     * @param page the page that will be passed into the constructor of the objects to be tested
     * @param suite the suite that all the tests will be placed inside
     * @throws Exception if the tests cannot be created
     */
    private static void addTestsForClass(
            final Class< ? > clazz,
            final HtmlPage page,
            final TestSuite suite)
        throws
            Exception {

        final Method[] methods = clazz.getMethods();
        for (final Method method : methods) {
            final String methodName = method.getName();
            if (methodName.startsWith("get")
                && methodName.endsWith("Attribute")
                && !EXCLUDED_METHODS.contains(methodName)) {

                String attributeName = methodName.substring(3, methodName.length() - 9).toLowerCase();
                if (attributeName.equals("xmllang")) {
                    attributeName = "xml:lang";
                }
                else if (attributeName.equals("columns")) {
                    attributeName = "cols";
                }
                else if (attributeName.equals("columnspan")) {
                    attributeName = "colspan";
                }
                else if (attributeName.equals("textdirection")) {
                    attributeName = "dir";
                }
                else if (attributeName.equals("httpequiv")) {
                    attributeName = "http-equiv";
                }
                else if (attributeName.equals("acceptcharset")) {
                    attributeName = "accept-charset";
                }
                else if (attributeName.equals("htmlfor")) {
                    attributeName = "for";
                }
                suite.addTest(new AttributesTest(attributeName, clazz, method, page));
            }
        }
    }

    /**
     * Creates an instance of the test. This will test one specific attribute
     * on one specific class.
     * @param attributeName the name of the attribute to test
     * @param classUnderTest the class containing the attribute
     * @param method the "getter" method for the specified attribute
     * @param page the page that will be passed into the constructor of the object to be tested
     */
    public AttributesTest(
            final String attributeName,
            final Class< ? > classUnderTest,
            final Method method,
            final HtmlPage page) {

        super(createTestName(classUnderTest, method));
        classUnderTest_ = classUnderTest;
        method_ = method;
        page_ = page;
        attributeName_ = attributeName;
    }

    /**
     * Creates a name for this particular test that reflects the attribute being tested.
     * @param clazz the class containing the attribute
     * @param method the getter method for the attribute
     * @return the test name
     */
    private static String createTestName(final Class< ? > clazz, final Method method) {
        String className = clazz.getName();
        final int index = className.lastIndexOf('.');
        className = className.substring(index + 1);

        return "testAttributes_" + className + '_' + method.getName();
    }

    /**
     * Runs the actual test.
     * @throws Exception if the test fails
     */
    @Override
    protected void runTest() throws Exception {
        final String value = new String("value");

        final HtmlElement objectToTest = getNewInstanceForClassUnderTest();
        objectToTest.setAttributeValue(attributeName_, value);

        final Object noObjects[] = new Object[0];
        final Object result = method_.invoke(objectToTest, noObjects);
        assertSame(value, result);
    }

    /**
     * Creates a new instance of the class being tested.
     * @return the new instance
     * @throws Exception if the new object cannot be created
     */
    private HtmlElement getNewInstanceForClassUnderTest() throws Exception {
        final HtmlElement newInstance;
        if (classUnderTest_ == HtmlTableRow.class) {
            newInstance = HTMLParser.getFactory(HtmlTableRow.TAG_NAME).createElement(
                    page_, HtmlTableRow.TAG_NAME, null);
        }
        else if (classUnderTest_ == HtmlTableHeaderCell.class) {
            newInstance = HTMLParser.getFactory(HtmlTableHeaderCell.TAG_NAME).createElement(
                    page_, HtmlTableHeaderCell.TAG_NAME, null);
        }
        else if (classUnderTest_ == HtmlTableDataCell.class) {
            newInstance = HTMLParser.getFactory(HtmlTableDataCell.TAG_NAME).createElement(
                    page_, HtmlTableDataCell.TAG_NAME, null);
        }
        else {
            final String tagName = (String) classUnderTest_.getField("TAG_NAME").get(null);
            newInstance = new DefaultElementFactory().createElement(page_, tagName, null);
        }

        return newInstance;
    }
}
