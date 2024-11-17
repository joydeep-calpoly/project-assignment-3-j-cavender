package project3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for verifying the implementation of the Visitor pattern in the news parser system.
 * Tests the interaction between parsers (visitors) and contexts (source types),
 * as well as the factory methods for creating these components.
 */
class VisitorPatternTest {
    private ParserVisitor newsAPIParser;
    private ParserVisitor simpleParser;
    private SourceContext newsAPIFileContext;
    private SourceContext simpleFileContext;
    private SourceContext urlContext;

    @TempDir
    Path tempDir;

    /**
     * Sets up the test environment before each test.
     * Creates temporary test files with sample JSON content and initializes
     * parser and context objects needed for testing the visitor pattern implementation.
     *
     * @throws IOException if there is an error creating the temporary test files
     */
    @BeforeEach
    void setUp() throws IOException {
        Path newsApiFile = tempDir.resolve("newsapi.json");
        Path simpleFile = tempDir.resolve("simple.json");

        Files.writeString(newsApiFile, """
        {
            "status": "ok",
            "totalResults": 1,
            "articles": [
                {
                    "title": "Test Article",
                    "description": "Test Description",
                    "url": "http://test.com",
                    "publishedAt": "2024-03-24T12:00:00Z",
                    "source": {
                        "id": "test-source",
                        "name": "Test Source"
                    }
                }
            ]
        }
        """);

        Files.writeString(simpleFile, """
        {
            "title": "Test Simple Article",
            "description": "Test Description",
            "url": "http://test.com",
            "publishedAt": "2024-03-24T12:00:00Z"
        }
        """);

        newsAPIParser = new NewsAPIParser();
        simpleParser = new SimpleParser();

        newsAPIFileContext = new FileContext(newsApiFile.toString());
        simpleFileContext = new FileContext(simpleFile.toString());
        urlContext = new URLContext();
    }

    /**
     * Tests the core functionality of the Visitor pattern implementation.
     * Verifies that parsers can visit appropriate contexts successfully,
     * and that incompatible parser-context combinations are properly rejected.
     * Tests both successful parsing scenarios and expected failure cases.
     *
     * @throws IOException if there is an error reading the test files
     * @throws InterruptedException if a URL connection is interrupted
     */
    @Test
    void testParserVisitation() throws IOException, InterruptedException {
        assertDoesNotThrow(() -> newsAPIFileContext.accept(newsAPIParser));
        assertDoesNotThrow(() -> simpleFileContext.accept(simpleParser));
        assertThrows(UnsupportedOperationException.class, () ->
                urlContext.accept(simpleParser)
        );
    }

    /**
     * Tests the string-based creation of SourceType enum values.
     * Verifies that both numeric and text-based inputs are correctly
     * converted to appropriate SourceType values, and that invalid
     * inputs are properly rejected with exceptions.
     */
    @Test
    void testSourceTypeFromString() {
        assertEquals(SourceType.FILE, SourceType.fromString("1"));
        assertEquals(SourceType.FILE, SourceType.fromString("file"));
        assertEquals(SourceType.URL, SourceType.fromString("2"));
        assertEquals(SourceType.URL, SourceType.fromString("url"));

        assertThrows(IllegalArgumentException.class, () ->
                SourceType.fromString("invalid"));
    }

    /**
     * Tests the string-based creation of FormatType enum values.
     * Verifies that both numeric and text-based inputs are correctly
     * converted to appropriate FormatType values, and that invalid
     * inputs are properly rejected with exceptions.
     */
    @Test
    void testFormatTypeFromString() {
        assertEquals(FormatType.NEWSAPI, FormatType.fromString("1"));
        assertEquals(FormatType.NEWSAPI, FormatType.fromString("newsapi"));
        assertEquals(FormatType.SIMPLE, FormatType.fromString("2"));
        assertEquals(FormatType.SIMPLE, FormatType.fromString("simple"));

        assertThrows(IllegalArgumentException.class, () ->
                FormatType.fromString("invalid"));
    }

    /**
     * Tests the ParserFactory's ability to create appropriate context and parser
     * combinations. Verifies that the factory correctly instantiates different
     * types of contexts and parsers based on the provided source and format types,
     * and that the created instances are of the expected classes.
     */
    @Test
    void testParserFactoryCreation() {
        SourceContext context1 = ParserFactory.createContext(SourceType.FILE, FormatType.NEWSAPI);
        ParserVisitor parser1 = ParserFactory.createParser(FormatType.NEWSAPI);
        assertTrue(context1 instanceof FileContext);
        assertTrue(parser1 instanceof NewsAPIParser);

        SourceContext context2 = ParserFactory.createContext(SourceType.FILE, FormatType.SIMPLE);
        ParserVisitor parser2 = ParserFactory.createParser(FormatType.SIMPLE);
        assertTrue(context2 instanceof FileContext);
        assertTrue(parser2 instanceof SimpleParser);

        SourceContext context3 = ParserFactory.createContext(SourceType.URL, FormatType.NEWSAPI);
        ParserVisitor parser3 = ParserFactory.createParser(FormatType.NEWSAPI);
        assertTrue(context3 instanceof URLContext);
        assertTrue(parser3 instanceof NewsAPIParser);
    }
}