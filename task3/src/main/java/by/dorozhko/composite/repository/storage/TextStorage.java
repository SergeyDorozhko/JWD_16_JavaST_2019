package by.dorozhko.composite.repository.storage;


import by.dorozhko.composite.entity.Component;
import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.comporator.TextComporator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

//        StringBuilder result = new StringBuilder();
//        for (Component component: sortParagraphBySentenceNumber()) {
//            result.append("\t");
//            result.append(component.getTextPart());
//            result.append("\n");
//        }
        return text.getSortedText(sortBy);
    }


    private List<Component> sortParagraphBySentenceNumber() {

        List<Component> sorted = new ArrayList<>(text.getComponents());

        Collections.sort(sorted, new TextComporator());
        return sorted;
    }
}
