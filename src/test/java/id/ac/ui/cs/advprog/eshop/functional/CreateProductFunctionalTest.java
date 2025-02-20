package id.ac.ui.cs.advprog.eshop.functional;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProduct_isSuccessful(ChromeDriver driver) throws Exception {
        // Exercise: Navigate to Create Product page
        driver.get(baseUrl + "/product/create");

        // Verify the create product page is displayed
        String createPageTitle = driver.getTitle();
        assertEquals("Create New Product", createPageTitle);

        // Exercise: Fill in the product details
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        nameInput.sendKeys("Test Product");
        quantityInput.sendKeys("100");
        submitButton.click();

        // Verify: Check if redirected to product list page
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.endsWith("/product/list"));

        // Verify: Check if the new product appears in the list
        WebElement productTable = driver.findElement(By.cssSelector("table"));
        String tableContent = productTable.getText();
        assertTrue(tableContent.contains("Test Product"));
        assertTrue(tableContent.contains("100"));
    }

    @Test
    void createProductPage_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");
        String pageTitle = driver.getTitle();

        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void createProductPost_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");
        driver.findElement(By.id("nameInput")).sendKeys("Sampo Cap Bambang");
        driver.findElement(By.id("quantityInput")).sendKeys("100");
        driver.findElement(By.id("submitButton")).click();
        String pageTitle = driver.getTitle();

        assertEquals("Product List", pageTitle);
        
        assertEquals(true, pageSourceContains(driver, "Sampo Cap Bambang"));
        assertEquals(true, pageSourceContains(driver, "100"));
    }

    @Test
    void editProductPage_isDisplayed(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");
        driver.findElement(By.id("nameInput")).sendKeys("Hulahup Domba Hitam");
        driver.findElement(By.id("quantityInput")).sendKeys("369");
        driver.findElement(By.tagName("form")).submit();

        driver.findElement(By.xpath(String.format("//*[text()='%s']", "Edit"))).click();
        assertPageTitle(driver, "Edit Product");
    }

    @Test
    void editProductPage_hasCorrectFormFields(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");
        driver.findElement(By.id("nameInput")).sendKeys("Hulahup Domba Hitam");
        driver.findElement(By.id("quantityInput")).sendKeys("369");
        driver.findElement(By.tagName("form")).submit();

        driver.findElement(By.xpath(String.format("//*[text()='%s']", "Edit"))).click();
        assertPageTitle(driver, "Edit Product");

        assertFormFieldNotNull(driver, "nameInput");
        assertFormFieldNotNull(driver, "quantityInput");
        assertEquals("Hulahup Domba Hitam", getFormFieldValue(driver, "nameInput"));
        assertEquals("369", getFormFieldValue(driver, "quantityInput"));
    }

    @Test
    void editProductPage_canEditProduct(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");
        driver.findElement(By.id("nameInput")).sendKeys("Hulahup Domba Hitam");
        driver.findElement(By.id("quantityInput")).sendKeys("369");
        driver.findElement(By.tagName("form")).submit();

        driver.findElement(By.xpath(String.format("//*[text()='%s']", "Edit"))).click();
        driver.findElement(By.id("nameInput")).clear();
        driver.findElement(By.id("nameInput")).sendKeys("Halohalo");
        driver.findElement(By.id("quantityInput")).clear();
        driver.findElement(By.id("quantityInput")).sendKeys("666666");
        driver.findElement(By.tagName("form")).submit();

        assertPageTitle(driver, "Product List");

        driver.get(baseUrl + "/product/list");
        assertEquals(true, pageSourceContains(driver, "Halohalo"));
        assertEquals(true, pageSourceContains(driver, "666666"));
    }

    @Test
    void deleteLastProduct(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");
        driver.findElement(By.id("nameInput")).sendKeys("Baju Three Little Pigs");
        driver.findElement(By.id("quantityInput")).sendKeys("7171733");
        driver.findElement(By.tagName("form")).submit();

        driver.get(baseUrl + "/product/create");
        driver.findElement(By.id("nameInput")).sendKeys("Baju Four Little Pigs");
        driver.findElement(By.id("quantityInput")).sendKeys("98");
        driver.findElement(By.tagName("form")).submit();

        driver.get(baseUrl + "/product/list");
        driver.findElement(By.xpath(String.format("(//*[text()='%s'])[last()]", "Delete"))).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        assertPageTitle(driver, "Product List");
        assertEquals(true, pageSourceContains(driver, "Baju Three Little Pigs"));
        assertEquals(false, pageSourceContains(driver, "Baju Four Little Pigs"));
    }

    @Test
    void deleteEditedProduct(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");
        driver.findElement(By.id("nameInput")).sendKeys("Pokemon");
        driver.findElement(By.id("quantityInput")).sendKeys("7171733");
        driver.findElement(By.tagName("form")).submit();

        driver.get(baseUrl + "/product/list");
        driver.findElement(By.xpath(String.format("//*[text()='%s']", "Edit"))).click();
        driver.findElement(By.id("nameInput")).clear();
        driver.findElement(By.id("nameInput")).sendKeys("Peppa pig");
        driver.findElement(By.id("quantityInput")).clear();
        driver.findElement(By.id("quantityInput")).sendKeys("98");
        driver.findElement(By.tagName("form")).submit();

        driver.get(baseUrl + "/product/list");
        driver.findElement(By.xpath(String.format("//*[text()='%s']", "Delete"))).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        assertPageTitle(driver, "Product List");
        assertEquals(false, pageSourceContains(driver, "Peppa pig"));
    }

    private String getPageTitle(ChromeDriver driver) {
        return driver.getTitle();
    }

    private void assertPageTitle(ChromeDriver driver, String expectedTitle) {
        String pageTitle = getPageTitle(driver);
        assertEquals(expectedTitle, pageTitle);
    }

    private void assertFormFieldNotNull(ChromeDriver driver, String fieldId) {
        assertNotNull(driver.findElement(By.id(fieldId)));
    }

    private String getFormFieldValue(ChromeDriver driver, String fieldId) {
        return driver.findElement(By.id(fieldId)).getAttribute("value");
    }

    private boolean pageSourceContains(ChromeDriver driver, String expectedContent) {
        String pageSource = driver.getPageSource();
        return pageSource.contains(expectedContent);
    }
}