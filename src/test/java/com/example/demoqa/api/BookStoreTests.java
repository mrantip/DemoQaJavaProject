package com.example.demoqa.api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.example.demoqa.api.models.accountmodels.UserModel;
import org.example.demoqa.api.models.accountmodels.RegisterViewModel;
import org.example.demoqa.api.models.accountmodels.TokenModel;
import org.example.demoqa.api.models.bookstoremodels.*;
import org.example.demoqa.api.services.BookService;
import org.example.demoqa.api.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;


import static io.restassured.RestAssured.given;
import static org.example.demoqa.api.utils.AccountUtils.PasswordGenerator.generatePassword;
import static org.example.demoqa.api.utils.AccountUtils.getRandomUser;
import static org.hamcrest.Matchers.hasSize;
import static org.example.demoqa.api.assertions.Conditions.hasMessage;
import static org.example.demoqa.api.assertions.Conditions.hasStatusCode;

public class BookStoreTests {
    private static Random random;
    private static UserService userService;
    private static BookService bookService;

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://demoqa.com/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        random = new Random();
        userService = new UserService();
        bookService = new BookService();
    }

    int randomNumber = Math.abs(random.nextInt());
    UserModel user = UserModel.builder()
            .userName("serg" + randomNumber)
            .password(generatePassword())
            .build();


    @Test
    public void getAllBooksTest() {
        List<BooksModel> books = bookService.getAllBooks()
                .should(hasStatusCode(200))
                .asList("books", BooksModel.class);
//        List<String> books = given().get("/BookStore/v1/Books")
//                .then().statusCode(200)
//                .extract().path("books");
        Assertions.assertFalse(books.isEmpty());
    }

    @Test
    public void postBookTest() {
        UserModel user = getRandomUser();
        String userId = userService.register(user).asUserId();
        String token = userService.auth(user).asJwt();

        String isbn = bookService.getAllBooks()
                .as(BooksModel.class).getBooks().get(0).getIsbn();

        CollectionOfIsbnsItem goodIsbn = new CollectionOfIsbnsItem();
        goodIsbn.setIsbn(isbn);

        AddListOfBooksModel bookIsbn = new AddListOfBooksModel();
        bookIsbn.setUserId(userId);
        bookIsbn.setCollectionOfIsbns(List.of(goodIsbn));

        BooksIsbnModel resIsbn = bookService.addUserBook(bookIsbn, token)
                .should(hasStatusCode(201)).as(BooksIsbnModel.class);

        Assertions.assertEquals(isbn, resIsbn.getBooks().get(0).getIsbn());
    }

    @Test
    public void deleteAllUserBooksTest() {
        UserModel user = getRandomUser();
        String userId = userService.register(user).asUserId();
        String token = userService.auth(user).asJwt();

        String isbn = bookService.getAllBooks()
                .as(BooksModel.class).getBooks().get(0).getIsbn();

        CollectionOfIsbnsItem goodIsbn = new CollectionOfIsbnsItem();
        goodIsbn.setIsbn(isbn);

        AddListOfBooksModel bookIsbn = new AddListOfBooksModel();
        bookIsbn.setUserId(userId);
        bookIsbn.setCollectionOfIsbns(List.of(goodIsbn));

        BooksIsbnModel resIsbn = bookService.addUserBook(bookIsbn, token)
                .should(hasStatusCode(201)).as(BooksIsbnModel.class);

        bookService.deleteAllUserBooks(userId, token).should(hasStatusCode(204));
    }

    @Test
    public void getOneBookTest() {
        String isbn = bookService.getAllBooks()
                .as(BooksModel.class).getBooks().get(0).getIsbn();

        BookModel book = bookService.getOneBook(isbn).should(hasStatusCode(200))
                .as(BookModel.class);
        Assertions.assertEquals(isbn, book.getIsbn());
    }

    @Test
    public void deleteOneUserBookTest() {
        UserModel user = getRandomUser();
        String userId = userService.register(user).asUserId();
        String token = userService.auth(user).asJwt();

        String isbn = bookService.getAllBooks()
                .as(BooksModel.class).getBooks().get(0).getIsbn();

        CollectionOfIsbnsItem goodIsbn = new CollectionOfIsbnsItem();
        goodIsbn.setIsbn(isbn);

        AddListOfBooksModel bookIsbn = new AddListOfBooksModel();
        bookIsbn.setUserId(userId);
        bookIsbn.setCollectionOfIsbns(List.of(goodIsbn));

        BooksIsbnModel resIsbn = bookService.addUserBook(bookIsbn, token)
                .should(hasStatusCode(201)).as(BooksIsbnModel.class);

        DeleteIsbnUserModel deleteIsbn = new DeleteIsbnUserModel(isbn, userId);

        UserBookResultModel resultModel = bookService.deleteBook(deleteIsbn, token)
                .should(hasStatusCode(204)).as(UserBookResultModel.class);

        Assertions.assertEquals(isbn, resultModel.getIsbn());
        Assertions.assertEquals(userId, resultModel.getUserId());
    }

    @Test
    public void replaceUserBookTest() {
        UserModel user = getRandomUser();
        String userId = userService.register(user).asUserId();
        String token = userService.auth(user).asJwt();

        BooksModel b = bookService.getAllBooks()
                .as(BooksModel.class);

        String isbn = b.getBooks().get(0).getIsbn();
        String isbnNew = b.getBooks().get(1).getIsbn();

//        String isbn = bookService.getAllBooks()
//                .as(BooksModel.class).getBooks().get(0).getIsbn();
//        String isbnNew = bookService.getAllBooks()
//                .as(BooksModel.class).getBooks().get(1).getIsbn();

        CollectionOfIsbnsItem goodIsbn = new CollectionOfIsbnsItem();
        goodIsbn.setIsbn(isbn);

        AddListOfBooksModel bookIsbn = new AddListOfBooksModel();
        bookIsbn.setUserId(userId);
        bookIsbn.setCollectionOfIsbns(List.of(goodIsbn));

        BooksIsbnModel resIsbn = bookService.addUserBook(bookIsbn, token)
                .should(hasStatusCode(201)).as(BooksIsbnModel.class);

        ReplaceUserModel deleteIsbn = new ReplaceUserModel(userId, isbnNew);


//        RegisterViewModel newBook = given().contentType(ContentType.JSON).auth().oauth2(token.getToken())
//                .pathParam("ISBN", isbn)
//                .body(deleteIsbn)
//                .put("/BookStore/v1/Books/{ISBN}")
//                .then().statusCode(200)
//                .extract().as(RegisterViewModel.class);
//        Assertions.assertEquals(isbnNew, newBook.getBooks().get(0).getIsbn());

        List<BooksItem> newBook = given().contentType(ContentType.JSON).auth().oauth2(token)
                .pathParam("ISBN", isbn)
                .body(deleteIsbn)
                .put("/BookStore/v1/Books/{ISBN}")
                .then().statusCode(200)
                .extract().jsonPath().getList("books", BooksItem.class);
        Assertions.assertEquals(isbnNew, newBook.get(0).getIsbn());
    }
}
