package project3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleArticle extends AbstractArticle {

  @JsonCreator
  SimpleArticle(
      @JsonProperty("description") String description,
      @JsonProperty("publishedAt") String publishedAt,
      @JsonProperty("title") String title,
      @JsonProperty("url") String url) {

    super(title, description, url, publishedAt);
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
}
