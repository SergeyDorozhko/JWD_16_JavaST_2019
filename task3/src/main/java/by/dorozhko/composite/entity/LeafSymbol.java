package by.dorozhko.composite.entity;


public class LeafSymbol implements Component {
    /**
     * symbol.
     */
    private char symbol;

    /**
     * constructor take symbol.
     *
     * @param newSymbol symbol.
     */
    public LeafSymbol(final char newSymbol) {
        symbol = newSymbol;
    }

    /**
     * do not realised.
     *
     * @param c component.
     */
    @Override
    public void add(final Component c) {
        throw new UnsupportedOperationException();
    }

    /**
     * do not realised.
     *
     * @param index index.
     */
    @Override
    public Component getChild(final int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * get symbol.
     * @return symbol.
     */
    @Override
    public String getTextPart() {
        return "" + symbol;
    }

    /**
     * check is it a needed symbol.
     * @param isSymbol symbol.
     * @return 1 if isSymbol = symbol, else 0.
     */
    @Override
    public int numOfSymbols(final String isSymbol) {

        if (isSymbol.equals("" + symbol)) {
            return 1;
        }
        return 0;
    }


}
