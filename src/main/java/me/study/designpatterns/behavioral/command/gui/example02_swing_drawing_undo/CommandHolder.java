package me.study.designpatterns.behavioral.command.gui.example02_swing_drawing_undo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

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
public class CommandHolder implements ListModel {

    /**
     * The list with the command history
     */
    private final List<AbstractDrawCommand> commandList = new ArrayList<>();
    /**
     * The list of the listeners to be informed on changes
     */
    private final List<ListDataListener> listener = new ArrayList<>();

    /**
     * Remove a command from the history and inform the listeners
     *
     * @param temp the command to be removed
     */
    void remove(AbstractDrawCommand temp) {
        commandList.remove(temp);
        listener.forEach((tempListener) -> {
            var event = new ListDataEvent(
                    this,
                    ListDataEvent.CONTENTS_CHANGED,
                    0,
                    commandList.size() - 1);
            tempListener.contentsChanged(event);
        });
    }

    /**
     * Add a command to the history and inform the listeners
     *
     * @param temp the new command
     */
    void add(AbstractDrawCommand temp) {
        commandList.add(temp);
        listener.forEach((tempListener) -> {
            var event = new ListDataEvent(
                    this,
                    ListDataEvent.CONTENTS_CHANGED,
                    0,
                    commandList.size() - 1);
            tempListener.contentsChanged(event);
        });
    }

    /**
     * Select a command from the list
     *
     * @param index the position of the command
     * @return the command at the position
     */
    @Override
    public Object getElementAt(int index) {
        return commandList.get(index);
    }

    /**
     * Retrieve the length of the list
     *
     * @return length of the list
     */
    @Override
    public int getSize() {
        return commandList.size();
    }

    /**
     * Add a new listener
     *
     * @param listener the new listener
     */
    @Override
    public void addListDataListener(ListDataListener listener) {
        this.listener.add(listener);
    }

    /**
     * Remove a listener
     *
     * @param listener to be removed
     */
    @Override
    public void removeListDataListener(ListDataListener listener) {
        this.listener.remove(listener);
    }
}
