/*

   Copyright 2002-2003  The Apache Software Foundation 

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package org.apache.flex.forks.batik.util;

import java.util.Locale;
import java.util.MissingResourceException;

import org.apache.flex.forks.batik.i18n.LocalizableSupport;
import org.apache.flex.forks.batik.util.gui.resource.ResourceManager;

/**
 * This class manages the message for the security utilities
 *
 * @author <a href="mailto:vhardy@apache.org">Vincent Hardy</a>
 * @version $Id$
 */
public class Messages {

    /**
     * This class does not need to be instantiated.
     */
    protected Messages() { }

    /**
     * The error messages bundle class name.
     */
    protected final static String RESOURCES =
        "org.apache.flex.forks.batik.util.resources.Messages";

    /**
     * The localizable support for the error messages.
     */
    protected static LocalizableSupport localizableSupport =
        new LocalizableSupport(RESOURCES, Messages.class.getClassLoader());

    /**
     * The resource manager to decode messages.
     */
    protected static ResourceManager resourceManager =
        new ResourceManager(localizableSupport.getResourceBundle());

    /**
     * Implements {@link org.apache.flex.forks.batik.i18n.Localizable#setLocale(Locale)}.
     */
    public static void setLocale(Locale l) {
        localizableSupport.setLocale(l);
        resourceManager = new ResourceManager(localizableSupport.getResourceBundle());
    }

    /**
     * Implements {@link org.apache.flex.forks.batik.i18n.Localizable#getLocale()}.
     */
    public static Locale getLocale() {
        return localizableSupport.getLocale();
    }

    /**
     * Implements {@link
     * org.apache.flex.forks.batik.i18n.Localizable#formatMessage(String,Object[])}.
     */
    public static String formatMessage(String key, Object[] args)
        throws MissingResourceException {
        return localizableSupport.formatMessage(key, args);
    }

    public static String getString(String key)
        throws MissingResourceException {
        return resourceManager.getString(key);
    }

    public static int getInteger(String key) 
        throws MissingResourceException {
        return resourceManager.getInteger(key);
    }

    public static int getCharacter(String key)
        throws MissingResourceException {
        return resourceManager.getCharacter(key);
    }
}
