/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Abstract Factory"
 */
package me.study.designpatterns.creational.abstract_factory.examples.example01_garden.Trial3;

public class Gardening {

    /**
     * Let a garden factory execute all the necessary steps
     *
     */
    Gardening() {
        AbstractGardenFactory factory = new MonasteryGardenFactory();
        var floor = factory.layFloor();
        var enclosure = factory.enclose();
        var plant = factory.plant();
    }

    /**
     * Start the gardening
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        var gardening = new Gardening();
    }
}
