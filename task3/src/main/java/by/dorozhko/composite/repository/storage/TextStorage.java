package by.dorozhko.composite.repository.storage;


import by.dorozhko.composite.entity.CompositeText;

public class TextStorage {

    private static final TextStorage instance = new TextStorage();

    private CompositeText text;

    private TextStorage() {
    }

    public static TextStorage getInstance() {
        return instance;
    }

    public boolean setText(CompositeText newText) {
        text = newText;
        return true;
    }

    public String getText(){
        if(text == null) {
            return "NO TEXT IN MEMORY.";
        }
        return text.getTextPart();
    }

}
