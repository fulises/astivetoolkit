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

import junit.framework.TestCase;
import org.astivetoolkit.agi.AgiException;
import org.astivetoolkit.agi.CommandProcessor;
import org.astivetoolkit.agi.command.SayPhonetic;

/**
 * Test case for command {@link org.astivetoolkit.agi.command.SayPhonetic}.
 * 
 * @since 1.0.0
 */
public class SayPhoneticTest extends TestCase {
  /**
   * Creates a new SayPhoneticTest object.
   *
   * @param testName {@inherit}
   */
  public SayPhoneticTest(String testName) {
    super(testName);
  }

  /**
   * Test method.
   *
   * @throws AgiException if command is malformed.
   */  
  public void testCommand() throws AgiException {
    String text = "abc";
    String escapeDigits;

    // Testing first constructor
    StringBuilder b = new StringBuilder("SAY PHONETIC");
    b.append(" ");
    b.append("\"");
    b.append(text);
    b.append("\"");
    b.append(" ");
    b.append("\"");
    b.append("\"");

    SayPhonetic command = new SayPhonetic(text);
    assertEquals(b.toString(), CommandProcessor.buildCommand(command));

    // Testing second constructor
    escapeDigits = "123";
    b = new StringBuilder("SAY PHONETIC");
    b.append(" ");
    b.append("\"");
    b.append(text);
    b.append("\"");
    b.append(" ");
    b.append("\"");
    b.append(escapeDigits);
    b.append("\"");

    command = new SayPhonetic(text, escapeDigits);
    assertEquals(b.toString(), CommandProcessor.buildCommand(command));
  }
}
