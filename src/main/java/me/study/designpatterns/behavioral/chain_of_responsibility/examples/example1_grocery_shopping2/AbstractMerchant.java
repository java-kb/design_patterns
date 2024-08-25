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
 * As traders I define the beverages store, the bakery and the farm shop of Old
 * McDonald,
 * where you can get fresh cheese, sausages and eggs. All merchants must have
 * identical
 * methods in addition to their actual tasks (purchasing, manufacturing, etc.):
 * sell and pass on
 * the order if it cannot be served. So you define an abstract superclass that
 * holds a reference
 * to the next merchant in the chain. You call the setNext() method when you
 * want to add
 * another merchant to the chain. Each dealer first checks whether it itself
 * already references
 * a following dealer. If so, it will not store the new merchant, but pass it to
 * the next merchant, which will now check if it is the last one in the chain.
 * If so, it will save the new
 * merchant as the following merchant. In addition, each trader must also
 * provide a sell()
 * method – you declare this in the superclass, but don’t define it yet.
 */
public abstract class AbstractMerchant {

    private AbstractMerchant next;

    /**
     *
     * @param purchase
     */
    public abstract void sell(Purchase purchase);

    /**
     *
     * @param merchant next merchant in the chain
     */
    public void setNext(AbstractMerchant merchant) {
        if (next == null)
            next = merchant;
        else
            next.setNext(merchant);
    }

    /**
     *
     * @param purchase forward a purchase to the next merchant
     */
    protected void forward(Purchase purchase) {
        if (next != null)
            next.sell(purchase);
        else
            /**
             * No merchant is able to sell, so print message
             */
            printMessage(purchase);
    }

    /*
     * If there is no supplier for eggs, for example, the request will not be
     * processed at all. Comment out the line
     * in which the farm shop is created and restart the project. You find that the
     * request is simply
     * ignored. So it makes sense to provide a default behavior
     */
    private void printMessage(Purchase purchase) {
        System.out.println("Unfortunately, no merchant is able to sell " + purchase + " can deliver.");
    }
}
