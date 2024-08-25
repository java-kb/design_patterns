package me.study.designpatterns.behavioral.state.examples.example9_food_order_management;

import java.util.ArrayList;
import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        Order order = new Order();
        order.printStatus();
        order.nextState();
        order.printStatus();
        order.nextState();
        order.printStatus();
        order.nextState();
        order.printStatus();
    }
}
