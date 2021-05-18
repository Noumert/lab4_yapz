import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SeleniumTests {
    WebDriver driver;
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\driverchrome\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @ParameterizedTest
    @CsvSource({"1,test1,test2,test3", "2,test1,test2,test3", "3,test1,test2,test3"})
    void clearTest(int expected, String input1, String input2, String input3) throws InterruptedException {
        driver.get("https://todomvc.com/examples/angularjs/#/");
        Thread.sleep(1000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.cssSelector(".new-todo"));
        searchBox.sendKeys(input1);
        searchBox.submit();
        searchBox.sendKeys(input2);
        searchBox.submit();
        searchBox.sendKeys(input3);
        searchBox.submit();
        Thread.sleep(1000);
        WebElement clear = driver.findElement(By.cssSelector(".clear-completed"));
        List<WebElement> checkElements = driver.findElements(By.cssSelector("input.toggle"));
        for (int i = 0; i < (3 - expected); i++) {
            checkElements.get(i).click();
        }
        if (clear.isDisplayed()) {
            clear.click();
        }
        List<WebElement> elements = driver.findElements(By.cssSelector("li.ng-scope"));
        int actualValue = elements.size();
        Thread.sleep(1000);  // Let the user actually see something!
        driver.quit();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvSource({"1,test1,test2,test3", "2,test1,test2,test3", "3,test1,test2,test3"})
    void activeTest(int expected, String input1, String input2, String input3) throws InterruptedException {
        driver.get("https://todomvc.com/examples/angularjs/#/");
        Thread.sleep(1000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.cssSelector(".new-todo"));
        searchBox.sendKeys(input1);
        searchBox.submit();
        searchBox.sendKeys(input2);
        searchBox.submit();
        searchBox.sendKeys(input3);
        searchBox.submit();
        Thread.sleep(1000);
        List<WebElement> checkElements = driver.findElements(By.cssSelector("input.toggle"));
        for (int i = 0; i < (3 - expected); i++) {
            checkElements.get(i).click();
        }
        driver.findElement(By.linkText("Active")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector("li.ng-scope"));
        int actualValue = elements.size();
        Thread.sleep(1000);  // Let the user actually see something!
        driver.quit();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvSource({"1,test1,test2,test3", "2,test1,test2,test3", "3,test1,test2,test3"})
    void completedTest(int expected, String input1, String input2, String input3) throws InterruptedException {
        driver.get("https://todomvc.com/examples/angularjs/#/");
        Thread.sleep(1000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.cssSelector(".new-todo"));
        searchBox.sendKeys(input1);
        searchBox.submit();
        searchBox.sendKeys(input2);
        searchBox.submit();
        searchBox.sendKeys(input3);
        searchBox.submit();
        Thread.sleep(1000);
        List<WebElement> checkElements = driver.findElements(By.cssSelector("input.toggle"));
        for (int i = 0; i < (expected); i++) {
            checkElements.get(i).click();
        }
        driver.findElement(By.linkText("Completed")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector("li.ng-scope"));
        int actualValue = elements.size();
        Thread.sleep(1000);  // Let the user actually see something!
        driver.quit();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvSource({"3,test1,test2,test3", "2,test1,'  ',test3"})
    void checkAllTest(int expected, String input1, String input2, String input3) throws InterruptedException {
        driver.get("https://todomvc.com/examples/angularjs/#/");
        Thread.sleep(1000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.cssSelector(".new-todo"));
        searchBox.sendKeys(input1);
        searchBox.submit();
        searchBox.sendKeys(input2);
        searchBox.submit();
        searchBox.sendKeys(input3);
        searchBox.submit();
        Thread.sleep(1000);
        WebElement checkElement = driver.findElement(By.cssSelector(".main > label"));
        checkElement.click();
        List<WebElement> elements = driver.findElements(By.cssSelector("li.ng-scope.completed"));
        int actualValue = elements.size();
        Thread.sleep(1000);  // Let the user actually see something!
        driver.quit();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvSource({"1,test1,test2,test3", "2,test1,test2,test3", "3,test1,test2,test3"})
    void checkTest(int expected, String input1, String input2, String input3) throws InterruptedException {
        driver.get("https://todomvc.com/examples/angularjs/#/");
        Thread.sleep(1000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.cssSelector(".new-todo"));
        searchBox.sendKeys(input1);
        searchBox.submit();
        searchBox.sendKeys(input2);
        searchBox.submit();
        searchBox.sendKeys(input3);
        searchBox.submit();
        Thread.sleep(1000);
        List<WebElement> checkElements = driver.findElements(By.cssSelector("input.toggle"));
        for (int i = 0; i < (expected); i++) {
            checkElements.get(i).click();
        }
        List<WebElement> elements = driver.findElements(By.cssSelector("li.ng-scope.completed"));
        int actualValue = elements.size();
        Thread.sleep(1000);  // Let the user actually see something!
        driver.quit();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvSource({"0,test1,test2,test3", "1,test1,test2,test3", "2,test1,test2,test3"})
    void deleteTest(int expected, String input1, String input2, String input3) throws InterruptedException {
        driver.get("https://todomvc.com/examples/angularjs/#/");
        Thread.sleep(1000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.cssSelector(".new-todo"));
        searchBox.sendKeys(input1);
        searchBox.submit();
        searchBox.sendKeys(input2);
        searchBox.submit();
        searchBox.sendKeys(input3);
        searchBox.submit();
        Thread.sleep(1000);
        for (int i = 0; i < (3 - expected); i++) {
            new Actions(driver).moveToElement(driver.findElement(By.cssSelector("label.ng-binding"))).perform();
            driver.findElement(By.cssSelector(".ng-scope:nth-child(1) > .view > .destroy")).click();
        }
        List<WebElement> elements = driver.findElements(By.cssSelector("li.ng-scope"));
        int actualValue = elements.size();
        Thread.sleep(1000);  // Let the user actually see something!
        driver.quit();
        assertEquals(expected, actualValue);
    }
}