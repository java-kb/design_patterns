package me.study.designpatterns.behavioral.chain_of_responsibility.examples.example1_grocery_shopping;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Chain of Responsibility"
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
     * @param article the asked good
     */
    @Override
    public void sell(Groceries article) {
        if (article == Groceries.BEER)
            System.out.println(name + " sells " + article);
        else
            forward(article);
    }
}
