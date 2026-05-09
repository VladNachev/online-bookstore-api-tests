package bookstore.clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static bookstore.config.BookStoreConfig.BASE_URL;

public abstract class BaseClient {

    protected RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .log(LogDetail.ALL)
                .build();
    }

    protected Response getRequest(String endpoint) {
        return RestAssured.given()
                .spec(getRequestSpecification())
                .basePath(endpoint)
                .when()
                .get()
                .then()
                .log().all()
                .extract()
                .response();
    }

    protected Response getRequest(String endpoint, String pathParamName, Object pathParamValue) {
        return RestAssured.given()
                .spec(getRequestSpecification())
                .basePath(endpoint)
                .pathParam(pathParamName, pathParamValue)
                .when()
                .get()
                .then()
                .log().all()
                .extract()
                .response();
    }

    protected Response postRequest(String endpoint, Object body) {
        return RestAssured.given()
                .spec(getRequestSpecification())
                .basePath(endpoint)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post()
                .then()
                .log().all()
                .extract()
                .response();
    }

    protected Response putRequest(String endpoint, String pathParamName, Object pathParamValue, Object body) {
        return RestAssured.given()
                .spec(getRequestSpecification())
                .basePath(endpoint)
                .pathParam(pathParamName, pathParamValue)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put()
                .then()
                .log().all()
                .extract()
                .response();
    }

    protected Response deleteRequest(String endpoint, String pathParamName, Object pathParamValue) {
        return RestAssured.given()
                .spec(getRequestSpecification())
                .basePath(endpoint)
                .pathParam(pathParamName, pathParamValue)
                .when()
                .delete()
                .then()
                .log().all()
                .extract()
                .response();
    }
}
