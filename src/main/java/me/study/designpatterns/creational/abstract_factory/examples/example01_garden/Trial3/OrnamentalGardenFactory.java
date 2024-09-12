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

public class OrnamentalGardenFactory implements AbstractGardenFactory {

    /**
     * Plant a rose
     *
     * @return the plant
     */
    @Override
    public Plant plant() {
        return new Rose();
    }

    /**
     * Sow lawn
     *
     * @return the floor
     */
    @Override
    public Floor layFloor() {
        return new Lawn();
    }

    /**
     * Build hedge
     *
     * @return the enclosure
     */
    @Override
    public Enclosure enclose() {
        return new Hedge();
    }
}
