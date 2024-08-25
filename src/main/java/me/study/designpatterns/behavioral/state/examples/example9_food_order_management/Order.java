package me.study.designpatterns.behavioral.state.examples.example9_food_order_management;

public class Order {
    private OrderState state = new ConfirmedState();

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void nextState() {
        state.next(this);
    }

    public void printStatus() {
        state.printStatus();
    }
}
