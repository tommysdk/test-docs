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
package com.tynja.docs.junit;

import com.tynja.docs.InvalidTestClass;
import com.tynja.docs.TestClass;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import java.util.ArrayList;

/**
 * @author Tommy Tynj&auml;
 */
public class TestDocumentation {

    @Test(expected = InitializationError.class)
    public void shouldThrowExceptionWhenMethodParameterIsMissingInputAnnotation() throws InitializationError {
        Documentation d = new Documentation(InvalidTestClass.class);
        d.validatePublicVoidNoArgMethods(Test.class, false, new ArrayList<Throwable>());
    }

    @Test
    public void shouldNotThrowExceptionWhenMethodParametersAreInputAnnotated() throws InitializationError {
        Documentation d = new Documentation(TestClass.class);
        d.validatePublicVoidNoArgMethods(Test.class, false, new ArrayList<Throwable>());
    }
}
