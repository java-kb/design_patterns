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
package me.study.designpatterns.creational.abstract_factory.examples.example01_garden.Trial2;

/**
 * Abstract superclass, implementation is in the respective garden classes
 */
class AbstractGarden {

    void layFloor(AbstractFlooring flooring) {
        // The activities for laying a floor
    }

    void plant(AbstractPlanting planting) {
        // The plants are put into the ground
    }

    void enclose(AbstractEnclosure enclosure) {
        // The enclosure is set
    }
}
