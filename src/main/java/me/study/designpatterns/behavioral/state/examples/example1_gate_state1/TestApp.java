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
public class TestApp {

    private static final Gate GATE = new Gate();

    /**
     * Try some state changes
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        System.out.print(GATE + "\n\t");
        GATE.open();
        System.out.print(GATE + "\n\t");
        GATE.close();
        System.out.print(GATE + "\n\t");
        GATE.open();
        System.out.print(GATE + "\n\t");
        GATE.unlock();
        System.out.print(GATE + "\n\t");
        GATE.lock();
        System.out.print(GATE + "\n\t");
        GATE.close();
        System.out.print(GATE + "\n\t");
        GATE.lock();
        System.out.print(GATE + "\n\t");
        GATE.open();
        System.out.print(GATE + "\n\t");
        GATE.unlock();
        System.out.print(GATE + "\n\t");
        GATE.open();
        System.out.print(GATE + "\n\t");
    }
}
