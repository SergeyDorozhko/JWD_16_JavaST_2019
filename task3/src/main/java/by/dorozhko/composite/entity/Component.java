package by.dorozhko.composite.entity;

public interface Component {
    /**
     * Method adding component to composite.
     *
     * @param c component.
     */
    void add(Component c);

    /**
     * get components child.
     *
     * @param index child index.
     * @return child component.
     */
    Component getChild(int index);

    /**
     * Default realization for leaf.
     *
     * @return number of childes.
     */
    default int getNumberOfChilds() {
        throw new UnsupportedOperationException();
    }

    /**
     * Makes text from all childes.
     *
     * @return text.
     */
    String getTextPart();

    /**
     * calculate number of symbols in tet part.
     *
     * @param symbol symbol to calculate.
     * @return number of matches.
     */
    int numOfSymbols(String symbol);


}
