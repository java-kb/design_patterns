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
public class TestApp {

    /**
     * Create one wholesaler, two customers and three producers then let the
     * customers start requests to the wholesaler
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        // Create a mediator
        var wholesale = new Wholesale();

        // Create two customers
        var jim = new PrivateCustomer("Jim Collins");
        var jack = new PrivateCustomer("Jack Meyers");

        // Create three suppliers
        var vineyard_1 = new ProducerImpl("Vineyard 1");
        var vineyard_2 = new ProducerImpl("Vineyard 2");
        var vineyard_3 = new ProducerImpl("Vineyard 3");

        // Register consumers and producers to the mediator
        jim.register(wholesale);
        jack.register(wholesale);
        vineyard_1.register(wholesale);
        vineyard_2.register(wholesale);
        vineyard_3.register(wholesale);

        // Generate enquiries and obtain quotations
        int quantity = 50;
        double price = jim.requestPrice(quantity);

        System.out.println("");

        quantity = 10;
        price = jack.requestPrice(quantity);
    }
}
