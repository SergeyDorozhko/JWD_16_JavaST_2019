package by.dorozhko.composite.repository;

import by.dorozhko.composite.entity.Component;
import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.repository.exception.ExceptionRepository;

import java.util.List;

public interface Repository {
    /**
     * set composite text to storage.
     * @param composite composite text.
     * @return result.
     */
    boolean setText(Composite composite);

    /**
     * get text from repository.
     * @return text.
     */
    String getText();

    /**
     * get composite of text from repository.
     * @return composite.
     * @throws ExceptionRepository cover repository exception.
     */
    List<Component> getCompositeText() throws ExceptionRepository;

}
