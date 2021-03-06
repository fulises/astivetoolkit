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
package org.astivetoolkit.menu.action;

import java.util.HashMap;

/**
 *
 * @since 1.0.0
 * @see ActionEvent
 * @see Action
 */
public class ActionMap {

    private ActionMap parent;
    private HashMap actions;

    /**
     * Creates a new ActionMap object.
     */
    public ActionMap() {
        actions = new HashMap();
    }

    /**
     * @return Returns an array of the keys defined in this ActionMap and its
     * parent.
     */
    public Object[] allKeys() {
        // to be implemented
        return null;
    }

    public void clear() {
        actions.clear();
    }

    public Action get(Object key) {
        return (Action) actions.get(key);
    }

    public ActionMap getParent() {
        return parent;
    }

    public Object[] keys() {
        return actions.entrySet().toArray();
    }

    public void put(Object key, Action action) {
        actions.put(key, action);
    }

    public void remove(Object key) {
        actions.remove(key);
    }

    public void setParent(ActionMap map) {
        parent = map;
    }

    public int size() {
        return actions.size();
    }
}
