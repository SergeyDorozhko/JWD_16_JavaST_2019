package by.dorozhko.composite.repository.impl;

import by.dorozhko.composite.entity.Component;
import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeText;
import by.dorozhko.composite.repository.Repository;
import by.dorozhko.composite.repository.storage.TextStorage;

public class RepositoryImpl implements Repository {
    @Override
    public boolean setText(Composite compositeText) {
        TextStorage textStorage = TextStorage.getInstance();
        return textStorage.setText(compositeText);
    }

    @Override
    public String getText() {
        TextStorage textStorage = TextStorage.getInstance();
        return textStorage.getText();
    }

    @Override
    public String getSortedText(String sortBy) {
        TextStorage textStorage = TextStorage.getInstance();
        return textStorage.getSortedText(sortBy);
    }
}
