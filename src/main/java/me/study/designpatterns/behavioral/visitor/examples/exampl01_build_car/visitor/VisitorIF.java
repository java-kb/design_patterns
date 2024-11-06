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
public interface VisitorIF {

    /**
     * Visit a wheel
     *
     * @param wheel
     */
    void visit(Wheel wheel);

    /**
     * Visit an engine
     *
     * @param engine
     */
    void visit(Engine engine);

    /**
     * Visit a chassis
     *
     * @param chassis
     */
    void visit(Chassis chassis);

    /**
     * Visit a car
     *
     * @param car
     */
    void visit(Car car);
}
