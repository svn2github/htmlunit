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
package com.gargoylesoftware.htmlunit;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

/**
 * Test of coding style for things that can not be detected by Checkstyle.
 *
 * @version $Revision$
 * @author Ahmed Ashour
 */
public class CodeStyleTest extends WebTestCase {

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void testIsDynamicContent() throws Exception {
        process(new File("src/main/java"));
        process(new File("src/test/java"));
    }

    private void process(final File dir) throws IOException {
        for (final File f : dir.listFiles()) {
            if (f.isDirectory() && !f.getName().equals(".svn")) {
                process(f);
            }
            else {
                if (f.getName().endsWith(".java")) {
                    final List<String> lines = getLines(f);
                    final String relativePath = f.getAbsolutePath().substring(
                        new File(".").getAbsolutePath().length() - 1);
                    openingCurlyBracket(lines, relativePath);
                    year(lines, relativePath);
                }
            }
        }
    }

    /**
     * Ensures that no opening curly bracket exists by itself in a single line.
     */
    private void openingCurlyBracket(final List<String> lines, final String path) throws IOException {
        int index = 1;
        for (final String line : lines) {
            if (line.trim().equals("{")) {
                fail("Opening curly bracket is alone at " + path + ", line: " + index);
            }
            index++;
        }
    }

    /**
     * Checks the year in the license.
     */
    private void year(final List<String> lines, final String path) throws IOException {
        assertTrue("Incorrect year in " + path, lines.get(1).contains("Copyright (c) 2002-"
            + Calendar.getInstance().get(Calendar.YEAR)));
    }

    /**
     * Reads the given file as lines.
     * @param file file to read.
     * @return the list of lines.
     * @throws IOException if an error occurs.
     */
    public static List<String> getLines(final File file) throws IOException {
        final List<String> rv = new ArrayList<String>();
        final BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            rv.add(line);
        }
        reader.close();
        return rv;
    }

}
