package bookstore.utils;

import bookstore.dto.BookRequestDto;
import bookstore.dto.BookResponseDto;
import org.testng.asserts.SoftAssert;


public class BooksResponseValidations extends BaseResponseValidations {

    public static void validateCreatedBookMatchesRequestDetails(BookRequestDto requestDto, BookResponseDto responseDto) {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(responseDto.getTitle(),
                requestDto.getTitle(),
                "Title does not match");

        softAssert.assertEquals(responseDto.getDescription(),
                requestDto.getDescription(),
                "Description does not match");

        softAssert.assertEquals(responseDto.getPageCount(),
                requestDto.getPageCount(),
                "Page count does not match");

        softAssert.assertEquals(responseDto.getExcerpt(),
                requestDto.getExcerpt(),
                "Excerpt does not match");

        softAssert.assertTrue(responseDto.getPublishDate().startsWith(
                requestDto.getPublishDate()),
                "Publish date does not match");

        softAssert.assertAll();
    }

    public static void validateBookMatchesExpectedDetails(BookResponseDto expectedDto, BookResponseDto actualDto) {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actualDto.getId(),
                expectedDto.getId(),
                "Book ID does not match");

        softAssert.assertEquals(actualDto.getTitle(),
                expectedDto.getTitle(),
                "Title does not match");

        softAssert.assertEquals(actualDto.getDescription(),
                expectedDto.getDescription(),
                "Description does not match");

        softAssert.assertEquals(actualDto.getPageCount(),
                expectedDto.getPageCount(),
                "Page count does not match");

        softAssert.assertEquals(actualDto.getExcerpt(),
                expectedDto.getExcerpt(),
                "Excerpt does not match");

        softAssert.assertNotNull(actualDto.getPublishDate(),
                "Publish date should not be null");

        softAssert.assertAll();
    }

}
