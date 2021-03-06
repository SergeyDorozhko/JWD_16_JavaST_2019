package by.dorozhko.composite.services.impl.comporator;

import by.dorozhko.composite.entity.Component;

import java.util.Comparator;

public class TextPartComporator implements Comparator<Component> {
    /**
     * text comparator.
     * @param o1 Component of text.
     * @param o2 componet of text.
     * @return result of compare.
     */
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
