package bookstore.utils;

import io.restassured.response.Response;
import java.util.List;
import static org.testng.Assert.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BaseResponseValidations {

    public static void validateHeadersContentTypeIsExpected(Response response, String contentType) {
        assertTrue(response.getContentType().contains(contentType),
                String.format("Expected content type to contain '%s' but got '%s'", contentType, response.getContentType()));

    }

    public static void validateListIsNotEmpty(Response response) {

        List<Object> items = response.jsonPath().getList("$");

        assertNotEquals(items, null, "Response list should not be null");
        assertFalse(items.isEmpty(), "Response list should not be empty");
    }

    public static void validateListIsEmpty(Response response) {
        List<Object> items = response.jsonPath().getList("$");

        assertNotEquals(items, null, "Response list should not be null");
        assertTrue(items.isEmpty(), "Response list should be empty");
    }

    public static void validateStatusCodeIsExpected(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();

        assertEquals(actualStatusCode, expectedStatusCode,
                String.format("Expected status code %d but got %d", expectedStatusCode, actualStatusCode));
    }

    public static void validateValueIsNotNull(Object actualValue, String fieldName) {
        assertNotNull(actualValue,
                String.format("%s should not be null", fieldName));
    }

    public static void validateValueIsExpected(Object actualValue, Object expectedValue, String fieldName) {
        assertEquals(actualValue,
                expectedValue,
                String.format("%s does not match", fieldName));
    }

    public static void validateSchema(Response response, String schemaPath) {
        response.then()
                .body(matchesJsonSchemaInClasspath(schemaPath));
    }
}
