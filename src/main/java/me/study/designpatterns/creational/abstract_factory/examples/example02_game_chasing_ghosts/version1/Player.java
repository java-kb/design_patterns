package me.study.designpatterns.creational.abstract_factory.examples.example02_game_chasing_ghosts.version1;

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
public class Player {

    // The actual room where the player is
    private Room currentRoom;

    /**
     * Put the player into a specific room
     *
     * @param room the room
     */
    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    /**
     * Check, where the player currently is
     *
     * @return the current room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }
}
