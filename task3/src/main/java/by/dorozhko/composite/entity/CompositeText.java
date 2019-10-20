package by.dorozhko.composite.entity;


public class CompositeText extends Composite {

    /**
     * Make text from components.
     * @return text.
     */
    @Override
    public String getTextPart() {
        StringBuilder result = new StringBuilder();
        for (Component info : components) {
            result.append("\t");
            result.append(info.getTextPart());
            result.append("\n");

        }
        return result.toString();
    }


}
