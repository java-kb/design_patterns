package me.study.designpatterns.behavioral.state.examples.example2_pop3.states;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "State"
 */
public class Update extends AbstractState {

    /**
     * A log entry
     */
    Update() {
        System.out.println("\nCreating Update-State");
    }

    /**
     * Do some cleanup stuff, then change to Start state
     *
     * @return Start state
     */
    @Override
    AbstractState QUIT() {
        System.out.println("\t>Delete all marked messages");
        System.out.println("\t>Server says \"Cuio vae!\"");
        return new Start();
    }
}
