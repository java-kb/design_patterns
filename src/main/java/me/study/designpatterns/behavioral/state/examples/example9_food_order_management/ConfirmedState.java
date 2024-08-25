package me.study.designpatterns.behavioral.state.examples.example9_food_order_management;

public class ConfirmedState implements OrderState {
    @Override
    public void next(Order order) {
        order.setState(new BakedState());
    }

    @Override
    public void printStatus() {
        System.out.println("Order Confirmed!");
    }
}
