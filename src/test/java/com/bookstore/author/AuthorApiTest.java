package com.bookstore.author;

import com.bookstore.api.AuthorApiClient;
import com.bookstore.base.BaseTest;
import com.bookstore.models.Author;
import com.bookstore.utils.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthorApiTest extends BaseTest {

    private final AuthorApiClient api = new AuthorApiClient();

    @Test
    @DisplayName("GET all authors returns 200 with valid data")
    void getAllAuthors_returns200() {
        assertThat(api.getAllAuthors().getStatusCode()).isEqualTo(200);
    }

    @Test
    @DisplayName("POST creates a new author successfully")
    void createAuthor_returns201() {
        Author createdAuthor = api.createAuthor(TestDataFactory.createValidAuthor()).as(Author.class);

        assertThat(createdAuthor.getId()).isPositive();
        api.deleteAuthor(createdAuthor.getId());
    }

    @Test
    @DisplayName("PUT updates an author successfully")
    void updateAuthor_returnsUpdatedData() {
        Author originalAuthor = api.createAuthor(TestDataFactory.createValidAuthor()).as(Author.class);
        Author updatedAuthor = TestDataFactory.createAuthorWithFirstName("UpdatedFirstName");

        Author result = api.updateAuthor(originalAuthor.getId(), updatedAuthor).as(Author.class);
        assertThat(result.getFirstName()).isEqualTo("UpdatedFirstName");

        api.deleteAuthor(originalAuthor.getId());
    }

    @Test
    @DisplayName("DELETE removes an author successfully")
    void deleteAuthor_returns200() {
        Author author = api.createAuthor(TestDataFactory.createValidAuthor()).as(Author.class);

        assertThat(api.deleteAuthor(author.getId()).getStatusCode()).isEqualTo(200);
        assertThat(api.getAuthorById(author.getId()).getStatusCode()).isEqualTo(404);
    }
}