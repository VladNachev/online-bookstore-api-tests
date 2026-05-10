package bookstore.tests.authors;

import bookstore.dto.AuthorResponseDto;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static bookstore.testdata.AuthorTestDataFactory.buildExpectedAuthorResponseDto;
import static bookstore.utils.BaseResponseValidations.*;
import static bookstore.utils.AuthorsResponseValidations.*;

@Feature("GET Authors")
@Tag("Authors")
public class GetAuthorsApiTest extends AuthorsBaseTest {

    private Response response;

    @BeforeClass
    public void getAuthors() {
        response = authorsClient.getAuthors();
    }

    @Test(description = "Verify retrieval of all authors")
    @Description("Verify that GET /Authors returns HTTP 200 with a non-empty list of authors.")
    public void getAuthorsShouldReturnListOfAuthors() {
        validateStatusCodeIsExpected(response, 200);
        validateHeadersContentTypeIsExpected(response, "application/json");
        validateAuthorsListSchema(response);
        validateListIsNotEmpty(response);
    }

    @Test(description = "Verify authors list contains author with expected ID")
    @Description("Verify that GET /Authors returns a list containing an author with the expected ID and populated stable fields.")
    public void getAuthorsShouldContainAuthorWithExpectedId() {
        AuthorResponseDto expectedAuthorDto = buildExpectedAuthorResponseDto();
        AuthorResponseDto actualAuthorDto = getAuthorByIdFromAuthorsList(response, 1);

        validateAuthorMatchesExpectedDetails(expectedAuthorDto, actualAuthorDto);
    }
}
