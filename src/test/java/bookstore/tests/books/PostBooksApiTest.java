package bookstore.tests.books;

import bookstore.dto.BookRequestDto;
import bookstore.dto.BookResponseDto;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import bookstore.testdata.BookDataProviders;

import static bookstore.testdata.BookTestDataFactory.buildBookRequestDto;
import static bookstore.utils.BaseResponseValidations.*;
import static bookstore.utils.BooksResponseValidations.validateCreatedBookMatchesRequestDetails;

@Feature("POST Books")
@Tag("Books")
public class PostBooksApiTest extends BooksBaseTest {

    @Test(description = "Verify successful book creation")
    @Description("Verify that POST /Books successfully creates a new book and returns HTTP 200 with the created book's details in the response body.")
    public void addNewBookShouldSucceedAndReturnCreatedBookDetails() {
        BookRequestDto requestDto = buildBookRequestDto();

        Response response = booksClient.addNewBook(requestDto);

        // General response validations
        validateStatusCodeIsExpected(response, 200);
        validateHeadersContentTypeIsExpected(response, "application/json");
        validateBookSchema(response);

        // Validate book details in response match the request
        BookResponseDto responseDto = response.as(BookResponseDto.class);
        validateCreatedBookMatchesRequestDetails(requestDto, responseDto);
    }

    @Test(
            dataProvider = "validEdgeCaseBookPayloads",
            dataProviderClass = BookDataProviders.class,
            description = "Verify book creation with valid edge case payloads"
    )
    @Description("Verify that POST /Books accepts valid edge case payloads and returns the created book details.")
    public void addNewBookWithValidEdgeCasePayloadShouldSucceed(BookRequestDto requestDto, String scenario) {
        Response response = booksClient.addNewBook(requestDto);

        validateStatusCodeIsExpected(response, 200);
        validateHeadersContentTypeIsExpected(response, "application/json");
        validateBookSchema(response);

        BookResponseDto responseDto = response.as(BookResponseDto.class);
        validateCreatedBookMatchesRequestDetails(requestDto, responseDto);
    }

    @Test(
            dataProvider = "invalidBookPayloads",
            dataProviderClass = BookDataProviders.class,
            description = "Verify that POST /Books returns Bad Request for invalid payloads"
    )
    @Description("Verify that POST /Books returns HTTP 400 Bad Request when invalid payloads are provided.")
    public void addNewBookWithInvalidPayloadShouldReturnBadRequest(String requestBody, String scenario) {
        Response response = booksClient.addNewBook(requestBody);

        validateStatusCodeIsExpected(response, 400);
    }
}
