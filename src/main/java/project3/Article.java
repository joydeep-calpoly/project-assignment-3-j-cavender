package project3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Article {
  private final String title;
  private final String description;
  private final String url;
  private final String published;
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
    this.published = publishedAt;
    this.source = source;
    this.author = author;
    this.urlImage = urlImage;
    this.content = content;
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
    if (published == null || published.isEmpty()) {
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
            + published
            + "\n";
  }
}
