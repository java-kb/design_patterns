package me.study.designpatterns.behavioral.mediator.examples.example1;
/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Mediator"
 */
public class PrivateCustomer implements ConsumerIF {

    /**
     * Name of the customer
     */
    private final String name;
    /**
     * Reference to the mediator
     */
    private MediatorIF mediator;

    /**
     *
     * @param name of consumer
     */
    public PrivateCustomer(String name) {
        this.name = name;
    }

    /**
     *
     * @param mediator to register to
     */
    @Override
    public void register(MediatorIF mediator) {
        mediator.addConsumer(this);
        this.mediator = mediator;
    }

    /**
     *
     * @param quantity to be requested at the mediator
     * @return cheapest offer
     */
    @Override
    public double requestPrice(int quantity) {
        System.out
                .println(name + " requests " + quantity
                        + " bottles of wine.");
        var totalPrice = mediator.getQuote(quantity);
        return totalPrice;
    }
}
