package by.dorozhko.composite.repository;

import by.dorozhko.composite.entity.Component;
import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeText;

public interface Repository {
    boolean setText(Composite composite);
    String getText();
    String getSortedText(String sortBy);

}
