package me.study.designpatterns.creational.abstract_factory.conceptual.conceptual1;

public class TestApp {
    public static void main(String[] args) {
        new Client().run();
    }
}

interface IAbstractFactory {
    IAbstractProductA createProductA();

    IAbstractProductB createProductB();
}

class ConcreteFactory1 implements IAbstractFactory {
    public IAbstractProductA createProductA() {
        return new ConcreteProductA1();
    }

    public IAbstractProductB createProductB() {
        return new ConcreteProductB1();
    }
}

class ConcreteFactory2 implements IAbstractFactory {
    public IAbstractProductA createProductA() {
        return new ConcreteProductA2();
    }

    public IAbstractProductB createProductB() {
        return new ConcreteProductB2();
    }
}

interface IAbstractProductA {
    String usefulFunctionA();
}

class ConcreteProductA1 implements IAbstractProductA {
    public String usefulFunctionA() {
        return "The result of the product A1.";
    }
}

class ConcreteProductA2 implements IAbstractProductA {
    public String usefulFunctionA() {
        return "The result of the product A2.";
    }
}

interface IAbstractProductB {
    String usefulFunctionB();

    String anotherUsefulFunctionB(IAbstractProductA collaborator);
}

class ConcreteProductB1 implements IAbstractProductB {
    public String usefulFunctionB() {
        return "The result of the product B1.";
    }

    public String anotherUsefulFunctionB(IAbstractProductA collaborator) {
        String result = collaborator.usefulFunctionA();
        return "The result of the B1 collaborating with the (" + result + ")";
    }
}

class ConcreteProductB2 implements IAbstractProductB {
    public String usefulFunctionB() {
        return "The result of the product B2.";
    }

    public String anotherUsefulFunctionB(IAbstractProductA collaborator) {
        String result = collaborator.usefulFunctionA();
        return "The result of the B2 collaborating with the (" + result + ")";
    }
}

class Client {
    public void run() {
        System.out.println("Client: Testing client code with the first factory type...");
        clientMethod(new ConcreteFactory1());
        System.out.println();

        System.out.println("Client: Testing the same client code with the second factory type...");
        clientMethod(new ConcreteFactory2());
    }

    public void clientMethod(IAbstractFactory factory) {
        IAbstractProductA productA = factory.createProductA();
        IAbstractProductB productB = factory.createProductB();

        System.out.println(productB.usefulFunctionB());
        System.out.println(productB.anotherUsefulFunctionB(productA));
    }
}
