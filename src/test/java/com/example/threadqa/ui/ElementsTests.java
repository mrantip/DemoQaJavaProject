package com.example.threadqa.ui;

import org.example.threadqa.ui.pages.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import static org.awaitility.Awaitility.await;

public class ElementsTests extends BaseTest{
//    private WebDriver driver;


    @Test
    public void fillTextBoxTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.goToElements();


    }
}
