package me.study.designpatterns.creational.builder.examples.example02_budget.budget.builder;

import java.text.NumberFormat;
import java.util.LinkedList;
import org.xml.sax.Attributes;

import me.study.designpatterns.creational.builder.examples.example02_budget.budget.composite.Composite;
import me.study.designpatterns.creational.builder.examples.example02_budget.budget.composite.Leaf;
import me.study.designpatterns.creational.builder.examples.example02_budget.budget.composite.Node;

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
public class HTMLBuilder extends Builder {

    /**
     * The root of the complete tree structure
     */
    private Composite root;
    /**
     * A string builder is suitable for assembling HTML text
     */
    private final StringBuilder html = new StringBuilder();
    /**
     * List to store all the elements
     */
    private final LinkedList<Composite> nodes = new LinkedList<>();

    /**
     * Creates the HTML out of the TreeModel
     *
     * @return HTML text
     */
    @Override
    public String getProduct() {
        html.append("<html><body><h1 align=\"center\">");
        html.append(root.getDescription());
        html.append("</h1>");
        html.append("<b>" + "Yearly items:</b><br/>");
        for (var i = 0; i < root.getNumberOfChildNodes(); i++) {
            var tempNode = root.getIndex(i);
            if (tempNode.getClass() == Leaf.class) {
                html.append("&#09;");
                var item = (Leaf) tempNode;
                formatLeaf(item);
                html.append("<br/>");
            } else {
                html.append("<p>");
                appendElements(tempNode, 0);
                html.append("</p>");
            }
        }
        html.append("</body></html>");
        return html.toString();
    }

    /**
     * Add sub elements recursively
     *
     * @param node the node to be added
     * @param tab the depth for indentation
     */
    private void appendElements(Node node, int tab) {
        html.append("<br/>");
        for (var i = 0; i < tab; i++)
            html.append("&#09;");
        if (node.getClass() == Leaf.class)
            formatLeaf((Leaf) node);
        else {
            if (tab == 0)
                html.append("<b>");
            html.append(node);
            if (tab == 0)
                html.append("</b>");
        }
        for (var j = 0; j < node.getNumberOfChildNodes(); j++) {
            var childNode = node.getIndex(j);
            appendElements(childNode, tab + 1);
        }
    }

    /**
     * Leafs will get red if applicable
     *
     * @param item to check
     */
    private void formatLeaf(Leaf item) {
        if (!item.expenseIsRequired())
            html.append("<font color=\"#FF0000\">");
        double amount = item.getValue();
        html.append(item
                .getDescription())
                .append(": ")
                .append(NumberFormat.getCurrencyInstance().format(amount));
        if (!item.expenseIsRequired())
            html.append("</font>");
    }

    /**
     * On starting a new element, set the individual properties and save them
     *
     * @param uri
     * @param localName
     * @param name of the XML element
     * @param attributes of the XML elements
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
        else if (this.nodes.size() > 0)
            this.nodes.peekLast().add(node);
        if (node.getClass() != Leaf.class)
            this.nodes.add((Composite) node);
    }

    /**
     * At the end of an XML element, remove the last node from the list
     *
     * @param uri
     * @param localName
     * @param name of the XML element
     */
    @Override
    public void endElement(String uri, String localName, String name) {
        if (!name.equalsIgnoreCase("Item"))
            this.nodes.pollLast();
    }
}
