package by.dorozhko.composite.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Composite implements Component {
    List<Component> components = new ArrayList<>();

    public List<Component> getComponents() {
        return components;
    }

    @Override
    public void add(final Component c) {
        components.add(c);
    }

    @Override
    public Component getChild(final int index) {
        return components.get(index);
    }

    @Override
    public int getNumberOfChilds() {
        return components.size();
    }

}
