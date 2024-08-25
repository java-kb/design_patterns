package me.study.designpatterns.behavioral.state.examples.example1_gate_state3;
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
     * Start in OPEN state
     */
    private State state = new Open();

    /**
     * From the current state: open
     */
    public void open() {
        this.state = state.open();
    }

    /**
     * From the current state: close
     */
    public void close() {
        this.state = state.close();
    }

    /**
     * From the current state: lock
     */
    public void lock() {
        this.state = state.lock();
    }

    /**
     * From the current state: unlock
     */
    public void unlock() {
        this.state = state.unlock();
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
