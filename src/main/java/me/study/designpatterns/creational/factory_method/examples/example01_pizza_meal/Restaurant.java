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
public abstract class Restaurant {

    /**
     * Ask the guest
     *
     * @return the order
     */
    protected abstract String takeOrder();

    /**
     * Prepare everything
     *
     * @param order of the guest
     *
     * @return the meal
     */
    protected abstract Meal prepareMeal(String order);

    /**
     * Hand the meal out to the guest
     *
     * @param meal the meal
     */
    private void serveMeal(Meal meal) {
        System.out.println("Meal is here! It's " + meal);
    }

    /**
     * The complete workflow in a restaurant
     *
     * @return the meal
     */
    public final Meal order() {
        var order = takeOrder();
        var meal = prepareMeal(order);
        serveMeal(meal);
        return meal;
    }
}
