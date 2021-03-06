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
package org.astivetoolkit.examples.ws.test;

import junit.framework.TestCase;
import org.astivetoolkit.examples.ws.Weather;
import org.astivetoolkit.examples.ws.WeatherAPI;

/**
 * Yahoo Weather example.
 *
 * @since 1.0.0
 */
public class WeatherAPITest extends TestCase {

    private String zip;

    public WeatherAPITest(String testName) {
        super(testName);
        // Spring Valley, NY.
        zip = "10977";
    }

    public void testWeatherAPI() throws Exception {
        Weather w = WeatherAPI.getWeather(zip);
        String country = w.getCountry();
        String region = w.getRegion();
        String city = w.getCity();

        assert (country.equals("US"));
        assert (region.equals("NY"));
        assert (city.equals("Spring Valley"));
    }
}
