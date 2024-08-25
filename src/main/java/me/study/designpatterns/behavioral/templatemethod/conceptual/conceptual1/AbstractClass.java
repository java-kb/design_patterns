package me.study.designpatterns.behavioral.templatemethod.conceptual.conceptual1;

// The Abstract Class defines a template method that contains a skeleton of
// some algorithm, composed of calls to (usually) abstract primitive
// operations.
//
// Concrete subclasses should implement these operations, but leave the
// template method itself intact.
abstract class AbstractClass {
    // The template method defines the skeleton of an algorithm.
    public void TemplateMethod() {
        this.BaseOperation1();
        this.RequiredOperations1();
        this.BaseOperation2();
        this.Hook1();
        this.RequiredOperation2();
        this.BaseOperation3();
        this.Hook2();
    }

    // These operations already have implementations.
    protected void BaseOperation1() {
        System.out.println("AbstractClass says: I am doing the bulk of the work");
    }

    protected void BaseOperation2() {
        System.out.println("AbstractClass says: But I let subclasses override some operations");
    }

    protected void BaseOperation3() {
        System.out.println("AbstractClass says: But I am doing the bulk of the work anyway");
    }

    // These operations have to be implemented in subclasses.
    protected abstract void RequiredOperations1();

    protected abstract void RequiredOperation2();

    // These are "hooks." Subclasses may override them, but it's not
    // mandatory since the hooks already have default (but empty)
    // implementation. Hooks provide additional extension points in some
    // crucial places of the algorithm.
    protected void Hook1() {
    };

    protected void Hook2() {
    };
}

// Concrete classes have to implement all abstract operations of the base
// class. They can also override some operations with a default
// implementation.
class ConcreteClass1 extends AbstractClass {
    protected void RequiredOperations1() {
        System.out.println("ConcreteClass1 says: Implemented Operation1");
    }

    protected void RequiredOperation2() {
        System.out.println("ConcreteClass1 says: Implemented Operation2");
    }
}

// Usually, concrete classes override only a fraction of base class'
// operations.
class ConcreteClass2 extends AbstractClass {
    protected void RequiredOperations1() {
        System.out.println("ConcreteClass2 says: Implemented Operation1");
    }

    protected void RequiredOperation2() {
        System.out.println("ConcreteClass2 says: Implemented Operation2");
    }

    protected void Hook1() {
        System.out.println("ConcreteClass2 says: Overridden Hook1");
    }
}

class Client {
    // The client code calls the template method to execute the algorithm.
    // Client code does not have to know the concrete class of an object it
    // works with, as long as it works with objects through the interface of
    // their base class.
    public static void ClientCode(AbstractClass abstractClass) {
        // ...
        abstractClass.TemplateMethod();
        // ...
    }
}

class Program {
    public static void main(String[] args) {
        System.out.println("Same client code can work with different subclasses:");

        Client.ClientCode(new ConcreteClass1());

        System.out.print("\n");

        System.out.println("Same client code can work with different subclasses:");
        Client.ClientCode(new ConcreteClass2());
    }
}
