package com.bookstore.api;

import com.bookstore.config.RequestSpecFactory;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.Step;

public abstract class BaseApiClient {
    protected final RequestSpecification spec;
    protected final String basePath;

    protected BaseApiClient(String basePath) {
        this.spec = RequestSpecFactory.getRequestSpec();
        this.basePath = basePath;
    }

    @Step("Log response details")
    protected Response logResponse(Response response) {
        response.then().log().all();
        return response;
    }
}