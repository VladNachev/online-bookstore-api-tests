package bookstore.tests.books;

import bookstore.clients.BooksClient;
import org.testng.annotations.BeforeClass;


public class BooksBaseTest {

    protected BooksClient booksClient;
    @BeforeClass
    public void setup() {
        booksClient = new BooksClient();
    }
}
