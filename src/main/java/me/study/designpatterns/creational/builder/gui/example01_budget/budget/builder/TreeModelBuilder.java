package me.study.designpatterns.creational.builder.gui.example01_budget.budget.builder;

import java.util.LinkedList;
import javax.swing.tree.TreeModel;
import org.xml.sax.Attributes;

import me.study.designpatterns.creational.builder.gui.example01_budget.budget.composite.Composite;
import me.study.designpatterns.creational.builder.gui.example01_budget.budget.composite.Leaf;
import me.study.designpatterns.creational.builder.gui.example01_budget.budget.composite.Node;
import me.study.designpatterns.creational.builder.gui.example01_budget.budget.tree.MyTreeModel;

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
public class TreeModelBuilder extends Builder {

    /**
     * The root of the complete tree structure
     */
    private Composite root;
    /**
     * List to store all the elements
     */
    private final LinkedList<Node> stack = new LinkedList<>();

    /**
     * On the start of a new element, assign the individual attributes to a new
     * node element and store it
     *
     * @param uri
     * @param localName
     * @param name of the XML elements
     * @param attributes of the XML element
     */
    @Override
    public void startElement(String uri, String localName, String name,
            Attributes attributes) {
        Node node;
        if (name.equalsIgnoreCase("Item")) {
            var tempDescription = attributes.getValue("description");
            var tempAmount = Double.
                    parseDouble(attributes.getValue("amount"));
            var tempRequired = attributes.getValue("required").
                    equalsIgnoreCase("yes");
            node = new Leaf(tempDescription, tempAmount, tempRequired);
        } else {
            var tempDescription = attributes.getValue("description");
            node = new Composite(tempDescription);
        }
        if (root == null)
            root = (Composite) node;
        else {
            var tempNode = (Composite) stack.peekLast();
            tempNode.add(node);
        }
        stack.add(node);
    }

    /**
     * At the end of an XML element, remove the last node from the stack
     *
     * @param uri
     * @param localName
     * @param name of the XML elements
     */
    @Override
    public void endElement(String uri, String localName, String name) {
        stack.pollLast();
    }

    /**
     * Return the structure as a TreeModel
     *
     * @return the complete tree
     */
    @Override
    public TreeModel getProduct() {
        return new MyTreeModel(root);
    }
}
