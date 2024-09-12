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

public interface AbstractGardenFactory {

    /**
     * Abstract action: plant
     *
     * @return the plant
     */
    Plant plant();

    /**
     * Abstract action: lay floor
     *
     * @return the flooring
     */
    Floor layFloor();

    /**
     * Abstract action: build enclosure
     *
     * @return the enclosure
     */
    Enclosure enclose();
}
