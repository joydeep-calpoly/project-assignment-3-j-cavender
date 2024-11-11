package project3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleArticleTest {
  private SimpleArticle simpleArticle;

  /** Creates a test article before each test. */
  @BeforeEach
  void setUp() {
    simpleArticle =
        new SimpleArticle("Test Description", "2024-03-24", "Test Title", "https://test.com");
  }

  /** Tests validation of required fields. */
  @Test
  void testValidation() {
    assertDoesNotThrow(() -> simpleArticle.validateArticle());

    SimpleArticle noTitle =
        new SimpleArticle(
            "Description",
            "2024-03-24",
            "", // empty title
            "https://test.com");
    assertThrows(IllegalArgumentException.class, noTitle::validateArticle);

    SimpleArticle noDescription =
        new SimpleArticle(
            "", // empty description
            "2024-03-24",
            "Title",
            "https://test.com");
    assertThrows(IllegalArgumentException.class, noDescription::validateArticle);
  }

  /** Tests conversion from SimpleArticle to Article format. */
  @Test
  void testToArticleConversion() {
    Article converted = simpleArticle.toArticle();
    assertNotNull(converted);
    assertEquals("Test Title", converted.getTitle());
    assertDoesNotThrow(converted::validateArticle);
  }

  /** Tests the string representation of SimpleArticle. */
  @Test
  void testToString() {
    String result = simpleArticle.toString();
    assertTrue(result.contains("Test Title"));
    assertTrue(result.contains("Test Description"));
    assertTrue(result.contains("https://test.com"));
    assertTrue(result.contains("2024-03-24"));
  }
}
