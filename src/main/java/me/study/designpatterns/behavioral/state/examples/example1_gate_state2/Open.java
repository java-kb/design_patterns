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
public class Open extends State {

    /**
     * Call constructor of the superclass
     *
     * @param gate the gate
     */
    Open(Gate gate) {
        super(gate);
    }

    /**
     * Open is not possible
     */
    @Override
    public void open() {
        System.out
                .println("The gate is already open an cannot be opened again.");
    }

    /**
     * Close will work
     */
    @Override
    public void close() {
        System.out.println("The gate will be closed.");
        gate.setClosedState();
    }

    /**
     * Lock is not possible
     */
    @Override
    public void lock() {
        System.out.println("Please close the gate before locking it.");
    }

    /**
     * Unlock also is not possible
     */
    @Override
    public void unlock() {
        System.out
                .println("The gate is not locked, so it cannot be unlocked.");
    }
}
