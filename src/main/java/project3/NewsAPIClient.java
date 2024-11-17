package project3;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.logging.Logger;

/**
 * Client for interacting with the News API service.
 * This class handles HTTP communication with the News API,
 * managing API requests and responses for retrieving news articles.
 */
public class NewsAPIClient {
    private static final String API_BASE_URL = "http://newsapi.org/v2";
    private static final String API_KEY = "56cb18c5b3fe491c86fb21208e2e1a60";
    private final HttpClient httpClient;
    private final Logger logger;

    /**
     * Constructs a new NewsAPIClient with default configuration.
     * Initializes an HTTP client with a 10-second connection timeout
     * and sets up logging capabilities.
     */
    public NewsAPIClient() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.logger = LoggerConfiguration.getInstance().getLogger();
    }

    /**
     * Retrieves the default URL for accessing top headlines from the News API.
     * The URL is configured to fetch US top headlines using the stored API key.
     *
     * @return A String containing the complete URL for accessing top US headlines
     */
    public String getDefaultUrl() {
        return String.format("%s/top-headlines?country=us&apiKey=%s", API_BASE_URL, API_KEY);
    }

    /**
     * Fetches content from the specified News API URL.
     * Sends an HTTP GET request to the provided URL and returns the response body.
     * The method includes error handling for non-200 status codes and logging of errors.
     *
     * @param url The complete URL to fetch content from, including any query parameters
     * @return A String containing the JSON response from the API
     * @throws IOException If there's an error during the HTTP request or if the response
     *                     status code is not 200
     * @throws InterruptedException If the HTTP request is interrupted
     */
    public String fetchContent(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            String errorMessage = "Request failed with status code: " + response.statusCode();
            logger.severe(errorMessage + "\nResponse: " + response.body());
            throw new IOException(errorMessage);
        }

        return response.body();
    }
}