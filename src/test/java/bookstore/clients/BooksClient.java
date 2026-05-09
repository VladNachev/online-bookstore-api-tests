package bookstore.clients;

import bookstore.dto.BookRequestDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static bookstore.config.BookStoreConfig.*;

public class BooksClient extends BaseClient {

    public Response getBooks() {
        return RestAssured.given()
                .spec(getRequestSpecification())
                .basePath(BOOKS)
                .when()
                .get()
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response getBookById(int id) {
        return RestAssured.given()
                .spec(getRequestSpecification())
                .basePath(BOOK_BY_ID)
                .pathParam("id", id)
                .when()
                .get()
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response addNewBook(BookRequestDto requestDto) {
        return RestAssured.given()
                .spec(getRequestSpecification())
                .basePath(BOOKS)
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when()
                .post()
                .then()
                .log().all()
                .extract()
                .response();
    }

    /* Method overloading is used here because some test scenarios require
       sending the request body as a DTO (positive test cases),
       while others require sending raw JSON payloads
       for negative validations such as invalid data types. */

    public Response addNewBook(String requestBody) {
        return RestAssured.given()
                .spec(getRequestSpecification())
                .basePath(BOOKS)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post()
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response updateBook(BookRequestDto requestDto, int id) {
        return RestAssured.given()
                .spec(getRequestSpecification())
                .basePath(BOOK_BY_ID)
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when()
                .put()
                .then()
                .log().all()
                .extract()
                .response();
    }

    /* Method overloading is used here because some test scenarios require
   sending the request body as a DTO (positive test cases),
   while others require sending raw JSON payloads
   for negative validations such as invalid data types. */
    public Response updateBook(String requestDto, int id) {
        return RestAssured.given()
                .spec(getRequestSpecification())
                .basePath(BOOK_BY_ID)
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when()
                .put()
                .then()
                .log().all()
                .extract()
                .response();
    }

}
