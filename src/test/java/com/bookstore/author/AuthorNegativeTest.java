package com.bookstore.author;

import com.bookstore.api.AuthorApiClient;
import com.bookstore.base.BaseTest;
import com.bookstore.utils.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthorNegativeTest extends BaseTest {

    private final AuthorApiClient api = new AuthorApiClient();

    @Test
    @DisplayName("GET author with invalid ID returns 404")
    void getAuthorWithInvalidId_returns404() {
        assertThat(api.getAuthorById(TestDataFactory.invalidAuthorId()).getStatusCode())
                .isEqualTo(404);
    }

    @Test
    @DisplayName("POST author with missing required fields returns 400")
    void createAuthorWithMissingFields_returns400() {
        assertThat(api.createAuthor(TestDataFactory.createAuthorWithMissingFields()).getStatusCode())
                .isEqualTo(400);
    }

    @Test
    @DisplayName("DELETE with invalid ID returns 200")
    void deleteWithInvalidId_returns400() {
        assertThat(api.deleteAuthor(TestDataFactory.invalidAuthorIdForDelete()).getStatusCode())
                .isEqualTo(200);
    }
}