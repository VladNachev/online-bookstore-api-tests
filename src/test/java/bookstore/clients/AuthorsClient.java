package bookstore.clients;

import bookstore.dto.AuthorRequestDto;
import io.restassured.response.Response;

import static bookstore.config.BookStoreConfig.*;

public class AuthorsClient extends BaseClient {

    public Response getAuthors() {
        return getRequest(AUTHORS_ENDPOINT);
    }

    public Response getAuthorById(int id) {
        return getRequest(AUTHOR_BY_ID_ENDPOINT, "id", id);
    }

    public Response getAuthorsByBookId(int bookId) {
        return getRequest(AUTHORS_BY_BOOK_ID_ENDPOINT, "idBook", bookId);
    }

    public Response addNewAuthor(AuthorRequestDto requestBody) {
        return postRequest(AUTHORS_ENDPOINT, requestBody);
    }

    public Response addNewAuthor(String requestBody) {
        return postRequest(AUTHORS_ENDPOINT, requestBody);
    }

    public Response updateAuthor(AuthorRequestDto requestBody, int id) {
        return putRequest(AUTHOR_BY_ID_ENDPOINT, "id", id, requestBody);
    }

    public Response updateAuthor(String requestBody, int id) {
        return putRequest(AUTHOR_BY_ID_ENDPOINT, "id", id, requestBody);
    }

    public Response deleteAuthorById(int id) {
        return deleteRequest(AUTHOR_BY_ID_ENDPOINT, "id", id);
    }
}
