package by.dorozhko.composite.repository.impl;

import by.dorozhko.composite.entity.Component;
import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.repository.Repository;
import by.dorozhko.composite.repository.exception.ExceptionRepository;
import by.dorozhko.composite.repository.storage.TextStorage;

import java.util.List;

public class RepositoryImpl implements Repository {

    /**
     * link to storage.
     */
    private TextStorage textStorage = TextStorage.getInstance();

    /**
     * set composite text to storage.
     * @param compositeText composite of text.
     * @return result.
     */
    @Override
    public boolean setText(final Composite compositeText) {
        return textStorage.setText(compositeText);
    }

    /**
     * get composite of text.
     * @return list of composite.
     * @throws ExceptionRepository cover repository exception.
     */
    @Override
    public List<Component> getCompositeText() throws ExceptionRepository {

        return textStorage.getCompositeText();
    }

    /**
     * get text.
     * @return text from composite.
     */
    @Override
    public String getText() {
        return textStorage.getText();
    }


}
