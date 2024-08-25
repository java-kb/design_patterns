package me.study.designpatterns.behavioral.mediator.examples.example1;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
public class Wholesale implements MediatorIF {

    /**
     * List of producers
     */
    private final List<ProducerIF> producers = new ArrayList<>();
    /**
     * List of consumers
     */
    private final List<ConsumerIF> consumers = new ArrayList<>();

    /**
     * Gets called on request by a consumer
     *
     * @param quantity requested
     * @return total price
     */
    @Override
    public double getQuote(int quantity) {
        if (producers.isEmpty())
            throw new IllegalStateException("There's no producer yet!");
        List<Double> quotes = new ArrayList<>();

        for (ProducerIF tempProducer : producers) {
            Double quote = tempProducer.getQuote(quantity);
            quotes.add(quote);
        }

        var price = Collections.min(quotes);
        var strPrice = NumberFormat.getCurrencyInstance().format(price);
        System.out
                .println("The best offer for " + quantity + " bottles is: " + strPrice);
        return price;
    }

    /**
     *
     * @param producer to add to the list
     */
    @Override
    public void addProducer(ProducerIF producer) {
        producers.add(producer);
    }

    /**
     *
     * @param producer to remove from the list
     */
    @Override
    public void removeProcuder(ProducerIF producer) {
        producers.remove(producer);
    }

    /**
     *
     * @param consumer to add to the list
     */
    @Override
    public void addConsumer(ConsumerIF consumer) {
        consumers.add(consumer);
    }

    /**
     *
     * @param consumer to delete from the list
     */
    @Override
    public void removeConsumer(ConsumerIF consumer) {
        consumers.remove(consumer);
    }
}
