package project3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/** Test class for Article. Tests article string representation and formatting. */
class ArticleTest {
  private Article validArticle;

  @BeforeEach
  void setUp() {
    validArticle =
        new Article(
            "Test Title",
            "Test Description",
            "https://example.com",
            "2024-03-24T12:00:00Z",
            new Source("test-source", "Test News"),
            "Test Author",
            "https://example.com/image.jpg",
            "Test content");
  }

  /**
   * Tests the toString method of Article. Verifies that the string representation includes all
   * required fields in the correct format.
   */
  @Test
  void testToString() {
    String expected =
        """
            Title: Test Title
            Description: Test Description
            URL: https://example.com
            Published:2024-03-24T12:00:00Z
            """;

    assertEquals(expected, validArticle.toString());
  }

  /** Tests the getTitle method. Verifies that the correct title is returned. */
  @Test
  void testGetTitle() {
    assertEquals("Test Title", validArticle.getTitle());
  }
}
