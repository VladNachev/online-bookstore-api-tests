package bookstore.utils;

import bookstore.dto.BookRequestDto;
import bookstore.dto.BookResponseDto;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;


public class BooksResponseValidations extends BaseResponseValidations {

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

    public static void validateBookMatchesExpectedDetails(BookResponseDto expectedDto, BookResponseDto actualDto) {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actualDto.getId(),
                expectedDto.getId(),
                "Book ID does not match");

        softAssert.assertEquals(actualDto.getTitle(),
                expectedDto.getTitle(),
                "Title does not match");

        softAssert.assertEquals(actualDto.getDescription(),
                expectedDto.getDescription(),
                "Description does not match");

        softAssert.assertEquals(actualDto.getPageCount(),
                expectedDto.getPageCount(),
                "Page count does not match");

        softAssert.assertEquals(actualDto.getExcerpt(),
                expectedDto.getExcerpt(),
                "Excerpt does not match");

        softAssert.assertNotNull(actualDto.getPublishDate(),
                "Publish date should not be null");

        softAssert.assertAll();
    }

    public static void validateBooksCountIsExpected(Response response, int expectedCount) {
        List<BookResponseDto> books = response.jsonPath().getList("$", BookResponseDto.class);

        assertEquals(books.size(),
                expectedCount,
                String.format("Expected books count to be %d but got %d", expectedCount, books.size()));
    }

    public static void validateBookWithIdExists(Response response, int expectedBookId) {
        List<Integer> bookIds = response.jsonPath().getList("id", Integer.class);

        assertTrue(bookIds.contains(expectedBookId),
                String.format("Book with ID %d should exist in the response", expectedBookId));
    }

    public static BookResponseDto getBookByIdFromBooksList(Response response, int expectedBookId) {
        List<BookResponseDto> books = response.jsonPath().getList("$", BookResponseDto.class);

        return books.stream()
                .filter(book -> book.getId() == expectedBookId)
                .findFirst()
                .orElseThrow(() -> new AssertionError(
                        String.format("Book with ID %d was not found", expectedBookId)));
    }

}
