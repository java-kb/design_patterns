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
public class Open extends State {

    /**
     * Open is not possible
     *
     * @return keep state
     */
    @Override
    public State open() {
        System.out
                .println("The gate is already open an cannot be opened again.");
        return this;
    }

    /**
     * Close will work
     *
     * @return new state: CLOSED
     */
    @Override
    public State close() {
        System.out.println("The gate will be closed.");
        return super.CLOSED;
    }

    /**
     * Lock is not possible
     *
     * @return keep state
     */
    @Override
    public State lock() {
        System.out.println("Please close the gate before locking it.");
        return this;
    }

    /**
     * Unlock also is not possible
     *
     * @return keep state
     */
    @Override
    public State unlock() {
        System.out
                .println("The gate is not locked, so it cannot be unlocked.");
        return this;
    }
}
