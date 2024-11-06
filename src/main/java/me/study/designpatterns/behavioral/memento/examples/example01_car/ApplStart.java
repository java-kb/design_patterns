package me.study.designpatterns.behavioral.memento.examples.example01_car;
import me.study.designpatterns.behavioral.memento.examples.example01_car.datamodel.Car;
import me.study.designpatterns.behavioral.memento.examples.example01_car.memento.Memento;
import me.study.designpatterns.behavioral.memento.examples.example01_car.util.Stack;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Memento"
 */
public class ApplStart {

    /**
     * Build a car and do some changes. Occasionally save and print out the
     * state
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        final var stack = new Stack<Memento>();
        Memento memento;

        var car = new Car();
        car.driveMuchFaster();
        System.out.println(car);
        memento = car.createMemento();
        stack.push(memento);

        car.driveMuchFaster();
        car.driveMuchFaster();
        System.out.println("\n" + car);
        memento = car.createMemento();
        stack.push(memento);

        car.driveSlower();
        System.out.println("\n" + car);
        memento = car.createMemento();
        stack.push(memento);

        car.driveFaster();
        System.out.println("\n" + car + "\n");

        System.out.println("Undo everything: ");
        while (!stack.empty()) {
            car.setMemento(stack.pop());
            System.out.println("\n" + car);
        }
    }
}
