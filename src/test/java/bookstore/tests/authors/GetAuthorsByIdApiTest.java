package bookstore.tests.authors;

import bookstore.dto.AuthorResponseDto;
import bookstore.testdata.AuthorDataProviders;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static bookstore.testdata.AuthorTestDataFactory.buildExpectedAuthorResponseDto;
import static bookstore.utils.AuthorsResponseValidations.validateAuthorMatchesExpectedDetails;
import static bookstore.utils.AuthorsResponseValidations.validateAuthorSchema;
import static bookstore.utils.BaseResponseValidations.validateHeadersContentTypeIsExpected;
import static bookstore.utils.BaseResponseValidations.validateStatusCodeIsExpected;

@Feature("GET Authors By ID")
@Tag("Authors")
public class GetAuthorsByIdApiTest extends AuthorsBaseTest {

    @Test(description = "Verify retrieval of an author by ID")
    @Description("Verify that GET /Authors/{id} returns HTTP 200-OK with the correct data")
    public void getAuthorByIdShouldReturnAuthorDetails() {
        Response response = authorsClient.getAuthorById(1); // authorId = 1

        validateStatusCodeIsExpected(response, STATUS_CODE_OK);
        validateHeadersContentTypeIsExpected(response, CONTENT_TYPE_JSON);
        validateAuthorSchema(response);

        // Validate Author details in response match the expected details for the author with ID 1.
        AuthorResponseDto expectedAuthorDto = buildExpectedAuthorResponseDto();
        AuthorResponseDto actualAuthorDto = response.as(AuthorResponseDto.class);
        validateAuthorMatchesExpectedDetails(expectedAuthorDto, actualAuthorDto);
    }

    @Test(
            dataProvider = "invalidAuthorIds",
            dataProviderClass = AuthorDataProviders.class,
            description = "Verify retrieval of an author by invalid ID"
    )
    @Description("Verify that GET /Authors/{id} returns 404 NOT FOUND when an invalid author ID is provided (9999, 0, -1)")
    public void getAuthorByInvalidIdShouldReturnNotFound(int authorId, String scenario) {
        Response response = authorsClient.getAuthorById(authorId);

        validateStatusCodeIsExpected(response, STATUS_CODE_NOT_FOUND);
    }
}
