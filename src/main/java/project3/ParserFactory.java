package project3;

/**
 * Factory class for creating parser and context instances.
 * Implements the Factory pattern to create appropriate parser and context objects.
 */
public class ParserFactory {
    private static final String NEWSAPI_FILE_PATH = "project_3/inputs/newsapi.json";
    private static final String SIMPLE_FILE_PATH = "project_3/inputs/simple.json";

    /**
     * Creates a source context based on the specified types.
     *
     * @param sourceType The type of source
     * @param formatType The type of format
     * @return The appropriate SourceContext instance
     */
    public static SourceContext createContext(SourceType sourceType, FormatType formatType) {
        if (sourceType == SourceType.FILE) {
            String path = (formatType == FormatType.NEWSAPI) ? NEWSAPI_FILE_PATH : SIMPLE_FILE_PATH;
            return new FileContext(path);
        } else {
            return new URLContext();
        }
    }

    /**
     * Creates a parser visitor based on the specified format type.
     *
     * @param formatType The type of format to parse
     * @return The appropriate ParserVisitor instance
     */
    public static ParserVisitor createParser(FormatType formatType) {
        if (formatType == FormatType.NEWSAPI) {
            return new NewsAPIParser();
        } else {
            return new SimpleParser();
        }
    }
}