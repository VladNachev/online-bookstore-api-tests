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
}
