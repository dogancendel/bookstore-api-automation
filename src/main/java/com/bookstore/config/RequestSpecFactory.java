package com.bookstore.config;

        import io.restassured.builder.RequestSpecBuilder;
        import io.restassured.specification.RequestSpecification;
        import io.qameta.allure.restassured.AllureRestAssured;
        import io.restassured.http.ContentType;

public class RequestSpecFactory {
    private static RequestSpecification requestSpec;

    public static RequestSpecification getRequestSpec() {
        if (requestSpec == null) {
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(ApiConfig.getBaseUrl())
                    .setContentType(ContentType.JSON)
                    .setAccept(ContentType.JSON)
                    .addFilter(new AllureRestAssured())
                    .build();
        }
        return requestSpec;
    }
}