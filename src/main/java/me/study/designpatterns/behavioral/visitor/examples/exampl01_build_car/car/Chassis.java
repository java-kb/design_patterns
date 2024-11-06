package me.study.designpatterns.behavioral.visitor.examples.exampl01_build_car.car;

import me.study.designpatterns.behavioral.visitor.examples.exampl01_build_car.visitor.VisitorIF;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Visitor"
 */
public class Chassis implements Part {

    /**
     * An array with all the wheels
     */
    private final Wheel[] wheels;

    /**
     * Delivers the wheels
     *
     * @return the array
     */
    public Wheel[] getWheels() {
        return wheels;
    }

    /**
     * Constructor. Sets the given wheels.
     *
     * @param wheels the array of the wheels
     */
    Chassis(Wheel[] wheels) {
        this.wheels = wheels;
    }

    /**
     * Gets a visitor and calls its visit method with itself as a parameter.
     * Finally, the visitor is passed to the wheels
     *
     * @param visitor
     */
    @Override
    public void accept(VisitorIF visitor) {
        visitor.visit(this);
        for (var wheel : wheels)
            wheel.accept(visitor);
    }

    /**
     * Print out the description
     *
     * @return text
     */
    @Override
    public String getDescription() {
        return "- Chassis";
    }

    /**
     * Printout the name
     *
     * @return text
     */
    @Override
    public String toString() {
        return "Chassis";
    }
}
