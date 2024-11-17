package project3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser for handling news content in the simple format. Extends AbstractParser to provide specific
 * parsing logic for simple JSON structure.
 */
public class SimpleParser extends AbstractParser<SimpleFormat> {
  /** Creates a new SimpleParser instance. */
  public SimpleParser() {
    super();
  }

  /**
   * Parses simple format JSON content into a list containing a single article.
   *
   * @param jsonContent The JSON string to parse
   * @return List containing a single validated SimpleFormat object
   * @throws IOException If there is an error parsing the JSON content
   */
  @Override
  protected List<SimpleFormat> parse(String jsonContent) throws IOException {
    SimpleFormat simpleFormat = mapper.readValue(jsonContent, SimpleFormat.class);
    List<SimpleFormat> formats = new ArrayList<>();
    formats.add(simpleFormat);
    return filterValid(formats);
  }

  /**
   * Throws UnsupportedOperationException as simple format is not supported for URL sources.
   *
   * @param urlContext The URL context to visit
   * @throws UnsupportedOperationException Always thrown as operation is not supported
   */
  @Override
  public void visit(URLContext urlContext) {
    throw new UnsupportedOperationException("Simple format not supported for URL source");
  }
}
