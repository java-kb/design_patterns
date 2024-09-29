package me.study.designpatterns.creational.builder.examples.example02_budget.budget.tree;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

import me.study.designpatterns.creational.builder.gui.example01_budget.budget.composite.Leaf;

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
public class MyTreeCellRenderer implements TreeCellRenderer {

    /**
     * The label to render the node with
     */
    private final JLabel lblDisplay = new JLabel();

    /**
     * Constructor. Set label opacity to true
     */
    public MyTreeCellRenderer() {
        lblDisplay.setOpaque(true);
    }

    /**
     * Render the cell. Not required expenses will be shown in red
     *
     * @param tree the tree structure
     * @param value the node to be rendered
     * @param selected true, if the node is currently selected
     * @param expanded true, if the node is expanded to its sub nodes
     * @param leaf true, if the node is a leaf
     * @param row number in the current view
     * @param hasFocus true, if the node has the focus
     *
     * @return label for display
     */
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean selected,
            boolean expanded, boolean leaf,
            int row, boolean hasFocus) {
        var displayText = value.toString();
        lblDisplay.setBackground(Color.WHITE);

        if (value.getClass() == Leaf.class) {
            var tempLeaf = (Leaf) value;
            displayText = tempLeaf.getDescription();
            if (!tempLeaf.expenseIsRequired())
                lblDisplay.setBackground(Color.RED);
        }

        lblDisplay.setText(displayText);
        return lblDisplay;
    }
}
