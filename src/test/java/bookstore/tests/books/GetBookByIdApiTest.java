package bookstore.tests.books;

import bookstore.dto.BookResponseDto;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import bookstore.testdata.BookDataProviders;

import static bookstore.testdata.BookTestDataFactory.buildExpectedBookByIdResponseDto;
import static bookstore.utils.BaseResponseValidations.*;
import static bookstore.utils.BooksResponseValidations.validateBookMatchesExpectedDetails;

@Feature("GET Book By ID")
@Tag("Books")
public class GetBookByIdApiTest extends BooksBaseTest {

    @Test(description = "Verify retrieval of a book by ID")
    @Description("Verify that GET /Books/{id} returns HTTP 200 with the correct book details when a valid book ID is provided")
    public void getBookByIdShouldReturnCorrectBookDetails() {
        Response response = booksClient.getBookById(1); // bookId = 1

        // General response validations
        validateStatusCodeIsExpected(response, 200);
        validateHeadersContentTypeIsExpected(response, "application/json");
        validateBookSchema(response);

        // Validate the expected book details match the actual book details
        BookResponseDto expectedDto = buildExpectedBookByIdResponseDto();
        BookResponseDto actualDto = response.as(BookResponseDto.class);
        validateBookMatchesExpectedDetails(expectedDto, actualDto);
    }

    @Test(
            dataProvider = "invalidBookIds",
            dataProviderClass = BookDataProviders.class,
            description = "Verify retrieval of a book by invalid ID"
    )
    @Description("Verify that GET /Books/{id} returns HTTP 404 when an invalid book ID is provided")
    public void getBookByInvalidIdShouldReturnNotFound(int bookId, String scenarioDescription) {
        Response response = booksClient.getBookById(bookId);

        validateStatusCodeIsExpected(response, 404);
    }
}
