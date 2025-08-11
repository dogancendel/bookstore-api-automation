package com.bookstore.author;

import com.bookstore.api.AuthorApiClient;
import com.bookstore.base.BaseTest;
import com.bookstore.models.Author;
import com.bookstore.utils.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthorEdgeCaseTest extends BaseTest {

    private final AuthorApiClient api = new AuthorApiClient();

    @Test
    @DisplayName("Create author with maximum length fields")
    void createAuthorWithMaxLengthFields() {
        Author author = api.createAuthor(TestDataFactory.createAuthorWithMaxLengthFields()).as(Author.class);

        assertThat(author.getFirstName()).hasSize(255);
        assertThat(author.getLastName()).hasSize(255);

        api.deleteAuthor(author.getId());
    }

    @Test
    @DisplayName("Create author with special characters")
    void createAuthorWithSpecialCharacters() {
        Author author = api.createAuthor(TestDataFactory.createAuthorWithSpecialCharacters()).as(Author.class);

        assertThat(author.getFirstName()).contains("ö", "'");
        assertThat(author.getLastName()).contains("-", "'");

        api.deleteAuthor(author.getId());
    }

    @Test
    @DisplayName("Create author with empty last name - currently accepted (200)")
    void createAuthorWithEmptyLastName() {
        Author author = api.createAuthor(TestDataFactory.createAuthorWithEmptyLastName()).as(Author.class);

        assertThat(author.getLastName()).isEmpty();
        api.deleteAuthor(author.getId());
    }

    @Test
    @DisplayName("Create author with null last name - currently accepted (200)")
    void createAuthorWithNullLastName() {
        Author author = api.createAuthor(TestDataFactory.createAuthorWithNullLastName()).as(Author.class);

        assertThat(author.getLastName()).isNull();
        api.deleteAuthor(author.getId());
    }

    @Test
    @DisplayName("Create author with empty first name - currently accepted (200)")
    void createAuthorWithEmptyFirstName() {
        Author author = api.createAuthor(TestDataFactory.createAuthorWithEmptyFirstName()).as(Author.class);

        assertThat(author.getFirstName()).isEmpty();
        api.deleteAuthor(author.getId());
    }

    @Test
    @DisplayName("Create author with null first name - currently accepted (200)")
    void createAuthorWithNullFirstName() {
        Author author = api.createAuthor(TestDataFactory.createAuthorWithNullFirstName()).as(Author.class);

        assertThat(author.getFirstName()).isNull();
        api.deleteAuthor(author.getId());
    }

    @Test
    @DisplayName("Create author with empty first and last name - currently accepted (200)")
    void createAuthorWithEmptyFirstAndLastName() {
        Author author = api.createAuthor(TestDataFactory.createAuthorWithEmptyFirstAndLastName()).as(Author.class);

        assertThat(author.getFirstName()).isEmpty();
        assertThat(author.getLastName()).isEmpty();
        api.deleteAuthor(author.getId());
    }
}
