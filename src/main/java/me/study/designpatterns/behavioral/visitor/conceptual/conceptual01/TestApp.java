package me.study.designpatterns.behavioral.visitor.conceptual.conceptual01;

import java.util.ArrayList;
import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        List<Component> components = new ArrayList<>();
        components.add(new ConcreteComponentA());
        components.add(new ConcreteComponentB());

        System.out.println("The client code works with all visitors via the base Visitor interface:");
        Visitor visitor1 = new ConcreteVisitor1();
        Client.ClientCode(components, visitor1);

        System.out.println();

        System.out.println("It allows the same client code to work with different types of visitors:");
        Visitor visitor2 = new ConcreteVisitor2();
        Client.ClientCode(components, visitor2);
    }
}

// The Component interface declares an `accept` method that should take the
// base visitor interface as an argument.
interface Component {
    void Accept(Visitor visitor);
}

// Each Concrete Component must implement the `Accept` method in such a way
// that it calls the visitor's method corresponding to the component's
// class.
class ConcreteComponentA implements Component {
    // Note that we're calling `VisitConcreteComponentA`, which matches the
    // current class name. This way we let the visitor know the class of the
    // component it works with.
    @Override
    public void Accept(Visitor visitor) {
        visitor.VisitConcreteComponentA(this);
    }

    // Concrete Components may have special methods that don't exist in
    // their base class or interface. The Visitor is still able to use these
    // methods since it's aware of the component's concrete class.
    public String ExclusiveMethodOfConcreteComponentA() {
        return "A";
    }
}

class ConcreteComponentB implements Component {
    // Same here: VisitConcreteComponentB => ConcreteComponentB
    @Override
    public void Accept(Visitor visitor) {
        visitor.VisitConcreteComponentB(this);
    }

    public String SpecialMethodOfConcreteComponentB() {
        return "B";
    }
}

// The Visitor Interface declares a set of visiting methods that correspond
// to component classes. The signature of a visiting method allows the
// visitor to identify the exact class of the component that it's dealing
// with.
interface Visitor {
    void VisitConcreteComponentA(ConcreteComponentA element);

    void VisitConcreteComponentB(ConcreteComponentB element);
}

// Concrete Visitors implement several versions of the same algorithm, which
// can work with all concrete component classes.
//
// You can experience the biggest benefit of the Visitor pattern when using
// it with a complex object structure, such as a Composite tree. In this
// case, it might be helpful to store some intermediate state of the
// algorithm while executing visitor's methods over various objects of the
// structure.
class ConcreteVisitor1 implements Visitor {
    @Override
    public void VisitConcreteComponentA(ConcreteComponentA element) {
        System.out.println(element.ExclusiveMethodOfConcreteComponentA() + " + ConcreteVisitor1");
    }

    @Override
    public void VisitConcreteComponentB(ConcreteComponentB element) {
        System.out.println(element.SpecialMethodOfConcreteComponentB() + " + ConcreteVisitor1");
    }
}

class ConcreteVisitor2 implements Visitor {
    @Override
    public void VisitConcreteComponentA(ConcreteComponentA element) {
        System.out.println(element.ExclusiveMethodOfConcreteComponentA() + " + ConcreteVisitor2");
    }

    @Override
    public void VisitConcreteComponentB(ConcreteComponentB element) {
        System.out.println(element.SpecialMethodOfConcreteComponentB() + " + ConcreteVisitor2");
    }
}

class Client {
    // The client code can run visitor operations over any set of elements
    // without figuring out their concrete classes. The accept operation
    // directs a call to the appropriate operation in the visitor object.
    public static void ClientCode(List<Component> components, Visitor visitor) {
        for (Component component : components) {
            component.Accept(visitor);
        }
    }
}
