package project3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Article extends AbstractArticle {
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

    super(title, description, url, publishedAt);
    this.source = source;
    this.author = author;
    this.urlImage = urlImage;
    this.content = content;
  }
}
