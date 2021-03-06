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
 * Deletes a <code>family</code> or specific <code>keytree</code> within a
 * <code>family</code> in the Asterisk database.
 *
 * <p>Returns 1 if successful, 0 otherwise.
 *
 * @since 1.0.0
 * @see DatabaseDel
 */
@AgiCommand(command = "DATABASE DELTREE")
public class DatabaseDelTree implements Serializable {
  /**
   * Serial version identifier.
   */
  private static final long serialVersionUID = -6776258350511425471L;

  /**
   * Database family.
   */
  @Parameter(optional = false)
  private String family;

  /**
   * Keytree within family.
   */
  @Parameter(position = 1)
  private String keyTree;

  /**
   * Create a new DatabaseDelTree object to delete a database family.
   *
   * @param family database family.
   */
  public DatabaseDelTree(final String family) {
    this.family = family;
  }

  /**
   * Create a new DatabaseDelTree object to delete a keytree within a database
   * family.
   *
   * @param family database family.
   * @param keyTree keytree to be deleted.
   */
  public DatabaseDelTree(final String family, final String keyTree) {
    this.family = family;
    this.keyTree = keyTree;
  }

  /**
   * Get database family.
   *
   * @return database family.
   */
  public String getFamily() {
    return family;
  }

  /**
   * Get family keytree.
   *
   * @return family keytree.
   */
  public String getKeyTree() {
    return keyTree;
  }

  /**
   * Set database family.
   *
   * @param family database family.
   */
  public void setFamily(final String family) {
    this.family = family;
  }

  /**
   * Set family keytree.
   *
   * @param keyTree family keytree.
   */
  public void setKeyTree(final String keyTree) {
    this.keyTree = keyTree;
  }
}
