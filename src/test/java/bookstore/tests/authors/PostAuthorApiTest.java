package bookstore.tests.authors;

import bookstore.dto.AuthorRequestDto;
import bookstore.dto.AuthorResponseDto;
import bookstore.testdata.AuthorDataProviders;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static bookstore.testdata.AuthorInvalidPayloads.invalidDataTypesPayload;
import static bookstore.testdata.AuthorTestDataFactory.buildAuthorRequestDto;
import static bookstore.utils.AuthorsResponseValidations.validateCreatedAuthorMatchesRequestDetails;
import static bookstore.utils.BaseResponseValidations.validateHeadersContentTypeIsExpected;
import static bookstore.utils.BaseResponseValidations.validateStatusCodeIsExpected;

@Feature("POST Author")
@Tag("Authors")
public class PostAuthorApiTest extends AuthorsBaseTest {

    @Test(description = "Verify successful author creation")
    @Description("Verify that POST /Authors successfully creates a new author and returns HTTP 200")
    public void addNewAuthorShouldSucceedAndReturnCreatedAuthorDetails() {
        AuthorRequestDto requestDto = buildAuthorRequestDto();
        Response response = authorsClient.addNewAuthor(requestDto);

        validateStatusCodeIsExpected(response, 200);
        validateHeadersContentTypeIsExpected(response, "application/json");

        AuthorResponseDto responseDto = response.as(AuthorResponseDto.class);
        validateCreatedAuthorMatchesRequestDetails(requestDto, responseDto);
    }

    @Test(
            dataProvider = "validEdgeCaseAuthorPayloads",
            dataProviderClass = AuthorDataProviders.class,
            description = "Verify author creation with valid edge case payloads"
    )
    @Description("Verify that POST /Authors accepts valid edge case payloads with nullFirstName, nullLastName, zeroBookId.")
    public void addNewAuthorWithValidEdgeCasePayloadShouldSucceed(AuthorRequestDto requestDto, String scenario) {
        Response response = authorsClient.addNewAuthor(requestDto);

        validateStatusCodeIsExpected(response, 200);
        validateHeadersContentTypeIsExpected(response, "application/json");

        AuthorResponseDto responseDto = response.as(AuthorResponseDto.class);
        validateCreatedAuthorMatchesRequestDetails(requestDto, responseDto);
    }

    @Test(description = "Verify that POST /Authors returns Bad Request for invalid data types")
    @Description("Verify that POST /Authors returns HTTP 400 Bad Request when invalid data types are provided.")
    public void addNewAuthorWithInvalidPayloadShouldReturnBadRequest() {
        Response response = authorsClient.addNewAuthor(invalidDataTypesPayload());

        validateStatusCodeIsExpected(response, 400);
    }

}
