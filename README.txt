This project aims to provide an easy way to generate documentation from standard unit tests. 
There are obviously frameworks available for creating document-like test cases, e.g. 
acceptance testing frameworks, but that requires an additional framework and syntax to be 
added to the project. The idea behind this project is to leverage from something that is 
already present (or definitely should be) in software projects; unit tests. 

Good and well written unit tests already acts as an up-to-date documentation of the code's 
current state. So why not add a thin layer on top of the existing unit test framework, which 
doesn't require a whole lot of new syntax to be introduced? With Java annotations, the amount 
of new syntax to leverage from this framework is minimal. The annotations does not only 
provide documentation generation, but can also improve the readability of your unit tests.
