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

public class MonasteryGardenFactory implements AbstractGardenFactory {

    /**
     * Plant herbs
     *
     * @return herbs
     */
    @Override
    public Plant plant() {
        return new Herb();
    }

    /**
     * Lay flagstone
     *
     * @return the flagstone
     */
    @Override
    public Floor layFloor() {
        return new Flagstone();
    }

    /**
     * Build a stone wall
     *
     * @return the stone wall
     */
    @Override
    public Enclosure enclose() {
        return new StoneWall();
    }
}
