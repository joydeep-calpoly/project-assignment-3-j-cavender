package project3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the SimpleParser. Contains unit tests to verify the correct parsing and validation
 * of articles in the simple JSON format, which represents individual articles rather than
 * collections.
 */
class SimpleParserTest {
  private SimpleParser parser;

  /**
   * Sets up the test environment before each test. Initializes a new SimpleParser instance for use
   * in the test methods.
   */
  @BeforeEach
  void setUp() {
    parser = new SimpleParser();
  }

  /**
   * Tests the successful parsing of valid simple format JSON content. Verifies that the parser
   * correctly processes JSON input, extracts all required fields properly, creates a single-element
   * list, and accurately populates the SimpleFormat object with the expected values.
   *
   * @throws IOException if there is an error parsing the JSON content
   */
  @Test
  void testValidSimpleParsing() throws IOException {
    String validJson =
        """
        {
            "title": "Test Simple Article",
            "description": "Test Description",
            "url": "http://test.com",
            "publishedAt": "2024-03-24T12:00:00Z"
        }
        """;

    List<SimpleFormat> formats = parser.parse(validJson);
    assertEquals(1, formats.size());
    assertEquals("Test Simple Article", formats.get(0).getTitle());
    assertEquals("Test Description", formats.get(0).getDescription());
    assertEquals("http://test.com", formats.get(0).getUrl());
  }

  /**
   * Tests the parser's handling of JSON content with missing required fields. Verifies that the
   * parser returns an empty list when processing JSON that lacks mandatory fields such as the URL,
   * ensuring invalid or incomplete articles are properly filtered out.
   *
   * @throws IOException if there is an error parsing the JSON content
   */
  @Test
  void testSimpleParsingWithMissingFields() throws IOException {
    String invalidJson =
        """
        {
            "title": "Test Article",
            "description": "Test Description",
            "publishedAt": "2024-03-24T12:00:00Z"
        }
        """;

    List<SimpleFormat> formats = parser.parse(invalidJson);
    assertTrue(formats.isEmpty());
  }

  /**
   * Tests the parser's handling of JSON content with empty field values. Verifies that the parser
   * returns an empty list when all fields are present but contain empty strings, ensuring that
   * articles with non-valid content are properly filtered out.
   *
   * @throws IOException if there is an error parsing the JSON content
   */
  @Test
  void testSimpleParsingWithEmptyFields() throws IOException {
    String invalidJson =
        """
        {
            "title": "",
            "description": "",
            "url": "",
            "publishedAt": ""
        }
        """;

    List<SimpleFormat> formats = parser.parse(invalidJson);
    assertTrue(formats.isEmpty());
  }

  /**
   * Tests the parser's handling of bad JSON input. Verifies that the parser throws an IOException
   * when processing invalid JSON, ensuring proper error handling for bad input data.
   */
  @Test
  void testInvalidJSON() {
    String invalidJson = "{ this is not valid JSON }";
    assertThrows(IOException.class, () -> parser.parse(invalidJson));
  }
}
