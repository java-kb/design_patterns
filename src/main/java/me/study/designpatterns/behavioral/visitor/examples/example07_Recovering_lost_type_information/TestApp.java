package me.study.designpatterns.behavioral.visitor.examples.example07_Recovering_lost_type_information;

import java.util.ArrayList;
import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        Component[] nodes = new Component[3];
        nodes[0] = new Composite(1);
        nodes[1] = new Composite(2);
        nodes[2] = new Composite(3);

        AddVisitor addVisitor = new AddVisitor();
        nodes[0].accept(addVisitor, nodes[1]);
        nodes[0].accept(addVisitor, nodes[2]);
        nodes[0].accept(addVisitor, new Primitive(4));
        nodes[1].accept(addVisitor, new Primitive(5));
        nodes[1].accept(addVisitor, new Primitive(6));
        nodes[2].accept(addVisitor, new Primitive(7));

        for (Component node : nodes) {
            node.traverse();
            System.out.println();
        }
    }
}

interface Visitor {
    void visit(Primitive primitive, Component component);

    void visit(Composite composite, Component component);
}

abstract class Component {
    protected int value;

    public Component(int val) {
        value = val;
    }

    public void traverse() {
        System.out.print(value + " ");
    }

    public abstract void accept(Visitor visitor, Component component);
}

class Primitive extends Component {

    public Primitive(int val) {
        super(val);
    }

    @Override
    public void accept(Visitor visitor, Component component) {
        visitor.visit(this, component);
    }

}

class Composite extends Component {
    private List<Component> children = new ArrayList<>();

    public Composite(int val) {
        super(val);
    }

    public void add(Component element) {
        children.add(element);
    }

    @Override
    public void accept(Visitor visitor, Component component) {
        visitor.visit(this, component);
    }

    @Override
    public void traverse() {
        System.out.print(value + " ");
        for (Component child : children) {
            child.traverse();
        }
    }
}

class AddVisitor implements Visitor {

    @Override
    public void visit(Primitive primitive, Component component) {
        // Does not make sense
    }

    @Override
    public void visit(Composite composite, Component component) {
        composite.add(component);
    }
}
