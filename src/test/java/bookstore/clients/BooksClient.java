package bookstore.clients;

import bookstore.dto.BookRequestDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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

    public Response addNewBook(BookRequestDto requestDto) {
        Response response = RestAssured.given()
                .log().all()
                .baseUri(BASE_URL)
                .basePath(BOOKS)
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when()
                .post()
                .then()
                .log().all()
                .extract()
                .response();

        return response;

    }

//    public Response getBookById(int id) {
//        return RestAssured.given()
//                .log().all()
//                .baseUri(BASE_URL)
//                .basePath(BOOK_BY_ID)
//                .pathParam("id", id)
//                .when()
//                .get()
//                .then()
//                .log().all()
//                .extract()
//                .response();
//
//        return response;
//    }
}
