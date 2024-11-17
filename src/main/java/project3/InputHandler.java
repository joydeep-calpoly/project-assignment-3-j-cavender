package project3;

import java.util.Scanner;

/**
 * Handles user input for selecting news source and format types. Provides an interactive
 * command-line interface for user choices.
 */
public class InputHandler {
  private final Scanner scanner;
  private SourceType currentSourceType;
  private FormatType currentFormat;

  /** Creates a new InputHandler instance. Initializes the scanner for reading user input. */
  public InputHandler() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Gets the user's preferred source type through command-line interaction.
   *
   * @return The selected SourceType
   */
  public SourceType getSourceType() {
    if (currentSourceType != null) {
      return currentSourceType;
    }

    while (true) {
      System.out.println("How would you like to obtain news?");
      System.out.println("1. From file");
      System.out.println("2. From URL");
      System.out.print("Enter choice (1 or 2): ");

      try {
        String input = scanner.nextLine().trim();
        currentSourceType = SourceType.fromString(input);
        return currentSourceType;
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid choice. Please enter 1 or 2.");
      }
    }
  }

  /**
   * Gets the user's preferred format type through command-line interaction. Automatically selects
   * NewsAPI format for URL sources.
   *
   * @return The selected FormatType
   */
  public FormatType getFormat() {
    currentSourceType = getSourceType();

    if (currentSourceType == SourceType.URL) {
      currentFormat = FormatType.NEWSAPI;
      return FormatType.NEWSAPI;
    }

    while (true) {
      System.out.println("\nWhich format would you like to use?");
      System.out.println("1. NewsAPI format");
      System.out.println("2. Simple format");
      System.out.print("Enter choice (1 or 2): ");

      try {
        String input = scanner.nextLine().trim();
        currentFormat = FormatType.fromString(input);
        return currentFormat;
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid choice. Please enter 1 or 2.");
      }
    }
  }

  /** Closes the input scanner. */
  public void close() {
    scanner.close();
  }
}
