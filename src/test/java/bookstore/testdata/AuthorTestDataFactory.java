package bookstore.testdata;

import bookstore.dto.AuthorRequestDto;
import bookstore.dto.AuthorResponseDto;

public class AuthorTestDataFactory {

    public static AuthorRequestDto buildAuthorRequestDto() {
        String uniqueValue = String.valueOf(System.currentTimeMillis());

        AuthorRequestDto requestDto = new AuthorRequestDto();

        requestDto.setIdBook(1);
        requestDto.setFirstName("Test First Name " + uniqueValue);
        requestDto.setLastName("Test Last Name " + uniqueValue);

        return requestDto;
    }

    public static AuthorResponseDto buildExpectedAuthorResponseDto() {

        /* Hardcoded expected response for author with ID 1 based on known test data in the fakeAPI.
           Since this is a fake API and data is not actually persisted, we assume that author with ID 1
           has consistently these specific details for testing purposes and they will remain unchanged. */

        AuthorResponseDto responseDto = new AuthorResponseDto();

        responseDto.setId(1);
        responseDto.setIdBook(1);
        responseDto.setFirstName("First Name 1");
        responseDto.setLastName("Last Name 1");

        return responseDto;
    }
}
