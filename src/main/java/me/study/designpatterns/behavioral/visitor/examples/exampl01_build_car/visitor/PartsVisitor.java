package me.study.designpatterns.behavioral.visitor.examples.exampl01_build_car.visitor;

import me.study.designpatterns.behavioral.visitor.examples.exampl01_build_car.car.Car;
import me.study.designpatterns.behavioral.visitor.examples.exampl01_build_car.car.Chassis;
import me.study.designpatterns.behavioral.visitor.examples.exampl01_build_car.car.Engine;
import me.study.designpatterns.behavioral.visitor.examples.exampl01_build_car.car.Wheel;

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
public class PartsVisitor implements VisitorIF {

    /**
     * For building the parts list
     */
    private final StringBuilder builder = new StringBuilder();

    /**
     * Deliver the parts list as one text
     *
     * @return text
     */
    public String getPartsList() {
        return "Components: \n" + builder.toString();
    }

    /**
     * Get the description of a wheel
     *
     * @param wheel
     */
    @Override
    public void visit(Wheel wheel) {
        builder.append(wheel.getDescription()).append("\n");
    }

    /**
     * Get the description of an engine
     *
     * @param engine
     */
    @Override
    public void visit(Engine engine) {
        builder.append(engine.getDescription()).append("\n");
    }

    /**
     * Get the description of a chassis
     *
     * @param chassis
     */
    @Override
    public void visit(Chassis chassis) {
        builder.append(chassis.getDescription()).append("\n");
    }

    /**
     * Get the description of a car
     *
     * @param car
     */
    @Override
    public void visit(Car car) {
        builder.append(car.getDescription())
                .append(" (remaining parts)")
                .append("\n");
    }
}
