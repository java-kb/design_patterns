package me.study.designpatterns.creational.factory_method.gui.example01_address_book;

import javax.swing.JPanel;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Factory Method"
 */
public interface EntryPanel {

    /**
     * The data need a method to save them
     */
    void save();

    /**
     * The data also need a method to edit them in a panel
     *
     * @return the editor
     */
    JPanel getEditor();
}
