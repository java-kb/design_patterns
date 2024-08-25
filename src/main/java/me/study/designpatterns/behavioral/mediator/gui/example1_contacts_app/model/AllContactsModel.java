package me.study.designpatterns.behavioral.mediator.gui.example1_contacts_app.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Data model of "all contacts"
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Mediator"
 */
public class AllContactsModel implements ListModel {

    /**
     * Basic list for the model data
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Listeners on the model
     */
    private final List<ListDataListener> listener = new ArrayList<>();

    {
        // Anonymous method, which puts some data into the model
        data.add("Mary-Lou");
        data.add("Shrek");
        data.add("Garfield");
        data.add("Winnie the Poo");
    }

    /**
     * Deletes a specific entry from the list
     *
     * @param toRemove the entry to be removed
     */
    public void removeData(String toRemove) {
        data.remove(toRemove);
        fireChangeEvent();
    }

    /**
     * Adds an entry to the list
     *
     * @param toAdd entry to be added
     */
    public void addData(String toAdd) {
        data.add(toAdd);
        fireChangeEvent();
    }

    /**
     * Private mehtod; informs every listener on a change of the data
     */
    private void fireChangeEvent() {
        var event = new ListDataEvent(
                this,
                ListDataEvent.CONTENTS_CHANGED,
                0,
                data.size() - 1);

        listener.forEach((tempListener) -> {
            tempListener.contentsChanged(event);
        });
    }

    /**
     *
     * @return size of list
     */
    @Override
    public int getSize() {
        return data.size();
    }

    /**
     *
     * @param index of element to look for
     *
     * @return the element at the given index
     */
    @Override
    public Object getElementAt(int index) {
        return data.get(index);
    }

    /**
     *
     * @param l a new listener
     */
    @Override
    public void addListDataListener(ListDataListener l) {
        listener.add(l);
    }

    /**
     *
     * @param l a listener to be removed
     */
    @Override
    public void removeListDataListener(ListDataListener l) {
        listener.remove(l);
    }
}
