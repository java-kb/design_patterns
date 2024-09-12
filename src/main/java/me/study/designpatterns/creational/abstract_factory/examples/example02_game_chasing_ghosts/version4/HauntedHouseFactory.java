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
public class HauntedHouseFactory extends EnchantedDoorFactory {

    /**
     * This overrides the createRoom method. Now, we randomly get haunted by a
     * ghost
     *
     * @param name of the room
     * @param description of the room
     *
     * @return the room, maybe haunted
     */
    @Override
    Room createRoom(String name, String description) {
        var number = (int) (Math.random() * 10);
        if (number > 5)
            return new Room(name, description);
        else
            return new HauntedRoom(name, description);
    }
}
