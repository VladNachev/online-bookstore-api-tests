package bookstore.tests.books;


import bookstore.dto.BookRequestDto;
import bookstore.dto.BookResponseDto;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import bookstore.testdata.BookDataProviders;

import static bookstore.testdata.BookInvalidPayloads.invalidDataTypesPayload;
import static bookstore.testdata.BookInvalidPayloads.invalidPublishDateFormatPayload;
import static bookstore.testdata.BookTestDataFactory.buildBookRequestDto;
import static bookstore.utils.BaseResponseValidations.*;
import static bookstore.utils.BooksResponseValidations.validateCreatedBookMatchesRequestDetails;

@Feature("PUT Books")
@Tag("Books")
public class PutBooksApiTest extends BooksBaseTest {
    private static final int BOOK_ID = 1;

    @DataProvider(name = "validEdgeCaseBookPayloads")
    public Object[][] validBookPayloads() {
        BookRequestDto nullTitle = buildBookRequestDto();
        nullTitle.setTitle(null);

        BookRequestDto nullDescription = buildBookRequestDto();
        nullDescription.setDescription(null);

        BookRequestDto nullExcerpt = buildBookRequestDto();
        nullExcerpt.setExcerpt(null);

        BookRequestDto zeroPageCount = buildBookRequestDto();
        zeroPageCount.setPageCount(0);

        return new Object[][]{
                {nullTitle, "null title"},
                {nullDescription, "null description"},
                {nullExcerpt, "null excerpt"},
                {zeroPageCount, "zero page count"}
        };
    }

    @DataProvider(name = "invalidBookPayloads")
    public Object[][] invalidBookPayloads() {
        return new Object[][]{
                {invalidDataTypesPayload(), "invalid data types"},
                {invalidPublishDateFormatPayload(), "invalid publish date format"}
        };
    }

    @Test(description = "Verify successful book update")
    @Description("Verify that PUT /Books/{id} successfully updates an existing book's details and returns HTTP 200")
    public void updateBookShouldSucceedAndReturnUpdatedBookDetails() {
        BookRequestDto requestDto = buildBookRequestDto();

        Response response = booksClient.updateBook(requestDto, BOOK_ID);

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
            description = "Verify book update with valid edge case payloads"
    )
    @Description("Verify that POST /Books accepts valid edge case payloads and returns the created book details.")
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
    @Description("Verify that POST /Books returns HTTP 400 Bad Request when invalid payloads are provided.")
    public void updateBookWithInvalidPayloadShouldReturnBadRequest(String requestBody, String scenario) {
        Response response = booksClient.updateBook(requestBody, BOOK_ID);

        validateStatusCodeIsExpected(response, 400);
    }

    


}
