package bookstore.tests.books;

import bookstore.dto.BookResponseDto;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static bookstore.testdata.BookTestDataFactory.buildExpectedBookByIdResponseDto;
import static bookstore.utils.BooksResponseValidations.*;

@Feature("GET Books")
@Tag("Books")
public class GetBooksApiTest extends BooksBaseTest {

    private Response response;

    @BeforeClass
    public void getBooks() {
        response = booksClient.getBooks();
    }

    @Test(description = "Verify retrieval of all books")
    @Description("Verify that GET /Books returns HTTP 200 with a non-empty list of books, and that the response refers to the expected JSON schema.")
    public void getBooksShouldReturnListOfBooks() {
        // General response validations
        validateStatusCodeIsExpected(response, 200);
        validateHeadersContentTypeIsExpected(response, "application/json");
        validateBooksListSchema(response);

        // Validate that the list of books is not empty and contains expected collection data
        validateListIsNotEmpty(response);
        validateBooksCountIsExpected(response, 200);
    }

    @Test(description = "Verify books list contains expected book")
    @Description("Verify that GET /Books returns a list containing the expected book details for a known book ID.")
    public void getBooksShouldContainExpectedBook() {
        BookResponseDto expectedDto = buildExpectedBookByIdResponseDto();

        validateBookWithIdExists(response, expectedDto.getId());

        BookResponseDto actualDto = getBookByIdFromBooksList(response, expectedDto.getId());
        validateBookMatchesExpectedDetails(expectedDto, actualDto);
    }
}
