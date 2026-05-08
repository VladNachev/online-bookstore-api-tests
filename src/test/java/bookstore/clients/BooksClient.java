package bookstore.clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static bookstore.config.BookStoreConfig.*;

public class BooksClient {

    public Response getBooks() {
        Response response = RestAssured.given()
                .log().all()
                .baseUri(BASE_URL)
                .basePath(BOOKS)
                .when()
                .get()
                .then()
                .log().all()
                .extract()
                .response();
        return response;
    }

    public Response getBookById(int id) {
        return RestAssured.given()
                .log().all()
                .baseUri(BASE_URL)
                .basePath(BOOK_BY_ID)
                .pathParam("id", id)
                .when()
                .get()
                .then()
                .log().all()
                .extract()
                .response();
    }
}
