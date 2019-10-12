package by.dorozhko.composite.entity;


import by.dorozhko.composite.entity.comporator.TextComporator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompositeText extends Composite {


    @Override
    public String getTextPart() {
        StringBuilder result = new StringBuilder();
        for (Component info : components) {
            result.append("\t");
            result.append(info.getTextPart());
            result.append("\n");

        }
        return result.toString();
    }

    @Override
    public String getSortedText(String sort) {
        List<Component> sortedList = new ArrayList<>(components);

        if (getClass().getSimpleName().contains(sort)) {
            Collections.sort(sortedList, new TextComporator());
        }

        StringBuilder result = new StringBuilder();
        for (Component info : sortedList) {
            result.append("\t");
            result.append(info.getSortedText(sort));
            result.append("\n");

        }
        return result.toString();
    }
}
