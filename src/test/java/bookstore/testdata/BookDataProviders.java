package bookstore.testdata;

import bookstore.dto.BookRequestDto;
import org.testng.annotations.DataProvider;

import static bookstore.testdata.BookInvalidPayloads.invalidDataTypesPayload;
import static bookstore.testdata.BookInvalidPayloads.invalidPublishDateFormatPayload;
import static bookstore.testdata.BookInvalidPayloads.malformedJsonPayload;
import static bookstore.testdata.BookTestDataFactory.buildBookRequestDto;

public class BookDataProviders {
    private static final String SPECIAL_CHARACTERS_BOOK_ATTRIBUTES = "!@#$%^&*()_+";

    @DataProvider(name = "validEdgeCaseBookPayloads")
    public static Object[][] validEdgeCaseBookPayloads() {
        BookRequestDto nullTitle = buildBookRequestDto();
        nullTitle.setTitle(null);

        BookRequestDto nullDescription = buildBookRequestDto();
        nullDescription.setDescription(null);

        BookRequestDto nullExcerpt = buildBookRequestDto();
        nullExcerpt.setExcerpt(null);

        BookRequestDto zeroPageCount = buildBookRequestDto();
        zeroPageCount.setPageCount(0);

        BookRequestDto blankTitle = buildBookRequestDto();
        blankTitle.setTitle("");

        BookRequestDto blankDescription = buildBookRequestDto();
        blankDescription.setDescription("");

        BookRequestDto blankExcerpt = buildBookRequestDto();
        blankExcerpt.setExcerpt("");

        BookRequestDto specialCharactersInBookAttributes = buildBookRequestDto();
        specialCharactersInBookAttributes.setTitle(SPECIAL_CHARACTERS_BOOK_ATTRIBUTES);
        specialCharactersInBookAttributes.setDescription(SPECIAL_CHARACTERS_BOOK_ATTRIBUTES);
        specialCharactersInBookAttributes.setExcerpt(SPECIAL_CHARACTERS_BOOK_ATTRIBUTES);

        return new Object[][]{
                {nullTitle, "null title"},
                {nullDescription, "null description"},
                {nullExcerpt, "null excerpt"},
                {zeroPageCount, "zero page count"},
                {blankTitle, "blank title"},
                {blankDescription, "blank description"},
                {blankExcerpt, "blank excerpt"},
                {specialCharactersInBookAttributes, "special characters in book attributes"}
        };
    }

    @DataProvider(name = "invalidBookPayloads")
    public static Object[][] invalidBookPayloads() {
        return new Object[][]{
                {invalidDataTypesPayload(), "invalid data types"},
                {invalidPublishDateFormatPayload(), "invalid publish date format"},
                {malformedJsonPayload(), "malformed JSON"}
        };
    }

    @DataProvider(name = "invalidBookIds")
    public static Object[][] invalidBookIds() {
        return new Object[][]{
                {9999, "non-existent book ID"},
                {0, "book ID = 0"},
                {-1, "negative book ID"}
        };
    }
}
