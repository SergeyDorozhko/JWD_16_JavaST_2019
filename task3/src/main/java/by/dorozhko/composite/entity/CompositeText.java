package by.dorozhko.composite.entity;


import java.util.ArrayList;
import java.util.List;

public class CompositeText implements Component {

    private List<Component> components = new ArrayList<Component>();

    public CompositeText() {
    }

    @Override
    public void add(Component c) {
        components.add(c);
    }

    @Override
    public Component getChild(int index) {
        return components.get(index);
    }

    @Override
    public String getTextPart(){
        StringBuilder result = new StringBuilder();
        for(Component info: components) {
            result.append("\t");
            result.append(info.getTextPart());
            result.append("\n");

        }
        return result.toString();
    }
}
