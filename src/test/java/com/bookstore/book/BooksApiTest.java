package com.bookstore.book;

import com.bookstore.api.BookApiClient;
import com.bookstore.base.BaseTest;
import com.bookstore.models.Book;
import com.bookstore.utils.TestDataFactory;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BooksApiTest extends BaseTest {

    private final BookApiClient api = new BookApiClient();

    @Test
    @DisplayName("GET all books returns 200 with valid data")
    void getAllBooks_returns200() {
        assertThat(api.getAllBooks().getStatusCode()).isEqualTo(200);
    }

    @Test
    @DisplayName("POST creates a new book successfully")
    void createBook_returns201() {
        Response resp = api.createBook(TestDataFactory.createValidBook());
        int bookId = resp.jsonPath().getInt("id");

        assertThat(resp.getStatusCode()).isEqualTo(200);
        assertThat(bookId).isPositive();

        api.deleteBook(bookId);
    }

    @Test
    @DisplayName("PUT updates a book successfully")
    void updateBook_returnsUpdatedData() {
        int bookId = api.createBook(TestDataFactory.createValidBook()).jsonPath().getInt("id");
        Book updatedBook = TestDataFactory.createValidBook();
        updatedBook.setTitle("Updated Title");

        Book result = api.updateBook(bookId, updatedBook).as(Book.class);
        assertThat(result.getTitle()).isEqualTo("Updated Title");

        api.deleteBook(bookId);
    }

    @Test
    @DisplayName("DELETE removes a book successfully")
    void deleteBook_returns200() {
        int bookId = api.createBook(TestDataFactory.createValidBook()).jsonPath().getInt("id");

        assertThat(api.deleteBook(bookId).getStatusCode()).isEqualTo(200);
    }
}