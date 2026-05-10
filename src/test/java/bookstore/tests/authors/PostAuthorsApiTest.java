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

@Feature("POST Authors")
@Tag("Authors")
public class PostAuthorsApiTest extends AuthorsBaseTest {

    @Test(description = "Verify successful author creation")
    @Description("Verify that POST /Authors successfully creates a new author and returns HTTP 200-OK")
    public void addNewAuthorShouldSucceedAndReturnCreatedAuthorDetails() {
        AuthorRequestDto requestDto = buildAuthorRequestDto();
        Response response = authorsClient.addNewAuthor(requestDto);

        validateStatusCodeIsExpected(response, STATUS_CODE_OK);
        validateHeadersContentTypeIsExpected(response, CONTENT_TYPE_JSON);
        validateAuthorSchema(response);

        AuthorResponseDto responseDto = response.as(AuthorResponseDto.class);
        validateCreatedAuthorMatchesRequestDetails(requestDto, responseDto);
    }

    @Test(
            dataProvider = "validEdgeCaseAuthorPayloads",
            dataProviderClass = AuthorDataProviders.class,
            description = "Verify author creation with valid edge case payloads"
    )
    @Description("Verify that POST /Authors accepts valid edge case payloads with null/blank names, " +
            "special characters in names, and zeroBookId.")
    public void addNewAuthorWithValidEdgeCasePayloadShouldSucceed(AuthorRequestDto requestDto, String scenario) {
        Response response = authorsClient.addNewAuthor(requestDto);

        validateStatusCodeIsExpected(response, STATUS_CODE_OK);
        validateHeadersContentTypeIsExpected(response, CONTENT_TYPE_JSON);
        validateAuthorSchema(response);

        AuthorResponseDto responseDto = response.as(AuthorResponseDto.class);
        validateCreatedAuthorMatchesRequestDetails(requestDto, responseDto);
    }

    @Test(
            dataProvider = "invalidAuthorPayloads",
            dataProviderClass = AuthorDataProviders.class,
            description = "Verify that POST /Authors returns Bad Request for invalid payloads"
    )
    @Description("Verify that POST /Authors returns 400 Bad Request when invalid data types or malformed JSON are provided.")
    public void addNewAuthorWithInvalidPayloadShouldReturnBadRequest(String requestBody, String scenario) {
        Response response = authorsClient.addNewAuthor(requestBody);

        validateStatusCodeIsExpected(response, STATUS_CODE_BAD_REQUEST);
    }

}
