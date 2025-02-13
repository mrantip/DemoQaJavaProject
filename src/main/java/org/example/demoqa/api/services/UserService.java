package org.example.demoqa.api.services;

import io.restassured.http.ContentType;
import org.example.demoqa.api.assertions.AssertableResponse;
import org.example.demoqa.api.models.accountmodels.TokenModel;
import org.example.demoqa.api.models.accountmodels.UserModel;
import org.example.demoqa.api.models.accountmodels.UserToken;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserService {
    public AssertableResponse register(UserModel user){
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/User")
                .then());
    }


    public AssertableResponse auth(UserModel user){
        UserToken token = new UserToken(user.getUserName(), user.getPassword());
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(token)
                .post("/Account/v1/GenerateToken")
                .then());
    }

    public String getToken(UserModel user){
       return auth(user).asJwt();
    }

    public AssertableResponse checkAuth(UserModel user){
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/Authorized")
                .then());
    }

    public AssertableResponse login(UserModel user){
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/Login")
                .then());
    }

    public AssertableResponse getUser(String jwt, String userId){
        return new AssertableResponse(given().auth().oauth2(jwt)
                .get("/Account/v1/User/{UserId}", userId)
                .then());
    }

    public AssertableResponse deleteUser(String jwt, String userId){
        return new AssertableResponse(given().auth().oauth2(jwt)
                .delete("/Account/v1/User/{UserId}", userId)
                .then());
    }
}
