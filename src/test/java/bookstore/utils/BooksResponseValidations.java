package bookstore.utils;

import bookstore.dto.BookRequestDto;
import bookstore.dto.BookResponseDto;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.testng.Assert.*;

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

}
