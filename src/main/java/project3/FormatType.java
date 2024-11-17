package project3;

/** Enum representing supported news data formats. */
public enum FormatType {
  NEWSAPI,
  SIMPLE;

  /**
   * Converts a string input to the corresponding FormatType.
   *
   * @param input String representation of format type ("1", "2", "newsapi", or "simple")
   * @return The corresponding FormatType
   * @throws IllegalArgumentException if the input is invalid
   */
  public static FormatType fromString(String input) {
    String lowercaseInput = input.toLowerCase();
    if (lowercaseInput.equals("newsapi") || lowercaseInput.equals("1")) {
      return NEWSAPI;
    } else if (lowercaseInput.equals("simple") || lowercaseInput.equals("2")) {
      return SIMPLE;
    } else {
      throw new IllegalArgumentException("Invalid format type: " + input);
    }
  }
}
