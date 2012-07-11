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

/**
 * @author Tommy Tynj&auml;
 */
public class ArbitraryObject {

    private String value;

    @Documented
    public String className() {
        return this.getClass().getName();
    }

    @Documented
    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ArbitraryObject{" +
                "value='" + value + '\'' +
                '}';
    }
}
