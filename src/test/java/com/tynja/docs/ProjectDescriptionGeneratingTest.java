package com.tynja.docs;

import com.tynja.docs.junit.Documentation;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Tommy Tynj&auml;
 */
@RunWith(Documentation.class)
public class ProjectDescriptionGeneratingTest {

    final static String lf = System.lineSeparator();

    @Test
    public Description projectDescription() {
        return new Description(
                System.lineSeparator() +
                        "This project aims to provide an easy way to generate documentation from standard unit tests. " + lf +
                        "There are obviously frameworks available for creating document-like test cases, e.g. " + lf +
                        "acceptance testing frameworks, but that requires an additional framework and syntax to be " + lf +
                        "added to the project. The idea behind this project is to leverage from something that is " + lf +
                        "already present (or definitely should be) in software projects; unit tests. " + lf +
                        lf +
                        "Good and well written unit tests already acts as an up-to-date documentation of the code's " + lf +
                        "current state. So why not add a thin layer on top of the existing unit test framework, which " + lf +
                        "doesn't require a whole lot of new syntax to be introduced? With Java annotations, the amount " + lf +
                        "of new syntax to leverage from this framework is minimal. The annotations does not only " + lf +
                        "provide documentation generation, but can also improve the readability of your unit tests." + lf +
                        lf
                        // TODO: Add examples
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
