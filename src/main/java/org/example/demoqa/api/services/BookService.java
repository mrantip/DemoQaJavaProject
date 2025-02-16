package org.example.demoqa.api.services;

import io.restassured.http.ContentType;
import org.example.demoqa.api.assertions.AssertableResponse;
import org.example.demoqa.api.models.bookstoremodels.AddListOfBooksModel;
import org.example.demoqa.api.models.bookstoremodels.DeleteIsbnUserModel;

import static io.restassured.RestAssured.given;

public class BookService {
    public AssertableResponse getAllBooks() {
        return new AssertableResponse(given().get("/BookStore/v1/Books").then());
    }

    public AssertableResponse addUserBook(AddListOfBooksModel isbn, String jwt) {
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .auth().oauth2(jwt)
                .body(isbn)
                .post("/BookStore/v1/Books")
                .then());
    }

    public AssertableResponse deleteAllUserBooks(String userId, String jwt) {
        return new AssertableResponse(given().auth().oauth2(jwt)
                .queryParam("UserId", userId)
                .delete("/BookStore/v1/Books")
                .then());
    }

    public AssertableResponse getOneBook(String isbn) {
        return new AssertableResponse(given()
                .queryParam("ISBN", isbn)
                .get("/BookStore/v1/Book")
                .then());
    }

    public AssertableResponse deleteBook(DeleteIsbnUserModel data, String jwt) {
        return new AssertableResponse(given().contentType(ContentType.JSON).auth().oauth2(jwt)
                .body(data)
                .delete("/BookStore/v1/Book")
                .then());
    }
}
