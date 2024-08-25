package me.study.designpatterns.behavioral.state.conceptual.conceptual1;

import java.util.Scanner;

public class TestApp {
    public static void main(String[] args) {
        // The client code.
        Context context = new Context(new ConcreteStateA());
        context.request1();
        context.request2();
    }
}
// The Context defines the interface of interest to clients. It also
// maintains a reference to an instance of a State subclass, which
// represents the current state of the Context.
class Context {
    // A reference to the current state of the Context.
    private State state = null;

    public Context(State state) {
        this.transitionTo(state);
    }

    // The Context allows changing the State object at runtime.
    public void transitionTo(State state) {
        System.out.println("Context: Transition to " + state.getClass().getSimpleName() + ".");
        this.state = state;
        this.state.setContext(this);
    }

    // The Context delegates part of its behavior to the current State
    // object.
    public void request1() {
        this.state.handle1();
    }

    public void request2() {
        this.state.handle2();
    }
}

// The base State class declares methods that all Concrete State should
// implement and also provides a backreference to the Context object,
// associated with the State. This backreference can be used by States to
// transition the Context to another State.
abstract class State {
    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void handle1();

    public abstract void handle2();
}

// Concrete States implement various behaviors, associated with a state of
// the Context.
class ConcreteStateA extends State {
    @Override
    public void handle1() {
        System.out.println("ConcreteStateA handles request1.");
        System.out.println("ConcreteStateA wants to change the state of the context.");
        this.context.transitionTo(new ConcreteStateB());
    }

    @Override
    public void handle2() {
        System.out.println("ConcreteStateA handles request2.");
    }
}

class ConcreteStateB extends State {
    @Override
    public void handle1() {
        System.out.print("ConcreteStateB handles request1.");
    }

    @Override
    public void handle2() {
        System.out.println("ConcreteStateB handles request2.");
        System.out.println("ConcreteStateB wants to change the state of the context.");
        this.context.transitionTo(new ConcreteStateA());
    }
}


