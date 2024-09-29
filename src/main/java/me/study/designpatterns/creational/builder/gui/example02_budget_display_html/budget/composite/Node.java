package me.study.designpatterns.creational.builder.gui.example02_budget_display_html.budget.composite;

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
public abstract class Node {

    /**
     * Description of the item
     */
    protected String description;
    /**
     * Link to parent node
     */
    private Composite parent = null;

    /**
     * Constructor, only gets the description
     *
     * @param description
     */
    public Node(String description) {
        this.description = description;
    }

    /**
     * Returns the current description
     *
     * @return text
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description to a new value
     *
     * @param description the new value to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the link to a parent node
     *
     * @param parent
     */
    protected void setParent(Composite parent) {
        this.parent = parent;
    }

    /**
     * Moves the node under a new parent
     *
     * @param newParent
     */
    public void changeParent(Composite newParent) {
        var parent = this.getParent();
        parent.remove(this);
        newParent.add(this);
    }

    /**
     * Returns the link to the parent node
     *
     * @return node
     */
    protected Composite getParent() {
        return this.parent;
    }

    /**
     * Deliver the amount of the node. For leaves, it's the amount directly, for
     * composites, it's the calculated cache value
     *
     * @return amount
     */
    abstract double getValue();

    /**
     * Calculate the sum of a node with all its children
     */
    public abstract void calculateCache();

    /**
     * Get the child at a specific position. For Leaves: throw an exception
     * (there are no sub nodes) Composites implement their own method
     *
     * @param index the specific position
     *
     * @return the child node
     */
    public Node getIndex(int index) {
        throw new RuntimeException("There's no child node for a leaf!");
    }

    /**
     * Return the number of child nodes. For leaves, it's 0. Composites
     * implement their own method
     *
     * @return number of children
     */
    public int getNumberOfChildNodes() {
        return 0;
    }
}
