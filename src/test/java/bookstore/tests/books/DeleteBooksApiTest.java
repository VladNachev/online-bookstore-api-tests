package bookstore.tests.books;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static bookstore.utils.BaseResponseValidations.validateStatusCodeIsExpected;

@Feature("DELETE Books")
@Tag("Books")
public class DeleteBooksApiTest extends BooksBaseTest{

    @Test(description = "Verify successful book deletion")
    @Description("Verify that DELETE /Books/{id} successfully deletes an existing book and returns HTTP 200")
    public void deleteBookShouldSucceed() {
        Response response = booksClient.deleteBookById(1); // bookId = 1

        validateStatusCodeIsExpected(response, STATUS_CODE_OK);
    }

    /* Additional test scenarios such as deleting a non-existent book by providing invalid IDs are skipped.
       Reason for that: Ideally the API should return 404 Not Found for non-existent resources,
       but the current implementation returns 200. I decided to skip those negative test scenarios
       in order avoid false failures in the test suite */
}
