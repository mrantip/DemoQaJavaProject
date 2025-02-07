package com.example.demoqa.api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.demoqa.api.models.accountmodels.RegisterUserModel;
import org.example.demoqa.api.models.accountmodels.RegisterViewModel;
import org.example.demoqa.api.models.accountmodels.TokenModel;
import org.example.demoqa.api.models.bookstoremodels.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.example.demoqa.api.utils.AccountUtils.PasswordGenerator.generatePassword;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class BookStoreTests {
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
    public void getAllBooksTest(){
        List<String> books = given().get("/BookStore/v1/Books")
                .then().statusCode(200)
                .extract().path("books");
        Assertions.assertFalse(books.isEmpty());
    }

    @Test
    public void postBookTest(){
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

        BooksModel books = given().get("/BookStore/v1/Books")
                .then()
                .extract().as(BooksModel.class);

        List<BooksItem> boo = books.getBooks();

        String isbn = boo.get(0).getIsbn();
        CollectionOfIsbnsItem goodIsbn = new CollectionOfIsbnsItem();
        goodIsbn.setIsbn(isbn);

        AddListOfBooksModel bookIsbn = new AddListOfBooksModel();
        bookIsbn.setUserId(UserId);
        bookIsbn.setCollectionOfIsbns(Arrays.asList(goodIsbn));


        BooksIsbnModel resIsbn = given().contentType(ContentType.JSON).auth().oauth2(token.getToken())
                .body(bookIsbn)
                .post("/BookStore/v1/Books")
                .then().statusCode(201)
                .extract().as(BooksIsbnModel.class);
        Assertions.assertEquals(isbn, resIsbn.getBooks().get(0).getIsbn());
    }


}
