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

import org.apache.commons.httpclient.methods.PostMethod;

/**
 * A collection of constants that represent the various ways a form can be encoded when submitted.
 *
 * @version $Revision$
 * @author Brad Clarke
 * @author Ahmed Ashour
 */
public final class FormEncodingType implements Serializable {

    private static final long serialVersionUID = -7341913381207910442L;

    /** URL-encoded form encoding. */
    public static final FormEncodingType URL_ENCODED = new FormEncodingType(PostMethod.FORM_URL_ENCODED_CONTENT_TYPE);

    /** Multipart form encoding (used to be a constant in HttpClient but it was deprecated with no alternative). */
    public static final FormEncodingType MULTIPART = new FormEncodingType("multipart/form-data");

    private final String name_;

    private FormEncodingType(final String name) {
        name_ = name;
    }

    /**
     * Returns the name of this encoding type.
     *
     * @return the name of this encoding type
     */
    public String getName() {
        return name_;
    }

    /**
     * Returns the constant that matches the specified name.
     *
     * @param name the name to search by
     * @return the constant that matches the specified name
     */
    public static FormEncodingType getInstance(final String name) {
        final String lowerCaseName = name.toLowerCase();

        for (final FormEncodingType type : new FormEncodingType[] {URL_ENCODED, MULTIPART}) {
            if (type.getName().equals(lowerCaseName)) {
                return type;
            }
        }

        // Special case: empty string defaults to URL encoded
        if (name.equals("")) {
            return URL_ENCODED;
        }

        throw new IllegalArgumentException("No encoding type found for [" + name + "]");
    }

    /**
     * Returns a string representation of this object.
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "EncodingType[name=" + getName() + "]";
    }
}
