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

}
