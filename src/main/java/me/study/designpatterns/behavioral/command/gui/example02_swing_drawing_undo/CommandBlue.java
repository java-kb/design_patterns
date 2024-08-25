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
public class CommandBlue extends AbstractDrawCommand {

    /**
     * Use the constructor of the super class. Color Blue is set. Distances and
     * inclination is fixed.
     *
     * @see AbstractDrawCommand
     *
     * @param undoCommands list of undo commands
     * @param redoCommands list of redo commands
     */
    public CommandBlue(CommandHolder undoCommands, CommandHolder redoCommands) {
        super(10, -20, Color.BLUE, undoCommands, redoCommands);
    }

    /**
     * Blue lines start at -1 (right border)
     *
     * @return position of first blue line
     */
    @Override
    protected int getFirstX() {
        return -1;
    }

    /**
     * Distance to next blue line
     *
     * @return distance to next blue line
     */
    @Override
    protected int getNextX() {
        return -distance;
    }

    /**
     * Log output
     *
     * @return name of command
     */
    @Override
    public String toString() {
        return "Blue Command " + commandCount;
    }
}
