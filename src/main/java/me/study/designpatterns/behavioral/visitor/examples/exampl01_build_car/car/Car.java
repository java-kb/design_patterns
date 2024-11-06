package me.study.designpatterns.behavioral.visitor.examples.exampl01_build_car.car;

import java.util.ArrayList;
import java.util.List;
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
public class Car implements Part {

    /**
     * All parts of the car
     */
    private final List<Part> parts = new ArrayList<>();

    /**
     * Tells, if tank is full
     */
    private final boolean tankIsFull = true;

    /**
     * Tells, if oil-level is sufficient
     */
    private final boolean oilLevelIsOkay = true;

    /**
     * Tells, if "blinker water" (could be anything you can possibly imagine) is
     * sufficient
     */
    private final boolean blinkerWaterIsSufficient = true;

    /**
     * Constructor. Adds an engine, a chassis and four wheels
     */
    public Car() {
        parts.add(new Engine());
        parts.add(new Chassis(new Wheel[]{
            new Wheel(Position.FL), new Wheel(Position.FR),
            new Wheel(Position.RL), new Wheel(Position.RR)
        }));
    }

    /**
     * The list of all parts
     *
     * @return the list
     */
    public List<Part> getParts() {
        return parts;
    }

    /**
     * Checks, if tank is full
     *
     * @return the flag
     */
    public boolean isTankFull() {
        return tankIsFull;
    }

    /**
     * Checks, if oil-level is okay
     *
     * @return the flag
     */
    public boolean isOilLevelOkay() {
        return oilLevelIsOkay;
    }

    /**
     * Checks, if "blinker water" is sufficient
     *
     * @return the flag
     */
    public boolean isBlinkerWaterSufficent() {
        return blinkerWaterIsSufficient;
    }

    /**
     * Gets a visitor and forwards it to all parts, only then the visitor to the
     * car object itself is called
     *
     * @param visitor the visitor
     */
    @Override
    public void accept(VisitorIF visitor) {
        parts.forEach((part) -> {
            part.accept(visitor);
        });
        visitor.visit(this);
    }

    /**
     * Print out the description
     *
     * @return text
     */
    @Override
    public String getDescription() {
        return "- Car";
    }

    /**
     * Print out the object
     *
     * @return text
     */
    @Override
    public String toString() {
        return "Car";
    }
}
