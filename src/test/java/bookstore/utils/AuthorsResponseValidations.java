package bookstore.utils;

import bookstore.dto.AuthorRequestDto;
import bookstore.dto.AuthorResponseDto;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class AuthorsResponseValidations extends BaseResponseValidations {

    private static final String AUTHOR_SCHEMA_PATH = "schemas/author-schema.json";
    private static final String AUTHORS_LIST_SCHEMA_PATH = "schemas/authors-list-schema.json";


    public static void validateCreatedAuthorMatchesRequestDetails(AuthorRequestDto requestDto, AuthorResponseDto responseDto) {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(responseDto.getIdBook(),
                requestDto.getIdBook(),
                "Book ID does not match");

        softAssert.assertEquals(responseDto.getFirstName(),
                requestDto.getFirstName(),
                "First name does not match");

        softAssert.assertEquals(responseDto.getLastName(),
                requestDto.getLastName(),
                "Last name does not match");

        softAssert.assertAll();
    }

    public static AuthorResponseDto getAuthorByIdFromAuthorsList(Response response, int expectedAuthorId) {
        List<AuthorResponseDto> authors = response.jsonPath().getList("$", AuthorResponseDto.class);

        return authors.stream()
                .filter(author -> author.getId() == expectedAuthorId)
                .findFirst()
                .orElseThrow(() -> new AssertionError(
                        String.format("Author with ID %d was not found", expectedAuthorId)));
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

    public static void validateAuthorSchema(Response response) {
        validateSchema(response, AUTHOR_SCHEMA_PATH);
    }

    public static void validateAuthorsListSchema(Response response) {
        validateSchema(response, AUTHORS_LIST_SCHEMA_PATH);
    }
}
