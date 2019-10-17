package by.dorozhko.composite.repository;

import by.dorozhko.composite.entity.Component;
import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.repository.exception.ExceptionRepository;

import java.util.List;

public interface Repository {
    boolean setText(Composite composite);
    String getText();
    List<Component> getCompositeText() throws ExceptionRepository;

}
