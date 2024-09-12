package me.study.designpatterns.creational.factory_method.examples.example01_pizza_meal;
import javax.swing.JOptionPane;

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
public class Pizzeria extends Restaurant {

    /**
     * Ask for the choice of the guest
     *
     * @return the order
     */
    @Override
    protected String takeOrder() {
        var bestellung = JOptionPane.
                showInputDialog("Have you already chosen your pizza?");
        return bestellung;
    }

    /**
     * Make a new pizza
     *
     * @param order of the guest
     *
     * @return the pizza
     */
    @Override
    protected Meal prepareMeal(String order) {
        if (order == null || order.isEmpty())
            return new Pizza();
        else
            return switch (order) {
                case "Calzone" ->
                    new Calzone();
                case "Hawaii" ->
                    new Hawaii();
                default -> {
                    System.out.
                            println("We don't offer this pizza!");
                    yield null;
                }
            };
//            switch (order) {
//                case "Calzone" -> {
//                    return new Calzone();
//                }
//                case "Hawaii" -> {
//                    return new Hawaii();
//                }
//                default -> {
//                    System.out.
//                            println("We don't offer this pizza!");
//                    return null;
//                }
//            }
    }
}
