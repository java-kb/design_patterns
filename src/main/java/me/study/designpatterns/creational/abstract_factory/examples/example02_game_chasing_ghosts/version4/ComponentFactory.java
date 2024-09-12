package me.study.designpatterns.creational.abstract_factory.examples.example02_game_chasing_ghosts.version4;

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
public class ComponentFactory {

    /**
     * Build a room
     *
     * @param name
     * @param description
     *
     * @return the room
     */
    Room createRoom(String name, String description) {
        return new Room(name, description);
    }

    /**
     * Build a door
     *
     * @param room1 from
     * @param room2 to
     *
     * @return the door
     */
    Door createDoor(Room room1, Room room2) {
        return new Door(room1, room2);
    }

    /**
     * Build a wall
     *
     * @return the wall
     */
    Wall createWall() {
        return new Wall();
    }

    /**
     * Place a wall to every direction of a room
     *
     * @param room the room
     */
    void createWalls(Room room) {
        for (var direction : Direction.values())
            room.addComponent(direction, new Wall());
    }
}
