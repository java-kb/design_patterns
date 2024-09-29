package me.study.designpatterns.creational.builder.gui.example01_budget.budget.tree;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.TreeCellEditor;

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
public class MyTreeCellEditor implements TreeCellEditor {

    /**
     * A list of all listeners to this editor
     */
    private final List<CellEditorListener> listeners = new ArrayList<>();
    /**
     * The panel for editing
     */
    private final JPanel pnlEditor = new JPanel();
    /**
     * A note to the user
     */
    private final JLabel lblNote = new JLabel("Please enter a new description: ");
    /**
     * The entry field
     */
    private final JTextField edtInput = new JTextField(15);

    /**
     * Constructor. Builds the editor and assigns a confirmation action
     */
    public MyTreeCellEditor() {
        pnlEditor.add(lblNote);
        pnlEditor.add(edtInput);
        edtInput.addActionListener((ActionEvent e)
                -> {
            stopCellEditing();
        });
    }

    /**
     * To edit a field, end other editing activities and start the editor at the
     * current node
     *
     * @param tree the structure
     * @param value the current node
     * @param isSelected true, if the node is selected
     * @param expanded true, if the sub nodes are expanded in the view
     * @param leaf true, if the current node is a leaf
     * @param row current row number in view
     *
     * @return the editor
     */
    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value,
            boolean isSelected,
            boolean expanded, boolean leaf,
            int row) {
        // To avoid possible conflicts, end other editing activities
        cancelCellEditing();
        var knoten = (Node) value;
        edtInput.setText(knoten.getDescription());
        return pnlEditor;
    }

    /**
     * The current text of the input field
     *
     * @return text
     */
    @Override
    public Object getCellEditorValue() {
        return edtInput.getText();
    }

    /**
     * Check, if the node is editable
     *
     * @param anEvent the triggering Event
     *
     * @return true, if the node is editable
     */
    @Override
    public boolean isCellEditable(EventObject anEvent) {
        // The user needs to "triple click" the item in order to edit the description
        if (anEvent instanceof MouseEvent mouseEvent)
            return mouseEvent.getClickCount() >= 3;
        else
            return false;
    }

    /**
     * A node always gets selected, if clicked for editing
     *
     * @param anEvent the triggering event
     *
     * @return always true in this case
     */
    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    /**
     * Inform listeners, that editing is ended and the new value is accepted
     *
     * @return always true in this case
     */
    @Override
    public boolean stopCellEditing() {
        var event = new ChangeEvent(this);

        listeners.forEach((listener) -> {
            listener.editingStopped(event);
        });

        return true;
    }

    /**
     * Inform listeners, that editing is aborted/canceled
     */
    @Override
    public void cancelCellEditing() {
        var event = new ChangeEvent(this);

        listeners.forEach((listener) -> {
            listener.editingCanceled(event);
        });
    }

    /**
     * Add a new listener
     *
     * @param l the new listener
     */
    @Override
    public void addCellEditorListener(CellEditorListener l) {
        listeners.add(l);
    }

    /**
     * Remove a listener from the list
     *
     * @param l the listener to be removed
     */
    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        listeners.remove(l);
    }
}
