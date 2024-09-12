package me.study.designpatterns.creational.abstract_factory.examples.example02_game_chasing_ghosts.version4;

import java.util.ArrayList;
import java.util.List;

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
public class HauntedRoom extends Room {

    /**
     * List of possible apparitions
     */
    private static final List<String> ghosts = new ArrayList<>();

    /**
     * Initialize the list
     */
    static {
        ghosts.add("A ghost flits through the door.");
        ghosts.add("A phantom floats across the room.");
        ghosts.add("A shadow appears at the wall.");
        ghosts.add("A little ghosts perches at the corner, it got scared.");
        ghosts.add("You hear a faint creak from the corner.");
        ghosts.add("You're hearing strange noises.");
    }

    /**
     * Constructor stays unchanged. Taken from the superclass
     *
     * @param name
     * @param description
     */
    HauntedRoom(String name, String description) {
        super(name, description);
    }

    /**
     * Entering a haunted room leads to a random apparition
     *
     * @param player
     */
    @Override
    public void enter(Player player) {
        super.enter(player);
        var number = (int) (Math.random() * (ghosts.size() * 2));
        if (number < ghosts.size() - 1) {
            System.out.println("\t******************");
            System.out.println("\t" + ghosts.get(number));
            System.out.println("\t******************\n");
        }
    }
}
