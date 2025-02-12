package com.example.demoqa.api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.example.demoqa.api.models.accountmodels.UserModel;
import org.example.demoqa.api.services.UserService;
import org.junit.jupiter.api.BeforeAll;

import java.util.Random;

import static org.example.demoqa.api.utils.AccountUtils.PasswordGenerator.generatePassword;

public class BaseApiTest {

//    private static Random random;
//    private static UserService userService;
//    private UserModel randomUser;
//
//    @BeforeAll
//    public static void setUp() {
//        RestAssured.baseURI = "https://demoqa.com/";
//        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
//        random = new Random();
//        userService = new UserService();
//    }
//
//        UserModel getRandomUser() {
//            int randomNumber = Math.abs(random.nextInt());
//            return UserModel.builder().userName("serg" + randomNumber).password(generatePassword()).build();
//        }
}
