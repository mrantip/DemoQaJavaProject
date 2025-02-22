package org.example.threadqa.ui.pages.elementspage;

import org.example.threadqa.ui.pages.BasePageT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementsPageT extends BasePageT {
    private final By textBox = By.xpath("//span[text()='Text Box']");

    public ElementsPageT(WebDriver driver) {
        super(driver);
    }

    public TextBoxPageT goToTextBox() {
        driver.findElement(textBox).click();
        return new TextBoxPageT(driver);
    }

}
