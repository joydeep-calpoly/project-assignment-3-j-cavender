package project3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleArticle{
  private final String title;
  private final String description;
  private final String url;
  private final String publishedAt;

  @JsonCreator
  SimpleArticle(
      @JsonProperty("description") String description,
      @JsonProperty("publishedAt") String publishedAt,
      @JsonProperty("title") String title,
      @JsonProperty("url") String url) {

    this.title = title;
    this.description = description;
    this.url = url;
    this.publishedAt = publishedAt;
  }

  /**
   * Converts this SimpleArticle to a standard Article format.
   *
   * @return A new Article instance containing this SimpleArticle's data
   */
  public Article toArticle() {
    return new Article(
        title,
        description,
        url,
        publishedAt,
        null, // source
        null, // author
        null, // urlImage
        null // content
        );
  }
  public void validateArticle() {
    if (title == null || title.isEmpty()) {
      throw new IllegalArgumentException("Title is missing");
    }
    if (description == null || description.isEmpty()) {
      throw new IllegalArgumentException("Description is missing");
    }
    if (url == null || url.isEmpty()) {
      throw new IllegalArgumentException("URL is missing");
    }
    if (publishedAt == null || publishedAt.isEmpty()) {
      throw new IllegalArgumentException("Published date is missing");
    }
  }
  public String toString() {
    return "Title: "
            + title
            + "\nDescription: "
            + description
            + "\nURL: "
            + url
            + "\nPublished:"
            + publishedAt
            + "\n";
  }
}
