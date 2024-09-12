package me.study.designpatterns.creational.factory_method.examples.example01_pizza_meal;
/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Factory Method"
 */
public class Calzone extends Pizza {

    /**
     * The description of a pizza Calzone
     *
     * @return text
     */
    @Override
    public String toString() {
        return "a folded pizza";
    }
}
