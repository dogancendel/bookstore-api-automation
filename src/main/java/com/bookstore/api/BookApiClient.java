package com.bookstore.api;

import com.bookstore.config.RequestSpecFactory;
import com.bookstore.models.Book;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.Step;
import io.qameta.allure.Attachment;
import static io.restassured.RestAssured.given;

public class BookApiClient {
    private static final String BASE_PATH = "/api/v1/Books";
    private final RequestSpecification spec;

    public BookApiClient() {
        this.spec = RequestSpecFactory.getRequestSpec();
    }

    @Step("Get all books")
    public Response getAllBooks() {
        return given(spec).when().get(BASE_PATH).then().extract().response();
    }

    public Response getBookById(int id) {
        return given(spec).when().get(BASE_PATH + "/" + id).then().extract().response();
    }

    public Response createBook(Book book) {
        return given(spec).body(book).when().post(BASE_PATH).then().extract().response();
    }

    public Response updateBook(int id, Book book) {
        return given(spec).body(book).when().put(BASE_PATH + "/" + id).then().extract().response();
    }

    public Response deleteBook(int id) {
        return given(spec).when().delete(BASE_PATH + "/" + id).then().extract().response();
    }
}
