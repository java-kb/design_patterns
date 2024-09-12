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
public class Door extends Component {

    /**
     * The state of a door
     */
    private boolean isOpen = false;
    /**
     * The first room, the door connects
     */
    private final Room room1;
    /**
     * The second room, the door connects
     */
    private final Room room2;

    /**
     * A door always connects two rooms
     *
     * @param room1 from
     * @param room2 too
     */
    Door(Room room1, Room room2) {
        this.room1 = room1;
        this.room2 = room2;
    }

    /**
     * Ask the door, to which room it connects if starting at a specific room
     *
     * @param aRoom a given room
     *
     * @return the other room, to which the door connects to
     */
    Room getNeighbor(Room aRoom) {
        return aRoom == room1 ? room2 : room1;
    }

    /**
     * Open
     */
    void open() {
        System.out.println("The door is opened");
        isOpen = true;
    }

    /**
     * Close
     */
    void close() {
        System.out.println("The door will is closed");
        isOpen = false;
    }

    /**
     * Walk through the door if possible
     *
     * @param player
     */
    @Override
    public void enter(Player player) {
        if (isOpen) {
            var currentRoom = player.getCurrentRoom();
            var newRoom = currentRoom == room1 ? room2 : room1;
            newRoom.enter(player);
        } else
            System.out.println("The door is closed. Thou shall not pass!");
    }

    /**
     * Describe the door
     *
     * @return text
     */
    @Override
    protected String describe() {
        return isOpen ? " an open door" : " a closed door.";
    }

    /**
     * Printout the description
     *
     * @return description
     */
    @Override
    public String toString() {
        return describe();
    }
}
