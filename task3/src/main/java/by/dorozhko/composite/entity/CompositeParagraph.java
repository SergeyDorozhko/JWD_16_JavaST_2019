package by.dorozhko.composite.entity;


public class CompositeParagraph extends Composite {

    /**
     * Make text from components.
     * @return text.
     */
    @Override
    public String getTextPart() {
        StringBuilder result = new StringBuilder();
        for (Component sentences : components) {
            result.append(sentences.getTextPart());
        }
        return result.toString();
    }




}
