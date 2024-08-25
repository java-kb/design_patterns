package me.study.designpatterns.behavioral.mediator.gui.example2_notesapp;

import javax.swing.*;

import me.study.designpatterns.behavioral.mediator.gui.example2_notesapp.components.*;
import me.study.designpatterns.behavioral.mediator.gui.example2_notesapp.mediator.Editor;
import me.study.designpatterns.behavioral.mediator.gui.example2_notesapp.mediator.Mediator;

/**
 * Demo class. Everything comes together here.
 */
public class TestApp {
    public static void main(String[] args) {
        Mediator mediator = new Editor();

        mediator.registerComponent(new Title());
        mediator.registerComponent(new TextBox());
        mediator.registerComponent(new AddButton());
        mediator.registerComponent(new DeleteButton());
        mediator.registerComponent(new SaveButton());
        mediator.registerComponent(new List(new DefaultListModel()));
        mediator.registerComponent(new Filter());

        mediator.createGUI();
    }
}
