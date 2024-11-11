package project3;

import java.util.List;

public class Driver {
  private static final String[] DEMO_FILES = {
          "project_2/inputs/bad.json",
          "project_2/inputs/simple.json",
          "project_2/inputs/newsapi.json"
  };

  private final NewsParser parser;
  private final NewsAPIClient apiClient;

  public Driver() {
    this.parser = new NewsParser();
    this.apiClient = new NewsAPIClient(parser.getLogger());
  }

  private void processArticles(List<Article> articles) {
    if (articles.isEmpty()) {
      System.out.println("No articles found.");
    } else {
      articles.forEach(System.out::println);
    }
  }

  private void demoLocalFiles() {
    for (String file : DEMO_FILES) {
      System.out.println("\nParsing " + file + ":\n\n");
      processArticles(parser.parseFile(file));
    }
  }

  private void demoLiveHeadlines() {
    System.out.println("\nFetching live headlines from NewsAPI:\n\n");
    processArticles(apiClient.getTopHeadlines());
  }

  /**
   * Runs all demonstrations.
   */
  public void run() {
    demoLocalFiles();
    demoLiveHeadlines();
  }

  public static void main(String[] args) {
    new Driver().run();
  }
}