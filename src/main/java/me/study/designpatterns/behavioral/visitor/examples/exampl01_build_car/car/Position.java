package me.study.designpatterns.behavioral.visitor.examples.exampl01_build_car.car;

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
public enum Position {

    /**
     * The wheel could be in front left position
     */
    FL("front left"),
    /**
     * The wheel could be in front right position
     */
    FR("front right"),
    /**
     * The wheel could be in rear left position
     */
    RL("rear left"),
    /**
     * The wheel could be in rear right position
     */
    RR("rear right");

    /**
     * Description of a wheel's position
     */
    private final String description;

    /**
     * Consntructor. Gets the description
     *
     * @param description
     */
    private Position(String description) {
        this.description = description;
    }

    /**
     * Print out the description
     *
     * @return text
     */
    @Override
    public String toString() {
        return description;
    }
}
