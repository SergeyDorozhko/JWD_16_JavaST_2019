package by.dorozhko.composite.entity;

public class CompositeSentence extends Composite {


    @Override
    public String getTextPart() {
        StringBuilder result = new StringBuilder();
        for (Component lexem: components){
            result.append(lexem.getTextPart());
            result.append(" ");
        }

        return result.toString();
    }

    @Override
    public String getSortedText(String sort) {


        StringBuilder result = new StringBuilder();
        for (Component info : components) {
            result.append(info.getSortedText(sort));
            result.append(" ");

        }
        return result.toString();
    }
}
