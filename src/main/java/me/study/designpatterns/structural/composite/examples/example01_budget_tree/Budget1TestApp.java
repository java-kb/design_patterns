package me.study.designpatterns.structural.composite.examples.example01_budget_tree;

import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;

public class Budget1TestApp {
    public static void main(String[] args) {
        final var root = new Composite("Budget book");
        final var january = new Composite("January");
        final var february = new Composite("February");
        final var income = new Composite("Income");
        final var expenses = new Composite("Expenses");
        final var insurances = new Composite("Insurances");
        final var books = new Composite("Books");

        january.add(income);
        january.add(expenses);

        root.add(january);
        root.add(february);

        income.add(new Leaf("Main job", 1900.00, true));
        income.add(new Leaf("Side job", 200.00, true));

        expenses.add(insurances);
        expenses.add(books);
        expenses.add(new Leaf("Rent", -600.00, true));
        insurances.add(new Leaf("Car", -50.00, true));
        insurances.add(new Leaf("ADB", -100.00, true));
        books.add(new Leaf("Design patterns", -29.9, true));
        books.add(new Leaf("Trashy novel", -9.99, false));

        // Printout whole tree
        root.print(0);

        // Just printout a part
        System.out.println("\n\nJust the expenses: ");
        expenses.print(0);
    }
}

abstract class Node {

    /**
     * Description of a node
     */
    protected final String description;

    /**
     * Constructor only gets the description
     *
     * @param description of the node
     */
    public Node(String description) {
        this.description = description;
    }

    /**
     * The print method always needs to be implemented
     *
     * @param indentation of the "subtree"
     */
    public abstract void print(int indentation);
}

class Leaf extends Node {

    /**
     * A marker, if the expenses (or the income) really is necessary
     */
    private final boolean required;
    /**
     * The amount (income positive, expenses negative)
     */
    private double amount = 0.0;

    /**
     * Constructor sets the properties
     *
     * @param description of the node (forwarded to the super class)
     * @param amount      of income/expense
     * @param required    the necessity
     */
    public Leaf(String description, double amount, boolean required) {
        super(description);
        this.amount = amount;
        this.required = required;
    }

    /**
     * Printout the leaf. First indentation, then put out the properties
     *
     * @param indentation number of tab steps
     */
    @Override
    public void print(int indentation) {
        for (var i = 0; i < indentation; i++)
            System.out.print("\t");
        System.out.println(this);
    }

    /**
     * The properties of the leaf within one line of text
     *
     * @return text
     */
    @Override
    public String toString() {
        var prefix = required ? "(!) " : "( ) ";
        var tempAmount = NumberFormat.getCurrencyInstance().format(amount);
        return prefix + description + ": " + tempAmount;
    }
}

class Composite extends Node {

    /**
     * A list of all children (could be nodes or leafs)
     *
     */
    private final List<Node> children = new ArrayList<>();

    /**
     * Constructor just gets the description
     *
     * @param description of the node
     */
    public Composite(String description) {
        super(description);
    }

    /**
     * Add a new child to the list (whether leaf or composite)
     *
     * @param child the new child
     */
    public void add(Node child) {
        children.add(child);
    }

    /**
     * Returns the child node at the given index
     *
     * @param index to look at
     *
     * @return child node at given index
     */
    public Node getChild(int index) {
        return children.get(index);
    }

    /**
     * How many children do we have at this node
     *
     * @return number of children
     */
    public int getNumberChildNodes() {
        return children.size();
    }

    /**
     * First print the appropriate indentation level, then the content of the
     * children with increased indentation
     *
     * @param indentation level
     */
    @Override
    public void print(int indentation) {
        for (var i = 0; i < indentation; i++)
            System.out.print("\t");
        System.out.println(this);
        children.forEach((var node) -> {
            node.print(indentation + 1);
        });
    }

    /**
     * Printout the description of the node
     *
     * @return the description
     */
    @Override
    public String toString() {
        return description;
    }
}