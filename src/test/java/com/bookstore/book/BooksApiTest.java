package com.bookstore.tests.book;

import com.bookstore.api.BookApiClient;
import com.bookstore.tests.base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Books API - Smoke")
public class BooksApiTest extends BaseTest {

    private final BookApiClient api = new BookApiClient();

    @Test
    @Tag("smoke")
    void getAllBooks_returns200() {
        Response resp = api.getAllBooks();
        assertThat(resp.getStatusCode()).isEqualTo(200);
        assertThat(resp.jsonPath().getList("id")).isNotNull();
    }
}
