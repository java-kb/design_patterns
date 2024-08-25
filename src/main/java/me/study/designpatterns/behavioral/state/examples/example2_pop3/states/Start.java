package me.study.designpatterns.behavioral.state.examples.example2_pop3.states;

/**
 * A specific start state
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "State"
 */
public class Start extends AbstractState {

    /**
     * Just a log entry
     */
    Start() {
        System.out.println("Creating Start-State");
    }

    /**
     * The only meaningful command in start state is a connect
     *
     * @return the new state: Authorization (without user name!)
     */
    @Override
    AbstractState connect() {
        System.out.println("\t>try to establish connection");
        System.out.println("\t+OK: server ready: Mae govannen, mellon!!");
        return new Authorization();
    }
}
