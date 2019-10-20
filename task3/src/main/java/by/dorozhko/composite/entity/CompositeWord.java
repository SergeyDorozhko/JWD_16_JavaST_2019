package by.dorozhko.composite.entity;

public class CompositeWord extends Composite {

    /**
     * Make text from components.
     * @return text.
     */
    @Override
    public String getTextPart() {
        StringBuilder result = new StringBuilder();
        for (Component symbol : components) {
            result.append(symbol.getTextPart());
        }
        return result.toString();
    }


}
