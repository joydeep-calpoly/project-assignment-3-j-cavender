package project3;

/**
 * Interface defining the required properties for validatable news content.
 * Classes implementing this interface must provide access to basic news article
 * data that can be validated for completeness and correctness.
 */
public interface Validatable {

    String getTitle();
    String getDescription();
    String getUrl();
    String getPublishedAt();
}