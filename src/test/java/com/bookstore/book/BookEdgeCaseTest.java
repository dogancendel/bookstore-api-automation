package com.bookstore.book;

import com.bookstore.api.BookApiClient;
import com.bookstore.base.BaseTest;
import com.bookstore.models.Book;
import com.bookstore.utils.TestDataFactory;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BookEdgeCaseTest extends BaseTest {

    private final BookApiClient api = new BookApiClient();

    @Test
    @DisplayName("Create book with maximum length fields")
    void createBookWithMaxLengthFields() {
        Response resp = api.createBook(TestDataFactory.createBookWithMaxLengthFields());

        assertThat(resp.getStatusCode()).isEqualTo(200);
        Book createdBook = resp.as(Book.class);

        assertThat(createdBook.getTitle()).hasSize(1000);
        assertThat(createdBook.getDescription()).hasSize(5000);

        api.deleteBook(createdBook.getId());
    }

    @Test
    @DisplayName("Create book with special characters")
    void createBookWithSpecialCharacters() {
        Response resp = api.createBook(TestDataFactory.createBookWithSpecialCharacters());

        assertThat(resp.getStatusCode()).isEqualTo(200);
        assertThat(resp.as(Book.class).getTitle())
                .contains("!", "@", "#", "$", "%", "^", "&", "*");
    }

    @Test
    @DisplayName("Create book with SQL injection attempt")
    void createBookWithSqlInjection() {
        Response resp = api.createBook(TestDataFactory.createBookWithSqlInjectionAttempt());
        assertThat(resp.getStatusCode()).isEqualTo(200);
    }

    @Test
    @DisplayName("Create book with missing required fields")
    void createBookWithMissingFields() {
        assertThat(api.createBook(TestDataFactory.createBookWithMissingFields()).getStatusCode())
                .isEqualTo(400);
    }

    @Test
    @DisplayName("Create book with invalid data")
    void createBookWithInvalidData() {
        assertThat(api.createBook(TestDataFactory.createBookWithInvalidData()).getStatusCode())
                .isEqualTo(400);
    }

    @Test
    @DisplayName("Create book with XSS attempt")
    void createBookWithXssAttempt() {
        Response resp = api.createBook(TestDataFactory.createBookWithXssAttempt());

        assertThat(resp.getStatusCode()).isEqualTo(200);
    }

    @Test
    @DisplayName("Create book with extremely long single word title")
    void createBookWithExtremelyLongSingleWordTitle() {
        Response resp = api.createBook(TestDataFactory.createBookWithExtremelyLongSingleWord());

        assertThat(resp.getStatusCode()).isEqualTo(200);
    }

    @Test
    @DisplayName("PUT book with negative page count returns 200 (system accepts invalid data)")
    void updateBookWithNegativePageCount_returns200() {
        Book validBook = TestDataFactory.createValidBook();
        Response createResp = api.createBook(validBook);
        int bookId = createResp.jsonPath().getInt("id");

        Book invalidBook = TestDataFactory.createBookWithNegativePageCount();
        Response updateResp = api.updateBook(bookId, invalidBook);

        assertThat(updateResp.getStatusCode())
                .as("System currently accepts negative page count (should be invalid)")
                .isEqualTo(200);

        api.deleteBook(bookId);
    }

    @Test
    @DisplayName("DELETE with negative ID returns 200 (system accepts negative IDs)")
    void deleteWithNegativeId_returns200() {
        int negativeId = TestDataFactory.createNegativeBookId();

        Response deleteResp = api.deleteBook(negativeId);

        assertThat(deleteResp.getStatusCode())
                .as("System unexpectedly accepts negative IDs for deletion")
                .isEqualTo(200);
    }
}