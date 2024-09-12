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
public class Pizza implements Meal {

    /**
     * A basic pizza
     *
     * @return text
     */
    @Override
    public String toString() {
        return "Pizza Margharita";
    }
}
