import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SeleniumTests {
    WebDriver driver;
    @BeforeEach
    public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "D:\\driverchrome\\chromedriver.exe");
        driver = new ChromeDriver();
        String jsonDirectory = System.getProperty("user.dir") + "/src/main/resources/json/";
        File file = new File(jsonDirectory + "jsonTags.json");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("");
    }

    @ParameterizedTest
    @CsvSource({"Successfully uploaded files: video16Mb.mkv,D:\\imgs\\video16Mb.mkv",
            "Successfully uploaded files: img1Mb.png,D:\\imgs\\img1Mb.png",
            "Successfully uploaded files: img2Mb.jpg,D:\\imgs\\img2Mb.jpg"})
    void correctUpload(String expected, String input1) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\driverchrome\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement upload = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input:nth-child(1)")));
        upload.sendKeys(input1);
        WebElement uploadB = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input:nth-child(2)")));
        uploadB.submit();
        WebElement text = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(1) > span")));
        String actualValue = text.getText();
        driver.quit();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvSource({"Incorrect format or size:,D:\\imgs\\text.txt",
            "Incorrect format or size:,D:\\imgs\\gif40kb.gif",
            "Incorrect format or size:,D:\\imgs\\img5Mb.jpg",})
    void incorrectUpload(String expected, String input1) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\driverchrome\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement upload = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input:nth-child(1)")));
        upload.sendKeys(input1);
        WebElement uploadB = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input:nth-child(2)")));
        uploadB.submit();
        WebElement text = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("h4:nth-child(1)")));
        String actualValue = text.getText();
        driver.quit();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvSource({"Incorrect format or size:"})
    void incorrectEmptyUpload(String expected) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\driverchrome\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement uploadB = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input:nth-child(2)")));
        uploadB.submit();
        WebElement text = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("h4:nth-child(1)")));
        String actualValue = text.getText();
        driver.quit();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvSource({"tag,tag",
            "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttestte" +
                    "sttesttesttesttesttesttesttesttesttesttesttesttesttesttestt" +
                    "esttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest" +
                    ",testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttestte" +
                    "sttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttestte" +
                    "sttesttesttesttesttesttesttesttesttesttest",})
    void correctAddTag(String expected,String input1) throws InterruptedException {
        driver.get("http://localhost:8080/view");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement textarea = wait.until(ExpectedConditions.elementToBeClickable(By.id("img1.jpg")));
        textarea.sendKeys(input1);
        WebElement uploadB = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(3) > button")));
        uploadB.click();
        driver.get("http://localhost:8080/");
        driver.get("http://localhost:8080/view");
        WebElement text = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.tag")));
        String actualValue = text.getText();
        driver.quit();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvSource({"testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttestte" +
            "sttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttestte" +
            "sttesttesttesttesttesttesttesttesttesttestе","' '", "''"})
    void incorrectAddTagOne(String input1) throws InterruptedException {
        driver.get("http://localhost:8080/view");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement textarea = wait.until(ExpectedConditions.elementToBeClickable(By.id("img1.jpg")));
        textarea.sendKeys(input1);
        WebElement uploadB = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(3) > button")));
        uploadB.click();
        driver.get("http://localhost:8080/");
        driver.get("http://localhost:8080/view");
        Thread.sleep(1000);
        boolean actualValue = driver.findElements(By.cssSelector("span.tag")).size() > 0;
        driver.quit();
        assertFalse(actualValue);
    }

    @ParameterizedTest
    @CsvSource({"100,teg"})
    void incorrectAddTagCountLimit(int expected, String input1) throws InterruptedException {
        driver.get("http://localhost:8080/view");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement textarea = wait.until(ExpectedConditions.elementToBeClickable(By.id("img1.jpg")));
        textarea.sendKeys(input1);
        WebElement uploadB = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(3) > button")));
        for (int i = 0; i <= 101; i++) {
            uploadB.click();
        }
        driver.get("http://localhost:8080/");
        driver.get("http://localhost:8080/view");
        Thread.sleep(1000);
        int actualValue = driver.findElements(By.cssSelector("span.tag")).size();
        driver.quit();
        assertEquals(expected,actualValue);
    }

    @ParameterizedTest
    @CsvSource({"2,art"})
    void searchCorrectTag(int expected, String input1) throws InterruptedException {
        driver.get("http://localhost:8080/view");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement textarea = wait.until(ExpectedConditions.elementToBeClickable(By.id("img1.jpg")));
        textarea.sendKeys(input1);
        WebElement uploadB = wait.until(ExpectedConditions.elementToBeClickable(By.className("img1.jpg")));
        uploadB.click();
        WebElement textarea2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("img1Mb.png")));
        textarea2.sendKeys(input1);
        WebElement uploadB2 = wait.until(ExpectedConditions.elementToBeClickable(By.className("img1Mb.png")));
            uploadB2.click();
        WebElement toSearch = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        toSearch.submit();
        WebElement searchT = wait.until(ExpectedConditions.elementToBeClickable(By.id("search")));
        searchT.sendKeys(input1);
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        search.submit();
        Thread.sleep(1000);
        int actualValue = driver.findElements(By.cssSelector("div")).size()-1;
        driver.quit();
        assertEquals(expected,actualValue);
    }

    @ParameterizedTest
    @CsvSource({"1,IMG1MB"})
    void searchCorrectName(int expected, String input1) throws InterruptedException {
        driver.get("http://localhost:8080/view");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement toSearch = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        toSearch.submit();
        WebElement searchT = wait.until(ExpectedConditions.elementToBeClickable(By.id("search")));
        searchT.sendKeys(input1);
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        search.submit();
        Thread.sleep(1000);
        int actualValue = driver.findElements(By.cssSelector("div")).size()-1;
        driver.quit();
        assertEquals(expected,actualValue);
    }
//
//    @ParameterizedTest
//    @CsvSource({"1,test1,test2,test3", "2,test1,test2,test3", "3,test1,test2,test3"})
//    void completedTest(int expected, String input1, String input2, String input3) throws InterruptedException {
//        driver.get("https://todomvc.com/examples/angularjs/#/");
//        Thread.sleep(1000);  // Let the user actually see something!
//        WebElement searchBox = driver.findElement(By.cssSelector(".new-todo"));
//        searchBox.sendKeys(input1);
//        searchBox.submit();
//        searchBox.sendKeys(input2);
//        searchBox.submit();
//        searchBox.sendKeys(input3);
//        searchBox.submit();
//        Thread.sleep(1000);
//        List<WebElement> checkElements = driver.findElements(By.cssSelector("input.toggle"));
//        for (int i = 0; i < (expected); i++) {
//            checkElements.get(i).click();
//        }
//        driver.findElement(By.linkText("Completed")).click();
//        List<WebElement> elements = driver.findElements(By.cssSelector("li.ng-scope"));
//        int actualValue = elements.size();
//        Thread.sleep(1000);  // Let the user actually see something!
//        driver.quit();
//        assertEquals(expected, actualValue);
//    }
//
//    @ParameterizedTest
//    @CsvSource({"3,test1,test2,test3", "2,test1,'  ',test3"})
//    void checkAllTest(int expected, String input1, String input2, String input3) throws InterruptedException {
//        driver.get("https://todomvc.com/examples/angularjs/#/");
//        Thread.sleep(1000);  // Let the user actually see something!
//        WebElement searchBox = driver.findElement(By.cssSelector(".new-todo"));
//        searchBox.sendKeys(input1);
//        searchBox.submit();
//        searchBox.sendKeys(input2);
//        searchBox.submit();
//        searchBox.sendKeys(input3);
//        searchBox.submit();
//        Thread.sleep(1000);
//        WebElement checkElement = driver.findElement(By.cssSelector(".main > label"));
//        checkElement.click();
//        List<WebElement> elements = driver.findElements(By.cssSelector("li.ng-scope.completed"));
//        int actualValue = elements.size();
//        Thread.sleep(1000);  // Let the user actually see something!
//        driver.quit();
//        assertEquals(expected, actualValue);
//    }
//
//    @ParameterizedTest
//    @CsvSource({"1,test1,test2,test3", "2,test1,test2,test3", "3,test1,test2,test3"})
//    void checkTest(int expected, String input1, String input2, String input3) throws InterruptedException {
//        driver.get("https://todomvc.com/examples/angularjs/#/");
//        Thread.sleep(1000);  // Let the user actually see something!
//        WebElement searchBox = driver.findElement(By.cssSelector(".new-todo"));
//        searchBox.sendKeys(input1);
//        searchBox.submit();
//        searchBox.sendKeys(input2);
//        searchBox.submit();
//        searchBox.sendKeys(input3);
//        searchBox.submit();
//        Thread.sleep(1000);
//        List<WebElement> checkElements = driver.findElements(By.cssSelector("input.toggle"));
//        for (int i = 0; i < (expected); i++) {
//            checkElements.get(i).click();
//        }
//        List<WebElement> elements = driver.findElements(By.cssSelector("li.ng-scope.completed"));
//        int actualValue = elements.size();
//        Thread.sleep(1000);  // Let the user actually see something!
//        driver.quit();
//        assertEquals(expected, actualValue);
//    }
//
//    @ParameterizedTest
//    @CsvSource({"0,test1,test2,test3", "1,test1,test2,test3", "2,test1,test2,test3"})
//    void deleteTest(int expected, String input1, String input2, String input3) throws InterruptedException {
//        driver.get("https://todomvc.com/examples/angularjs/#/");
//        Thread.sleep(1000);  // Let the user actually see something!
//        WebElement searchBox = driver.findElement(By.cssSelector(".new-todo"));
//        searchBox.sendKeys(input1);
//        searchBox.submit();
//        searchBox.sendKeys(input2);
//        searchBox.submit();
//        searchBox.sendKeys(input3);
//        searchBox.submit();
//        Thread.sleep(1000);
//        for (int i = 0; i < (3 - expected); i++) {
//            new Actions(driver).moveToElement(driver.findElement(By.cssSelector("label.ng-binding"))).perform();
//            driver.findElement(By.cssSelector(".ng-scope:nth-child(1) > .view > .destroy")).click();
//        }
//        List<WebElement> elements = driver.findElements(By.cssSelector("li.ng-scope"));
//        int actualValue = elements.size();
//        Thread.sleep(1000);  // Let the user actually see something!
//        driver.quit();
//        assertEquals(expected, actualValue);
//    }
}