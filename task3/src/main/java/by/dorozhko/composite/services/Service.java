package by.dorozhko.composite.services;

public interface Service {
    String createCompositeFromData(String pathToData);
    String viewTextFromRepository();
    String saveTextToData(String pathToData);
    String viewSortedText(String sortBy);

}
