package me.study.designpatterns.behavioral.observer.examples.example2;
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
public class TestApp {

    /**
     * Testing the observer pattern with an apartment market, a student and an
     * employee
     *
     * @param args are ignoted
     */
    public static void main(String[] args) {
        // Create an apartment market and two observers
        var apartments = new ApartmentMarket();
        var tristan = new Employee("Tristan");
        var isolde = new Student("Isolde");

        // Register a student and an employee as observers
        apartments.addObserver(isolde);
        apartments.addObserver(tristan);

        // Offer new apartments
        apartments.addApartment(new Apartment("little palace"));
        apartments.addApartment(new Apartment("studio apartment"));
    }
}
