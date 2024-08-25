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
public class LiquorStore extends AbstractMerchant {

    private final String name;

    /**
     *
     * @param name of the store
     */
    LiquorStore(String name) {
        this.name = name;
    }

    /**
     * An implementation of a sale. When BEER is asked, it is sold, otherwise,
     * the task is forwarded
     *
     * @param purchase the requested purchase
     */
    @Override
    public void sell(Purchase purchase) {
        if (purchase.article == Groceries.BEER)
            while (isAvailable() && purchase.stillDemanding()) {
                System.out.println(name + " sells " + purchase.article);
                purchase.sellGood();
            }

        if (purchase.stillDemanding())
            forward(purchase);
    }

    private boolean isAvailable() {
        // With a 60% chance, there's something left to sell.
        var zahl = Math.random() * 10;
        return zahl >= 4;
    }
}
