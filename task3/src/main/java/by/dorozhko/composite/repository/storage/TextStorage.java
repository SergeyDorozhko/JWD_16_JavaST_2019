package by.dorozhko.composite.repository.storage;


import by.dorozhko.composite.entity.Component;
import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.repository.exception.ExceptionRepository;

import java.util.ArrayList;
import java.util.List;

public final class TextStorage {

    private static final TextStorage INSTANCE = new TextStorage();

    private Composite text;

    private TextStorage() {
    }

    public static TextStorage getInstance() {
        return INSTANCE;
    }

    public boolean setText(final Composite newText) {
        text = newText;
        return true;
    }

    public List<Component> getCompositeText() throws ExceptionRepository {
        if (text == null) {
            throw new ExceptionRepository("NO TEXT IN MEMORY");
        }
        return new ArrayList<>(text.getComponents());

    }

    public String getText() {
        if (text == null) {
            return "NO TEXT IN MEMORY.";
        }
        return text.getTextPart();
    }


}
