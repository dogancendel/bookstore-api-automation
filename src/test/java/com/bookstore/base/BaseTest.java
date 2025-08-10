package com.bookstore.base;

import com.bookstore.config.RequestSpecFactory;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    public static void globalSetup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecFactory.getRequestSpec();
    }
}
