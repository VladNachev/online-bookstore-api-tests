package bookstore.testdata;

import bookstore.dto.AuthorRequestDto;
import org.testng.annotations.DataProvider;

import static bookstore.testdata.AuthorTestDataFactory.buildAuthorRequestDto;

public class AuthorDataProviders {

    @DataProvider(name = "validEdgeCaseAuthorPayloads")
    public static Object[][] validEdgeCaseAuthorPayloads() {
        AuthorRequestDto nullFirstName = buildAuthorRequestDto();
        nullFirstName.setFirstName(null);

        AuthorRequestDto nullLastName = buildAuthorRequestDto();
        nullLastName.setLastName(null);

        AuthorRequestDto zeroBookId = buildAuthorRequestDto();
        zeroBookId.setIdBook(0);

        return new Object[][]{
                {nullFirstName, "null first name"},
                {nullLastName, "null last name"},
                {zeroBookId, "zero book ID"}
        };
    }

    @DataProvider(name = "invalidAuthorIds")
    public static Object[][] invalidAuthorIds() {
        return new Object[][]{
                {9999, "non-existent author ID"},
                {0, "author ID = 0"},
                {-1, "negative author ID"}
        };
    }

    @DataProvider(name = "invalidBookIdsForAuthors")
    public static Object[][] invalidBookIdsForAuthors() {
        return new Object[][]{
                {9999, "non-existent book ID"},
                {0, "book ID = 0"},
                {-1, "negative book ID"}
        };
    }
}
