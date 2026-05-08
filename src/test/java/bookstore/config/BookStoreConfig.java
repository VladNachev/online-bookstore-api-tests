package bookstore.config;

public class BookStoreConfig {

    // URLs
    public static final String BASE_URL = "https://fakerestapi.azurewebsites.net";

    // API versions
    public static final String API_VERSION = "/api/v1";

    // Endpoints
    public static final String BOOKS = API_VERSION + "/Books";
    public static final String BOOK_BY_ID = API_VERSION + "/Books/{id}";

    // Verbs
    public static final String POST = "POST";
    public static final String GET = "GET";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";

}
