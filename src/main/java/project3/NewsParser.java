package project3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NewsParser {
  private final ObjectMapper mapper;
  private final java.util.logging.Logger logger;

  /** Creates a new NewsParser with default ObjectMapper and logging configuration. */
  public NewsParser() {
    this.mapper = new ObjectMapper();
    this.logger = project3.Logger.setupLogger(NewsParser.class.getName(), "article%u.log");
  }

  /**
   * Gets the logger instance used by this parser.
   *
   * @return The logger instance
   */
  public java.util.logging.Logger getLogger() {
    return logger;
  }

  /**
   * Parses a JSON string into a list of articles.
   *
   * @param json The JSON string to parse
   * @return A list of valid Article objects. Returns an empty list if parsing fails or no valid
   *     articles are found.
   */
  public List<Article> parseString(String json) {
    try {
      FormatType format = checkFormat(json);
      if (format == FormatType.STANDARD) {
        return parseStandardFormat(json);
      } else if (format == FormatType.SIMPLE) {
        return parseSimpleFormat(json);
      } else {
        logger.warning("Unknown JSON format");
        return new ArrayList<>();
      }
    } catch (Exception e) {
      logger.warning("Failed to parse JSON: " + e.getMessage());
      return new ArrayList<>();
    }
  }

  /**
   * Reads and parses a JSON file into a list of articles.
   *
   * @param fileName Path to the JSON file to parse
   * @return A list of valid Article objects. Returns an empty list if reading fails, parsing fails,
   *     or no valid articles are found.
   */
  public List<Article> parseFile(String fileName) {
    try {
      String json = Files.readString(Path.of(fileName));
      return parseString(json);
    } catch (Exception e) {
      logger.warning("Failed to read file: " + fileName + " - " + e.getMessage());
      return new ArrayList<>();
    }
  }

  FormatType checkFormat(String json) throws Exception {
    JsonNode rootNode = mapper.readTree(json);

    if (rootNode.has("status") && rootNode.has("articles")) {
      return FormatType.STANDARD;
    }

    if (rootNode.has("title")
        && rootNode.has("description")
        && rootNode.has("url")
        && rootNode.has("publishedAt")) {
      return FormatType.SIMPLE;
    }

    return FormatType.UNKNOWN;
  }

  private List<Article> parseStandardFormat(String json) throws Exception {
    Fetch fetch = mapper.readValue(json, Fetch.class);
    return validate(fetch.getArticles());
  }

  private List<Article> parseSimpleFormat(String json) throws Exception {
    SimpleArticle simpleArticle = mapper.readValue(json, SimpleArticle.class);
    return validate(List.of(simpleArticle.toArticle()));
  }

  private List<Article> validate(List<Article> articles) {
    List<Article> validArticles = new ArrayList<>();
    for (Article article : articles) {
      try {
        article.validateArticle();
        validArticles.add(article);
      } catch (IllegalArgumentException e) {
        logger.warning("Invalid article: " + e.getMessage());
      }
    }
    return validArticles;
  }

  enum FormatType {
    STANDARD,
    SIMPLE,
    UNKNOWN
  }
}
