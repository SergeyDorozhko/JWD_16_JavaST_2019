package by.dorozhko.composite.entity;

public interface Component {
    void add(Component c);
    Component getChild(int index);
    String getTextPart();
}
