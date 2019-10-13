package by.dorozhko.composite.entity.comporator;

import by.dorozhko.composite.entity.Component;

import java.util.Comparator;

public class TextComporator implements Comparator<Component> {
    @Override
    public int compare(final Component o1, final Component o2) {

        int result;

        if (o1.getNumberOfChilds() > o2.getNumberOfChilds()) {
            result = 1;
        } else if (o1.getNumberOfChilds() < o2.getNumberOfChilds()) {
            result = -1;
        } else {
            result = 0;
        }

        return result;
    }
}
