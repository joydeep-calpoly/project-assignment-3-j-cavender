package project3;

import java.io.IOException;

/**
 * Interface for implementing the Visitor pattern for different parser types. Defines the contract
 * for parser visitors that can process different source contexts.
 */
public interface ParserVisitor {
  /**
   * Visits a file context to parse its content.
   *
   * @param fileContext The file context to visit
   * @throws IOException If there is an error reading or parsing the file
   */
  void visit(FileContext fileContext) throws IOException;

  /**
   * Visits a URL context to parse its content.
   *
   * @param urlContext The URL context to visit
   * @throws IOException If there is an error fetching or parsing the content
   * @throws InterruptedException If the HTTP request is interrupted
   */
  void visit(URLContext urlContext) throws IOException, InterruptedException;
}
