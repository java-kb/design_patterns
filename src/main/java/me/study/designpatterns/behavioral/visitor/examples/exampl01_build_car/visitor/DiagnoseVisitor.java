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
public class DiagnoseVisitor implements VisitorIF {

    /**
     * Flag, if the car is roadworthy
     */
    private boolean roadworthy = true;

    /**
     * Check, if car is roadworthy
     *
     * @return true, if car is ready to start
     */
    public boolean isRoadworthy() {
        return roadworthy;
    }

    /**
     * Check the air pressure of a given wheel. Needs to be above a specific
     * value to be roadworthy
     *
     * @param wheel das Wheel
     */
    @Override
    public void visit(Wheel wheel) {
        var pressure = wheel.getPressure();
        System.out.print("Pressure " + wheel.toString() + ": " + pressure);
        System.out.
                println(pressure == 2.0 ? " is sufficient" : " is not sufficient");

        if (pressure != 2.0)
            roadworthy = false;
    }

    /**
     * For an engine, just the condition is inquired
     *
     * @param engine
     */
    @Override
    public void visit(Engine engine) {
        var condition = engine.getCondition();
        System.out.println(condition);
    }

    /**
     * For a chassis, the attached wheels are counted. This car needs 4 of them
     *
     * @param chassis
     */
    @Override
    public void visit(Chassis chassis) {
        var wheels = chassis.getWheels();
        if (wheels.length != 4)
            roadworthy = false;
    }

    /**
     * For a car, several checks are made. Every result needs to be "roadworthy"
     *
     * @param car
     */
    @Override
    public void visit(Car car) {
        var blinkerWaterSufficient = car.isBlinkerWaterSufficent();
        var oilLevelOkay = car.isOilLevelOkay();
        var tankIsFull = car.isTankFull();
        var conditionOverall = tankIsFull && oilLevelOkay && blinkerWaterSufficient;
        System.out.
                println("The car is" + (conditionOverall ? " " : " not ") + "okay");
        roadworthy = conditionOverall && roadworthy;
    }
}
