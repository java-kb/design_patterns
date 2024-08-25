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
public class FarmShop extends AbstractMerchant {

    private final String name;

    /**
     *
     * @param name of the farm shop
     */
    FarmShop(String name) {
        this.name = name;
    }

    /**
     * An implementation of a sale. When CHEESE, EGGS or SAUSAGES is asked, it
     * is sold, otherwise, the task is forwarded
     *
     * @param purchase the requested purchase
     */
    @Override
    public void sell(Purchase purchase) {
        if (purchase.article == Groceries.EGGS || purchase.article == Groceries.CHEESE
                || purchase.article == Groceries.SAUSAGES)
            while (isAvailable() && purchase.stillDemanding()) {
                System.out.println(name + " sells " + purchase.article);
                purchase.sellGood();
            }

        if (purchase.stillDemanding())
            forward(purchase);
    }

    private boolean isAvailable() {
        // With a 40% chance, the merchant still has something to sell
        var zahl = Math.random() * 10;
        return zahl >= 6;
    }
}
