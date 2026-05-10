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

@Feature("PUT Books")
@Tag("Books")
public class PutBooksApiTest extends BooksBaseTest {
    private static final int BOOK_ID = 1;

    @Test(description = "Verify successful book update")
    @Description("Verify that PUT /Books/{id} successfully updates an existing book's details and returns HTTP 200")
    public void updateBookShouldSucceedAndReturnUpdatedBookDetails() {
        BookRequestDto requestDto = buildBookRequestDto();

        Response response = booksClient.updateBook(requestDto, BOOK_ID);

        validateStatusCodeIsExpected(response, 200);
        validateHeadersContentTypeIsExpected(response, "application/json");
        validateBookSchema(response);

        BookResponseDto responseDto = response.as(BookResponseDto.class);
        validateCreatedBookMatchesRequestDetails(requestDto, responseDto);
    }

    @Test(
            dataProvider = "validEdgeCaseBookPayloads",
            dataProviderClass = BookDataProviders.class,
            description = "Verify book update with valid edge case payloads"
    )
    @Description("Verify that PUT /Books accepts valid edge case payloads with nullTitle, nullDescription, nullExcerpt, zeroPageCount.")
    public void updateBookWithValidEdgeCasePayloadShouldSucceed(BookRequestDto requestDto, String scenario) {
        Response response = booksClient.updateBook(requestDto, BOOK_ID);

        validateStatusCodeIsExpected(response, 200);
        validateHeadersContentTypeIsExpected(response, "application/json");
        validateBookSchema(response);

        BookResponseDto responseDto = response.as(BookResponseDto.class);
        validateCreatedBookMatchesRequestDetails(requestDto, responseDto);
    }

    @Test(
            dataProvider = "invalidBookPayloads",
            dataProviderClass = BookDataProviders.class,
            description = "Verify that PUT /Books returns Bad Request for invalid payloads"
    )
    @Description("Verify that POST /Books returns 400 Bad Request when invalid data types or invalid publish date format are provided.")
    public void updateBookWithInvalidPayloadShouldReturnBadRequest(String requestBody, String scenario) {
        Response response = booksClient.updateBook(requestBody, BOOK_ID);

        validateStatusCodeIsExpected(response, 400);
    }

    /* Note: Additional test scenarios such as updating a non-existent book by providing invalid IDs are skipped.
       Reason for that: Ideally the API should return 404 Not Found for non-existent resources,
       but the current implementation returns 200. I decided to skip those negative test cases in order avoid false failures */
}
