package me.study.designpatterns.structural.composite.examples.example01_budget_tree;

import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;

public class Budget2TestApp {
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
        books.add(new Leaf("Design Patterns", -29.9, true));
        books.add(new Leaf("Trashy novel", -9.99, false));

        print(root, 0);
        var retirement = new Leaf("retirement provisions", -1000.00, true);
        insurances.add(retirement);
        print(root, 0);

        retirement.changeParent(root);
        print(root, 0);

        expenses.changeParent(february);
        print(root, 0);
    }

    /**
     * Printout recursively iterates on the given node and all its children
     *
     * @param node        starting node
     * @param indentation depth for printout
     */
    private static void print(Node node, int indentation) {
        for (var i = 0; i < indentation; i++)
            System.out.print("\t");
        System.out.println(node);

        for (var j = 0; j < node.getNumberChildNodes(); j++) {
            var child = node.getChild(j);
            print(child, indentation + 1);
        }

    }

    static abstract class Node {

        /**
         * Description of a node
         */
        protected final String description;
        /**
         * Link to a parent node
         */
        private Composite parent = null;

        /**
         * Constructor only gets the description
         *
         * @param description of the node
         */
        public Node(String description) {
            this.description = description;
        }

        /**
         * Sets the link to the parent node
         *
         * @param parent link to parent node
         */
        protected void setParent(Composite parent) {
            this.parent = parent;
        }

        /**
         * Moves a node to a new parent
         *
         * @param newParent the new parent node
         */
        public void changeParent(Composite newParent) {
            var parent = this.getParent();
            parent.remove(this);
            newParent.add(this);
        }

        /**
         * Ask for the parent node
         *
         * @return parent node
         */
        protected Composite getParent() {
            return this.parent;
        }

        /**
         * Get the value of the node
         *
         * @return the value
         */
        abstract double getValue();

        /**
         * Calculate the sum of amounts below the node
         */
        public abstract void calculateCache();

        /**
         * Return a child from a given position
         *
         * Standard implementation (for Leaves): Exception
         *
         * Composite implements its own version
         *
         * @param index position of the element
         *
         * @return child at given position
         */
        public Node getChild(int index) {
            throw new RuntimeException("A leaf doesn't have any children!");
        }

        /**
         * Get the number of children
         *
         * Standard implementation (for leaves): 0
         *
         * Composite implements its own version
         *
         * @return number of children
         */
        public int getNumberChildNodes() {
            return 0;
        }
    }

    static class Leaf extends Node {

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
         * A leaf has no cache
         */
        @Override
        public void calculateCache() {
        }

        /**
         * The value is the amount
         *
         * @return amount
         */
        @Override
        double getValue() {
            return amount;
        }

        /**
         * The properties of the leaf within one line of text
         *
         * @return text
         */
        @Override
        public String toString() {
            var prefix = required ? "(!) " : "( ) ";
            return prefix + description + ": " + NumberFormat.getCurrencyInstance().format(amount);
        }
    }

    static class Composite extends Node {

        /**
         * A list of all children (could be nodes or leafs)
         */
        private final List<Node> children = new ArrayList<>();
        /**
         * A sum on the node level
         */
        private double cache = 0.0;
        /**
         * Marks, if the calculated value in the cache is valid or not
         */
        private boolean cacheIsValid = false;

        /**
         * Constructor just gets the description
         *
         * @param description of the node
         */
        public Composite(String description) {
            super(description);
        }

        /**
         * Add a new child to the list (whether leaf or composite).
         *
         * Additionally, the link to the parent node is checked, if the node already
         * has one. Finally, the cache value is set invalid
         *
         * @param child the new child
         */
        public void add(Node child) {
            children.add(child);
            var parent = child.getParent();
            if (parent == null)
                child.setParent(this);
            else
                throw new RuntimeException(child + " already has a parent: " + parent);
            this.setCacheIsValid(false);
        }

        /**
         * Remove a child from a node's list. Set cache invalid
         *
         * @param child
         */
        public void remove(Node child) {
            children.remove(child);
            child.setParent(null);
            this.setCacheIsValid(false);
        }

        /**
         * Set flag according to parameter. If necessary, recalculate
         *
         * @param isValid new value of flag
         */
        void setCacheIsValid(boolean isValid) {
            this.cacheIsValid = isValid;
            if (!isValid) {
                var parent = this.getParent();
                if (parent == null)
                    this.calculateCache();
                else if (this != parent)
                    parent.setCacheIsValid(isValid);
            }
        }

        /**
         * Evaluate the subtotal for all the children. Set cache to valid
         */
        @Override
        public void calculateCache() {
            if (!cacheIsValid) {
                cache = 0;
                for (var node : children) {
                    node.calculateCache();
                    cache += node.getValue();
                }
                this.setCacheIsValid(true);
            }
        }

        /**
         * Return the calculated sum
         *
         * @return the sum
         */
        @Override
        double getValue() {
            return cache;
        }

        /**
         * Returns the child node at the given index
         *
         * @param index to look at
         *
         * @return child node at given index
         */
        @Override
        public Node getChild(int index) {
            return children.get(index);
        }

        /**
         * How many children do we have at this node
         *
         * @return number of children
         */
        @Override
        public int getNumberChildNodes() {
            return children.size();
        }

        /**
         * Printout the description of the node
         *
         * @return the description
         */
        @Override
        public String toString() {
            var tempCache = NumberFormat.getCurrencyInstance().format(cache);
            return description + " (Sum: " + tempCache + ")";
        }
    }
}
