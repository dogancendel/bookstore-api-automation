package com.bookstore.book;

import com.bookstore.api.BookApiClient;
import com.bookstore.base.BaseTest;
import com.bookstore.models.Book;
import com.bookstore.utils.TestDataFactory;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BookNegativeTest extends BaseTest {

    private final BookApiClient api = new BookApiClient();

    @Test
    @DisplayName("GET book with invalid ID returns 404")
    void getBookWithInvalidId_returns404() {
        Response resp = api.getBookById(999999);
        assertThat(resp.getStatusCode()).isEqualTo(404);
    }

    @Test
    @DisplayName("POST book with missing required fields returns 400")
    void createBookWithMissingFields_returns400() {
        Response resp = api.createBook(TestDataFactory.createBookWithMissingFields());
        assertThat(resp.getStatusCode()).isEqualTo(400);
    }

    @Test
    @DisplayName("PUT book with invalid date format returns 400")
    void updateBookWithInvalidDateFormat_returns400() {
        Book validBook = TestDataFactory.createValidBook();
        Response createResp = api.createBook(validBook);
        int bookId = createResp.jsonPath().getInt("id");

        Response updateResp = api.updateBook(bookId, TestDataFactory.createBookWithInvalidDate());
        assertThat(updateResp.getStatusCode()).isEqualTo(400);

        api.deleteBook(bookId);
    }

    @Test
    @DisplayName("DELETE with out-of-range ID returns 400")
    void deleteWithOutOfRangeId_returns400() {
        Response resp = api.deleteBook(TestDataFactory.createOutOfRangeId());

        assertThat(resp.getStatusCode()).isEqualTo(400);
        assertThat(resp.jsonPath().getList("errors.id"))
                .contains("The value '11111111111' is not valid.");
    }
}