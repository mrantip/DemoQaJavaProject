package com.example.demoqa.ui;


import org.example.demoqa.ui.pages.MainPage;
import org.example.demoqa.ui.pages.elementspages.ElementsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.logging.Logger;

public class ElementsTests extends BaseTest{

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://demoqa.com/elements");
    }

    @Test
    public void fillTextBoxTest(){
        MainPage mainPage = new MainPage(driver);
//        driver.get("https://demoqa.com/elements");
//        mainPage.goToElements();
        ElementsPage elementsPage = new ElementsPage(driver);
        elementsPage.goToTextBox();
//                .fillFullName().fillEmail().fillCurrentAddress().fillPermanentAddress().submitButton();


    }

//    @Test
//    public void selectTextBoxTest(){
//        final Logger logger = Logger.getLogger(ElementsTests.class.getName());
//
//
//            logger.info("Открытие страницы...");
//            driver.get("https://demoqa.com/");
//
//            logger.info("Поиск элемента...");
//            WebElement element = driver.findElement(By.xpath("//div[@class='card-body']//h5[text()='Elements']"));
//
//            logger.info("Клик по элементу...");
//            element.click();
//
//    }
}
