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
public interface MediatorIF {

    /**
     * Gets an inquiry from the consumer
     *
     * @param quantity (liters or bottles)
     * @return the best offer
     */
    double getQuote(int quantity);

    /**
     * Register a new supplier
     *
     * @param producer
     */
    void addProducer(ProducerIF producer);

    /**
     * Deregister a supplier
     *
     * @param producer
     */
    void removeProcuder(ProducerIF producer);

    /**
     * Register a new customer
     *
     * @param consumer
     */
    void addConsumer(ConsumerIF consumer);

    /**
     * Deregister a customer
     *
     * @param consumer
     */
    void removeConsumer(ConsumerIF consumer);
}
