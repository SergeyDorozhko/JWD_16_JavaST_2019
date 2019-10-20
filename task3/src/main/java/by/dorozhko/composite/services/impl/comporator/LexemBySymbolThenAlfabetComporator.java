package by.dorozhko.composite.services.impl.comporator;

import by.dorozhko.composite.entity.Component;

import java.util.Comparator;

public class LexemBySymbolThenAlfabetComporator
        implements Comparator<Component> {

    /**
     * symbol to sort by.
     */
    private String symbol;

    /**
     * constructor take symbol to sort  by.
     *
     * @param sortBySymbol symbol.
     */
    public LexemBySymbolThenAlfabetComporator(final String sortBySymbol) {
        symbol = sortBySymbol;
    }

    /**
     * compare method.
     *
     * @param o1 Component.
     * @param o2 component.
     * @return result of compare.
     */
    @Override
    public int compare(final Component o1, final Component o2) {
        int result = 0;


        if (o1.numOfSymbols(symbol) < o2.numOfSymbols(symbol)) {
            result = 1;
        } else if (o1.numOfSymbols(symbol) > o2.numOfSymbols(symbol)) {
            result = -1;
        } else {
            result = (o1.getTextPart().compareToIgnoreCase(o2.getTextPart())
                    > 0 ? 1 : (o1.getTextPart()
                    .compareToIgnoreCase(o2.getTextPart()) < 0 ? -1 : 0));

        }

        return result;
    }
}
