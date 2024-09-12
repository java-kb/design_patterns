package me.study.designpatterns.creational.factory_method.conceptual.conceptual01;

public class TestApp {
    public static void main(String[] args) {
        new Client().Main();
    }
}

abstract class Creator {
    public abstract Product FactoryMethod();

    public String SomeOperation() {
        Product product = FactoryMethod();
        String result = "Creator: The same creator's code has just worked with " + product.Operation();
        return result;
    }
}

class ConcreteCreator1 extends Creator {
    public Product FactoryMethod() {
        return new ConcreteProduct1();
    }
}

class ConcreteCreator2 extends Creator {
    public Product FactoryMethod() {
        return new ConcreteProduct2();
    }
}

interface Product {
    String Operation();
}

class ConcreteProduct1 implements Product {
    public String Operation() {
        return "{Result of ConcreteProduct1}";
    }
}

class ConcreteProduct2 implements Product {
    public String Operation() {
        return "{Result of ConcreteProduct2}";
    }
}

class Client {
    public void Main() {
        System.out.println("App: Launched with the ConcreteCreator1.");
        ClientCode(new ConcreteCreator1());

        System.out.println("");

        System.out.println("App: Launched with the ConcreteCreator2.");
        ClientCode(new ConcreteCreator2());
    }

    public void ClientCode(Creator creator) {
        System.out.println(
                "Client: I'm not aware of the creator's class, but it still works.\n" + creator.SomeOperation());
    }
}
