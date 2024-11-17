package project3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Context for handling file-based news sources. Implements the SourceContext interface for the
 * Visitor pattern.
 */
public class FileContext implements SourceContext {
  private final String filePath;

  /**
   * Creates a new FileContext with the specified file path.
   *
   * @param filePath Path to the file containing news data
   */
  public FileContext(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Accepts a parser visitor to process the file content.
   *
   * @param visitor The parser visitor to accept
   * @throws IOException If there is an error reading the file
   */
  @Override
  public void accept(ParserVisitor visitor) throws IOException {
    visitor.visit(this);
  }

  /**
   * Gets the content of the file as a string.
   *
   * @return The file content as a string
   * @throws IOException If there is an error reading the file
   */
  public String getContent() throws IOException {
    return new String(Files.readAllBytes(Paths.get(filePath)));
  }
}
