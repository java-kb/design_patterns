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
 * Calls the build methods of the abstract superclass, but passes the
 * ingredients for a monastery garden
 */
public class MonasteryGarden extends AbstractGarden {

    MonasteryGarden() {
        super.layFloor(new OldStonePlate());
        super.plant(new Herbs());
        super.enclose(new StoneWall());
    }
}
