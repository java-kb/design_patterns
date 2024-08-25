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
public interface ConsumerIF {

    /**
     * Inquires the price for a certain amount of wine at the mediator the
     * answer will be the price
     *
     * @param quantity of wine
     * @return price for this quantity
     */
    double requestPrice(int quantity);

    /**
     * Registers at the mediator and gets back a reference to it
     *
     * @param mediator
     */
    void register(MediatorIF mediator);
}
