package project3;

import java.io.IOException;

public class Driver {
  /**
   * Main method that runs the news parser application. Manages the user interaction flow and
   * handles any errors that occur during execution.
   */
  public static void main(String[] args) {
    InputHandler inputHandler = new InputHandler();
    try {
      SourceType sourceType = inputHandler.getSourceType();
      FormatType formatType = inputHandler.getFormat();

      SourceContext context = ParserFactory.createContext(sourceType, formatType);
      ParserVisitor parser = ParserFactory.createParser(formatType);

      context.accept(parser);

      System.out.println(
          "\nCheck "
              + LoggerConfiguration.getInstance().getLogPath()
              + " for warnings about invalid items");

    } catch (IOException | InterruptedException e) {
      System.err.println("Error processing source: " + e.getMessage());
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      System.err.println("Configuration error: " + e.getMessage());
    } finally {
      inputHandler.close();
      LoggerConfiguration.getInstance().close();
    }
  }
}
