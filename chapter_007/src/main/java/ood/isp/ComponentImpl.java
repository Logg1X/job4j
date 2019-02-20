package ood.isp;

import java.util.ArrayList;
import java.util.List;

public class ComponentImpl implements Component {

    private String name;
    private List<Component> elements = new ArrayList<>();

    public ComponentImpl(String name) {
        this.name = name;
    }

    @Override
    public List<Component> getList() {
        return this.elements;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

