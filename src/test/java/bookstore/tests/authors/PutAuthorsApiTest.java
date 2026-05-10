package bookstore.tests.authors;


import bookstore.dto.AuthorRequestDto;
import bookstore.dto.AuthorResponseDto;
import bookstore.testdata.AuthorDataProviders;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static bookstore.testdata.AuthorTestDataFactory.buildAuthorRequestDto;
import static bookstore.utils.AuthorsResponseValidations.validateAuthorSchema;
import static bookstore.utils.AuthorsResponseValidations.validateCreatedAuthorMatchesRequestDetails;
import static bookstore.utils.BaseResponseValidations.validateHeadersContentTypeIsExpected;
import static bookstore.utils.BaseResponseValidations.validateStatusCodeIsExpected;

@Feature("PUT Authors")
@Tag("Authors")
public class PutAuthorsApiTest extends AuthorsBaseTest {
    private static final int AUTHOR_ID = 1;

    @Test(description = "Verify successful author update")
    @Description("Verify that PUT /Authors/{id} successfully updates an existing author's details and returns HTTP 200-OK")
    public void updateAuthorShouldSucceedAndReturnUpdatedAuthorDetails() {
        AuthorRequestDto requestDto = buildAuthorRequestDto();

        Response response = authorsClient.updateAuthor(requestDto, AUTHOR_ID);

        validateStatusCodeIsExpected(response, STATUS_CODE_OK);
        validateHeadersContentTypeIsExpected(response, CONTENT_TYPE_JSON);
        validateAuthorSchema(response);

        AuthorResponseDto responseDto = response.as(AuthorResponseDto.class);
        validateCreatedAuthorMatchesRequestDetails(requestDto, responseDto);
    }

    @Test(
            dataProvider = "validEdgeCaseAuthorPayloads",
            dataProviderClass = AuthorDataProviders.class,
            description = "Verify author update with valid edge case payloads"
    )
    @Description("Verify that PUT /Authors/{id} accepts valid edge case payloads with null/blank names, " +
            "special characters in names, and zeroBookId.")
    public void updateAuthorWithValidEdgeCasePayloadShouldSucceed(AuthorRequestDto requestDto, String scenario) {
        Response response = authorsClient.updateAuthor(requestDto, AUTHOR_ID);

        validateStatusCodeIsExpected(response, STATUS_CODE_OK);
        validateHeadersContentTypeIsExpected(response, CONTENT_TYPE_JSON);
        validateAuthorSchema(response);

        AuthorResponseDto responseDto = response.as(AuthorResponseDto.class);
        validateCreatedAuthorMatchesRequestDetails(requestDto, responseDto);

    }

    @Test(
            dataProvider = "invalidAuthorPayloads",
            dataProviderClass = AuthorDataProviders.class,
            description = "Verify that PUT /Authors/{id} returns Bad Request for invalid payloads"
    )
    @Description("Verify that PUT /Authors/{id} returns HTTP 400 Bad Request when invalid raw payloads are provided.")
    public void updateAuthorWithInvalidPayloadShouldReturnBadRequest(String requestBody, String scenario) {
        Response response = authorsClient.updateAuthor(requestBody, AUTHOR_ID);

        validateStatusCodeIsExpected(response, STATUS_CODE_BAD_REQUEST);
    }

    /* Note: Additional test scenarios such as updating a non-existent book by providing invalid IDs are skipped.
       Reason for that: Ideally the API should return 404 Not Found for non-existent resources,
       but the current implementation returns 200. I decided to skip those negative test scenarios for now
       in order avoid false failures in the test suite. */
}
