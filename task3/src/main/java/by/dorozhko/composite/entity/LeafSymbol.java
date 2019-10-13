package by.dorozhko.composite.entity;


public class LeafSymbol implements Component {
    private char symbol;

    public LeafSymbol(final char newSymbol) {
        symbol = newSymbol;
    }

    @Override
    public void add(final Component c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Component getChild(final int index) {
        return null;
    }

    @Override
    public String getTextPart() {
        return "" + symbol;
    }


}
