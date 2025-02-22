package org.example.threadqa.ui.pages.elementspage;

import com.github.javafaker.Faker;
import org.example.threadqa.ui.pages.BasePageT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class TextBoxPageT extends BasePageT {
    private final By fullName = By.cssSelector("input[id='userName']");
    private final By email = By.xpath("//input[@type='email']");
    private final By currentAddress = By.cssSelector("textarea[id='currentAddress']");
    private final By permanentAddress = By.cssSelector("textarea[id='permanentAddress']");
    private final By submitButton = By.id("submit");

    Faker faker = new Faker();

    public TextBoxPageT(WebDriver driver) {
        super(driver);
    }



    public TextBoxPageT fillFullName() {
        driver.findElement(fullName).sendKeys(faker.name().fullName());
        return this;
    }

    public TextBoxPageT fillEmail() {
        driver.findElement(email).sendKeys(faker.internet().emailAddress());
        return this;
    }

    public TextBoxPageT fillCurrentAddress() {
        driver.findElement(currentAddress).sendKeys(faker.address().fullAddress());
        return this;
    }

    public TextBoxPageT fillPermanentAddress() {
        driver.findElement(permanentAddress).sendKeys(faker.address().fullAddress());
        return this;
    }

    public void submitButton() {
        driver.findElement(submitButton).click();
    }
}
