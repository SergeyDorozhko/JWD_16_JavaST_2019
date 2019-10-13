package by.dorozhko.composite.repository.storage;


import by.dorozhko.composite.entity.Composite;

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

    public String getText() {
        if (text == null) {
            return "NO TEXT IN MEMORY.";
        }
        return text.getTextPart();
    }

    public String getSortedText(final String sortBy) {
        if (text == null) {
            return "NO TEXT IN MEMORY.";
        }


        return text.getSortedText(sortBy);
    }
}
