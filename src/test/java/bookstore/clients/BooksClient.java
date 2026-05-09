package bookstore.clients;

import bookstore.dto.BookRequestDto;
import io.restassured.response.Response;

import static bookstore.config.BookStoreConfig.*;

public class BooksClient extends BaseClient {

    public Response getBooks() {
        return getRequest(BOOKS_ENDPOINT);
    }

    public Response getBookById(int id) {
        return getRequest(BOOK_BY_ID_ENDPOINT, "id", id);
    }

    public Response addNewBook(BookRequestDto requestDto) {
        return postRequest(BOOKS_ENDPOINT, requestDto);
    }

    public Response addNewBook(String requestBody) {
        return postRequest(BOOKS_ENDPOINT, requestBody);
    }

    public Response updateBook(BookRequestDto requestDto, int id) {
        return putRequest(BOOK_BY_ID_ENDPOINT, "id", id, requestDto);
    }

    public Response updateBook(String requestBody, int id) {
        return putRequest(BOOK_BY_ID_ENDPOINT, "id", id, requestBody);
    }

    public Response deleteBookById(int id) {
        return deleteRequest(BOOK_BY_ID_ENDPOINT, "id", id);
    }

}
