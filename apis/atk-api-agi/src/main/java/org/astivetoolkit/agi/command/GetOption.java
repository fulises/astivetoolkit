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
package org.astivetoolkit.agi.command;

import java.io.Serializable;
import org.astivetoolkit.agi.annotation.AgiCommand;
import org.astivetoolkit.agi.annotation.Parameter;

/**
 * Behaves similar to {@link StreamFile} but used with a timeout option.
 *
 * @since 1.0.0
 */
@AgiCommand(command = "GET OPTION")
public class GetOption implements Serializable {
  /**
   * Serial version identifier.
   */
  private static final long serialVersionUID = -5788144017301928558L;

  /**
   * Time in milliseconds to wait for DTMF.
   * <p>With timeout set to -1 this command wait forever.
   */
  @Parameter(position = 2)
  private Integer timeout;

  /**
   * Can be use to the interrupt the audio on a channel.
   */
  @Parameter(position = 1, optional = false)
  private String escapeDigits;

  /**
   * Audio to send to channel.
   */
  @Parameter(optional = false)
  private String file;

  /**
   * Create a new GetOption object with audio an escape digits. With this
   * constructor the command wait until a digit in escapeDigits is press.
   *
   * @param file audio to stream.
   * @param escapeDigits digits to interrupt the audio.
   */
  public GetOption(String file, String escapeDigits) {
    this.file = file;
    this.escapeDigits = escapeDigits;
    this.timeout = -1;
  }

  /**
   * Create a new GetOption object with audio an escape digits. With this
   * constructor the command wait until a digit in escapeDigits is press.
   *
   * @param file audio to stream.
   * @param escapeDigits digits to interrupt the audio.
   * @param timeout time in milliseconds to wait for DTMF digit.
   */
  public GetOption(String file, String escapeDigits, Integer timeout) {
    this.file = file;
    this.escapeDigits = escapeDigits;
    this.timeout = timeout;
  }

  /**
   * Get the digits used to interrupt the audio.
   *
   * @return digits used to interrupt the audio.
   */
  public String getEscapeDigits() {
    return escapeDigits;
  }

  /**
   * Get audio to be send to <code>channel</code>.
   *
   * @return audio to be send to <code>channel</code>.
   */
  public String getFile() {
    return file;
  }

  /**
   * Get timeout(in milliseconds).
   *
   * @return time in milliseconds.
   */
  public Integer getTimeout() {
    return timeout;
  }

  /**
   * Set the digits to be use to interrupt the audio.
   *
   * @param escapeDigits digits to be use to interrupt the audio.
   */
  public void setEscapeDigits(String escapeDigits) {
    this.escapeDigits = escapeDigits;
  }

  /**
   * Set audio to be send to <code>channel</code>. Keep in mind that
   * the audio must not contain any extension.
   *
   * @param file audio to be send to <code>channel</code>
   */
  public void setFile(String file) {
    this.file = file;
  }

  /**
   * Set timeout(in milliseconds).
   *
   * @param timeout time in milliseconds
   */
  public void setTimeout(Integer timeout) {
    this.timeout = timeout;
  }
}
