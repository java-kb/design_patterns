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
public interface Part {

    /**
     * Each part can deliver a description of itself
     *
     * @return text
     */
    String getDescription();

    /**
     * Each part can accept a visitor
     *
     * @param visitor
     */
    void accept(VisitorIF visitor);
}
