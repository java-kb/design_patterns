package me.study.designpatterns.creational.factory_method.gui.example01_address_book;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

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
public class MyFocusListener implements FocusListener {

    /**
     * On getting focus, all the data in the text field is selected
     *
     * @param e the focus event
     */
    @Override
    public void focusGained(FocusEvent e) {
        var source = e.getSource();
        if (source.getClass() == JTextField.class) {
            var entryField = (JTextField) source;
            entryField.selectAll();
        }
    }

    /**
     * On losing focus, nothing happens
     *
     * @param e the focus event
     */
    @Override
    public void focusLost(FocusEvent e) {
        //
    }
}
