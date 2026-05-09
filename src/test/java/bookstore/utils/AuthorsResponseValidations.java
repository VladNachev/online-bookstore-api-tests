package bookstore.utils;

import bookstore.dto.AuthorRequestDto;
import bookstore.dto.AuthorResponseDto;
import bookstore.dto.BookResponseDto;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AuthorsResponseValidations extends BaseResponseValidations {

    public static void validateAuthorWithIdExists(Response response, int expectedBookId) {
        List<Integer> bookIds = response.jsonPath().getList("id", Integer.class);

        assertTrue(bookIds.contains(expectedBookId),
                String.format("Author with ID %d should exist in the response", expectedBookId));
    }

    public static AuthorResponseDto getAuthorByIdFromAuthorsList(Response response, int expectedBookId) {
        List<AuthorResponseDto> books = response.jsonPath().getList("$", AuthorResponseDto.class);

        return books.stream()
                .filter(book -> book.getId() == expectedBookId)
                .findFirst()
                .orElseThrow(() -> new AssertionError(
                        String.format("Author with ID %d was not found", expectedBookId)));
    }

    public static void validateAuthorMatchesExpectedDetails(AuthorResponseDto expectedDto, AuthorResponseDto actualDto) {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actualDto.getId(),
                expectedDto.getId(),
                "Author ID does not match");

        softAssert.assertEquals(actualDto.getIdBook(),
                expectedDto.getIdBook(),
                "Book ID does not match");

        softAssert.assertEquals(actualDto.getFirstName(),
                expectedDto.getFirstName(),
                "First name does not match");

        softAssert.assertEquals(actualDto.getLastName(),
                expectedDto.getLastName(),
                "Last name does not match");

        softAssert.assertAll();
    }
}
