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
/*
 * The
 * Purchasing class is an auxiliary class. It has two attributes: the goods
 * themselves, and the quantity. When a merchant sells the
 * goods, he
 * calls the method sell(); the method stillDemanding() returns whether the
 * customer wants to buy more of the same article.
 */
public class Purchase {

    // The parts of a purchase: article and quantity
    protected final Groceries article;
    private int quantity;

    Purchase(Groceries article, int quantity) {
        this.article = article;
        this.quantity = quantity;
    }

    /**
     * Sell one article at a time, reduce remaining quantity of this purchase
     */
    public void sellGood() {
        quantity--;
    }

    /**
     * If some quantity is left over, there's still a demand
     *
     * @return if more is needed
     */
    public boolean stillDemanding() {
        return quantity > 0;
    }

    @Override
    public String toString() {
        return article.toString();
    }
}
