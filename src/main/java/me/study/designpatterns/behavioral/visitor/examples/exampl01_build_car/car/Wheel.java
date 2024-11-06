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
public class Wheel implements Part {

    /**
     * The position of the wheel
     */
    private final Position position;

    /**
     * The pressure
     */
    private final double pressure = 2.0;

    /**
     * Get the pressure
     *
     * @return a double value
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * Constructor. Sets the given position
     *
     * @param position
     */
    public Wheel(Position position) {
        this.position = position;
    }

    /**
     * Take a visitor and call its visit method with the wheel as parameter
     *
     * @param visitor
     */
    @Override
    public void accept(VisitorIF visitor) {
        visitor.visit(this);
    }

    /**
     * Print out the desription
     *
     * @return text
     */
    @Override
    public String getDescription() {
        return "- Wheel " + position;
    }

    /**
     * Print out the name
     *
     * @return text
     */
    @Override
    public String toString() {
        return "Wheel " + position;
    }
}
