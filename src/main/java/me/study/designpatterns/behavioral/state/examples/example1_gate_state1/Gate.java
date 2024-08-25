package me.study.designpatterns.behavioral.state.examples.example1_gate_state1;
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

    private State state = new Open(this);

    /**
     * set a new state for the gate
     *
     * @param state the new state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Change state to Open if possible
     */
    public void open() {
        state.open();
    }

    /**
     * Change state to Closed if possible
     */
    public void close() {
        state.close();
    }

    /**
     * Change state to Locked if possible
     */
    public void lock() {
        state.lock();
    }

    /**
     * Change state to unlocked (Closed) if possible
     */
    public void unlock() {
        state.unlock();
    }

    /**
     * Print out the current state
     *
     * @return text
     */
    @Override
    public String toString() {
        return "The gate is " + state + ".";
    }
}
