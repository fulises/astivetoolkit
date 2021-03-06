/*
 * Copyright (C) 2010-2013 by PhonyTive LLC (http://phonytive.com)
 * http://astivetoolkit.org
 *
 * This file is part of Astive Toolkit(ATK)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.astivetoolkit.util.test;

import java.util.Locale;
import junit.framework.TestCase;
import org.astivetoolkit.util.AppLocale;

/**
 * Test case for {@link org.astivetoolkit.util.AppLocale}.
 *
 * @since 1.0.0
 */
public class AppLocaleTest extends TestCase {

    /**
     * Creates a new AppLocaleTest object.
     */
    public AppLocaleTest() {
    }

    /**
     * Test method.
     */
    public void testAppLocale() {
        // This test only work if the Locale is English
        if(Locale.getDefault() == Locale.ENGLISH) {
            assertEquals(AppLocale.getI18n("messageTest", new Object[]{"test"}), "This is a test.");
        }
    }
}
