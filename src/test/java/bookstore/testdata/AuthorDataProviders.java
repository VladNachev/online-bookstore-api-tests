package bookstore.testdata;

import bookstore.dto.AuthorRequestDto;
import org.testng.annotations.DataProvider;

import static bookstore.testdata.AuthorInvalidPayloads.invalidDataTypesPayload;
import static bookstore.testdata.AuthorInvalidPayloads.malformedJsonPayload;
import static bookstore.testdata.AuthorTestDataFactory.buildAuthorRequestDto;

public class AuthorDataProviders {
    private static final String SPECIAL_CHARACTERS_AUTHOR_NAME = "!@#$%^&*()_+";

    @DataProvider(name = "validEdgeCaseAuthorPayloads")
    public static Object[][] validEdgeCaseAuthorPayloads() {
        AuthorRequestDto nullFirstName = buildAuthorRequestDto();
        nullFirstName.setFirstName(null);

        AuthorRequestDto nullLastName = buildAuthorRequestDto();
        nullLastName.setLastName(null);

        AuthorRequestDto zeroBookId = buildAuthorRequestDto();
        zeroBookId.setIdBook(0);

        AuthorRequestDto blankFirstName = buildAuthorRequestDto();
        blankFirstName.setFirstName("");

        AuthorRequestDto blankLastName = buildAuthorRequestDto();
        blankLastName.setLastName("");

        AuthorRequestDto specialCharactersInNames = buildAuthorRequestDto();
        specialCharactersInNames.setFirstName(SPECIAL_CHARACTERS_AUTHOR_NAME);
        specialCharactersInNames.setLastName(SPECIAL_CHARACTERS_AUTHOR_NAME);

        return new Object[][]{
                {nullFirstName, "null first name"},
                {nullLastName, "null last name"},
                {zeroBookId, "zero book ID"},
                {blankFirstName, "blank first name"},
                {blankLastName, "blank last name"},
                {specialCharactersInNames, "special characters in names"}
        };
    }

    @DataProvider(name = "invalidAuthorPayloads")
    public static Object[][] invalidAuthorPayloads() {
        return new Object[][]{
                {invalidDataTypesPayload(), "invalid data types"},
                {malformedJsonPayload(), "malformed JSON"}
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
