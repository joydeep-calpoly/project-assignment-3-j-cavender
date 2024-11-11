package project3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.List;

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
   * Returns an unmodifiable list of articles from this fetch.
   *
   * @return An unmodifiable List containing the articles
   */
  public List<Article> getArticles() {
    return Collections.unmodifiableList(articles);
  }

  /**
   * Returns a string representation of the Fetch.
   *
   * @return A formatted string containing the status and total results
   */
  @Override
  public String toString() {
    return "Status: " + status + "\nTotal Results: " + totalResults + "\n";
  }
}
