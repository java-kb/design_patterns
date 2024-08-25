package me.study.designpatterns.behavioral.chain_of_responsibility.examples.example1_grocery_shopping2;

/**
 *
 * @author Olaf Musch
 *
 *         Design Patterns with Java
 *
 *         An Introduction, Springer Vieweg
 *
 *         chapter "Chain of Responsibility"
 */
public class Bakery extends AbstractMerchant {

    private final String name;

    /**
     *
     * @param name of the baker
     */
    Bakery(String name) {
        this.name = name;
    }

    /**
     * An implementation of a sale. When BREAD is asked, it is sold, otherwise,
     * the task is forwarded
     * A trader now sells the desired goods until either he himself can no longer
     * offer any or
     * the quantity demanded is zero. The question of whether he has any goods at
     * all is decided
     * by a random generator.
     * 
     * @param purchase the requested purchase
     */
    @Override
    public void sell(Purchase purchase) {
        if (purchase.article == Groceries.BREAD)
            while (isAvailable() && purchase.stillDemanding()) {
                // a chance of 50% on having more available
                System.out.println(name + " sells " + purchase.article);
                purchase.sellGood();
            }

        if (purchase.stillDemanding())
            forward(purchase);
    }

    private boolean isAvailable() {
        var zahl = Math.random() * 10;
        return zahl >= 5;
    }
}
