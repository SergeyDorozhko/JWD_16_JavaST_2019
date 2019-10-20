package by.dorozhko.composite.repository.storage;


import by.dorozhko.composite.entity.Component;
import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.repository.exception.ExceptionRepository;

import java.util.ArrayList;
import java.util.List;

public final class TextStorage {

    /**
     * single tone.
     */
    private static final TextStorage INSTANCE = new TextStorage();

    /**
     * storage of composite.
     */
    private Composite text;

    private TextStorage() { }

    /**
     * get link to storage.
     * @return link.
     */
    public static TextStorage getInstance() {
        return INSTANCE;
    }

    /**
     * set composite text to storage.
     * @param newText composite of text.
     * @return result.
     */
    public boolean setText(final Composite newText) {
        text = newText;
        return true;
    }

    /**
     * get list of components.
     * @return list of cmponents.
     * @throws ExceptionRepository throw if storage is empty.
     */
    public List<Component> getCompositeText() throws ExceptionRepository {
        if (text == null) {
            throw new ExceptionRepository("NO TEXT IN MEMORY");
        }
        return new ArrayList<>(text.getComponents());

    }

    /**
     * make text from composite.
     * @return text in string view.
     */
    public String getText() {
        if (text == null) {
            return "NO TEXT IN MEMORY.";
        }
        return text.getTextPart();
    }


}
