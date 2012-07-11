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
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Tommy Tynj&auml;
 */
@RunWith(Documentation.class)
public class ProjectDescriptionGeneratingTest {

    private final static String lf = System.lineSeparator();

    private final static String runner = Documentation.class.getSimpleName();

    @Test
    public Description projectDescription() {
        return new Description(
                        lf +
                        "Project name: test-docs" + lf +
                        "Language: Java/JVM" + lf +
                        "Author: Tommy Tynjä" + lf +
                        lf +
                        "This project aims to provide an easy way to generate documentation from standard unit tests. " +
                        "There are obviously frameworks available for creating document-like test cases, e.g. " +
                        "acceptance testing frameworks, but that requires an additional framework and syntax to be " +
                        "added to the project. The idea behind this project is to leverage from something that is " +
                        "already present (or definitely should be) in software projects; unit tests. " + lf +
                        lf +
                        "Good and well written unit tests already acts as an up-to-date documentation of the code's " +
                        "current state. So why not add a thin layer on top of the existing unit test framework, which " +
                        "doesn't require a whole lot of new syntax to be introduced? With Java annotations, the " +
                        "amount of new syntax needed to leverage from this framework is minimal. The annotations does " +
                        "not only provide documentation generation, but can also improve the readability of your unit " +
                        "tests." + lf +
                        lf +
                        "The framework consists of a custom JUnit runner, a set of annotations and a class for " +
                        "outputting the documentation to a text file, that's all. You can easily override the " +
                        "behaviour of the output file generation to suite your own needs. Any contributions to " +
                        "the project are obviously welcomed!" + lf +
                        lf +
                        "The output file will be placed in the " + TextFileResult.outputDirectory + " directory relative to your project base " +
                        "path, one text file per test class, with the fully qualified class name, suffixed by .txt as " +
                        "filename. The file will contain input parameter names and values for each test method, as " +
                        "well as the test method output, all according to the usage of the test-docs annotations." + lf +
                        lf +
                        "Usage: " + lf +
                        "test-docs provides a set of annotations and a thin custom JUnit runner which can be used on " +
                        "top of JUnit to produce auto-generated documentation. To enable the test-docs runner, just " +
                        "add the JUnit @RunWith annotation to your test class with the test-docs " + runner + " runner " +
                        "specified, such as: " + lf +
                        lf +
                        "@RunWith(" + runner + ".class)" + lf +
                        "public class TestClass { ... }" + lf +
                        lf +
                        "This will activate the documentation generation features of test-docs, which will run " +
                        "simultaneously with the default JUnit test runner capabilities." + lf +
                        lf +
                        "The next step is to instruct the test-docs runner what to document. Standard JUnit tests " +
                        "doesn't allow your test methods to have a return type, but the test-docs runner will! The "+
                        runner + " runner will treat the object returned from a test method as the expected output " +
                        "from that test case. The runner will inspect the returned object and get the value returned " +
                        "from each method in that object annotated with " + Documented.class.getName() + " and use it as output " +
                        "documentation for that particular test case. The most convenient usage is to put the @" + Documented.class.getSimpleName() + " annotation on " +
                        "the appropriate getters for the fields that are of documentation interest, such as: " + lf +
                        lf +
                        "public class ArbitraryBusinessClass {" + lf +
                        "\t private String someDocumentedField;" + lf +
                        "\t private String someOtherField;" + lf +
                        "\t ..." + lf +
                        "\t @" + Documented.class.getSimpleName() + lf +
                        "\t public String getSomeDocumentedField() {" + lf +
                        "\t \t return someDocumentedField;" + lf +
                        "\t }" + lf +
                        "\t public String getSomeOtherField() {" + lf +
                        "\t \t return someOtherField;" + lf +
                        "\t }" + lf +
                        "}" + lf +
                        lf +
                        "If the example class above is used as a return type from a test method, the " + runner + " runner " +
                        "will invoke the getSomeDocumentedField-method, as it's annotated with @" + Documented.class.getSimpleName() + ", retrieve its " +
                        "value and use it as an output value in the documentation for that test case. If a test method " +
                        "has a void return type, the test-docs runner will just skip the output documentation generation " +
                        "for that particular test case." + lf +
                        lf +
                        "So how do you document what to input to your test method? A standard JUnit test case might look " +
                        "like this:" + lf +
                        lf +
                        "@Test" + lf +
                        "public void shouldParseString() {" + lf +
                        "\tString expected = \"123\";" + lf +
                        "\tAssert.assertEquals(123, Integer.parseInt(expected));" + lf +
                        "}" + lf +
                        lf +
                        "Wouldn't it be convenient to be able to document that expected string, as the input variable to " +
                        "the test? The test-docs framework allows you to declare method parameters with expected input. " +
                        "You then annotate the parameters with @" + Input.class.getSimpleName() + ", assign a name for the parameter which will be used " +
                        "in the documentation, and a value which will be injected into the method parameter when the " +
                        "test is executed. For the example test case above, it will look like this:" + lf +
                        lf +
                        "@Test" + lf +
                        "public void shouldParseString(" +
                        "@" + Input.class.getSimpleName() + "(name = \"Number\", value = \"123\") String expected) {" + lf +
                        "\tAssert.assertEquals(123, Integer.parseInt(expected));" + lf +
                        "}" + lf +
                        lf +
                        "If you have a test case in your test class which you want to exclude from the generated " +
                        "documentation, but which you want to execute as an ordinary test case, put the @" + Excluded.class.getSimpleName() +
                        " annotation on your test method and it will be run, but excluded from the generated " +
                        "documentation." + lf +
                        lf +
                        "If you need more examples of how to use this framework, browse through the source code! This " +
                        "documentation is actually generated with the test-docs framework itself! To see how it's done, " +
                        "check out the " + this.getClass().getSimpleName() + " class." + lf +
                        lf +
                        "Happy testing!"
        );
    }

    public class Description {
        public String description;
        public Description(final String description) {
            this.description = description;
        }
        @Documented
        public String getDescription() {
            return description;
        }
    }
}
