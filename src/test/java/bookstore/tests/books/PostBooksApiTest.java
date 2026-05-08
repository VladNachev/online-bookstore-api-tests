package bookstore.tests.books;

import bookstore.dto.BookRequestDto;
import bookstore.dto.BookResponseDto;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static bookstore.testdata.BookTestDataFactory.buildBookRequestDto;
import static bookstore.utils.BaseResponseValidations.validateStatusCodeIsExpected;
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

        validateStatusCodeIsExpected(response, 200);

        BookResponseDto responseDto = response.as(BookResponseDto.class);
        validateCreatedBookMatchesRequestDetails(requestDto, responseDto);
    }
}
