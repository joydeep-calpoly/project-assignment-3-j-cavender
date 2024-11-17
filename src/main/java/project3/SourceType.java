package project3;

/** Enum representing supported news data sources. */
public enum SourceType {
  FILE,
  URL;

  /**
   * Converts a string input to the corresponding SourceType enum value. This method supports both
   * numeric and text-based input formats for flexibility.
   *
   * @param input The string to convert. Valid inputs are: "file" or "1" for FILE type "url" or "2"
   *     for URL type
   * @return The corresponding SourceType enum value
   * @throws IllegalArgumentException if the input string doesn't match any valid source type
   */
  public static SourceType fromString(String input) {
    String lowercaseInput = input.toLowerCase();
    if (lowercaseInput.equals("file") || lowercaseInput.equals("1")) {
      return FILE;
    } else if (lowercaseInput.equals("url") || lowercaseInput.equals("2")) {
      return URL;
    } else {
      throw new IllegalArgumentException("Invalid source type: " + input);
    }
  }
}
