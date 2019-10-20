package by.dorozhko.composite.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Composite implements Component {
    /**
     * list of text components.
     */
    List<Component> components = new ArrayList<>();

    /**
     * get components.
     *
     * @return list of components.
     */
    public List<Component> getComponents() {
        return components;
    }

    /**
     * Adding component to list of components.
     *
     * @param c component.
     */
    @Override
    public void add(final Component c) {
        components.add(c);
    }

    /**
     * Getting child of component.
     *
     * @param index child index.
     * @return child of component.
     */
    @Override
    public Component getChild(final int index) {
        return components.get(index);
    }

    /**
     * getting number of nearest childes.
     *
     * @return number.
     */
    @Override
    public int getNumberOfChilds() {
        return components.size();
    }

    /**
     * Calculate symbols in text part.
     * @param symbol symbol to calculate.
     * @return number of matches.
     */
    public int numOfSymbols(final String symbol) {
        int result = 0;
        for (Component component : components) {
            result += component.numOfSymbols(symbol);
        }
        return result;
    }
}
