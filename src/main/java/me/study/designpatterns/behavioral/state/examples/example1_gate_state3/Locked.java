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
public class Locked extends State {

    /**
     * Open is not possible
     *
     * @return keep state
     */
    @Override
    public State open() {
        System.out
                .println("The gate is locked and cannot be opened.");
        return this;
    }

    /**
     * Close also is not possible
     *
     * @return keep state
     */
    @Override
    public State close() {
        System.out
                .println("The gate is locked and cannot be closed.");
        return this;
    }

    /**
     * Lock won't work either
     *
     * @return keep state
     */
    @Override
    public State lock() {
        System.out
                .println("The gate is already locked and cannot be locked again.");
        return this;
    }

    /**
     * Only unlock will work
     *
     * @return new state: CLOSED
     */
    @Override
    public State unlock() {
        System.out.println("The gate will be unlocked.");
        return super.CLOSED;
    }
}
