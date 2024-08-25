package me.study.designpatterns.behavioral.chain_of_responsibility.examples.example4_zoo_food.handler;

public class SquirrelHandler extends AbstractHandler {
    @Override
    public Object handle(Object request) {
        if (request.toString() == "Nut") {
            return "Squirrel: I'll eat the " + request.toString();
        } else {
            return super.handle(request);
        }
    }
}
