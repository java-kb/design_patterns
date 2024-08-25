https://www.learncsdesign.com/learn-the-state-design-pattern/

Imagine that you have a class called Order for ordering Pizzas. The order can be in one of four states: Confirmed, Baked, Dispatched, or Delivered. The next method of the order works a little bit differently in each state. For instance, in Confirmed, it moves the order state to Baked. In Baked, it moves to Dispatched, and in Dispatched, it moves to Delivered.

The Order (Context) class stores a reference to one of the concrete state objects and delegates all state-specific operations to it. The context communicates with the state object using the state interface. There is a setter in the context for passing a new state object.

State-specific methods are defined in the OrderState (State) interface.

ConfirmedState, BakedState, DispatchedState & DeliveredState each implement their own state-specific methods. Concrete State objects may store a reference to the context object. This reference allows the state to fetch any required information from the context object, as well as initiate state transitions.