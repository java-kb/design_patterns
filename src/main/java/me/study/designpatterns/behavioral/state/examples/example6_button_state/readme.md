# State design pattern - an FSM with two states and one event

https://sourcemaking.com/design_patterns/state/java/4

- Create a "wrapper" class that models the state machine
- The wrapper class maintains a "current" state object
- All client requests are simply delegated to the current state object and the wrapper object's "this" pointer is passed
- Create a state base class that makes the concrete states interchangeable
- The State base class specifies any useful "default" behavior
- The State derived classes only override the messages they need to o/r
- The State methods will change the "current" state in the "wrapper"