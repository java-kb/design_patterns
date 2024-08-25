package me.study.designpatterns.behavioral.mediator.gui.example1_contacts_app;

import me.study.designpatterns.behavioral.mediator.gui.example1_contacts_app.delegate.GUI;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Mediator"
 */
public class ApplStart {

    /**
     * Creates a new GUI
     *
     * @param args are ignored
     *
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        var frame = new GUI();
    }
}
