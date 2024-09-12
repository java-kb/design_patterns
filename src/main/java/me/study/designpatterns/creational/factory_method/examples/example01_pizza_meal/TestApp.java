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
public class TestApp {

    /**
     * First, go to the pizzeria, then to the takeaway
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        Restaurant mammaMia = new Pizzeria();
        mammaMia.order();

        Restaurant istanbul = new Takeaway();
        istanbul.order();
    }
}
