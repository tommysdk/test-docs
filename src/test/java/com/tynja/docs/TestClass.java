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

import com.tynja.docs.junit.Documentation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Tommy Tynj&auml;
 */
@RunWith(Documentation.class)
public class TestClass {

    @Test
    @Excluded
    public void standardTestCase() {
    }

    @Test
    public ArbitraryObject documentedTestCase(@Input(name = "name", value = "Tommy") String param) {
        Assert.assertEquals("Tommy", param);

        ArbitraryObject d = new ArbitraryObject();
        d.setValue(param);

        return d;
    }

    @Test
    public ObjectWithVariousDocumentationOutputTypes shouldDocumentArbitraryObjectThroughTheirToStringMethods() {
        return new ObjectWithVariousDocumentationOutputTypes();
    }

}
