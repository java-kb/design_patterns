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
public abstract class State {

    /**
     * The gate is stored in the superclass
     */
    public final Gate gate;

    /**
     * The states know their gate from the beginning
     *
     * @param gate which is managed with the states
     */
    State(Gate gate) {
        this.gate = gate;
    }

    /**
     * Has to be implemented by every subclass
     */
    abstract void open();

    /**
     * Has to be implemented by every subclass
     */
    abstract void close();

    /**
     * Has to be implemented by every subclass
     */
    abstract void lock();

    /**
     * Has to be implemented by every subclass
     */
    abstract void unlock();

    /**
     * It's sufficient to use the class name of the specific state
     *
     * @return the name of the specific state
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
