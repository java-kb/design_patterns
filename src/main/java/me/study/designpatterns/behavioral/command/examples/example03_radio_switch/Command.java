package me.study.designpatterns.behavioral.command.examples.example03_radio_switch;

public interface Command {

    /**
     * The implementation needs to be done in an implementing class
     */
    void execute();

    /**
     * There also will be an undo command
     *
     * @return the undo command
     */
    Command undo();
}
