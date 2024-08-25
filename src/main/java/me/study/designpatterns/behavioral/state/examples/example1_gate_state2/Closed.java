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
public class Closed extends State {

    /**
     * Call constructor of the superclass
     *
     * @param gate the gate
     */
    Closed(Gate gate) {
        super(gate);
    }

    /**
     * Open will work
     */
    @Override
    public void open() {
        System.out.println("The gate will be opened.");
        gate.setOpenState();
    }

    /**
     * Close is not possible
     */
    @Override
    public void close() {
        System.out
                .println("The gate is alread closed and cannot be closed again.");
    }

    /**
     * Lock is possible though
     */
    @Override
    public void lock() {
        System.out.println("The gate will be locked.");
        gate.setLockedState();
    }

    /**
     * Unlock is not possible
     */
    @Override
    public void unlock() {
        System.out
                .println("The gate is not locked and cannot be unlocked.");
    }
}
