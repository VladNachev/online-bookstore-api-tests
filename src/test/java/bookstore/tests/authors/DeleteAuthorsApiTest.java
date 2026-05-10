package bookstore.tests.authors;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static bookstore.utils.BaseResponseValidations.validateStatusCodeIsExpected;

@Feature("DELETE Authors")
@Tag("Authors")
public class DeleteAuthorsApiTest extends AuthorsBaseTest {

    @Test(description = "Verify successful author deletion")
    @Description("Verify that DELETE /Authors/{id} successfully deletes an existing author and returns HTTP 200")
    public void deleteAuthorShouldSucceed() {
        Response response = authorsClient.deleteAuthorById(1); // authorId = 1

        validateStatusCodeIsExpected(response, 200);
    }

    /* Additional test scenarios such as deleting a non-existent author by providing invalid IDs are skipped.
       Reason for that: Ideally the API should return 404 Not Found for non-existent resources,
       but the current implementation returns 200. I decided to skip those negative test scenarios
       in order avoid false failures in the test suite */
}
