package com.example.demoqa.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    protected static final Duration BASE_WAITING = Duration.ofSeconds(30);
    protected WebDriver driver;


    @BeforeAll
    public static void downloadDriver() {
        WebDriverManager.chromedriver().setup();
    }

//    @BeforeEach
//    public void setUp() {
//        driver = new ChromeDriver();
//        driver.manage().window().setSize(new Dimension(1920, 1080));
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//        driver.get("https://demoqa.com/");
//    }

//    @AfterEach
//    public void tearDown() {
//        driver.close();
//    }
}
