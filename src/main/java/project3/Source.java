package project3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Represents a news source with its identifying information. */
public class Source {
  private final String id;
  private final String name;

  /**
   * Creates a new Source instance.
   *
   * @param id The source identifier
   * @param name The source name
   */
  @JsonCreator
  Source(@JsonProperty("id") String id, @JsonProperty("name") String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * Returns a string representation of the Source.
   *
   * @return A string containing the source ID and name
   */
  @Override
  public String toString() {
    return "Id: " + id + "\nName: " + name;
  }
}
