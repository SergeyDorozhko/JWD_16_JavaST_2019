package by.dorozhko.composite.entity;


public class LeafSymbol implements Component {
    private char symbol;

    public LeafSymbol(char newSymbol) {
        symbol = newSymbol;
    }

    @Override
    public void add(Component c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Component getChild(int index) {
        return null;
    }

    @Override
    public String getTextPart() {
        return "" + symbol;
    }


}
