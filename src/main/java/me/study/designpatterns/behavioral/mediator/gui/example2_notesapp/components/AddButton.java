package me.study.designpatterns.behavioral.mediator.gui.example2_notesapp.components;

import javax.swing.*;

import me.study.designpatterns.behavioral.mediator.gui.example2_notesapp.mediator.Mediator;
import me.study.designpatterns.behavioral.mediator.gui.example2_notesapp.mediator.Note;

import java.awt.event.ActionEvent;

/**
 * Concrete components don't talk with each other. They have only one
 * communication channel–sending requests to the mediator.
 */
public class AddButton extends JButton implements Component {
    private Mediator mediator;

    public AddButton() {
        super("Add");
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent actionEvent) {
        mediator.addNewNote(new Note());
    }

    @Override
    public String getName() {
        return "AddButton";
    }
}
