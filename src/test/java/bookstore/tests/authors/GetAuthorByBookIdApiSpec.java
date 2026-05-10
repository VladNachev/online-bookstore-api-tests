package bookstore.tests.authors;

import bookstore.dto.AuthorResponseDto;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static bookstore.testdata.AuthorTestDataFactory.buildExpectedAuthorResponseDto;
import static bookstore.utils.AuthorsResponseValidations.getAuthorByIdFromAuthorsList;
import static bookstore.utils.AuthorsResponseValidations.validateAuthorMatchesExpectedDetails;
import static bookstore.utils.BaseResponseValidations.*;

@Feature("GET Authors By Book ID")
@Tag("Authors")
public class GetAuthorByBookIdApiSpec extends AuthorsBaseTest {

    @Test(description = "Verify retrieval of authors by book ID")
    @Description("Verify that GET /Authors/authors/books/{idBook} returns HTTP 200 with a non-empty list of authors for the requested book ID.")
    public void getAuthorsByBookIdShouldReturnListOfAuthors() {
        Response response = authorsClient.getAuthorsByBookId(1); // bookId = 1

        validateStatusCodeIsExpected(response, 200);
        validateHeadersContentTypeIsExpected(response, "application/json");
        validateListIsNotEmpty(response);

        /* fakeRestAPI return dynamics list content, but author with ID 1
           is stable enough to validate as a known reference record. */
        AuthorResponseDto expectedAuthorDto = buildExpectedAuthorResponseDto();
        AuthorResponseDto actualAuthorDto = getAuthorByIdFromAuthorsList(response, expectedAuthorDto.getId());
        validateAuthorMatchesExpectedDetails(expectedAuthorDto, actualAuthorDto);
    }

    @Test(description = "Verify retrieval of authors by non-existent book ID")
    @Description("Verify that GET /Authors/authors/books/{idBook} returns HTTP 200 with an empty list when the book ID does not exist.")
    public void getAuthorsByNonExistentBookIdShouldReturnEmptyList() {
        Response response = authorsClient.getAuthorsByBookId(9999); // boookId = 9999

        validateStatusCodeIsExpected(response, 200);
        validateHeadersContentTypeIsExpected(response, "application/json");
        validateListIsEmpty(response);
    }
}
