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
public class Takeaway extends Restaurant {

    /**
     * Ask for the desired toppings of the doner
     *
     * @return die Bestellung
     */
    @Override
    protected String takeOrder() {
        var bestellung = JOptionPane.
                showInputDialog("Which ingridients do you like for your doner?");
        return bestellung;
    }

    /**
     * Create a new doner
     *
     * @param order
     * @return the doner
     */
    @Override
    protected Meal prepareMeal(String order) {
        return order.isEmpty() ? new Doner() : new Doner(order);
    }
}
