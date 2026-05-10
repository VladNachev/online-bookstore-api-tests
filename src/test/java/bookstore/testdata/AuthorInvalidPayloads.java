package bookstore.testdata;

public class AuthorInvalidPayloads {

    public static String invalidDataTypesPayload() {
        return """
                {
                  "idBook": "invalid id book",
                  "firstName": 123,
                  "lastName": 123
                }
                """;
    }

    public static String malformedJsonPayload() {
        return """
                {
                  "idBook": 1,
                  "firstName": "Malformed JSON author",
                  "lastName": "Missing closing brace"
                """;
    }
}
