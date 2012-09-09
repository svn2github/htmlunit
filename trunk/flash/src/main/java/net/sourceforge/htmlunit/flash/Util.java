/*
 * Copyright (c) 2002-2012 Gargoyle Software Inc.
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
package net.sourceforge.htmlunit.flash;

import java.lang.reflect.Field;

import macromedia.asc.embedding.avmplus.ActionBlockConstants;

public class Util {

    public static String getOpcodeName(final int op) {
        try {
            for (final Field f : ActionBlockConstants.class.getFields()) {
                if (f.getName().startsWith("OP_") && (Integer) f.get(null) == op) {
                    return f.getName().substring(3);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
