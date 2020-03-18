package com.lazerycode.selenium.tests.ui;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.page_objects.VeriffDemoPageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VeriffUiExampleTest extends DriverBase {

    @BeforeTest
    public void initDriver() {
        //By the some reasons Maven get the drivers path and values, but couldn't implement it for my tests.
        //I added some preconditions for implementation on different OS (but to be honest it's a dirty hack, I know).

        String os;
        if (System.getProperty("os.name").matches("(.*)Mac(.*)")){
            os = "osx";
        } else {
            if (System.getProperty("os.name").matches("(.*)Linux(.*)")){
                os = "linux";
            } else {
                os = "windows";
            }
        }

        if (os.equals("windows")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/selenium_standalone_binaries/" + os + "/googlechrome/64bit/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/selenium_standalone_binaries/" + os + "/googlechrome/64bit/chromedriver");
        }

    }

    @Test
    public void veriffPositiveCaseInContext() throws Exception {

        //Initialize of a new WebDriver object
        WebDriver driver = getDriver();
        //Open the test URL using the created WebDriver
        driver.get("https://demo.veriff.me/");
        //Waiting while page loaded
        Thread.sleep(1000);
        //Create new pageObject for tests (main page)
        VeriffDemoPageObject veriffDemoPage = new VeriffDemoPageObject();
        //Interaction with the Web Page
        veriffDemoPage.enterUserName("Pavel Konev") // Enter User Name to User Name field
                .selectLanguage() // Select user's language
                .documentCountry("Estonia") // Select user's country
                .documentType() // Select user's ID document type
                .radioSet(0) // Select Launch Veriff method (in this case hardcoded but possible to switch)
                .submitVeriff(); // Clicking submit button

        //Wait until modal window loaded
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div")).isEnabled());
    }

    @Test
    public void veriffPositiveCaseRedirect() throws Exception {

        //Initialize of a new WebDriver object
        WebDriver driver = getDriver();
        //Open the test URL using the created WebDriver
        driver.get("https://demo.veriff.me/");
        //Waiting while page loaded
        Thread.sleep(1000);
        //Create new pageObject for tests (main page)
        VeriffDemoPageObject veriffDemoPage = new VeriffDemoPageObject();
        //Interaction with the Web Page
        veriffDemoPage.enterUserName("Pavel Konev") // Enter User Name to User Name field
                .selectLanguage() // Select user's language
                .documentCountry("Estonia") // Select user's country
                .documentType() // Select user's ID document type
                .radioSet(1) // Select Launch Veriff method (in this case hardcoded but possible to switch)
                .submitVeriff(); // Clicking submit button

        //Wait until page is loaded
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div")).isEnabled());
    }

    @Test
    public void emptyFieldsTest() throws Exception {

        //Initialize of a new WebDriver object
        WebDriver driver = getDriver();
        //Open the test URL using the created WebDriver
        driver.get("https://demo.veriff.me/");
        //Waiting while page loaded
        Thread.sleep(1000);
        //Create new pageObject for tests (main page)
        VeriffDemoPageObject veriffDemoPage = new VeriffDemoPageObject();
        //Interaction with the Web Page
        veriffDemoPage.submitVeriff(); // Clicking submit button without any inputs

        //Wait until modal window loaded
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='incontext']")).isEnabled());
    }

}
