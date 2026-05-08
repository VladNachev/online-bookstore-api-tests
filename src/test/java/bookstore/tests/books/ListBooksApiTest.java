package bookstore.tests.books;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static bookstore.utils.BooksResponseValidations.*;

@Feature("Books API Tests")
@Tag("Books")
public class ListBooksApiTest extends BooksBaseTest {

    @Test(description = "Verify retrieval of all books")
    @Description("Verify that GET /Books returns HTTP 200 and a non-empty list of books")
    public void listBooksShouldReturnListOfBooks() {
            Response response = booksClient.getBooks();

            validateStatusCodeIsExpected(response, 200);
            validateHeadersContentTypeIsExpected(response, "application/json");
            validateListIsNotEmpty(response);
        }
}
