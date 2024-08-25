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
public class Authorization extends AbstractState {

    private final String user;

    /**
     * Create anonymous authorization
     */
    Authorization() {
        System.out.println("\nCreating Authorization-State");
        this.user = null;
    }

    /**
     * Authorization with user id
     *
     * @param user id
     */
    Authorization(String user) {
        System.out
                .println("\nCreating Authorization-State for user \"" + user + "\"");
        this.user = user;
    }

    /**
     * Creates an authorization for a specific user
     *
     * @param user name on the server
     * @return new state
     */
    @Override
    AbstractState USER(String user) {
        return new Authorization(user);
    }

    /**
     * Evaluates the given password. Depending on the results, the state is
     * changed accordingly
     *
     * @param pass the password
     * @return the new state
     */
    @Override
    AbstractState PASS(String pass) {
        if (user != null)
            if (this.user.equalsIgnoreCase("Admin") && pass
                    .equalsIgnoreCase("Admin")) {
                System.out.println("\t+OK: user and passwort match");
                return new Transaction();
            } else {
                System.out.println("\t-ERR: user and passwort don't match");
                return new Start();
            }
        else {
            System.out.println("\t-ERR: type in user name");
            return this;
        }
    }

    /**
     * Switches to start state
     *
     * @return a new start state
     */
    @Override
    AbstractState QUIT() {
        System.out.println("\t>will terminate authorization");
        return new Start();
    }
}
