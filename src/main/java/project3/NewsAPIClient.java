package project3;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class NewsAPIClient {
  private static final String API_KEY = "56cb18c5b3fe491c86fb21208e2e1a60";
  private static final String BASE_URL = "https://newsapi.org/v2/top-headlines";

  private final HttpClient client;
  private final NewsParser parser;
  private final java.util.logging.Logger logger;

  /**
   * Creates a new NewsAPIClient with the specified logger.
   *
   * @param logger The logger to use for error and status messages
   */
  public NewsAPIClient(java.util.logging.Logger logger) {
    this(logger, HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build());
  }

  NewsAPIClient(java.util.logging.Logger logger, HttpClient client) {
    this.logger = logger;
    this.client = client;
    this.parser = new NewsParser();
  }

  /**
   * Fetches the current top headlines from the NewsAPI service.
   *
   * @return A list of Article objects representing the top headlines
   */
  public List<Article> getTopHeadlines() {
    try {
      HttpResponse<String> response = makeRequest();
      return handleResponse(response);
    } catch (Exception e) {
      logger.warning("Failed to fetch headlines: " + e.getMessage());
      return List.of();
    }
  }

  private HttpResponse<String> makeRequest() throws Exception {
    String url = String.format("%s?country=us&apiKey=%s", BASE_URL, API_KEY);

    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Accept", "application/json")
            .GET()
            .build();

    return client.send(request, HttpResponse.BodyHandlers.ofString());
  }

  private List<Article> handleResponse(HttpResponse<String> response) {
    if (response.statusCode() == 200) {
      return parser.parseString(response.body());
    }

    logger.warning("API request failed with status code: " + response.statusCode());
    logger.warning("Response body: " + response.body());
    return List.of();
  }
}
