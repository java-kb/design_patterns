package me.study.designpatterns.behavioral.chain_of_responsibility.examples.example1_grocery_shopping;

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
     * @param article to be sold
     */
    public abstract void sell(Groceries article);

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
     * @param article forward the task to the next merchant
     */
    protected void forward(Groceries article) {
        if (next != null)
            next.sell(article);
            else
            printMessage(article);
    }

    private void printMessage(Groceries purchase) {
        System.out.println("Unfortunately, no merchant is able to sell " + purchase + "can deliver.");
    }
}
