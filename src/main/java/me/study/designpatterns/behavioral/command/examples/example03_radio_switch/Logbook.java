package me.study.designpatterns.behavioral.command.examples.example03_radio_switch;

import java.util.ArrayList;
import java.util.List;


public class Logbook {

    /**
     * List of all applied commands (for possible future undo)
     */
    private final List<Command> history = new ArrayList<>();

    /**
     * Append executed command to history
     *
     * @param command
     */
    public void execute(Command command) {
        history.add(command);
        command.execute();
    }

    /**
     * Only undo something, if there's command left to undo
     */
    public void undo() {
        var size = history.size();
        if (size > 0) {
            // Pull last command out of history
            var command = history.remove(size - 1);
            // Get undo command for it
            var undoCommand = command.undo();
            System.out.println("\tundo " + command + " with " + undoCommand);
            // Execute undo (and do NOT put it into history)
            undoCommand.execute();
        }
    }
}
