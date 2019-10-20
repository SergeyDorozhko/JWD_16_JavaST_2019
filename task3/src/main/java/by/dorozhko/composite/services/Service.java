package by.dorozhko.composite.services;

public interface Service {
    /**
     * create composite from date service.
     * @param pathToData path to data.
     * @return result.
     */
    String createCompositeFromData(String pathToData);

    /**
     * display text to console.
     * @return text to console.
     */
    String viewTextFromRepository();

    /**
     * save text from repository to data.
     * @param pathToData path to save.
     * @return result.
     */
    String saveTextToData(String pathToData);

    /**
     * view sorted text by query.
     * @param sortBy sort params.
     * @return sorted text by query.
     */
    String viewSortedText(String sortBy);

    /**
     * save sorted by query text.
     * @param sortBy sort query with path save to.
     * @return result.
     */
    String saveSortedText(String sortBy);


}
