package project3;

import java.io.IOException;

/**
 * Interface for implementing the Visitor pattern for different source types. Defines the contract
 * for source contexts that can be visited by parser visitors.
 */
public interface SourceContext {
  /**
   * Accepts a parser visitor to process the source content.
   *
   * @param visitor The parser visitor to accept
   * @throws IOException If there is an error processing the content
   * @throws InterruptedException If the process is interrupted
   */
  void accept(ParserVisitor visitor) throws IOException, InterruptedException;
}
