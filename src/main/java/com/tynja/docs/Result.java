/*
* Copyright 2012 Tommy Tynjä
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.tynja.docs;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Abstract result class to use in the Documentation runner.
 *
 * @author Tommy Tynj&auml;
 */
public abstract class Result {

    private String category;
    private String title;
    private Map<String, String> parameters = new TreeMap<>();
    private Map<String, Object> results = new TreeMap<>();

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void addParameter(final String name, final String value) {
        parameters.put(name, value);
    }

    public void addResult(final String name, final Object value) {
        if (name != null && name.startsWith("get")) {
            results.put(name.substring(3), value);
        } else {
            results.put(name, value);
        }
    }

    public Map<String, String> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }

    public Map<String, Object> getResults() {
        return Collections.unmodifiableMap(results);
    }

    public abstract void produce();
}
