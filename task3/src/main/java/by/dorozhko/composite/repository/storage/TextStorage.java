package by.dorozhko.composite.repository.storage;


import by.dorozhko.composite.entity.Composite;

public class TextStorage {

    private static final TextStorage instance = new TextStorage();

    private Composite text;

    private TextStorage() {
    }

    public static TextStorage getInstance() {
        return instance;
    }

    public boolean setText(Composite newText) {
        text = newText;
        return true;
    }

    public String getText() {
        if (text == null) {
            return "NO TEXT IN MEMORY.";
        }
        return text.getTextPart();
    }

    public String getSortedText(String sortBy) {
        if (text == null) {
            return "NO TEXT IN MEMORY.";
        }


        return text.getSortedText(sortBy);
    }
}
