package me.study.designpatterns.behavioral.chain_of_responsibility.examples.example4_zoo_food.handler;

public abstract class AbstractHandler implements Handler {
    private Handler _nextHandler;

    @Override
    public Handler setNext(Handler handler) {
        this._nextHandler = handler;

        // Returning a handler from here will let us link handlers in a
        // convenient way like this:
        // monkey.SetNext(squirrel).SetNext(dog);
        return handler;
    }

    @Override
    public Object handle(Object request) {
        if (this._nextHandler != null) {
            return this._nextHandler.handle(request);
        } else {
            return null;
        }
    }
}
