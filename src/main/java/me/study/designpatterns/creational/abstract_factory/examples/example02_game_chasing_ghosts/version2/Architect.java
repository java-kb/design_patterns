package me.study.designpatterns.creational.abstract_factory.examples.example02_game_chasing_ghosts.version2;

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
public class Architect {

    /**
     * The architect needs a factory
     */
    private final ComponentFactory factory;

    /**
     * Constructor: Get the factory
     *
     * @param factory
     */
    Architect(ComponentFactory factory) {
        this.factory = factory;
    }

    /**
     * Build the house, create rooms, set doors
     *
     * @return Entrance location
     */
    Room buildHouse() {
        var hallway = factory.createRoom("hallway", "in the hallway");
        var corridor = factory.createRoom("corridor", "in the corridor");
        var bathroom = factory.createRoom("bathroom", "in the bathroom");
        var study = factory.createRoom("study", "in the study");
        var livingroom = factory.createRoom("living room", "in the living room");
        var kitchen = factory.createRoom("kitchen", "in the kitchen");
        var pantry = factory.createRoom("pantry", "in the pantry");
        var library = factory.createRoom("library", "in the library");

        // First, specify the doors between the rooms
        var door = new Door[7];
        door[0] = factory.createDoor(hallway, corridor);
        door[1] = factory.createDoor(hallway, bathroom);
        door[2] = factory.createDoor(corridor, library);
        door[3] = factory.createDoor(corridor, kitchen);
        door[4] = factory.createDoor(kitchen, pantry);
        door[5] = factory.createDoor(corridor, livingroom);
        door[6] = factory.createDoor(livingroom, study);

        // Then place the doors
        hallway.addComponent(Direction.NORTH, door[0]);
        hallway.addComponent(Direction.WEST, door[1]);
        corridor.addComponent(Direction.NORTH, door[2]);
        corridor.addComponent(Direction.EAST, door[3]);
        kitchen.addComponent(Direction.NORTH, door[4]);
        corridor.addComponent(Direction.WEST, door[5]);
        livingroom.addComponent(Direction.NORTH, door[6]);

        return hallway;
    }
}
