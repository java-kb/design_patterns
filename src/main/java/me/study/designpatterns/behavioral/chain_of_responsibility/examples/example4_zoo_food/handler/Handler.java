package me.study.designpatterns.behavioral.chain_of_responsibility.examples.example4_zoo_food.handler;
    // The Handler interface declares a method for building the chain of
    // handlers. It also declares a method for executing a request.
    public interface Handler
    {
        Handler setNext(Handler handler);
        
        Object handle(Object request);
    }