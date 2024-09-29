package me.study.designpatterns.creational.builder.gui.example02_budget_display_html.budget.tree;

import java.util.LinkedList;
import java.util.List;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import me.study.designpatterns.creational.builder.gui.example01_budget.budget.composite.Composite;
import me.study.designpatterns.creational.builder.gui.example01_budget.budget.composite.Leaf;
import me.study.designpatterns.creational.builder.gui.example01_budget.budget.composite.Node;

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
public class MyTreeModel implements TreeModel {

    /**
     * All listeners to the model
     */
    private final List<TreeModelListener> listeners = new LinkedList<>();
    /**
     * The root of the tree
     */
    private final Composite root;

    /**
     * Constructor. Just gets the root
     *
     * @param root
     */
    public MyTreeModel(Composite root) {
        this.root = root;
    }

    /**
     * Return the link to the root node
     *
     * @return the root
     */
    @Override
    public Composite getRoot() {
        return root;
    }

    /**
     * Get the child of a node at a specific position
     *
     * @param parent the node, whose child is to be looked for
     * @param index the position of the child node
     *
     * @return the child node
     */
    @Override
    public Object getChild(Object parent, int index) {
        var composite = (Node) parent;
        return composite.getIndex(index);
    }

    /**
     * Get the number of Children of a specific node
     *
     * @param parent the node
     *
     * @return number of child nodes
     */
    @Override
    public int getChildCount(Object parent) {
        var composite = (Node) parent;
        return composite.getNumberOfChildNodes();
    }

    /**
     * Checks, if a node is a leaf
     *
     * @param node
     *
     * @return true, if the given node is a leaf
     */
    @Override
    public boolean isLeaf(Object node) {
        return node instanceof Leaf;
    }

    /**
     * Set the new description after editing
     *
     * @param path for recalculation
     * @param newValue the new description
     */
    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        var lastPath = (Node) path.getLastPathComponent();
        lastPath.setDescription(newValue.toString());
    }

    /**
     * Look for a specific child of a node and return its index
     *
     * @param parent
     * @param child
     *
     * @return index position, -1 if not found
     */
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        var tempParent = (Node) parent;
        for (var i = 0; i < tempParent.getNumberOfChildNodes(); i++) {
            var tempChild = tempParent.getIndex(i);
            if (tempChild == child) {
                System.out.println(i + " " + tempChild);
                return i;
            }
        }
        return -1;
    }

    /**
     * Add a new listener
     *
     * @param l
     */
    @Override
    public void addTreeModelListener(TreeModelListener l) {
        listeners.add(l);
    }

    /**
     * Remove a given listener
     *
     * @param l
     */
    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        listeners.remove(l);
    }

    /**
     * Print out the description
     *
     * @return text
     */
    @Override
    public String toString() {
        return root.toString() + this.getChildCount(root);
    }
}
