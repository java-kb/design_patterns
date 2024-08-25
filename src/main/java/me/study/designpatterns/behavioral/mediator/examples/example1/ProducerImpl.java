package me.study.designpatterns.behavioral.mediator.examples.example1;
import java.text.NumberFormat;

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
public class ProducerImpl implements ProducerIF {

    /**
     * Reference to the mediator
     */
    private MediatorIF mediator;
    /**
     * Name of the producer
     */
    private final String name;

    /**
     *
     * @param name of the producer
     */
    public ProducerImpl(String name) {
        this.name = name;
    }

    /**
     * Producer registers itself to the mediator
     *
     * @param mediator the mediator
     */
    @Override
    public void register(MediatorIF mediator) {
        mediator.addProducer(this);
        this.mediator = mediator;
    }

    /**
     * Gets called, when a mediator initiates a request
     *
     * @param quantity required
     * @return total price
     */
    @Override
    public double getQuote(int quantity) {
        var discountFactor = 1.0;
        if (quantity > 100)
            discountFactor = 0.7;
        else if (quantity > 50)
            discountFactor = 0.8;
        else
            discountFactor = 0.9;
        double price = Math.random() * 9 + 1;
        price *= discountFactor;
        var strPrice = NumberFormat.getCurrencyInstance().format(price);
        System.out
                .println("Producer " + name + " asks " + strPrice + " per bottle.");
        return price * quantity;
    }
}
