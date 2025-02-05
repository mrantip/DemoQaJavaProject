package com.example.demoqa.api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.example.demoqa.api.models.accountmodels.RegisterViewModel;
import org.example.demoqa.api.models.accountmodels.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.example.demoqa.api.utils.AccountUtils.PasswordGenerator.generatePassword;

public class AccountTests {

    private static Random random;

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://demoqa.com/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        random = new Random();
    }
    String password = generatePassword();
    int randomNumber = Math.abs(random.nextInt());
    RegisterViewModel user = RegisterViewModel.builder()
            .userName("serg" + randomNumber)
            .password(password)
            .build();



    @Test
    public void positiveRegisterUserTest() {
        Response response = given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/User")
                .then().statusCode(201)
                .extract().as(Response.class); //jsonPath().getObject("response", Response.class);
        Assertions.assertEquals(user.getUserName(), response.getUsername());

    }
}
