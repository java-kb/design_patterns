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
public class Hawaii extends Pizza {

    /**
     * The description of a pizza Hawaii
     *
     * @return text
     */
    @Override
    public String toString() {
        return "a pizza with ham and pineapple";
    }
}
