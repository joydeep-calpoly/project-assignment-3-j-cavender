package project3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewsParserTest {
  private NewsParser parser;

  /** Sets up a fresh NewsParser instance before each test. */
  @BeforeEach
  void setUp() {
    parser = new NewsParser();
  }

  /** Tests that the NewsParser properly initializes its logger. */
  @Test
  void testParserInitialization() {
    assertNotNull(parser.getLogger(), "Logger should be initialized");
  }

  /**
   * Tests parsing of a simple format JSON string. Verifies that: Exactly one article is parsed, the
   * article title matches the expected value, the article passes validation
   */
  @Test
  void testSimpleFormatParsing() {
    String simpleJson =
            """
                {
                    "title": "Assignment #2",
                    "description": "Test Description",
                    "url": "https://example.com",
                    "publishedAt": "2024-03-24T12:00:00Z"
                }
                """;

    List<Article> articles = parser.parseString(simpleJson);

    assertEquals(1, articles.size(), "Simple format should parse exactly one article");
    Article article = articles.get(0);
    assertEquals("Assignment #2", article.getTitle());
    assertDoesNotThrow(article::validateArticle);
  }

  /**
   * Tests parsing of a standard format JSON string. Verifies that: Correct number of articles is
   * parsed, article content matches expected values, articles pass validation
   */
  @Test
  void testStandardFormatParsing() {
    String standardJson =
            """
                {
                    "status": "ok",
                    "totalResults": 1,
                    "articles": [
                        {
                            "source": {
                                "id": "test-source",
                                "name": "Test News"
                            },
                            "title": "Test Article",
                            "description": "Test Description",
                            "url": "https://example.com",
                            "publishedAt": "2024-03-24T12:00:00Z",
                            "author": "Test Author",
                            "urlToImage": "https://example.com/image.jpg",
                            "content": "Test content"
                        }
                    ]
                }
                """;

    List<Article> articles = parser.parseString(standardJson);
    assertEquals(1, articles.size(), "Should parse one valid article");
    assertEquals("Test Article", articles.get(0).getTitle());
    assertDoesNotThrow(() -> articles.get(0).validateArticle());
  }

  /**
   * Tests parsing of JSON strings containing invalid article data. The parser should: skip invalid
   * articles, return an empty list when no valid articles are found, log appropriate error messages
   */
  @Test
  void testBadFormatParsing() {
    String badJson =
            """
                {
                    "status": "ok",
                    "totalResults": 1,
                    "articles": [
                        {
                            "source": {"id": "test-source", "name": "Test News"},
                            "description": "Missing Title",
                            "url": "https://example.com",
                            "publishedAt": "2024-03-24T12:00:00Z"
                        }
                    ]
                }
                """;

    List<Article> articles = parser.parseString(badJson);
    assertTrue(articles.isEmpty(), "Should return empty list for invalid article");
  }

  /**
   * Tests the format detection logic of the parser. Verifies that the parser correctly identifies:
   * Simple format, Standard format
   */
  @Test
  void testFormatDetection() throws Exception {
    String simpleJson =
            """
                {
                    "title": "Test Title",
                    "description": "Test Description",
                    "url": "https://example.com",
                    "publishedAt": "2024-03-24T12:00:00Z"
                }
                """;

    String standardJson =
            """
                {
                    "status": "ok",
                    "totalResults": 1,
                    "articles": []
                }
                """;

    assertEquals(
            NewsParser.FormatType.SIMPLE,
            parser.checkFormat(simpleJson),
            "Should detect simple format");

    assertEquals(
            NewsParser.FormatType.STANDARD,
            parser.checkFormat(standardJson),
            "Should detect standard format");
  }

  /**
   * Tests parsing of invalid JSON strings. Handles bad JSON, returns an empty list, logs
   * appropriate error messages
   */
  @Test
  void testInvalidJsonParsing() {
    String invalidJson = "{ this is not valid json }";
    List<Article> articles = parser.parseString(invalidJson);
    assertTrue(articles.isEmpty(), "Should return empty list for invalid JSON");
  }

  /**
   * Tests parsing of JSON with an unknown format. Verifies that the parser: Handles unknown
   * formats, returns an empty list, logs appropriate error messages
   */
  @Test
  void testUnknownFormatParsing() {
    String unknownJson =
            """
                {
                    "someField": "someValue",
                    "otherField": 123
                }
                """;
    List<Article> articles = parser.parseString(unknownJson);
    assertTrue(articles.isEmpty(), "Should return empty list for unknown format");
  }

  /**
   * Tests the parseFile method with a nonexistent file. Verifies that: An empty list is returned
   * the error is logged
   */
  @Test
  void testParseFileNonexistentFile() {
    List<Article> articles = parser.parseFile("nonexistent.json");
    assertTrue(articles.isEmpty(), "Should return empty list for nonexistent file");
  }
}