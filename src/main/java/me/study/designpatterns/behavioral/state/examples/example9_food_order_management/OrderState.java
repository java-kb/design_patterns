package me.study.designpatterns.behavioral.state.examples.example9_food_order_management;

public interface OrderState {
    public void next(Order order);

    public void printStatus();
}
