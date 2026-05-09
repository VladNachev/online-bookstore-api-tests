package bookstore.config;

public class BookStoreConfig {

    // URLs
    public static final String BASE_URL = "https://fakerestapi.azurewebsites.net";

    // API versions
    public static final String API_VERSION = "/api/v1";

    // / Books Endpoints
    public static final String BOOKS_ENDPOINT = API_VERSION + "/Books";
    public static final String BOOK_BY_ID_ENDPOINT = API_VERSION + "/Books/{id}";

    // Authors Endpoints
    public static final String AUTHORS_ENDPOINT = API_VERSION + "/Authors";
    public static final String AUTHOR_BY_ID_ENDPOINT = API_VERSION + "/Authors/{id}";
    public static final String AUTHORS_BY_BOOK_ID_ENDPOINT = API_VERSION + "/Authors/authors/books/{idBook}";



}
