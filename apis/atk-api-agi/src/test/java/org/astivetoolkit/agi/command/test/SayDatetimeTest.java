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
package org.astivetoolkit.agi.command.test;

import java.util.Date;
import java.util.TimeZone;
import junit.framework.TestCase;
import org.astivetoolkit.agi.AgiException;
import org.astivetoolkit.agi.CommandProcessor;
import org.astivetoolkit.agi.command.SayDatetime;

/**
 * Test case for command {@link org.astivetoolkit.agi.command.SayDatetime}.
 * 
 * @since 1.0.0
 */
public class SayDatetimeTest extends TestCase {
  /**
   * Creates a new SayDatetimeTest object.
   *
   * @param testName {@inheritDoc}.
   */
  public SayDatetimeTest(String testName) {
    super(testName);
  }

    /**
   * Test method.
   *
   * @throws AgiException if command is malformed.
   */
  public void testCommand() throws AgiException {
    Date date = new Date();
    long seconds = (date).getTime() / 1000;
    String escapeDigits;
    String format = SayDatetime.DEFAULT_FORMAT;
    TimeZone tz = TimeZone.getDefault();

    // Testing first constructor
    StringBuilder b = new StringBuilder("SAY DATETIME");
    b.append(" ");
    b.append("\"");
    b.append(seconds);
    b.append("\"");
    b.append(" ");
    b.append("\"");
    b.append("\"");

    SayDatetime command = new SayDatetime(date);
    assertEquals(b.toString(), CommandProcessor.buildCommand(command));

    // Testing second constructor
    escapeDigits = "123";
    b = new StringBuilder("SAY DATETIME");
    b.append(" ");
    b.append("\"");
    b.append(seconds);
    b.append("\"");
    b.append(" ");
    b.append("\"");
    b.append(escapeDigits);
    b.append("\"");

    command = new SayDatetime(date, escapeDigits);
    assertEquals(b.toString(), CommandProcessor.buildCommand(command));

    // Testing third constructor
    b.append(" ");
    b.append("\"");
    b.append(format);
    b.append("\"");

    command = new SayDatetime(date, escapeDigits, format);
    assertEquals(b.toString(), CommandProcessor.buildCommand(command));

    // Testing last constructor
    b.append(" ");
    b.append("\"");
    b.append(tz.getID());
    b.append("\"");

    command = new SayDatetime(date, escapeDigits, format, tz);
    assertEquals(b.toString(), CommandProcessor.buildCommand(command));
  }
}
