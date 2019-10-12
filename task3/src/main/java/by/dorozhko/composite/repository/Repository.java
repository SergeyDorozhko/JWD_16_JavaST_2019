package by.dorozhko.composite.repository;

import by.dorozhko.composite.entity.Composite;

public interface Repository {
    boolean setText(Composite composite);
    String getText();
    String getSortedText(String sortBy);

}
