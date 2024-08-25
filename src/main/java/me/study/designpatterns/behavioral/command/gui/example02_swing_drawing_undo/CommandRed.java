package me.study.designpatterns.behavioral.command.gui.example02_swing_drawing_undo;
import java.awt.Color;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Command"
 */
public class CommandRed extends AbstractDrawCommand {

    /**
     * Use the constructor of the super class. Color Red is set. Distances and
     * inclination is fixed.
     *
     * @see AbstractDrawCommand
     *
     * @param undoCommands list of undo commands
     * @param redoCommands list of redo commands
     */
    public CommandRed(CommandHolder undoCommands, CommandHolder redoCommands) {
        super(10, 20, Color.RED, undoCommands, redoCommands);
    }

    /**
     * Red lines start at 0 (left border)
     *
     * @return position of the first red line
     */
    @Override
    protected int getFirstX() {
        return 0;
    }

    /**
     * Distance to next red line
     *
     * @return distance to next red line
     */
    @Override
    protected int getNextX() {
        return distance;
    }

    /**
     * Log output
     *
     * @return name of command
     */
    @Override
    public String toString() {
        return "Red-Command " + commandCount;
    }
}
