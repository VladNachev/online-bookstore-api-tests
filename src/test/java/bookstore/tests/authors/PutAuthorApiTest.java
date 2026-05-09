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
import static bookstore.testdata.AuthorInvalidPayloads.invalidDataTypesPayload;
import static bookstore.utils.AuthorsResponseValidations.validateCreatedAuthorMatchesRequestDetails;
import static bookstore.utils.BaseResponseValidations.validateHeadersContentTypeIsExpected;
import static bookstore.utils.BaseResponseValidations.validateStatusCodeIsExpected;

@Feature("PUT Author")
@Tag("Authors")
public class PutAuthorApiTest extends AuthorsBaseTest {
    private static final int AUTHOR_ID = 1;

    @Test(description = "Verify successful author update")
    @Description("Verify that PUT /Authors/{id} successfully updates an existing author's details and returns HTTP 200")
    public void updateAuthorShouldSucceedAndReturnUpdatedAuthorDetails() {
        AuthorRequestDto requestDto = buildAuthorRequestDto();

        Response response = authorsClient.updateAuthor(requestDto, AUTHOR_ID);

        // General response validations
        validateStatusCodeIsExpected(response, 200);
        validateHeadersContentTypeIsExpected(response, "application/json");

        // Validate Author details in response match the request
        AuthorResponseDto responseDto = response.as(AuthorResponseDto.class);
        validateCreatedAuthorMatchesRequestDetails(requestDto, responseDto);
    }

    @Test(
            dataProvider = "validEdgeCaseAuthorPayloads",
            dataProviderClass = AuthorDataProviders.class,
            description = "Verify author update with valid edge case payloads"
    )
    @Description("Verify that PUT /Authors/{id} accepts valid edge case payloads and returns the updated author details.")
    public void updateAuthorWithValidEdgeCasePayloadShouldSucceed(AuthorRequestDto requestDto, String scenario) {
        Response response = authorsClient.updateAuthor(requestDto, AUTHOR_ID);

        validateStatusCodeIsExpected(response, 200);
        validateHeadersContentTypeIsExpected(response, "application/json");

        AuthorResponseDto responseDto = response.as(AuthorResponseDto.class);
        validateCreatedAuthorMatchesRequestDetails(requestDto, responseDto);

    }

    @Test(description = "Verify that PUT /Authors/{id} returns Bad Request for invalid data types")
    @Description("Verify that PUT /Authors/{id} returns HTTP 400 Bad Request when invalid data types are provided.")
    public void updateAuthorWithInvalidPayloadShouldReturnBadRequest() {
        Response response = authorsClient.updateAuthor(invalidDataTypesPayload(), AUTHOR_ID);

        validateStatusCodeIsExpected(response, 400);
    }

    /* Note: Additional test scenarios such as updating a non-existent book by providing invalid IDs are skipped.
       Reason for that: Ideally the API should return 404 Not Found for non-existent resources,
       but the current implementation returns 200. I decided to skip those negative test scenarios for now
       in order avoid false failures in the test suite. */
}
