package com.lazerycode.selenium.page_objects;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;

import static com.lazerycode.selenium.util.AssignDriver.initQueryObjects;

public class VeriffDemoPageObject {

    // Name text field object
    private Query nameBar = new Query().defaultLocator(By.name("name"));
    // Language selector object
    private Query languageSelector = new Query().defaultLocator(By.name("language"));
    // Selector option object
    private Query languageSelectorValue = new Query().defaultLocator(By.id("downshift-0-item-5"));
    // Country selector object
    private Query documentCountrySelector = new Query().defaultLocator(By.name("documentCountry"));
    // Country selector option object
    private Query documentCountrySelectorValue = new Query().defaultLocator(By.xpath("//span[.='Estonia']"));
    // Document type selector object
    private Query documentTypeSelector = new Query().defaultLocator(By.name("documentType"));
    // Document type option selector object
    private Query documentTypeSelectorValue = new Query().defaultLocator(By.id("downshift-2-item-0"));
    // Submit button object
    private Query submitButton = new Query().defaultLocator(By.xpath("//button[@type='submit']"));

    // Some options in implementation for the next varables:
    // It's possible to find elements using specific tag in HTML:

    private Query inContextRadioXpath = new Query().defaultLocator(By.xpath("//input[@value='incontext']"));
    private Query redirectRadioXpath = new Query().defaultLocator(By.xpath("//input[@value='redirect']"));

    // OR
    // It's possible to find this radios via Link Text

    private Query inContextRadioByText = new Query().defaultLocator(By.linkText("InContext"));
    private Query redirectRadioByText = new Query().defaultLocator(By.linkText("Redirect"));

    // Initialization of PageObject
    public VeriffDemoPageObject() throws Exception {
        initQueryObjects(this, DriverBase.getDriver());
    }

    // Enter user name function
    public VeriffDemoPageObject enterUserName(String userName) {
        nameBar.findWebElement().clear();
        nameBar.findWebElement().sendKeys(userName);

        return this;
    }

    // Language selector interaction function
    public VeriffDemoPageObject selectLanguage() throws InterruptedException {
        languageSelector.findWebElement().click();
        Thread.sleep(500);
        languageSelectorValue.findWebElement().click();

        return this;
    }

    // Country selector interaction function
    public VeriffDemoPageObject documentCountry(String countryName) {
        documentCountrySelector.findWebElement().click();
        documentCountrySelector.findWebElement().sendKeys(countryName);
        documentCountrySelectorValue.findWebElement().click();

        return this;
    }

    // Document selector interaction function
    public VeriffDemoPageObject documentType() {
        documentTypeSelector.findWebElement().click();
        documentTypeSelectorValue.findWebElement().click();

        return this;
    }

    // Radio button interaction function
    // Some additional interaction possibilities commented
    public VeriffDemoPageObject radioSet(int launchVia) {

        switch (launchVia){
            case 0:
                inContextRadioXpath.findWebElement().click();
                //inContextRadioByText.findWebElement().click();
                break;

            case 1:
                redirectRadioXpath.findWebElement().click();
                //redirectRadioByText.findWebElement().click();
                break;
        }
        return this;
    }

    // Submit button clicking function
    public VeriffDemoPageObject submitVeriff() {
        submitButton.findWebElement().submit();

        return this;
    }

}
