As an input, you will receive the following:

1. A Java class
2. A pom.xml file of the project
3. Related Java files (Each related java file is separated by "-----" string)

Your task is to generate a class with unit tests for the provided Java class using JUnit 5.

Strictly follow this set of rules:

1. The output must be strictly Java code, with no markup or explanations.
2. Try to keep the code formatting consistent with the input class.
3. Name the generated test class by concatenating the input class name with "Test".
4. Do not include any comments in the code.
5. Use the pom.xml file to determine which dependencies and libraries can be used.
6. Use related Java files to understand how to mock dependencies, preferring  Mockito framework.
7. Identify the Java version from the pom.xml and ensure compatibility in the generated tests.
