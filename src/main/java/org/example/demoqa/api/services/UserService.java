package org.example.demoqa.api.services;

import io.restassured.http.ContentType;
import org.example.demoqa.api.assertions.AssertableResponse;
import org.example.demoqa.api.models.accountmodels.TokenModel;
import org.example.demoqa.api.models.accountmodels.UserModel;

import static io.restassured.RestAssured.given;

public class UserService {
    public AssertableResponse register(UserModel user){
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/User")
                .then());
    }

    public AssertableResponse auth(UserModel User){
        UserModel token = new UserModel(User.getUserName(), User.getPassword());
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(token)
                .post("/Account/v1/GenerateToken")
                .then());
    }
}
