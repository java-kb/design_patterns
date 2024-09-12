package me.study.designpatterns.creational.abstract_factory.examples.example02_game_chasing_ghosts.version3;

import java.util.EnumMap;

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
public class Room extends Component {

    /**
     * The description of a room. The extension to "You're located..."
     */
    private final String description;
    /**
     * Name of the room
     */
    private final String name;
    /**
     * The components adjacent to this room in all directions
     */
    private final EnumMap<Direction, Component> directions = new EnumMap<>(Direction.class);

    /**
     * Create a room
     *
     * @param name of the room
     * @param description of the room
     */
    Room(String name, String description) {
        this.name = name;
        this.description = description;
        // For starters, put a wall in every direction
        for (var tempdirections : Direction.values())
            directions.put(tempdirections, new Wall());
    }

    /**
     * Place a wall at a specif direction of this room
     *
     * @param direction
     * @param wall
     */
    void addComponent(Direction direction, Wall wall) {
        if (directions.get(direction) == null)
            directions.put(direction, wall);
        else
            System.out.println("Not possible to place a wall in this direction: "
                    + name + " " + direction + " " + directions.get(direction));
    }

    /**
     * Place a door at a specific direction
     *
     * @param direction
     * @param door
     */
    void addComponent(Direction direction, Door door) {
        // First get the opposing direction
        var oppositeDirection = direction.getOppositeDirection();
        // Check, which rooms should be connected
        var neighbor = door.getNeighbor(this);
        // What do we have currently placed into this direction
        var mySide = directions.get(direction);
        // Find out, which component the neighbour has towards us
        var opposingSide = neighbor.directions.get(oppositeDirection);
        // We only can place a door, if there's both walls currently placed
        if (opposingSide instanceof Wall && mySide instanceof Wall) {
            // Place the door at the own room
            directions.put(direction, door);
            // Place the door at the neighbours respective position
            neighbor.directions.put(oppositeDirection, door);
        } else
            System.out.println("I cannot place a door here!");
    }

    /**
     * Look for the component in a specific direction of the room
     *
     * @param direction
     *
     * @return the component into this direction of the room
     */
    Component getElementAt(Direction direction) {
        return directions.get(direction);
    }

    /**
     * Enter the room
     *
     * @param player
     */
    @Override
    public void enter(Player player) {
        System.out.println("You now are located " + description);
        player.setCurrentRoom(this);
    }

    /**
     * Decribe a room and look at all directions
     *
     * @return the descriptive text
     */
    @Override
    protected String describe() {
        var answer = "\nYou are " + description + ".\nYou see \n";
        // Look at all directions
        for (var direction : Direction.values())
            if (!(direction == Direction.UNKNOWN))
                answer += "\t- " + direction + ":" + directions.get(direction) + "\n";
        return answer;
    }

    /**
     * Printout the name of the room
     *
     * @return text
     */
    @Override
    public String toString() {
        return name;
    }
}
