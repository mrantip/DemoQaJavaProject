package com.example.demoqa.api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.example.demoqa.api.models.accountmodels.*;
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
    int randomNumber = Math.abs(random.nextInt());
    RegisterUserModel user = RegisterUserModel.builder()
            .userName("serg" + randomNumber)
            .password(generatePassword())
            .build();



    @Test
    public void positiveRegisterUserTest() {
        RegisterViewModel response = given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/User")
                .then().statusCode(201)
                .extract().as(RegisterViewModel.class); //jsonPath().getObject("response", Response.class);
        Assertions.assertEquals(user.getUserName(), response.getUsername());

    }

    @Test
    public void generateTokenTest() {
        given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/User");

        TokenModel token = given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/GenerateToken")
                .then().statusCode(200)
                .extract().as(TokenModel.class);
        Assertions.assertNotNull(token);
        Assertions.assertEquals("Success", token.getStatus());
    }

    @Test
    public void positiveAuthoriseUserTest() {
        given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/User");

        given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/GenerateToken");

        boolean yes = given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/Authorized")
                .then().statusCode(200)
                .extract().as(Boolean.class);
        Assertions.assertTrue(yes);


    }

    @Test
    public void positiveLoginUserTest() {
        given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/User");

        given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/GenerateToken");


        LoginResponseModel response = given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/Login")
                .then().statusCode(200)
                .extract().as(LoginResponseModel.class);

        Assertions.assertEquals(user.getUserName(), response.getUsername());
    }

    @Test
    public void getUserTest() {
        RegisterViewModel response = given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/User")
                .then().extract().as(RegisterViewModel.class);


        String UserId = response.getUserId();
        TokenModel token = given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/GenerateToken")
                .then().statusCode(200)
                .extract().as(TokenModel.class);

        given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/Authorized");

        given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/Login");


        RegisterViewModel result = given().auth().oauth2(token.getToken())
        .get("/Account/v1/User/{UserId}", UserId)
        .then().statusCode(200)
        .extract().as(RegisterViewModel.class);
        Assertions.assertEquals(user.getUserName(), result.getUsername());
    }

    @Test
    public void deleteUserTest() {
        RegisterViewModel response = given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/User")
                .then().extract().as(RegisterViewModel.class);


        String UserId = response.getUserId();
        TokenModel token = given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/GenerateToken")
                .then().statusCode(200)
                .extract().as(TokenModel.class);

        given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/Authorized");

        given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/Login");

        given().auth().oauth2(token.getToken())
                .delete("/Account/v1/User/{UserId}", UserId)
                .then().statusCode(204);

    }
}
