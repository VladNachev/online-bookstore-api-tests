package bookstore.testdata;

import bookstore.dto.BookRequestDto;
import bookstore.dto.BookResponseDto;

import java.time.LocalDate;

public class BookTestDataFactory {
    public static BookRequestDto buildBookRequestDto() {
        String uniqueValue = String.valueOf(System.currentTimeMillis());

        BookRequestDto requestDto = new BookRequestDto();

        requestDto.setTitle("Test Book " + uniqueValue);
        requestDto.setDescription("Test Description " + uniqueValue);
        requestDto.setPageCount(100);
        requestDto.setExcerpt("Sample excerpt text " + uniqueValue);
        requestDto.setPublishDate(LocalDate.now().toString());

        return requestDto;
    }

    public static BookResponseDto buildExpectedBookByIdResponseDto() {

        /* Hardcoded expected response for book with ID 1 based on known test data in the system.
           Since this a fake API and data is not actually persisted, we assume that book with ID 1
           has consistently these specific details for testing purposes and they will remain unchanged */

        BookResponseDto responseDto = new BookResponseDto();

        responseDto.setId(1);
        responseDto.setTitle("Book 1");
        responseDto.setDescription("Lorem lorem lorem. Lorem lorem lorem. Lorem lorem lorem.\n");
        responseDto.setPageCount(100);
        responseDto.setExcerpt("""
                Lorem lorem lorem. Lorem lorem lorem. Lorem lorem lorem.
                Lorem lorem lorem. Lorem lorem lorem. Lorem lorem lorem.
                Lorem lorem lorem. Lorem lorem lorem. Lorem lorem lorem.
                Lorem lorem lorem. Lorem lorem lorem. Lorem lorem lorem.
                Lorem lorem lorem. Lorem lorem lorem. Lorem lorem lorem.
                """);

        return responseDto;
    }
}
