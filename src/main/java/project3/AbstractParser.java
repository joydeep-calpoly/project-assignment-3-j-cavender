package project3;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Abstract base class for JSON content parsers that implements the Visitor pattern.
 * Provides common functionality for parsing and validating content from different sources.
 *
 * @param <T> The type of content being parsed, must implement Validatable interface
 */
public abstract class AbstractParser<T extends Validatable> implements ParserVisitor {
    protected final ObjectMapper mapper;
    protected final Logger logger;
    protected List<T> parsedContent;

    protected AbstractParser() {
        this.mapper = new ObjectMapper();
        this.logger = LoggerConfiguration.getInstance().getLogger();
    }

    /**
     * Parses JSON content into a list of validated items.
     *
     * @param jsonContent The JSON string to parse
     * @return List of parsed and validated items
     * @throws IOException If there is an error parsing the JSON content
     */
    protected abstract List<T> parse(String jsonContent) throws IOException;

    /**
     * Visits a file context to parse its content.
     *
     * @param fileContext The file context to visit
     * @throws IOException If there is an error reading or parsing the file
     */
    @Override
    public void visit(FileContext fileContext) throws IOException {
        parsedContent = parse(fileContext.getContent());
        displayResults();
    }

    /**
     * Visits a URL context to parse its content.
     *
     * @param urlContext The URL context to visit
     * @throws IOException If there is an error fetching or parsing the content
     * @throws InterruptedException If the HTTP request is interrupted
     */
    @Override
    public void visit(URLContext urlContext) throws IOException, InterruptedException {
        parsedContent = parse(urlContext.getContent());
        displayResults();
    }

    protected void displayResults() {
        System.out.println("\nParsed " + parsedContent.size() + " valid items from source:");
        for (T item : parsedContent) {
            System.out.println(item.toString());
        }
    }

    protected void validate(T item) {
        List<String> missingFields = new ArrayList<>();

        if (isEmpty(item.getTitle())) {
            missingFields.add("title");
        }
        if (isEmpty(item.getDescription())) {
            missingFields.add("description");
        }
        if (isEmpty(item.getUrl())) {
            missingFields.add("url");
        }
        if (isEmpty(item.getPublishedAt())) {
            missingFields.add("publishedAt");
        }

        if (!missingFields.isEmpty()) {
            String identifier = !isEmpty(item.getTitle()) ? item.getTitle() : "Unknown Entry";
            throw new IllegalArgumentException(String.format(
                    "Entry '%s' is missing required fields: %s",
                    identifier,
                    String.join(", ", missingFields)
            ));
        }
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    protected List<T> filterValid(List<T> items) {
        List<T> validItems = new ArrayList<>();

        for (T item : items) {
            try {
                validate(item);
                validItems.add(item);
            } catch (IllegalArgumentException e) {
                logger.warning(e.getMessage());
            }
        }

        return validItems;
    }
}