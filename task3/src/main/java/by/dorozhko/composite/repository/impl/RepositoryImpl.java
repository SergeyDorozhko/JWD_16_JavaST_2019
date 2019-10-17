package by.dorozhko.composite.repository.impl;

import by.dorozhko.composite.entity.Component;
import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.repository.Repository;
import by.dorozhko.composite.repository.exception.ExceptionRepository;
import by.dorozhko.composite.repository.storage.TextStorage;

import java.util.List;

public class RepositoryImpl implements Repository {
    TextStorage textStorage = TextStorage.getInstance();

    @Override
    public boolean setText(final Composite compositeText) {
        return textStorage.setText(compositeText);
    }

    @Override
    public List<Component> getCompositeText() throws ExceptionRepository {

        return textStorage.getCompositeText();
    }

    @Override
    public String getText() {
        return textStorage.getText();
    }


}
