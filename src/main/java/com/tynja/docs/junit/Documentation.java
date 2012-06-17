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

import com.tynja.docs.Documented;
import com.tynja.docs.Excluded;
import com.tynja.docs.TextFileResult;
import com.tynja.docs.Input;
import com.tynja.docs.Result;
import org.junit.Test;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * JUnit runner which generates documentation for all test methods in the annotated class.<br/>
 * <br/>
 * Usage:<br/>
 * Annotate your test class with <tt>@RunWith(Documentation.class)</tt>, such as:<br/>
 * <tt>@RunWith(Documentation.class)<br/>
 * public class TestClass { ... }</tt>
 * <br/><br/>
 * This will generate documentation in the target/docs directory of your project root, one text
 * file per test class annotated with <tt>@RunWith(Documentation.class)</tt>. Use this runner
 * in conjunction with the <tt>@Input</tt> and <tt>@Documented</tt> annotations to produce the
 * desired documentation.<br/>
 * <br/>
 *
 * @author Tommy Tynj&auml;
 * @see Documented
 * @see Input
 */
public class Documentation extends BlockJUnit4ClassRunner {

    public Documentation(final Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    protected void validatePublicVoidNoArgMethods(final Class<? extends Annotation> annotation, final boolean isStatic, final List<Throwable> errors) {
        for (FrameworkMethod method : getTestClass().getAnnotatedMethods(Test.class)) {
            for(Annotation[] annotations : method.getMethod().getParameterAnnotations()) {
                if (annotations.length == 0) {
                    throw new IllegalArgumentException("Parameter without @" + Input.class.getSimpleName()
                            + " annotation on method: " + method.getName());
                }
            }
        }
    }

    @Override
    protected Statement methodInvoker(final FrameworkMethod method, final Object test) {
        return new Statement() {
            private final FrameworkMethod testMethod = method;
            private Object target = test;

            @Override
            public void evaluate() throws Throwable {
                Result result = initializeResultObject(testMethod);

                List<String> parameters = new ArrayList<>();
                if (includeDocumentation()) {
                    for (Annotation[] annotations : method.getMethod().getParameterAnnotations()) {
                        for (Annotation a : annotations) {
                            if (a instanceof Input) {
                                Input d = (Input) a;
                                parameters.add(d.value());
                                result.addParameter(d.name(), d.value());
                            }
                        }
                    }
                }

                Object returnObject = testMethod.invokeExplosively(target, parameters.toArray());

                if (returnObject != null && includeDocumentation()) {
                    for (Method m : returnObject.getClass().getMethods()) {
                        if (m.getAnnotation(Documented.class) != null) {
                            result.addResult(m.getName(), m.invoke(returnObject));
                        }
                    }
                }

                if (includeDocumentation()) {
                    result.produce();
                }
            }

            private boolean includeDocumentation() {
                return method.getAnnotation(Excluded.class) == null;
            }
        };
    }

    protected Result initializeResultObject(final FrameworkMethod testMethod) {
        Result result = createResult();
        result.setCategory(testMethod.getMethod().getDeclaringClass().getName());
        result.setTitle(testMethod.getMethod().getName());
        return result;
    }

    protected Result createResult() {
        return new TextFileResult();
    }

}
