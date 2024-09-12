package me.study.designpatterns.creational.abstract_factory.examples.example02_game_chasing_ghosts.version3;

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
public enum Direction {
    /**
     * Direction North
     */
    NORTH("to the North"),
    /**
     * Direction South
     */
    SOUTH("to the South"),
    /**
     * Direction West
     */
    WEST("to the West"),
    /**
     * Direction East
     */
    EAST("to the East"),
    /**
     * Direction unknown
     */
    UNKNOWN("unknown direction");
    /**
     * The current direction
     */
    private final String description;
    /**
     * The way back
     */
    private Direction oppositeDirection;

    /**
     * Constructor gets a description
     *
     * @param description of the direction
     */
    private Direction(String description) {
        this.description = description;
    }

    /**
     * Set the opposite direction
     *
     * @param oppositeDirection the way back
     */
    private void setOppositeDirection(Direction oppositeDirection) {
        this.oppositeDirection = oppositeDirection;
    }

    /**
     * Specify an opposing direction for every direction
     */
    static {
        NORTH.setOppositeDirection(SOUTH);
        SOUTH.setOppositeDirection(NORTH);
        EAST.setOppositeDirection(WEST);
        WEST.setOppositeDirection(EAST);
    }

    /**
     * Query the way back
     *
     * @return the opposing direction
     */
    Direction getOppositeDirection() {
        return oppositeDirection;
    }

    /**
     * Convert text commands into directions
     *
     * @param direction the text command
     *
     * @return the appropriate direction
     */
    static Direction getDirection(String direction) {
        direction = direction.toLowerCase();
        return switch (direction) {
            case "south" ->
                SOUTH;
            case "north" ->
                NORTH;
            case "east" ->
                EAST;
            case "west" ->
                WEST;
            default ->
                UNKNOWN;
        };
    }

    /**
     * Printout the direction
     *
     * @return the description
     */
    @Override
    public String toString() {
        return description;
    }
}
