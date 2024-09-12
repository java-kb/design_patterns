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
public class Doner implements Meal {

    /**
     * Just one ingredient
     */
    private final String ingredient;

    /**
     * Constructor without ingredient
     */
    Doner() {
        ingredient = null;
    }

    /**
     * Constructor with a specific ingredient
     *
     * @param ingredient
     */
    Doner(String ingredient) {
        this.ingredient = ingredient;
    }

    /**
     * Description, depending on the ingredient
     *
     * @return text
     */
    @Override
    public String toString() {
        var description = ingredient != null ? ingredient : "complete ";
        return "Doner " + description;
    }
}
