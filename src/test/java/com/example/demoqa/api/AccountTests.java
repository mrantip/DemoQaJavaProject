package com.example.demoqa.api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.example.demoqa.api.assertions.Condition;
import org.example.demoqa.api.assertions.Conditions;
import org.example.demoqa.api.models.accountmodels.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.example.demoqa.api.services.UserService;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.example.demoqa.api.assertions.Conditions.hasMessage;
import static org.example.demoqa.api.assertions.Conditions.hasStatusCode;
import static org.example.demoqa.api.utils.AccountUtils.PasswordGenerator.generatePassword;

public class AccountTests extends BaseApiTest{

    private static Random random;
    private static UserService userService;
    private UserModel randomUser;

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://demoqa.com/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        random = new Random();
        userService = new UserService();
    }
//    int randomNumber = Math.abs(random.nextInt());
//    UserModel user = UserModel.builder()
//            .userName("serg" + randomNumber)
//            .password(generatePassword())
//            .build();

    private UserModel getRandomUser() {
        Integer randomNumber = Math.abs(random.nextInt());
        return UserModel.builder().userName("serg" + randomNumber).password(generatePassword()).build();
    }


    @Test
    public void positiveRegisterUserTest() {
        UserModel user = getRandomUser();
        RegisterViewModel response = userService.register(user)
                .should(hasStatusCode(201))
                .as(RegisterViewModel.class);
        Assertions.assertEquals(user.getUserName(), response.getUsername());
        userService.register(user);

//        RegisterViewModel response = given().contentType(ContentType.JSON)
//                .body(user)
//                .post("/Account/v1/User")
//                .then().statusCode(201)
//                .extract().as(RegisterViewModel.class); //jsonPath().getObject("response", Response.class);
//        Assertions.assertEquals(user.getUserName(), response.getUsername());

    }

    @Test
    public void negativeRegisterUserExistsTest() {
        UserModel user = getRandomUser();
        userService.register(user);
        userService.register(user)
                .should(hasStatusCode(406))
                .should(hasMessage("User exists!"));
    }

    @Test
    public void negativeRegisterUserNoPasswordTest() {
        UserModel user = getRandomUser();
        user.setPassword(null);
        userService.register(user)
                .should(hasStatusCode(400))
                .should(hasMessage("UserName and Password required."));
    }

    @Test
    public void negativeRegisterUserWrongPasswordTest() {
        UserModel user = getRandomUser();
        user.setPassword(random.nextInt()+"");
        userService.register(user)
                .should(hasStatusCode(400))
                .should(hasMessage("Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer."));
    }

    @Test
    public void generateTokenTest() {
        UserModel user = getRandomUser();
        userService.register(user);

        String token = userService.auth(user).should(hasStatusCode(200)).asJwt();
        Assertions.assertNotNull(token);

//        TokenModel token = given().contentType(ContentType.JSON)
//                .body(user)
//                .post("/Account/v1/GenerateToken")
//                .then().statusCode(200)
//                .extract().as(TokenModel.class);
//        Assertions.assertNotNull(token);
//        Assertions.assertEquals("Success", token.getStatus());
    }

//    @Test
//    public void positiveAuthoriseUserTest() {
//        given().contentType(ContentType.JSON)
//                .body(user)
//                .post("/Account/v1/User");
//
//        given().contentType(ContentType.JSON)
//                .body(user)
//                .post("/Account/v1/GenerateToken");
//
//        boolean yes = given().contentType(ContentType.JSON)
//                .body(user)
//                .post("/Account/v1/Authorized")
//                .then().statusCode(200)
//                .extract().as(Boolean.class);
//        Assertions.assertTrue(yes);
//
//
//    }
//
//    @Test
//    public void positiveLoginUserTest() {
//        given().contentType(ContentType.JSON)
//                .body(user)
//                .post("/Account/v1/User");
//
//        given().contentType(ContentType.JSON)
//                .body(user)
//                .post("/Account/v1/GenerateToken");
//
//
//        LoginResponseModel response = given().contentType(ContentType.JSON)
//                .body(user)
//                .post("/Account/v1/Login")
//                .then().statusCode(200)
//                .extract().as(LoginResponseModel.class);
//
//        Assertions.assertEquals(user.getUserName(), response.getUsername());
//    }
//
//    @Test
//    public void getUserTest() {
//        RegisterViewModel response = given().contentType(ContentType.JSON)
//                .body(user)
//                .post("/Account/v1/User")
//                .then().extract().as(RegisterViewModel.class);
//
//
//        String UserId = response.getUserID();
//        TokenModel token = given().contentType(ContentType.JSON)
//                .body(user)
//                .post("/Account/v1/GenerateToken")
//                .then().statusCode(200)
//                .extract().as(TokenModel.class);
//
//        given().contentType(ContentType.JSON)
//                .body(user)
//                .post("/Account/v1/Authorized");
//
//        given().contentType(ContentType.JSON)
//                .body(user)
//                .post("/Account/v1/Login");
//
//
//        RegisterViewModel result = given().auth().oauth2(token.getToken())
//        .get("/Account/v1/User/{UserId}", UserId)
//        .then().statusCode(200)
//        .extract().as(RegisterViewModel.class);
//        Assertions.assertEquals(user.getUserName(), result.getUsername());
//    }
//
//    @Test
//    public void deleteUserTest() {
//        RegisterViewModel response = given().contentType(ContentType.JSON)
//                .body(user)
//                .post("/Account/v1/User")
//                .then().extract().as(RegisterViewModel.class);
//
//
//        String UserId = response.getUserID();
//        TokenModel token = given().contentType(ContentType.JSON)
//                .body(user)
//                .post("/Account/v1/GenerateToken")
//                .then().statusCode(200)
//                .extract().as(TokenModel.class);
//
//        given().contentType(ContentType.JSON)
//                .body(user)
//                .post("/Account/v1/Authorized");
//
//        given().contentType(ContentType.JSON)
//                .body(user)
//                .post("/Account/v1/Login");
//
//        given().auth().oauth2(token.getToken())
//                .delete("/Account/v1/User/{UserId}", UserId)
//                .then().statusCode(204);
//
//    }
}
