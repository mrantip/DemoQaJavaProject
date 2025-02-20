package org.example.threadqa.ui.pages;

import lombok.Data;
import lombok.Getter;
import org.example.threadqa.ui.pages.elementspage.TextBoxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementsPage extends BasePage {
    private final By textBox = By.xpath("//span[text()='Text Box']");

    public ElementsPage(WebDriver driver) {
        super(driver);
    }

    public TextBoxPage goToTextBox() {
        driver.findElement(textBox).click();
        return new TextBoxPage(driver);
    }

}
