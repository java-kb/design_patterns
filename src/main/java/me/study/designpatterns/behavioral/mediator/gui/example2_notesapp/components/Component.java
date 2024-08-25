package me.study.designpatterns.behavioral.mediator.gui.example2_notesapp.components;

import me.study.designpatterns.behavioral.mediator.gui.example2_notesapp.mediator.Mediator;

/**
 * Common component interface.
 */
public interface Component {
    void setMediator(Mediator mediator);
    String getName();
}
