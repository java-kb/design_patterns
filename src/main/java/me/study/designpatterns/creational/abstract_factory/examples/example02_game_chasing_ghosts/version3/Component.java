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
public abstract class Component {

    /**
     * Enter a room
     *
     * @param player who enters the room
     */
    protected abstract void enter(Player player);

    /**
     * Get the describe of a component
     *
     * @return the describe
     */
    protected abstract String describe();
}
