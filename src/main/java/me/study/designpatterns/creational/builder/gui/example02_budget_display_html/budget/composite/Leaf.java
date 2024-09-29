package me.study.designpatterns.creational.builder.gui.example02_budget_display_html.budget.composite;

import java.text.NumberFormat;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Builder"
 */
public class Leaf extends Node {

    /**
     * A flag if the expense (or income) is required or "just for fun"
     */
    private final boolean required;
    /**
     * The amount of the item (income is positive, expenses are negative)
     */
    private double amount = 0.0;

    /**
     * Constructor gets the properties of the leaf
     *
     * @param description
     * @param amount
     * @param required
     */
    public Leaf(String description, double amount, boolean required) {
        super(description);
        this.amount = amount;
        this.required = required;
    }

    /**
     * Tells you, if the expenses were really necessary or not
     *
     * @return true if expense is required
     */
    public boolean expenseIsRequired() {
        return this.required;
    }

    /**
     * Print out the description and amount of the item
     *
     * @return text
     */
    @Override
    public String getDescription() {
        return description + ": " + NumberFormat.getCurrencyInstance().
                format(amount);
    }

    /**
     * A leaf doesn't have a cache
     */
    @Override
    public void calculateCache() {
    }

    /**
     * The value is just the amount
     *
     * @return the value
     */
    @Override
    public double getValue() {
        return amount;
    }

    /**
     * Print out the properties
     *
     * @return text
     */
    @Override
    public String toString() {
        var prefix = required ? "(+) " : "(-) ";
        return prefix + this.getDescription();
    }
}
