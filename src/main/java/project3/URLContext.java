package project3;

import java.io.IOException;

/**
 * Context class for handling URL-based news sources. This class implements the SourceContext
 * interface and provides functionality for fetching news content from remote URLs using the News
 * API service.
 */
public class URLContext implements SourceContext {
  private final NewsAPIClient client;

  /**
   * Constructs a new URLContext. Initializes a NewsAPIClient instance that will be used for making
   * HTTP requests to the News API service.
   */
  public URLContext() {
    this.client = new NewsAPIClient();
  }

  /**
   * Accepts a parser visitor to process the URL content. This method is part of the Visitor pattern
   * implementation and allows different parsers to process the URL content in their own way.
   *
   * @param visitor The parser visitor that will process the URL content
   * @throws IOException If there is an error reading from the URL or processing the content
   * @throws InterruptedException If the HTTP request is interrupted during execution
   */
  @Override
  public void accept(ParserVisitor visitor) throws IOException, InterruptedException {
    visitor.visit(this);
  }

  /**
   * Retrieves the content from the default News API URL. Makes an HTTP request to fetch the latest
   * news content using the default configuration from the NewsAPIClient.
   *
   * @return A String containing the JSON response from the News API
   * @throws IOException If there is an error making the HTTP request or processing the response
   * @throws InterruptedException If the HTTP request is interrupted during execution
   */
  public String getContent() throws IOException, InterruptedException {
    return client.fetchContent(client.getDefaultUrl());
  }
}
