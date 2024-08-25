package me.study.designpatterns.behavioral.state.examples.example9_food_order_management;

public class BakedState  implements OrderState {

	@Override
	public void next(Order order) {
		order.setState(new DispatchedState());
	}

	@Override
	public void printStatus() {
		System.out.println("Order Baked!");
	}

}