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
package me.study.designpatterns.creational.abstract_factory.examples.example01_garden.Trial1;

public class Garden {

    // We have these types of gardens
    private enum GardenType {
        MonasteryGarden, OrnamentalGarden
    };
    // And we start with a MonestaryGarden
    private final GardenType garden = GardenType.MonasteryGarden;

    // Here, we lay the floor
    public void layFloor() {
        // depending on garden type
        switch (garden) {

            case MonasteryGarden -> {
                // lay old stone plates
            }
            default -> {
                // sow grass
            }
        }
    }

    // Here, we plant something
    public void plant() {
        // depending on garden type
        switch (garden) {

            case MonasteryGarden -> {
                // plant some herbs
            }
            default -> {
                // plant roses
            }
        }
    }

    // Here, we construct the enclosure
    public void enclose() {
        // depending on garden type
        switch (garden) {

            case MonasteryGarden -> {
                // build a stone wall
            }
            default -> {
                // set and cut a hedge
            }
        }
    }
}
