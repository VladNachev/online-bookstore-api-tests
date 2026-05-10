package bookstore.tests.authors;

import bookstore.clients.AuthorsClient;

public class AuthorsBaseTest {

    // Status codes constants
    protected static final int STATUS_CODE_OK = 200;
    protected static final int STATUS_CODE_BAD_REQUEST = 400;
    protected static final int STATUS_CODE_NOT_FOUND = 404;

    // Content type constants
    protected static final String CONTENT_TYPE_JSON = "application/json";

    protected AuthorsClient authorsClient = new AuthorsClient();
}
