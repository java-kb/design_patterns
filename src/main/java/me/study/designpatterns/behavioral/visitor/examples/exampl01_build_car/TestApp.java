package me.study.designpatterns.behavioral.visitor.examples.exampl01_build_car;

import me.study.designpatterns.behavioral.visitor.examples.exampl01_build_car.car.Car;
import me.study.designpatterns.behavioral.visitor.examples.exampl01_build_car.visitor.DiagnoseVisitor;
import me.study.designpatterns.behavioral.visitor.examples.exampl01_build_car.visitor.PartsVisitor;

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
public class TestApp {

    /**
     * Build a car, visit all the parts with the PartsVisitor and then again
     * with the DiagnoseVisitor
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        var car = new Car();
        var visitor = new PartsVisitor();
        car.accept(visitor);
        var parts = visitor.getPartsList();
        System.out.println(parts);
        var diagnose = new DiagnoseVisitor();
        car.accept(diagnose);
        var roadworthy = diagnose.isRoadworthy();
        System.out.
                println("\n\tThe car is " + (roadworthy ? "ready" : "not ready"));
    }
}
