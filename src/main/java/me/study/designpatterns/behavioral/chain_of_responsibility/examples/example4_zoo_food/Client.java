package me.study.designpatterns.behavioral.chain_of_responsibility.examples.example4_zoo_food;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.study.designpatterns.behavioral.chain_of_responsibility.examples.example4_zoo_food.handler.Handler;

public class Client {
    // The client code is usually suited to work with a single handler. In
    // most cases, it is not even aware that the handler is part of a chain.
    public static void clientCode(Handler handler) {
        List<String> foods = new ArrayList<>(Arrays.asList("Nut", "Banana", "Cup of coffee"));
        for (String food : foods) {
            System.out.println("Client: Who wants a " + food + "?");
            String result = (String) handler.handle(food);
            if (result != null) {
                System.out.println("   " + result);
            } else {
                System.out.println("   " + food + " was left untouched.");
            }
        }
    }
}
