package project3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FetchTest {
  private List<Article> articles;
  private Fetch fetch;

  /** Sets up test data before each test. Creates a sample article and fetch object. */
  @BeforeEach
  void setUp() {
    articles = new ArrayList<>();
    articles.add(
        new Article(
            "Test Title",
            "Test Description",
            "https://test.com",
            "2024-03-24",
            new Source("test-id", "Test Source"),
            "Test Author",
            "https://test.com/image",
            "Test content"));
    fetch = new Fetch("ok", 1, articles);
  }

  /** Tests that getArticles returns an unmodifiable list and contains the correct articles. */
  @Test
  void testGetArticles() {
    List<Article> fetchedArticles = fetch.getArticles();
    assertNotNull(fetchedArticles);
    assertEquals(1, fetchedArticles.size());
    assertThrows(UnsupportedOperationException.class, () -> fetchedArticles.add(articles.get(0)));
  }

  /** Tests the toString method returns the correct format including status and total results. */
  @Test
  void testToString() {
    String result = fetch.toString();
    assertTrue(result.contains("Status: ok"));
    assertTrue(result.contains("Total Results: 1"));
  }
}
