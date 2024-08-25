package me.study.designpatterns.behavioral.state.examples.example9_food_order_management;

public class DeliveredState implements OrderState {

    @Override
    public void next(Order order) {
        order.setState(null);
    }

    @Override
    public void printStatus() {
        System.out.println("Order Delivered Yeh!");
    }

}