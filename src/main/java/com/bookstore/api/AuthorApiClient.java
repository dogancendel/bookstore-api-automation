package com.bookstore.api;

import com.bookstore.models.Author;
import io.restassured.response.Response;
import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;

public class AuthorApiClient extends BaseApiClient {
    private static final String BASE_PATH = "/api/v1/Authors";

    public AuthorApiClient() {
        super(BASE_PATH);
    }

    @Step("Get all authors")
    public Response getAllAuthors() {
        return logResponse(
                given(spec).when().get(basePath)
        );
    }

    @Step("Get author by ID: {id}")
    public Response getAuthorById(int id) {
        return logResponse(
                given(spec).pathParam("id", id)
                        .when().get(basePath + "/{id}")
        );
    }

    @Step("Create new author")
    public Response createAuthor(Author author) {
        return logResponse(
                given(spec).body(author)
                        .when().post(basePath)
        );
    }

    @Step("Update author with ID: {id}")
    public Response updateAuthor(int id, Author author) {
        return logResponse(
                given(spec).pathParam("id", id)
                        .body(author)
                        .when().put(basePath + "/{id}")
        );
    }

    @Step("Delete author with ID: {id}")
    public Response deleteAuthor(int id) {
        return logResponse(
                given(spec).pathParam("id", id)
                        .when().delete(basePath + "/{id}")
        );
    }
}