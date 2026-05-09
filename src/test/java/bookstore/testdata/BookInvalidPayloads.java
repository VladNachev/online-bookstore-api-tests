package bookstore.testdata;

public class BookInvalidPayloads {

    public static String invalidDataTypesPayload() {
        return """
                {
                  "title": 123,
                  "description": 123,
                  "pageCount": 100,
                  "excerpt": 123,
                  "publishDate": "2026-05-09T12:45:30Z"
                }
                """;
    }

    public static String invalidPublishDateFormatPayload() {
        return """
                {
                  "title": "Invalid date book",
                  "description": "Invalid date description",
                  "pageCount": 100,
                  "excerpt": "Invalid date excerpt",
                  "publishDate": "09-05-2026"
                }
                """;
    }
}
