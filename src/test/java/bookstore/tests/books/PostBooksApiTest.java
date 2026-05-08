package bookstore.tests.books;

import bookstore.dto.BookRequestDto;
import bookstore.dto.BookResponseDto;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static bookstore.testdata.BookTestDataFactory.buildBookRequestDto;
import static bookstore.utils.BaseResponseValidations.*;
import static bookstore.utils.BooksResponseValidations.validateCreatedBookMatchesRequestDetails;

@Feature("POST Books")
@Tag("Books")
public class PostBooksApiTest extends BooksBaseTest {

    private BookRequestDto requestDto;

    @BeforeMethod
    public void setupRequestDto() {
        // Build book request DTO with valid unique data for each test
        requestDto = buildBookRequestDto();
    }

    @Test(description = "Verify successful book creation")
    @Description("Verify that POST /Books successfully creates a new book and returns HTTP 200 with the created book's details in the response body.")
    public void addNewBookShouldSucceedAndReturnCreatedBookDetails() {

        Response response = booksClient.addNewBook(requestDto);

        // General response validations
        validateStatusCodeIsExpected(response, 200);
        validateHeadersContentTypeIsExpected(response, "application/json");
        validateBookSchema(response);

        // Validate book details in response match the request
        BookResponseDto responseDto = response.as(BookResponseDto.class);
        validateCreatedBookMatchesRequestDetails(requestDto, responseDto);
    }

    @Test(description = "Verify book creation with nullable text fields")
    @Description("Verify that POST /Books accepts nullable title, description, and excerpt fields according to the API schema.")
    public void addNewBookWithNullableTextFieldsShouldSucceed() {

        requestDto.setTitle(null);
        requestDto.setDescription(null);
        requestDto.setExcerpt(null);

        Response response = booksClient.addNewBook(requestDto);

        validateStatusCodeIsExpected(response, 200);

        // Validate book details in response match the request
        BookResponseDto responseDto = response.as(BookResponseDto.class);
        validateCreatedBookMatchesRequestDetails(requestDto, responseDto);
    }

    @Test(description = "Verify book creation with zero page count")
    @Description("Verify that POST /Books accepts a book request with pageCount set to 0.")
    public void addNewBookWithZeroPageCountShouldSucceed() {

        requestDto.setPageCount(0);

        Response response = booksClient.addNewBook(requestDto);

        validateStatusCodeIsExpected(response, 200);

        BookResponseDto responseDto = response.as(BookResponseDto.class);
        validateCreatedBookMatchesRequestDetails(requestDto, responseDto);
    }

    @Test(description = "Verify that POST /Books returns Bad Request for invalid publish date format")
    @Description("Verify that POST /Books returns HTTP 400 Bad Request when the publishDate field is provided in an invalid format.")
    public void addNewBookWithInvalidPublishDateFormatShouldReturnBadRequest() {

        requestDto.setPublishDate(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))); // Invalid format

        Response response = booksClient.addNewBook(requestDto);

        validateStatusCodeIsExpected(response, 400);
    }
}
