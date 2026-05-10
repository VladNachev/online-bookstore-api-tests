package bookstore.tests.books;

import bookstore.dto.BookResponseDto;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import bookstore.testdata.BookDataProviders;

import static bookstore.utils.BaseResponseValidations.*;
import static bookstore.utils.BooksResponseValidations.validateBookSchema;

@Feature("GET Book By ID")
@Tag("Books")
public class GetBooksByIdApiTest extends BooksBaseTest {

    @Test(description = "Verify retrieval of a book by ID")
    @Description("Verify that GET /Books/{id} returns HTTP 200 with the requested book ID and validate stable fields")
    public void getBookByIdShouldReturnBookWithExpectedIdAndPopulatedFields() {
        int bookId = 1;

        Response response = booksClient.getBookById(bookId);

        validateStatusCodeIsExpected(response, 200);
        validateHeadersContentTypeIsExpected(response, "application/json");
        validateBookSchema(response);

        BookResponseDto actualDto = response.as(BookResponseDto.class);

        // fakeRestAPI returns dynamic content on each run, so validate stable fields instead of exact text values.
        validateValueIsExpected(actualDto.getId(), bookId, "Book ID");
        validateValueIsNotNull(actualDto.getTitle(), "Book title");
        validateValueIsNotNull(actualDto.getDescription(), "Book description");
        validateValueIsNotNull(actualDto.getExcerpt(), "Book excerpt");
        validateValueIsNotNull(actualDto.getPublishDate(), "Publish date");
    }

    @Test(
            dataProvider = "invalidBookIds",
            dataProviderClass = BookDataProviders.class,
            description = "Verify retrieval of a book by invalid ID"
    )
    @Description("Verify that GET /Books/{id} returns HTTP 404 when an invalid book ID (9999, 0, -1) is provided")
    public void getBookByInvalidIdShouldReturnNotFound(int bookId, String scenario) {
        Response response = booksClient.getBookById(bookId);

        validateStatusCodeIsExpected(response, 404);
    }
}
