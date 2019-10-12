package by.dorozhko.composite.entity;

public class CompositeWord extends Composite {


    @Override
    public String getTextPart() {
        StringBuilder result = new StringBuilder();
        for (Component symbol : components) {
            result.append(symbol.getTextPart());
        }
        return result.toString();
    }

    @Override
    public String getSortedText(String sort) {


        StringBuilder result = new StringBuilder();
        for (Component info : components) {
            result.append(info.getTextPart());

        }
        return result.toString();
    }
}
