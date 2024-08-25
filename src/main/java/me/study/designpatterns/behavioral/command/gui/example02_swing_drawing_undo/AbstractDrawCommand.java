package me.study.designpatterns.behavioral.command.gui.example02_swing_drawing_undo;
import java.awt.Color;
import java.awt.Graphics;

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
public abstract class AbstractDrawCommand {

    /**
     * The command knows its undo and redo lists
     */
    private final CommandHolder undoCommands;
    private final CommandHolder redoCommands;
    protected int commandCount = 0;

    /**
     * The distance to the next line
     */
    protected final int distance;

    /**
     * The inclination of the line
     */
    protected final int inclination;

    /**
     * The color of the line
     */
    protected final Color color;

    /**
     * The constructor gets the properties and the reference to the command
     * history
     *
     * @param distance to the next line
     * @param inclination of the line
     * @param color of the line
     * @param undoCommands list of undos
     * @param redoCommands list of redos
     */
    public AbstractDrawCommand(int distance, int inclination,
            Color color,
            CommandHolder undoCommands,
            CommandHolder redoCommands) {
        this.distance = distance;
        this.inclination = inclination;
        this.color = color;
        this.undoCommands = undoCommands;
        this.redoCommands = redoCommands;
    }

    /**
     * For getting the start of the first line
     *
     * @return X-position of the first line
     */
    protected abstract int getFirstX();

    /**
     * For getting the start of the next line
     *
     * @return X-postion of the next line
     */
    protected abstract int getNextX();

    /**
     * Execute the command: increase counter and add to undo list
     *
     */
    public void execute() {
        commandCount++;
        undoCommands.add(this);
    }

    /**
     * Redo consists of execution and deletion from the redo list
     *
     */
    public void redo() {
        execute();
        redoCommands.remove(this);
    }

    /**
     * Undo is a deletion from the undo list and addition to the redo list
     *
     */
    public void undo() {
        undoCommands.remove(this);
        redoCommands.add(this);
        commandCount--;
    }

    /**
     * The drawing itself is implicitly called by the GUI All lines of the color
     * are redrawn
     *
     * @param graphics context
     * @param width of the canvas
     * @param height of the canvas
     */
    public void draw(Graphics graphics, int width, int height) {
        graphics.setColor(color);
        var tempX = getFirstX();
        if (tempX < 0)
            tempX = width;

        for (int i = 0; i < commandCount; i++) {
            graphics.drawLine(tempX, 0, tempX + inclination, height);
            tempX += getNextX();
        }
    }
}
