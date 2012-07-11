Project name: test-docs
Language: Java/JVM
Author: Tommy Tynjä

This project aims to provide an easy way to generate documentation from standard unit tests. There are obviously frameworks available for creating document-like test cases, e.g. acceptance testing frameworks, but that requires an additional framework and syntax to be added to the project. The idea behind this project is to leverage from something that is already present (or definitely should be) in software projects; unit tests. 

Good and well written unit tests already acts as an up-to-date documentation of the code's current state. So why not add a thin layer on top of the existing unit test framework, which doesn't require a whole lot of new syntax to be introduced? With Java annotations, the amount of new syntax needed to leverage from this framework is minimal. The annotations does not only provide documentation generation, but can also improve the readability of your unit tests.

The framework consists of a custom JUnit runner, a set of annotations and a class for outputting the documentation to a text file, that's all. You can easily override the behaviour of the output file generation to suite your own needs. Any contributions to project are obviously welcomed!

The output file will be placed in the target/docs/ directory relative to your project base path, one text file per test class, with the fully qualified class name, suffixed by .txt as filename. The file will contain input parameter names and values for each test method, as well as the test method output, all according to the usage of the test-docs annotations.

Usage: 
test-docs provides a set of annotations and a thin custom JUnit runner which can be used on top of JUnit to produce auto-generated documentation. To enable the test-docs runner, just add the JUnit @RunWith annotation to your test class with the test-docs Documentation runner specified, such as: 

@RunWith(Documentation.class)
public class TestClass { ... }

This will activate the documentation generation features of test-docs, which will run simultaneously with the default JUnit test runner capabilities.

The next step is to instruct the test-docs runner what to document. Standard JUnit tests doesn't allow your test methods to have a return type, but the test-docs runner will! The Documentation runner will treat the object returned from a test method as the expected output from that test case. The runner will inspect the returned object and get the value returned from each method in that object annotated with com.tynja.docs.Documented and use it as output documentation for that particular test case. The most convenient usage is to put the @Documented annotation on the appropriate getters for the fields that are of documentation interest, such as:

public class ArbitraryBusinessClass {
	 private String someDocumentedField;
	 private String someOtherField;
	 ...
	 @Documented
	 public String getSomeDocumentedField() {
	 	 return someDocumentedField;
	 }
	 public String getSomeOtherField() {
	 	 return someOtherField;
	 }
}

If the example class above is used as a return type from a test method, the Documentation runner will invoke the getSomeDocumentedField-method, as it's annotated with @Documented, retrieve its value and use it as an output value in the documentation for that test case. If a test method has a void return type, the test-docs runner will just skip the output documentation generation for that particular test case.

So how do you document what to input to your test method? A standard JUnit test case might look like this:

@Test
public void shouldParseString() {
	String expected = "123";
	Assert.assertEquals(123, Integer.parseInt(expected));
}

Wouldn't it be convenient to be able to document that expected string, as the input variable to the test? The test-docs framework allows you to declare method parameters with expected input. You then annotate the parameters with @Input, assign a name for the parameter which will be used in the documentation, and a value which will be injected into the method parameter when the test is executed. For the example test case above, it will look like this:

@Test
public void shouldParseString(@Input(name = "Number", value = "123") String expected) {
	Assert.assertEquals(123, Integer.parseInt(expected));
}

If you have a test case in your test class which you want to exclude from the generated documentation, but which you want to execute as an ordinary test case, put the @Excluded annotation on your test method and it will be run, but excluded from the generated documentation.

If you need more examples of how to use this framework, browse through the source code! This documentation is actually generated with the test-docs framework itself! To see how it's done, check out the ProjectDescriptionGeneratingTest class.

Happy testing!