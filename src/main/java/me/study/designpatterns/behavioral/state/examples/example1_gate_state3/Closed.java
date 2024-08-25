package me.study.designpatterns.behavioral.state.examples.example1_gate_state3;

/**
 *
 * @author Olaf Musch
 *
 *         Design Patterns with Java
 *
 *         An Introduction, Springer Vieweg
 *
 *         chapter "State"
 */
public class Closed extends State {

    /**
     * Open will work
     *
     * @return new state: OPEN
     */
    @Override
    public State open() {
        System.out.println("The gate will be opened.");
        return super.OPEN;
    }

    /**
     * Close is not possible
     *
     * @return keep state
     */
    @Override
    public State close() {
        System.out
                .println("The gate is alread closed and cannot be closed again.");
        return this;
    }

    /**
     * Lock is possible though
     *
     * @return new state: LOCKED
     */
    @Override
    public State lock() {
        System.out.println("The gate will be locked.");
        return super.LOCKED;
    }

    /**
     * Unlock is not possible
     *
     * @return keep state
     */
    @Override
    public State unlock() {
        System.out
                .println("The gate is not locked and cannot be unlocked.");
        return this;
    }
}
