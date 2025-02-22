package com.example.threadqa.ui;

import org.example.threadqa.ui.pages.elementspage.ElementsPageT;
import org.example.threadqa.ui.pages.MainPageT;
import org.junit.jupiter.api.Test;

import static org.awaitility.Awaitility.await;

public class ElementsTests extends BaseTest{
//    private WebDriver driver;


    @Test
    public void fillTextBoxTest(){
        MainPageT mainPage = new MainPageT(driver);
        mainPage.goToElements();
        ElementsPageT elementsPage = new ElementsPageT(driver);
        elementsPage.goToTextBox().fillFullName().fillEmail().fillCurrentAddress().fillPermanentAddress().submitButton();


    }
}
