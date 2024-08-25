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
public interface ProducerIF {

    /**
     * inquire the price for the requested quantity at the producer
     *
     * @param quantity requested
     * @return total price
     */
    double getQuote(int quantity);

    /**
     * register a producer at the mediator
     *
     * @param mediator reference
     */
    void register(MediatorIF mediator);
}
