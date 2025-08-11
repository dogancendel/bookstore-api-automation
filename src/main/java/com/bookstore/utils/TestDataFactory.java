package com.bookstore.utils;

import com.bookstore.models.Author;
import net.datafaker.Faker;
import com.bookstore.models.Book;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataFactory {
    private static final Faker faker = new Faker();

    public static Book createValidBook() {
        Book book = new Book();
        book.setId(ThreadLocalRandom.current().nextInt(100_000, 1_000_000));
        book.setTitle(faker.book().title());
        book.setDescription(faker.lorem().paragraph(1));
        book.setPageCount(faker.number().numberBetween(50, 500));
        book.setExcerpt(faker.lorem().paragraph(2));
        book.setPublishDate(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        return book;
    }

    public static Book createBookWithMaxLengthFields() {
        Book book = createValidBook();
        book.setTitle(faker.lorem().characters(1000)); // 1000 karakterlik rastgele metin
        book.setDescription(faker.lorem().characters(5000)); // 5000 karakterlik
        return book;
    }

    public static Book createBookWithSpecialCharacters() {
        Book book = createValidBook();
        book.setTitle("Test Book !@#$%^&*()_+-=[]{};':\",./<>?");
        return book;
    }

    public static Book createBookWithSqlInjectionAttempt() {
        Book book = createValidBook();
        book.setTitle("Robert'); DROP TABLE Books;--");
        return book;
    }

    public static Book createBookWithMissingFields() {
        Book book = new Book();
// Intentionally leaving all fields null
        return book;
    }


    public static Book createBookWithInvalidData() {
        Book book = new Book();
        book.setTitle(""); // Empty title
        book.setDescription(""); // Empty description
        book.setPageCount(-100); // Negative page count
        book.setExcerpt(""); // Empty excerpt
        book.setPublishDate("invalid-date"); // Invalid date format
        return book;
    }

    public static Book createBookWithXssAttempt() {
        Book book = createValidBook();
        book.setTitle("<script>alert('XSS')</script>");
        book.setDescription("<img src=x onerror=alert('XSS')>");
        return book;
    }

    public static Book createBookWithExtremelyLongSingleWord() {
        Book book = createValidBook();
        book.setTitle(faker.lorem().characters(10000));
        return book;
    }

    public static Book createBookWithNegativePageCount() {
        Book book = createValidBook();
        book.setPageCount(-100);
        return book;
    }

    public static Book createBookWithInvalidDate() {
        Book book = createValidBook();
        book.setPublishDate("invalid-date-format");
        return book;
    }

    public static long createOutOfRangeId() {
        return 11111111111L;
    }

    public static int createNegativeBookId() {
        return faker.number().negative();
    }

    public static Author createAuthorWithMissingFields() {
        Author author = new Author();
// Intentionally leaving required fields null
        return author;
    }

    public static Author createValidAuthor() {
        Author author = new Author();
        author.setId(ThreadLocalRandom.current().nextInt(1, 100000));
        author.setIdBook(ThreadLocalRandom.current().nextInt(1, 100000));
        author.setFirstName(faker.name().firstName());
        author.setLastName(faker.name().lastName());
        return author;
    }

    public static Author createAuthorWithMaxLengthFields() {
        Author author = new Author();
        author.setId(ThreadLocalRandom.current().nextInt(1, 100000));
        author.setIdBook(ThreadLocalRandom.current().nextInt(1, 100000));
        author.setFirstName(faker.lorem().characters(255));
        author.setLastName(faker.lorem().characters(255));
        return author;
    }

    public static Author createAuthorWithSpecialCharacters() {
        Author author = createValidAuthor();
        author.setFirstName("Jöhn D'oe");
        author.setLastName("O'Conner-Smith");
        return author;
    }

    public static Author createAuthorWithEmptyLastName() {
        Author author = createValidAuthor();
        author.setLastName("");
        return author;
    }

    public static Author createAuthorWithNullLastName() {
        Author author = createValidAuthor();
        author.setLastName(null);
        return author;
    }

    public static Author createAuthorWithEmptyFirstName() {
        Author author = createValidAuthor();
        author.setFirstName("");
        return author;
    }

    public static Author createAuthorWithNullFirstName() {
        Author author = createValidAuthor();
        author.setFirstName(null);
        return author;
    }

    public static Author createAuthorWithEmptyFirstAndLastName() {
        Author author = createValidAuthor();
        author.setFirstName("");
        author.setLastName("");
        return author;
    }

    public static Author createAuthorWithFirstName(String firstName) {
        Author author = createValidAuthor();
        author.setFirstName(firstName);
        return author;
    }

    public static Author createAuthorWithLastName(String lastName) {
        Author author = createValidAuthor();
        author.setLastName(lastName);
        return author;
    }

    public static Author createAuthorWithCustomDetails(String firstName, String lastName) {
        Author author = createValidAuthor();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        return author;
    }

    public static int invalidAuthorId() {
        return 999999; // Non-existent ID
    }

    public static int invalidAuthorIdForDelete() {
        return -1; // Negative ID
    }

    public static Author createAuthorWithNullFields() {
        Author author = new Author();
        author.setId(faker.number().numberBetween(1, 100000));
        author.setFirstName(null);
        author.setLastName(null);
        author.setIdBook(null);
        return author;
    }

    public static Author createAuthorWithEmptyFields() {
        Author author = new Author();
        author.setId(faker.number().numberBetween(1, 100000));
        author.setFirstName("");
        author.setLastName("");
        return author;
    }
}