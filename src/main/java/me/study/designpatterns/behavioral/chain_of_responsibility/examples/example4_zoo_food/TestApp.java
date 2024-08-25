package me.study.designpatterns.behavioral.chain_of_responsibility.examples.example4_zoo_food;

import me.study.designpatterns.behavioral.chain_of_responsibility.examples.example4_zoo_food.handler.DogHandler;
import me.study.designpatterns.behavioral.chain_of_responsibility.examples.example4_zoo_food.handler.Handler;
import me.study.designpatterns.behavioral.chain_of_responsibility.examples.example4_zoo_food.handler.MonkeyHandler;
import me.study.designpatterns.behavioral.chain_of_responsibility.examples.example4_zoo_food.handler.SquirrelHandler;

public class TestApp {
    public static void main(String[] args) {
        // The other part of the client code constructs the actual chain.
        Handler monkey = new MonkeyHandler();
        Handler squirrel = new SquirrelHandler();
        Handler dog = new DogHandler();

        monkey.setNext(squirrel).setNext(dog);

        // The client should be able to send a request to any handler, not
        // just the first one in the chain.
        System.out.println("Chain: Monkey > Squirrel > Dog\n");
        Client.clientCode(monkey);
        System.out.println();

        System.out.println("Subchain: Squirrel > Dog\n");
        Client.clientCode(squirrel);
    }
}
