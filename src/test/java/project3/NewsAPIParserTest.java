package project3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the NewsAPIParser.
 * Contains unit tests to verify the correct parsing and validation of news articles
 * from the NewsAPI JSON format.
 */
class NewsAPIParserTest {
    private NewsAPIParser parser;

    /**
     * Sets up the test environment before each test.
     * Initializes a new NewsAPIParser instance for use in the test methods.
     */
    @BeforeEach
    void setUp() {
        parser = new NewsAPIParser();
    }

    /**
     * Tests the successful parsing of valid NewsAPI JSON content.
     * Verifies that the parser correctly processes JSON input,
     * all required fields are properly extracted, the correct number of
     * articles is returned, and article titles match the expected values.
     *
     * @throws IOException if there is an error parsing the JSON content
     */
    @Test
    void testValidNewsAPIParsing() throws IOException {
        String validJson = """
        {
            "status": "ok",
            "totalResults": 2,
            "articles": [
                {
                    "title": "Test Article 1",
                    "description": "Test Description 1",
                    "url": "http://test1.com",
                    "publishedAt": "2024-03-24T12:00:00Z",
                    "source": {
                        "id": "test-source",
                        "name": "Test Source"
                    }
                },
                {
                    "title": "Test Article 2",
                    "description": "Test Description 2",
                    "url": "http://test2.com",
                    "publishedAt": "2024-03-24T13:00:00Z",
                    "source": {
                        "id": "test-source",
                        "name": "Test Source"
                    }
                }
            ]
        }
        """;

        List<Article> articles = parser.parse(validJson);
        assertEquals(2, articles.size());
        assertEquals("Test Article 1", articles.get(0).getTitle());
        assertEquals("Test Article 2", articles.get(1).getTitle());
    }

    /**
     * Tests the parser's handling of JSON content with missing fields.
     * Verifies that articles with missing required fields are filtered out,
     * only valid articles with all required fields are returned, and the
     * parser continues processing despite encountering invalid articles.
     *
     * @throws IOException if there is an error parsing the JSON content
     */
    @Test
    void testNewsAPIParsingWithMissingFields() throws IOException {
        String invalidJson = """
        {
            "status": "ok",
            "totalResults": 3,
            "articles": [
                {
                    "title": "Valid Article",
                    "description": "Valid Description",
                    "url": "http://valid.com",
                    "publishedAt": "2024-03-24T12:00:00Z"
                },
                {
                    "title": "",
                    "description": "Missing Title",
                    "url": "http://invalid1.com",
                    "publishedAt": "2024-03-24T13:00:00Z"
                },
                {
                    "title": "Missing URL",
                    "description": "Description",
                    "publishedAt": "2024-03-24T14:00:00Z"
                }
            ]
        }
        """;

        List<Article> articles = parser.parse(invalidJson);
        assertEquals(1, articles.size());
        assertEquals("Valid Article", articles.get(0).getTitle());
    }

    /**
     * Tests the parser's handling of empty article arrays.
     * Verifies that the parser correctly handles JSON with no articles,
     * returns an empty list rather than null, and does not throw
     * exceptions for empty article arrays.
     *
     * @throws IOException if there is an error parsing the JSON content
     */
    @Test
    void testNewsAPIParsingWithEmptyArticles() throws IOException {
        String emptyJson = """
        {
            "status": "ok",
            "totalResults": 0,
            "articles": []
        }
        """;

        List<Article> articles = parser.parse(emptyJson);
        assertTrue(articles.isEmpty());
    }

    /**
     * Tests the parser's handling of bad JSON input.
     * Verifies that the parser throws an IOException for invalid JSON syntax
     * and that error handling works as expected for bad input.
     */
    @Test
    void testInvalidJSON() {
        String invalidJson = "{ this is not valid JSON }";
        assertThrows(IOException.class, () -> parser.parse(invalidJson));
    }
}