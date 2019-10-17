package by.dorozhko.composite.entity;

public class CompositeLexem extends Composite {

    /**
     * Make text from components.
     * @return text.
     */
    @Override
    public String getTextPart() {
        StringBuilder result = new StringBuilder();
        for (Component word : components) {
            result.append(word.getTextPart());
        }
        return result.toString();
    }


}
