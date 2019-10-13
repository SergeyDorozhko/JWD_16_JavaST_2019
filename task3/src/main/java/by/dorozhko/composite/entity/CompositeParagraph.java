package by.dorozhko.composite.entity;


public class CompositeParagraph extends Composite {

    @Override
    public String getTextPart() {
        StringBuilder result = new StringBuilder();
        for (Component sentences : components) {
            result.append(sentences.getTextPart());
        }
        return result.toString();
    }

    @Override
    public String getSortedText(final String sort) {


        StringBuilder result = new StringBuilder();
        for (Component info : components) {
            result.append(info.getSortedText(sort));

        }
        return result.toString();
    }


}
