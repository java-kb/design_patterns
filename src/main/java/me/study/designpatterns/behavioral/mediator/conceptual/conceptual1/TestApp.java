package me.study.designpatterns.behavioral.mediator.conceptual.conceptual1;

import java.util.Objects;


public class TestApp {
    public static void main(String[] args) {
        // The client code.
        Component1 component1 = new Component1();
        Component2 component2 = new Component2();
        new ConcreteMediator(component1, component2);

        System.out.println("Client triggers operation A.");
        component1.doA();

        System.out.println();

        System.out.println("Client triggers operation D.");
        component2.doD();
    }
}
// The Mediator interface declares a method used by components to notify the
// mediator about various events. The Mediator may react to these events and
// pass the execution to other components.
interface Mediator {
    void notify(Object sender, String event);
}

// Concrete Mediators implement cooperative behavior by coordinating several
// components.
class ConcreteMediator implements Mediator {
    private final Component1 component1;
    private final Component2 component2;

    public ConcreteMediator(Component1 component1, Component2 component2) {
        this.component1 = component1;
        this.component1.setMediator(this);
        this.component2 = component2;
        this.component2.setMediator(this);
    }

    @Override
    public void notify(Object sender, String event) {
        if (Objects.equals(event, "A")) {
            System.out.println("Mediator reacts on A and triggers following operations:");
            this.component2.doC();
        }
        if (Objects.equals(event, "D")) {
            System.out.println("Mediator reacts on D and triggers following operations:");
            this.component1.doB();
            this.component2.doC();
        }
    }
}

// The Base Component provides the basic functionality of storing a
// mediator's instance inside component objects.
abstract class BaseComponent {
    protected Mediator mediator;

    public BaseComponent(Mediator mediator) {
        this.mediator = mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}

// Concrete Components implement various functionality. They don't depend on
// other components. They also don't depend on any concrete mediator
// classes.
class Component1 extends BaseComponent {
    public Component1() {
        super(null);
    }

    public void doA() {
        System.out.println("Component 1 does A.");
        this.mediator.notify(this, "A");
    }

    public void doB() {
        System.out.println("Component 1 does B.");
        this.mediator.notify(this, "B");
    }
}

class Component2 extends BaseComponent {
    public Component2() {
        super(null);
    }

    public void doC() {
        System.out.println("Component 2 does C.");
        this.mediator.notify(this, "C");
    }

    public void doD() {
        System.out.println("Component 2 does D.");
        this.mediator.notify(this, "D");
    }
}




