package project3;

import java.io.IOException;
import java.util.List;

/**
 * Parser for handling news content in the NewsAPI format. Extends AbstractParser to provide
 * specific parsing logic for NewsAPI JSON structure.
 */
public class NewsAPIParser extends AbstractParser<Article> {
  /** Creates a new NewsAPIParser instance. */
  public NewsAPIParser() {
    super();
  }

  /**
   * Parses NewsAPI format JSON content into a list of articles.
   *
   * @param jsonContent The JSON string to parse
   * @return List of validated Article objects
   * @throws IOException If there is an error parsing the JSON content
   */
  @Override
  protected List<Article> parse(String jsonContent) throws IOException {
    Fetch fetch = mapper.readValue(jsonContent, Fetch.class);
    return filterValid(fetch.getArticles());
  }
}
