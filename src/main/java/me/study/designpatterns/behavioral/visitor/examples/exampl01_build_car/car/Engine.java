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
public class Engine implements Part {

    /**
     * The condition of the engine
     */
    private final String condition = "The carburator is coughing a little bit, but in total, the engine is running smoothly";

    /**
     * Get the condition of the engine
     *
     * @return text
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Take a visitor and call its visit method with the engine as parameter
     *
     * @param visitor
     */
    @Override
    public void accept(VisitorIF visitor) {
        visitor.visit(this);
    }

    /**
     * Print out the description
     *
     * @return text
     */
    @Override
    public String getDescription() {
        return "- Engine";
    }

    /**
     * Print out the name
     *
     * @return text
     */
    @Override
    public String toString() {
        return "Engine";
    }
}
