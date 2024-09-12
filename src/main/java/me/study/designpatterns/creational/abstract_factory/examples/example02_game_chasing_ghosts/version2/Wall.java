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
public class Wall extends Component {

    /**
     * You cannot enter a wall
     *
     * @param player
     */
    @Override
    public void enter(Player player) {
        System.out.println("You step into a wall!");
    }

    /**
     * A wall can describe itself
     *
     * @return a description
     */
    @Override
    protected String describe() {
        return " a wall.";
    }

    /**
     * For printout, use the description
     *
     * @return text
     */
    @Override
    public String toString() {
        return describe();
    }
}
