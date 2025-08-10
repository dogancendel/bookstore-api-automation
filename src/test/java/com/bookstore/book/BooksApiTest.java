package com.bookstore.book;

import com.bookstore.api.BookApiClient;
import com.bookstore.base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;

public class BooksApiTest extends BaseTest {

    private final BookApiClient api = new BookApiClient();

    @Test
    @Tag("smoke")
    @Step("Get all books and verify status code 200")
    void getAllBooks_returns200() {
        Response resp = api.getAllBooks();
        assertThat(resp.getStatusCode()).isEqualTo(200);
        assertThat(resp.jsonPath().getList("id")).isNotNull();
    }
}
