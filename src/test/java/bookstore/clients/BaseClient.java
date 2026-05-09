package bookstore.clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import static bookstore.config.BookStoreConfig.BASE_URL;

public abstract class BaseClient {

    protected RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .log(LogDetail.ALL)
                .build();
    }
}
