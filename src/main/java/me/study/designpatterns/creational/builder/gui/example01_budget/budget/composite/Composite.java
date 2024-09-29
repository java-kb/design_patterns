package me.study.designpatterns.creational.builder.gui.example01_budget.budget.composite;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

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
public class Composite extends Node {

    /**
     * A list of all children, which also need to be nodes
     */
    private final List<Node> children = new ArrayList<>();
    /**
     * The sum on this node
     */
    private double cache = 0.0;
    /**
     * True, if the cached value still is valid
     */
    private boolean cacheIsValid = false;

    /**
     * Constructor gets the description
     *
     * @param description of the node element
     */
    public Composite(String description) {
        super(description);
    }

    /**
     * Add a new sub node (composite or leaf). The parent node is checked and
     * set if necessary and the cache is set invalid to initiate a recalculation
     *
     * @param child
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
     * Remove a child of a node. Cache gets invalid
     *
     * @param child
     */
    public void remove(Node child) {
        children.remove(child);
        child.setParent(null);
        this.setCacheIsValid(false);
    }

    /**
     * Set cache valid, initiate recalculation if necessary
     *
     * @param isValid new value of the flag
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
     * Calculate the sum of all sub nodes to this node. Cache gets valid
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
     * @return the cache value
     */
    @Override
    double getValue() {
        return cache;
    }

    /**
     * Get the child node at the given position of the list
     *
     * @param index
     *
     * @return the child node
     */
    @Override
    public Node getIndex(int index) {
        return children.get(index);
    }

    /**
     * Count the number of child nodes
     *
     * @return number
     */
    @Override
    public int getNumberOfChildNodes() {
        return children.size();
    }

    /**
     * Print out the description and the sum on this node
     *
     * @return text
     */
    @Override
    public String toString() {
        var tempCache = NumberFormat.getCurrencyInstance().format(cache);
        return description + " (Sum: " + tempCache + ")";
    }
}
