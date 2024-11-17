package project3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a news article in the NewsAPI format with all its data. Implements the Validatable
 * interface for content validation.
 */
public class Article implements Validatable {
  private final String title;
  private final String description;
  private final String url;
  private final String publishedAt;
  private final Source source;
  private final String author;
  private final String urlImage;
  private final String content;

  @JsonCreator
  Article(
      @JsonProperty("title") String title,
      @JsonProperty("description") String description,
      @JsonProperty("url") String url,
      @JsonProperty("publishedAt") String publishedAt,
      @JsonProperty("source") Source source,
      @JsonProperty("author") String author,
      @JsonProperty("urlToImage") String urlImage,
      @JsonProperty("content") String content) {
    this.title = title;
    this.description = description;
    this.url = url;
    this.publishedAt = publishedAt;
    this.source = source;
    this.author = author;
    this.urlImage = urlImage;
    this.content = content;
  }

  /**
   * Gets the article's title.
   *
   * @return The article title
   */
  @Override
  public String getTitle() {
    return title;
  }

  /**
   * Gets the article's description.
   *
   * @return The article description
   */
  @Override
  public String getDescription() {
    return description;
  }

  /**
   * Gets the article's URL.
   *
   * @return The article URL
   */
  @Override
  public String getUrl() {
    return url;
  }

  /**
   * Gets the article's publication date.
   *
   * @return The publication date as a string
   */
  @Override
  public String getPublishedAt() {
    return publishedAt;
  }

  @Override
  public String toString() {
    return "Title: "
        + title
        + "\nDescription: "
        + description
        + "\nURL: "
        + url
        + "\nPublished: "
        + publishedAt
        + "\n";
  }
}
