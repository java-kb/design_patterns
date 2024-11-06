package me.study.designpatterns.behavioral.memento.gui.example02_graph_editor.memento;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Memento"
 *
 * @param <E> Datentyp der Elemente auf dem Stack
 */
public final class Stack<E> {

    /**
     * The list to hold everything of type E
     */
    private final List<E> internalList = new ArrayList<>();

    /**
     * Check, if the list is empty
     *
     * @return true, if empty
     */
    public boolean empty() {
        return internalList.isEmpty();
    }

    /**
     * Put a new object on top
     *
     * @param e the new object
     *
     * @return the new object
     */
    public synchronized E push(E e) {
        internalList.add(e);
        return e;
    }

    /**
     * Remove the newest object from the list
     *
     * @return the object
     */
    public synchronized E pop() {
        var element = peek();
        internalList.remove(element);
        return element;
    }

    /**
     * Determine the last object in the list
     *
     * @return the last object
     */
    public synchronized E peek() {
        var lastIndex = internalList.size() - 1;
        if (lastIndex < 0)
            throw new EmptyStackException();
        return internalList.get(lastIndex);
    }

    /**
     * Look for a specific object in the list
     *
     * @param o the object to look for
     *
     * @return the index position of the object in the list, -1 if not found
     */
    public synchronized int search(Object o) {
        var result = -1;
        var i = internalList.lastIndexOf(0);
        if (i >= 0)
            result = internalList.size() - i;
        return result;
    }
}
