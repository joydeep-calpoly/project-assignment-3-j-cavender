package project3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a news article in the simple format.
 * Implements the Validatable interface for content validation.
 */
public class SimpleFormat implements Validatable {
    private final String title;
    private final String description;
    private final String url;
    private final String publishedAt;

    @JsonCreator
    SimpleFormat(
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
        return "Title: " + title +
                "\nDescription: " + description +
                "\nURL: " + url +
                "\nPublished: " + publishedAt +
                "\n";
    }
}