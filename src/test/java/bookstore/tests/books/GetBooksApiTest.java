package bookstore.tests.books;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static bookstore.utils.BooksResponseValidations.*;

@Feature("GET Books")
@Tag("Books")
public class GetBooksApiTest extends BooksBaseTest {

    @Test(description = "Verify retrieval of all books")
    @Description("Verify that GET /Books returns HTTP 200 with a non-empty list of books, and that the response refers to the expected JSON schema.")
    public void GetBooksShouldReturnListOfBooks() {
            Response response = booksClient.getBooks();

            // General response validations
            validateStatusCodeIsExpected(response, 200);
            validateHeadersContentTypeIsExpected(response, "application/json");
            validateBooksListSchema(response);

            // Validate that the list of books is not empty
            validateListIsNotEmpty(response);
        }
}
