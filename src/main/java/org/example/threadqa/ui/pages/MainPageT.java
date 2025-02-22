package org.example.threadqa.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPageT extends BasePageT {
    private final By elements = By.xpath("//div[@class='card-body']//h5[text()='Elements']");

    public MainPageT(WebDriver driver) {
        super(driver);
    }

    public void goToElements() {
        driver.findElement(elements).click();
    }
}
