package bookstore.tests.books;

import bookstore.dto.BookResponseDto;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static bookstore.utils.BaseResponseValidations.validateValueIsNotNull;
import static bookstore.utils.BooksResponseValidations.*;

@Feature("GET Books")
@Tag("Books")
public class GetBooksApiTest extends BooksBaseTest {

    @Test(description = "Verify retrieval of all books")
    @Description("Verify that GET /Books returns HTTP 200 with a non-empty list of books, and that the response refers to the expected JSON schema.")
    public void getBooksShouldReturnListOfBooks() {
        Response response = booksClient.getBooks();

        // General response validations
        validateStatusCodeIsExpected(response, 200);
        validateHeadersContentTypeIsExpected(response, "application/json");
        validateBooksListSchema(response);

        /* Validate that the books list is not empty and contains the expected number of records.
           In a real-world scenario, the expected count would typically be retrieved from the database
           or another trusted data source and use that value for validation instead of being hardcoded.
           For the purposes of this FakeAPI, the expected count is statically validated against 200. */
        validateListIsNotEmpty(response);
        validateBooksCountIsExpected(response, 200);
    }

}
