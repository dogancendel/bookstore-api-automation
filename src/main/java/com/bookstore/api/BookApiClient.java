package com.bookstore.api;

import com.bookstore.models.Book;
import io.restassured.response.Response;
import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;

public class BookApiClient extends BaseApiClient {
    private static final String BASE_PATH = "/api/v1/Books";

    public BookApiClient() {
        super(BASE_PATH);
    }

    @Step("Get all books")
    public Response getAllBooks() {
        return logResponse(
                given(spec).when().get(basePath)
        );
    }

    @Step("Get book by ID: {id}")
    public Response getBookById(int id) {
        return logResponse(
                given(spec).pathParam("id", id)
                        .when().get(basePath + "/{id}")
        );
    }

    @Step("Create new book")
    public Response createBook(Book book) {
        return logResponse(
                given(spec).body(book)
                        .when().post(basePath)
        );
    }

    @Step("Update book with ID: {id}")
    public Response updateBook(int id, Book book) {
        return logResponse(
                given(spec).pathParam("id", id)
                        .body(book)
                        .when().put(basePath + "/{id}")
        );
    }

    @Step("Delete book with ID: {id}")
    public Response deleteBook(long id) {
        return logResponse(
                given(spec).pathParam("id", id)
                        .when().delete(basePath + "/{id}")
        );
    }
}