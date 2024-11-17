package project3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.List;

/**
 * Represents a response from the News API containing a collection of articles.
 * Represents the status of the API response, total results count, and the list
 * of retrieved articles. It provides immutable access to the fetched data.
 */
public class Fetch {
  private final String status;
  private final int totalResults;
  private final List<Article> articles;

  @JsonCreator
  Fetch(
          @JsonProperty("status") String status,
          @JsonProperty("totalResults") int totalResults,
          @JsonProperty("articles") List<Article> articles) {
    this.status = status;
    this.totalResults = totalResults;
    this.articles = articles;
  }

  /**
   * Returns an unmodifiable view of the articles list.
   * This method ensures that the internal list cannot be modified by external code,
   *
   * @return An unmodifiable List containing the Article objects
   */
  public List<Article> getArticles() {
    return Collections.unmodifiableList(articles);
  }

  /**
   * Returns a string representation of the Fetch object.
   * The string includes the API response status and the total number of results.
   *
   * @return A formatted string containing the status and total results information
   */
  @Override
  public String toString() {
    return "Status: " + status + "\nTotal Results: " + totalResults + "\n";
  }
}