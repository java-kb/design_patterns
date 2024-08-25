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
public class Locked extends State {

    /**
     * Call constructor of the superclass
     *
     * @param gate the gate
     */
    Locked(Gate gate) {
        super(gate);
    }

    /**
     * Open is not possible
     */
    @Override
    public void open() {
        System.out
                .println("The gate is locked and cannot be opened.");
    }

    /**
     * Close also is not possible
     */
    @Override
    public void close() {
        System.out
                .println("The gate is locked and cannot be closed.");
    }

    /**
     * Lock won't work either
     */
    @Override
    public void lock() {
        System.out
                .println("The gate is already locked and cannot be locked again.");
    }

    /**
     * Only unlock will work
     */
    @Override
    public void unlock() {
        System.out.println("The gate will be unlocked.");
        gate.setState(new Closed(gate));
    }
}
