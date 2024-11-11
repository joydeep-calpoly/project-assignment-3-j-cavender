package project3;

public abstract class AbstractArticle {
  protected final String title;
  protected final String description;
  protected final String url;
  protected final String publishedAt;

  protected AbstractArticle(String title, String description, String url, String publishedAt) {
    this.title = title;
    this.description = description;
    this.url = url;
    this.publishedAt = publishedAt;
  }

  protected String getTitle() {
    return title;
  }

  protected String getDescription() {
    return description;
  }

  protected String getUrl() {
    return url;
  }

  protected String getPublishedAt() {
    return publishedAt;
  }

  /**
   * Validates that all required fields are present and non-empty.
   *
   * @throws IllegalArgumentException if any required field is missing or empty
   */
  public void validateArticle() {
    if (getTitle() == null || getTitle().isEmpty()) {
      throw new IllegalArgumentException("Title is missing");
    }
    if (getDescription() == null || getDescription().isEmpty()) {
      throw new IllegalArgumentException("Description is missing");
    }
    if (getUrl() == null || getUrl().isEmpty()) {
      throw new IllegalArgumentException("URL is missing");
    }
    if (getPublishedAt() == null || getPublishedAt().isEmpty()) {
      throw new IllegalArgumentException("Published date is missing");
    }
  }

  /**
   * Returns a string representation of the Article.
   *
   * @return A formatted string containing all article fields
   */
  @Override
  public String toString() {
    return "Title: "
        + getTitle()
        + "\nDescription: "
        + getDescription()
        + "\nURL: "
        + getUrl()
        + "\nPublished:"
        + getPublishedAt()
        + "\n";
  }
}
