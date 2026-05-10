package bookstore.tests.books;

import bookstore.clients.BooksClient;


public class BooksBaseTest {

    // Status codes constants
    protected static final int STATUS_CODE_OK = 200;
    protected static final int STATUS_CODE_BAD_REQUEST = 400;
    protected static final int STATUS_CODE_NOT_FOUND = 404;

    // Content type constants
    protected static final String CONTENT_TYPE_JSON = "application/json";

    protected BooksClient booksClient = new BooksClient();
}