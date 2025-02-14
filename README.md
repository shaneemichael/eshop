**Reflection 1**

In implementing the edit and delete product features, I made sure to follow clean code principles and secure coding practices, as taught by Dr. Ade Azurat and in the SCELE coding standards module. I kept methods simple, each focused on one task, and separated the code into different layers (controller, service, and repository) following the approach from the tutorial for creating products. This makes the code easier to read, test, and maintain. I also used clear and consistent names for methods, which makes it easy for others to understand their purpose. 

For security, I focused on checking inputs and handling data safely. For example, using UUIDs for product IDs prevents predictable patterns, and using null-safe comparisons in the repository methods helps avoid errors. Given the simplicity of the code, I don't think additional security measures are needed right now.

However, I was confused on the last several commits since the functional test required me to make an additional home page, which made me create a new branch `homepage`, and from there, I think my commit step is a little bit messy, which can be seen from two merge requests from unit-test. For next time, I will make sure that I read all of the tasks first before starting to code so the commit flow will be much better.

**Reflection 2**

**After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?**

I got a panic attack when I saw my functional test didn't work for hours even though it looked fine. However, it turned out that I forgot to re-run my apps which made the functional test detect the old version one :((. After I realized that and my functional test worked fine, I felt really relieved and happy, knowing that I can continue to the next part, which is writing this reflection.

I don’t think there’s a fixed number of unit tests that should be written for a class, as it really depends on the complexity of the code, such as how many CRUD functions it has, and its overall functionality. However, we must make sure that our tests are able to check all of the function thoroughly, as having little but higher quality checkers are better than having many but less quality checkers. This means covering all logical paths, including typical use cases, edge cases, boundary conditions, and error scenarios.

Achieving 100% code coverage doesn’t guarantee that the code is error-free. It simply shows that every line has been executed in tests, but it doesn’t confirm that all logic and edge cases have been thoroughly checked. Therefore, while code coverage metrics are useful for identifying untested parts, the focus should be on the quality and relevance of the tests, not just the percentage of coverage.

**Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables.**

**What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!**

If the new test suite is repeating the same setup logic and instance variables as the previous one, it could lead to unnecessary code duplication. This can make the tests harder to maintain in the future, as any changes to the setup would require updating multiple places in the codebase. Duplication increases the maintenance burden and the risk of bugs, as changes need to be made in multiple locations. It also violates the DRY (Don't Repeat Yourself) principle. 

Moreover, If the new test suite is too similar to the old one, it may lead to tests that are too generic and not tailored to the new feature. For example, if the tests are just checking basic functionality without validating edge cases or performance, you may not be thoroughly testing the new feature.

The improvements that can be made is you can create a base test class with common setup code and have your functional tests extend this base class. This allows you to keep the setup DRY and makes it easier to update in the future. Also, for verifying the product list size, you should add tests that not only check the normal case but also edge cases such as when the list is empty, when there are duplicate products, etc.
