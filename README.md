# Reflection of Advanced Programming
### Name: Shane Michael Tanata Tendy
### NPM: 2306259976
### Class: B

----

[Reflection 1](#reflection-1)

[Reflection 2](#reflection-2)

[Reflection 3 (Module 2 Reflection)](#reflection-3-module-2-reflection)

---

### Reflection 1 

In implementing the edit and delete product features, I made sure to follow clean code principles and secure coding practices, as taught by Dr. Ade Azurat and in the SCELE coding standards module. I kept methods simple, each focused on one task, and separated the code into different layers (controller, service, and repository) following the approach from the tutorial for creating products. This makes the code easier to read, test, and maintain. I also used clear and consistent names for methods, which makes it easy for others to understand their purpose. 

For security, I focused on checking inputs and handling data safely. For example, using UUIDs for product IDs prevents predictable patterns, and using null-safe comparisons in the repository methods helps avoid errors. Given the simplicity of the code, I don't think additional security measures are needed right now.

However, I was confused on the last several commits since the functional test required me to make an additional home page, which made me create a new branch `homepage`, and from there, I think my commit step is a little bit messy, which can be seen from two merge requests from unit-test. For next time, I will make sure that I read all of the tasks first before starting to code so the commit flow will be much better.

### Reflection 2 

#### After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors? ####

I got a panic attack when I saw my functional test didn't work for hours even though it looked fine. However, it turned out that I forgot to re-run my apps which made the functional test detect the old version one :((. After I realized that and my functional test worked fine, I felt really relieved and happy, knowing that I can continue to the next part, which is writing this reflection.

I don’t think there’s a fixed number of unit tests that should be written for a class, as it really depends on the complexity of the code, such as how many CRUD functions it has, and its overall functionality. However, we must make sure that our tests are able to check all of the function thoroughly, as having little but higher quality checkers are better than having many but less quality checkers. This means covering all logical paths, including typical use cases, edge cases, boundary conditions, and error scenarios.

Achieving 100% code coverage doesn’t guarantee that the code is error-free. It simply shows that every line has been executed in tests, but it doesn’t confirm that all logic and edge cases have been thoroughly checked. Therefore, while code coverage metrics are useful for identifying untested parts, the focus should be on the quality and relevance of the tests, not just the percentage of coverage.

#### Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables. ####

#### What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner! ####

If the new test suite is repeating the same setup logic and instance variables as the previous one, it could lead to unnecessary code duplication. This can make the tests harder to maintain in the future, as any changes to the setup would require updating multiple places in the codebase. Duplication increases the maintenance burden and the risk of bugs, as changes need to be made in multiple locations. It also violates the DRY (Don't Repeat Yourself) principle. 

Moreover, If the new test suite is too similar to the old one, it may lead to tests that are too generic and not tailored to the new feature. For example, if the tests are just checking basic functionality without validating edge cases or performance, you may not be thoroughly testing the new feature.

The improvements that can be made is you can create a base test class with common setup code and have your functional tests extend this base class. This allows you to keep the setup DRY and makes it easier to update in the future. Also, for verifying the product list size, you should add tests that not only check the normal case but also edge cases such as when the list is empty, when there are duplicate products, etc.

### Reflection 3 (Module 2 Reflection) 
#### List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them. ####

1. I started by removing unused imports and functions in my code, such as:
    ```java
    // HomePageFunctionalTest.java

    import net.bytebuddy.asm.Advice;
    ```

    ```java
    // CreateProductFunctionalTest.java

    private void assertFormAction(ChromeDriver driver, String expectedAction) {
        String formAction = driver.findElement(By.tagName("form")).getAttribute("action");
        assertEquals(expectedAction, formAction);
    }
    ```

    ```java
    // HomePageFunctionalTest.java

    import static org.junit.jupiter.api.Assertions.assertEquals;
    ```

2. I also reconfigured `dependencies` in `build.gradle.kts`
    Previously:
    ```java
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
        implementation("org.springframework.boot:spring-boot-starter-web")
        compileOnly("org.projectlombok:lombok")
        developmentOnly("org.springframework.boot:spring-boot-devtools")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        annotationProcessor("org.projectlombok:lombok")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.seleniumhq.selenium:selenium-java:$seleniumJavaVersion")
        testImplementation("io.github.bonigarcia:selenium-jupiter:$seleniumJupiterVersion")
        testImplementation("io.github.bonigarcia:webdrivermanager:$webdrivermanagerVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    }
    ```

    After reconfiguration:
    ```java
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
        implementation("org.springframework.boot:spring-boot-starter-web")
        compileOnly("org.projectlombok:lombok")
        developmentOnly("org.springframework.boot:spring-boot-devtools")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        annotationProcessor("org.projectlombok:lombok")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.seleniumhq.selenium:selenium-java:$seleniumJavaVersion")
        testImplementation("io.github.bonigarcia:selenium-jupiter:$seleniumJupiterVersion")
        testImplementation("io.github.bonigarcia:webdrivermanager:$webdrivermanagerVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }
    ```
3. I also removed the modifiers in `Product.java` a.k.a. the model
    Previously:
    ```java
    public interface ProductService {
        public Product create(Product product);
        public List<Product> findAll();
        public Product findById(String id);
        public Product edit(Product product);
        public Product delete(String id);
    }
    ```

    After changes:
    ```java
    public interface ProductService {
        Product create(Product product);
        List<Product> findAll();
        Product findById(String id);
        Product edit(Product product);
        Product delete(String id);
    }
    ```

4. For the `SetUp` function that I did not use in the test, I left a comment to make it clear to the reader. For example:
    Previously:
    ```java
    void setUp() {
        
    }
    ```

    After changes:
    ```java
    void setUp() {
        // This function is intentionally left empty, as no setup is needed
    }
    ```

5. Added `findById` function in `ProductService.java`, `ProductServiceImpl.java`, and `ProductRepository.java` for tidier code
    ```java
    // ProductRepository.java

    public Product findById(String id) {
        for (Product product : productData) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }
    ```

    ```java
    // ProductService.java

    Product findById(String id);
    ```

    ```java
    // ProductServiceImpl.java

     @Override
    public Product findById(String id) {
        return productRepository.findById(id);
    }
    ```
6. Added variables in `ProductController.java` for redirect URL to remove redundancy
    Previously:
    ```java
    @PostMapping("/edit/{id}")
    public String editProductPost(@PathVariable("id") String id, @ModelAttribute Product product, Model model) {
        product.setProductId(id);
        service.edit(product);
        return "redirect:/product/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") String id) {
        service.delete(id);
        return "redirect:/product/list";
    }
    ```

    After changes:
    ```java
    private final String REDIRECT_LIST = "redirect:/product/list";
    ...
    @PostMapping("/edit/{id}")
    public String editProductPost(@PathVariable("id") String id, @ModelAttribute Product product, Model model) {
        product.setProductId(id);
        service.edit(product);
        return REDIRECT_LIST;
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") String id) {
        service.delete(id);
        return REDIRECT_LIST;
    }
    ```

#### Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)! ####

My current CI/CD implementation meets the definition of Continuous Integration and Continuous Deployment. The CI pipeline runs unit tests, checks supply chain security, and performs static code analysis with ci.yml, scorecard.yml, and sonarcloud.yml to ensure code quality and prevent regressions. These workflows guarantee that each change is validated before merging, ensuring consistency in the development process. On the CD side, every update to the main branch triggers an automatic deployment to Koyeb, ensuring fast and reliable delivery to production. The pipeline stops deployments if any tests fail, preventing broken code from being released. This automated process eliminates human errors and ensures that only thoroughly tested code is deployed. By automating these steps, the CI/CD implementation supports a stable, efficient, and secure release process.