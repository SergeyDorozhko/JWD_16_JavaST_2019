package by.dorozhko.composite.entity;

public interface Component {
    void add(Component c);

    Component getChild(int index);

    default  int getNumberOfChilds() {
        throw new UnsupportedOperationException();
    }

    String getTextPart();

    default String getSortedText(String sort) {
        throw new UnsupportedOperationException();
    }





}
