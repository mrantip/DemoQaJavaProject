package com.example.demoqa.api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.example.demoqa.api.models.accountmodels.UserModel;
import org.example.demoqa.api.models.accountmodels.RegisterViewModel;
import org.example.demoqa.api.models.accountmodels.TokenModel;
import org.example.demoqa.api.models.bookstoremodels.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.example.demoqa.api.utils.AccountUtils.PasswordGenerator.generatePassword;
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
    UserModel user = UserModel.builder()
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


        String UserId = response.getUserID();
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
        bookIsbn.setCollectionOfIsbns(List.of(goodIsbn));


        BooksIsbnModel resIsbn = given().contentType(ContentType.JSON).auth().oauth2(token.getToken())
                .body(bookIsbn)
                .post("/BookStore/v1/Books")
                .then().statusCode(201)
                .extract().as(BooksIsbnModel.class);
        Assertions.assertEquals(isbn, resIsbn.getBooks().get(0).getIsbn());
    }

    @Test
    public void deleteUserBookTest(){
        RegisterViewModel response = given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/User")
                .then().extract().as(RegisterViewModel.class);


        String UserId = response.getUserID();
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
        bookIsbn.setCollectionOfIsbns(List.of(goodIsbn));


        given().contentType(ContentType.JSON).auth().oauth2(token.getToken())
                .body(bookIsbn)
                .post("/BookStore/v1/Books");


        given().auth().oauth2(token.getToken())
                .queryParam("UserId", UserId)
                .delete("/BookStore/v1/Books")
                .then().statusCode(204);
    }

    @Test
    public void getAllUserBooksTest(){
        RegisterViewModel response = given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/User")
                .then().extract().as(RegisterViewModel.class);


        String UserId = response.getUserID();
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
        bookIsbn.setCollectionOfIsbns(List.of(goodIsbn));


        given().contentType(ContentType.JSON).auth().oauth2(token.getToken())
                .body(bookIsbn)
                .post("/BookStore/v1/Books");

        BookModel book = given().auth().oauth2(token.getToken())
                .queryParam("ISBN", isbn)
                .get("/BookStore/v1/Book")
                .then().statusCode(200)
                .extract().as(BookModel.class);

        Assertions.assertEquals(isbn, book.getIsbn());
    }

    @Test
    public void deleteOneUserBookTest(){
        RegisterViewModel response = given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/User")
                .then().extract().as(RegisterViewModel.class);


        String UserId = response.getUserID();
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
        bookIsbn.setCollectionOfIsbns(List.of(goodIsbn));

        DeleteIsbnUserModel deleteIsbn = new DeleteIsbnUserModel(isbn, UserId);


        given().contentType(ContentType.JSON).auth().oauth2(token.getToken())
                .body(deleteIsbn)
                .post("/BookStore/v1/Books");

        UserBookResultModel resultModel = given().contentType(ContentType.JSON).auth().oauth2(token.getToken())
                .body(deleteIsbn)
                .delete("/BookStore/v1/Book")
                .then().statusCode(204)
                .extract().as(UserBookResultModel.class);
        Assertions.assertEquals(isbn, resultModel.getIsbn());
        Assertions.assertEquals(UserId, resultModel.getUserId());
    }

    @Test
    public void replacaUserIsbnTest(){
        RegisterViewModel response = given().contentType(ContentType.JSON)
                .body(user)
                .post("/Account/v1/User")
                .then().extract().as(RegisterViewModel.class);


        String UserId = response.getUserID();
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
        String isbnNew = boo.get(1).getIsbn();
        CollectionOfIsbnsItem goodIsbn = new CollectionOfIsbnsItem();
        goodIsbn.setIsbn(isbn);

        AddListOfBooksModel bookIsbn = new AddListOfBooksModel();
        bookIsbn.setUserId(UserId);
        bookIsbn.setCollectionOfIsbns(List.of(goodIsbn));

        ReplaceUserModel deleteIsbn = new ReplaceUserModel(UserId, isbnNew);


        given().contentType(ContentType.JSON).auth().oauth2(token.getToken())
                .body(bookIsbn)
                .post("/BookStore/v1/Books");

//        RegisterViewModel newBook = given().contentType(ContentType.JSON).auth().oauth2(token.getToken())
//                .pathParam("ISBN", isbn)
//                .body(deleteIsbn)
//                .put("/BookStore/v1/Books/{ISBN}")
//                .then().statusCode(200)
//                .extract().as(RegisterViewModel.class);
//        Assertions.assertEquals(isbnNew, newBook.getBooks().get(0).getIsbn());

        List<BooksItem> newBook = given().contentType(ContentType.JSON).auth().oauth2(token.getToken())
                .pathParam("ISBN", isbn)
                .body(deleteIsbn)
                .put("/BookStore/v1/Books/{ISBN}")
                .then().statusCode(200)
                .extract().jsonPath().getList("books", BooksItem.class);
        Assertions.assertEquals(isbnNew, newBook.get(0).getIsbn());
    }


}
