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
public class TestApp {

    /**
     * Specifiy one store of every typeand chain them together then request some
     * goods at the chain
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        // Create the stores
        var farmshop = new FarmShop("Old McDonald's");
        var bakery = new Bakery("Ben's Bakery");
        var liquorstore = new LiquorStore("BeeBee's Beer & Wine");

        // build up the chain, beginning with the bakery
        bakery.setNext(liquorstore);
        liquorstore.setNext(farmshop);

        // Request a good, always at the first element of the chain
        bakery.sell(new Purchase(Groceries.CHEESE, 3));
        farmshop.sell(new Purchase(Groceries.EGGS, 4));
        bakery.sell(new Purchase(Groceries.SAUSAGES, 1));
        bakery.sell(new Purchase(Groceries.BREAD, 2));
        liquorstore.sell(new Purchase(Groceries.BEER, 1));
    }
}
