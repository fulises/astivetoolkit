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
package org.astivetoolkit.menu;

import org.astivetoolkit.agi.command.SayAlpha;
import org.astivetoolkit.agi.command.SayPhonetic;
import org.astivetoolkit.agi.command.StreamFile;
import org.astivetoolkit.agi.command.SayDate;
import org.astivetoolkit.agi.command.SayNumber;
import org.astivetoolkit.agi.command.SayDigits;
import org.astivetoolkit.agi.command.SayTime;
import org.astivetoolkit.agi.command.SayDatetime;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @since 1.0.0
 */
public class VoiceComposer {
  private static ArrayList<Object> commands;
  private static String escapeDigits;
  private static String format;
  private static TimeZone timeZone;
  private static VoiceComposer instance = new VoiceComposer();
  private static int offset;

  //hide the constructor
  private VoiceComposer() {
    commands = new ArrayList<Object>();
  }

  public static VoiceComposer addSilence(int silence) {
    if (hasEscapeDigits()) {
      commands.add(new StreamFile("silence/" + silence, VoiceComposer.escapeDigits));
    } else {
      commands.add(new StreamFile("silence/" + silence));
    }

    return instance;
  }

  public static VoiceComposition create() {
    VoiceComposition vc = new VoiceComposition(VoiceComposer.commands);
    VoiceComposer.reset();

    return vc;
  }

  private static boolean hasEscapeDigits() {
    return (escapeDigits != null) && (escapeDigits.length() != 0x0);
  }

  private static boolean hasFormat() {
    return (VoiceComposer.format != null) ;
  }

  private static boolean hasOffset() {
     return (VoiceComposer.offset == 0x0);
  }

  private static boolean hasTimeZone() {
    return (VoiceComposer.timeZone != null);    
  }

  private static void reset() {
    VoiceComposer.escapeDigits = "";
    VoiceComposer.commands = new ArrayList<Object>();
    VoiceComposer.format = "";
    VoiceComposer.timeZone = null;
    VoiceComposer.offset = 0x0;
  }

  public static VoiceComposer sayAlpha(final String text) {
    if (hasEscapeDigits()) {
      commands.add(new SayAlpha(text, VoiceComposer.escapeDigits));
    } else {
      commands.add(new SayAlpha(text));
    }

    return instance;
  }

  public static VoiceComposer sayDate(final Date date) {
    if (hasEscapeDigits()) {
      commands.add(new SayDate(date, VoiceComposer.escapeDigits));
    } else {
      commands.add(new SayDate(date));
    }

    return instance;
  }

  public static VoiceComposer sayDatetime(final Date datetime) {
    if (hasEscapeDigits()) {
      commands.add(new SayDatetime(datetime, VoiceComposer.escapeDigits));
    } else {
      commands.add(new SayDatetime(datetime));
    }

    return instance;
  }

  public static VoiceComposer sayDigits(final String digits) {
    if (hasEscapeDigits()) {
      commands.add(new SayDigits(digits, VoiceComposer.escapeDigits));
    } else {
      commands.add(new SayDigits(digits));
    }

    return instance;
  }

  public static VoiceComposer sayNumber(int number) {
    if (hasEscapeDigits()) {
      commands.add(new SayNumber(number, VoiceComposer.escapeDigits));
    } else {
      commands.add(new SayNumber(number));
    }

    return instance;
  }

  public static VoiceComposer sayPhonetic(String text) {
    if (hasEscapeDigits()) {
      commands.add(new SayPhonetic(text, VoiceComposer.escapeDigits));
    } else {
      commands.add(new SayPhonetic(text));
    }

    return instance;
  }

  public static VoiceComposer sayTime(Date time) {
    if (hasEscapeDigits()) {
      commands.add(new SayTime(time, VoiceComposer.escapeDigits));
    } else {
      commands.add(new SayTime(time));
    }

    return instance;
  }

  public static VoiceComposer streamFile(String file) {
    if (hasEscapeDigits()) {
      commands.add(new StreamFile(file, VoiceComposer.escapeDigits));
    } else {
      commands.add(new StreamFile(file));
    }

    return instance;
  }

  public static VoiceComposer withEscapeDigits(String escapeDigits) {
    VoiceComposer.escapeDigits = escapeDigits;

    return instance;
  }

  public static VoiceComposer withFormat(String format) {
    VoiceComposer.format = format;

    return instance;
  }

  public static VoiceComposer withOffset(int offset) {
    VoiceComposer.offset = offset;

    return instance;
  }

  public static VoiceComposer withTimeZone(TimeZone timeZone) {
    VoiceComposer.timeZone = timeZone;

    return instance;
  }
}
