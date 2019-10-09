package by.dorozhko.composite.entity;

import java.util.ArrayList;
import java.util.List;

public class CompositeParagraph implements Component {
    private List<Component> components = new ArrayList<>();
    @Override
    public void add(Component c) {
      components.add(c);
    }

    @Override
    public Component getChild(int index) {
        return components.get(index);
    }

    @Override
    public String getTextPart() {
        StringBuilder result = new StringBuilder();
        for(Component sentences: components) {
            result.append(sentences.getTextPart());
        }
        return result.toString();
    }
}
