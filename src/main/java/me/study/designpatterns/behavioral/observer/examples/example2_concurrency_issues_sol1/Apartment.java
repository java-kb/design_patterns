package me.study.designpatterns.behavioral.observer.examples.example2_concurrency_issues_sol1;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Observer"
 */
public class Apartment {

    /**
     * Description of the apartment
     */
    public final String description;

    /**
     * Constuctor gets the apartment description
     *
     * @param description of the new apartment
     */
    public Apartment(String description) {
        this.description = description;
    }
}
