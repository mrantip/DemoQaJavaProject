package com.example.demoqa.api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.example.demoqa.api.models.accountmodels.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.demoqa.api.services.UserService;
import static org.example.demoqa.api.assertions.Conditions.hasMessage;
import static org.example.demoqa.api.assertions.Conditions.hasStatusCode;
import static org.example.demoqa.api.utils.AccountUtils.getRandomUser;
import static org.example.demoqa.api.utils.AccountUtils.random;

public class AccountTests extends BaseApiTest{

    private static UserService userService;
    private UserModel user;

    @BeforeEach
    public void initUser() {
        user = getRandomUser();
    }

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://demoqa.com/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        userService = new UserService();
    }

    @Test
    public void positiveRegisterUserTest() {
        RegisterViewModel response = userService.register(user)
                .should(hasStatusCode(201))
                .as(RegisterViewModel.class);
        Assertions.assertEquals(user.getUserName(), response.getUsername());
    }

    @Test
    public void negativeRegisterUserExistsTest() {
        userService.register(user);
        userService.register(user)
                .should(hasStatusCode(406))
                .should(hasMessage("User exists!"));
    }

    @Test
    public void negativeRegisterUserNoPasswordTest() {
        user.setPassword(null);
        userService.register(user)
                .should(hasStatusCode(400))
                .should(hasMessage("UserName and Password required."));
    }

    @Test
    public void negativeRegisterUserWrongPasswordTest() {
        user.setPassword(random.nextInt()+"");
        userService.register(user)
                .should(hasStatusCode(400))
                .should(hasMessage("Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer."));
    }

    @Test
    public void positiveGenerateTokenTest() {
        userService.register(user);

        String token = userService.auth(user).should(hasStatusCode(200)).asJwt();
        Assertions.assertNotNull(token);
    }

    @Test
    public void negativeGenerateTokenTest() {
        userService.register(user);
        user.setPassword(random.nextInt()+"");

        String token = userService.auth(user).should(hasStatusCode(200)).asJwt();
        Assertions.assertNull(token);

    }

    @Test
    public void positiveAuthoriseUserTest() {
        userService.register(user);
        userService.auth(user);

        boolean yes = userService.checkAuth(user).should(hasStatusCode(200)).as(Boolean.class);
        Assertions.assertTrue(yes);
    }

    @Test
    public void negativeAuthoriseUserTest() {
        userService.register(user);

        boolean no = userService.checkAuth(user).should(hasStatusCode(200)).as(Boolean.class);
        Assertions.assertFalse(no);
    }

    @Test
    public void positiveLoginUserTest() {
        userService.register(user);
        userService.auth(user);

        LoginResponseModel response = userService.login(user)
                .should(hasStatusCode(200))
                .as(LoginResponseModel.class);

        Assertions.assertEquals(user.getUserName(), response.getUsername());
    }

    @Test
    public void positiveGetUserTest() {
        String userId = userService.register(user).asUserId();
        String token = userService.auth(user).asJwt();

        RegisterViewModel result = userService.getUser(token, userId)
                .should(hasStatusCode(200))
                .as(RegisterViewModel.class);

        Assertions.assertEquals(user.getUserName(), result.getUsername());
    }

    @Test
    public void negativeGetUserTest() {
        String userId = userService.register(user).asUserId();
        String token = userService.auth(user).asJwt();

        userService.getUser(token, "UserId")
                .should(hasStatusCode(401))
                .should(hasMessage("User not found!"));

    }

    @Test
    public void positiveDeleteUserTest() {
        String userId = userService.register(user).asUserId();
        String token = userService.auth(user).asJwt();

        userService.deleteUser(token, userId).should(hasStatusCode(204));

    }

    @Test
    public void negativeDeleteUserTest() {
        String userId = userService.register(user).asUserId();
        String token = userService.auth(user).asJwt();

        userService.deleteUser(token, "userId").should(hasStatusCode(200))
                .should(hasMessage("User Id not correct!"));

    }
}
