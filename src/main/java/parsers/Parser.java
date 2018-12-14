package parsers;

/**
 * Интерфейс для парсеров. СОдержит методы, соответствующие техзаданию.
 */
public interface Parser {
    long open (String url, int timeoutInMillis);
    boolean checkLinkPresentByHref (String href);
    boolean	checkLinkPresentByName (String linkName);
    boolean	checkPageTitle (String query);
    boolean	checkPageContains (String query);
}
