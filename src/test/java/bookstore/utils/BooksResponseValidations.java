package bookstore.utils;

import bookstore.dto.BookRequestDto;
import bookstore.dto.BookResponseDto;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;
import java.util.List;

import static org.testng.Assert.*;


public class BooksResponseValidations extends BaseResponseValidations {

    private static final String BOOK_SCHEMA_PATH = "schemas/book-schema.json";
    private static final String BOOKS_LIST_SCHEMA_PATH = "schemas/books-list-schema.json";


    public static void validateCreatedBookMatchesRequestDetails(BookRequestDto requestDto, BookResponseDto responseDto) {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(responseDto.getTitle(),
                requestDto.getTitle(),
                "Title does not match");

        softAssert.assertEquals(responseDto.getDescription(),
                requestDto.getDescription(),
                "Description does not match");

        softAssert.assertEquals(responseDto.getPageCount(),
                requestDto.getPageCount(),
                "Page count does not match");

        softAssert.assertEquals(responseDto.getExcerpt(),
                requestDto.getExcerpt(),
                "Excerpt does not match");

        softAssert.assertTrue(responseDto.getPublishDate().startsWith(
                requestDto.getPublishDate()),
                "Publish date does not match");

        softAssert.assertAll();
    }

    public static void validateBooksCountIsExpected(Response response, int expectedCount) {
        List<BookResponseDto> books = response.jsonPath().getList("$", BookResponseDto.class);

        assertEquals(books.size(),
                expectedCount,
                String.format("Expected books count to be %d but got %d", expectedCount, books.size()));
    }

    public static void validateBookSchema(Response response) {
        validateSchema(response, BOOK_SCHEMA_PATH);
    }

    public static void validateBooksListSchema(Response response) {
        validateSchema(response, BOOKS_LIST_SCHEMA_PATH);
    }

}
