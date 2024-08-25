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
public abstract class State {

    /**
     * The state object for OPEN
     */
    protected static final State OPEN = new Open();

    /**
     * The state object for CLOSED
     */
    protected static final State CLOSED = new Closed();

    /**
     * The state object for LOCKED
     */
    protected static final State LOCKED = new Locked();

    /**
     * Must be implemented within every state class
     *
     * @return new state as reference where appropriate
     */
    abstract State open();

    /**
     * Must be implemented within every state class
     *
     * @return new state as reference where appropriate
     */
    abstract State close();

    /**
     * Must be implemented within every state class
     *
     * @return new state as reference where appropriate
     */
    abstract State lock();

    /**
     * Must be implemented within every state class
     *
     * @return new state as reference where appropriate
     */
    abstract State unlock();

    /**
     * Print out the class name as state name
     *
     * @return name of current state class
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
