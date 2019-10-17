package by.dorozhko.composite.entity;

public class CompositeSentence extends Composite {

    /**
     * Make text from components..
     * @return text.
     */
    @Override
    public String getTextPart() {
        StringBuilder result = new StringBuilder();
        for (Component lexem : components) {
            result.append(lexem.getTextPart());
            result.append(" ");
        }

        return result.toString();
    }


}
