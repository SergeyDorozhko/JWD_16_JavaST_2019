package by.dorozhko.composite.repository;

import by.dorozhko.composite.entity.Component;
import by.dorozhko.composite.entity.CompositeText;

public interface Repository {
    boolean setText(CompositeText composite);
    String getText();

}
