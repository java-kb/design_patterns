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
/*
 * In the first step, you define which foods are to be supplied by the electronic supply chain.
In the example project CoR_1 this should be bread, cheese, sausage, eggs and beer
 */
public enum Groceries {
    /**
     * First, the list of the constants with their descriptions
     */
    CHEESE("cheese"), SAUSAGES("sausages"), EGGS("eggs"), BEER("beer"), BREAD("bread");
    /**
     * The descriptions of each commodity
     */
    private final String description;

    /**
     * This constructor will be called implicitly
     *
     * @param description of the commodities
     */
    private Groceries(String description) {
        this.description = description;
    }

    /**
     *
     * @return the description
     */
    @Override
    public String toString() {
        return description;
    }
}
