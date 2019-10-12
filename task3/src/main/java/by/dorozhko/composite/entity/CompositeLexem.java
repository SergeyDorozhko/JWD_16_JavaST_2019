package by.dorozhko.composite.entity;

public class CompositeLexem extends Composite {

    @Override
    public String getTextPart() {
        StringBuilder result = new StringBuilder();
        for (Component word : components) {
            result.append(word.getTextPart());
        }
        return result.toString();
    }

    @Override
    public String getSortedText(String sort) {


        StringBuilder result = new StringBuilder();
        for (Component info : components) {
            result.append(info.getSortedText(sort));

        }
        return result.toString();
    }
}
