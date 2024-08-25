package me.study.designpatterns.behavioral.state.examples.example1_gate_state2;
/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "State"
 */
public class Gate {

    /**
     * Now with own state objects
     */
    private final State openState = new Open(this);
    private final State closedState = new Closed(this);
    private final State lockedState = new Locked(this);
    /**
     * again we start in open state
     */
    private State currentState = openState;

    /**
     * Set OPEN state
     */
    public void setOpenState() {
        currentState = openState;
    }

    /**
     * Set CLOSED state
     */
    public void setClosedState() {
        currentState = closedState;
    }

    /**
     * Set LOCKED state
     */
    public void setLockedState() {
        currentState = lockedState;
    }

    /**
     * Request state change
     */
    public void open() {
        currentState.open();
    }

    /**
     * Request state change
     */
    public void close() {
        currentState.close();
    }

    /**
     * Request state change
     */
    public void lock() {
        currentState.lock();
    }

    /**
     * Request state change
     */
    public void unlock() {
        currentState.unlock();
    }

    /**
     * Print out the current state
     *
     * @return text
     */
    @Override
    public String toString() {
        return "Gate is " + currentState + ".";
    }
}
