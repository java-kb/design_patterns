package me.study.designpatterns.behavioral.state.examples.example2_pop3.states;

/**
 * This abstract class also implements the standard behaviour for all commands.
 * The specific states only need to implement their respective valid set of
 * commands.
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "State"
 */
public abstract class AbstractState {

    /**
     * Connect to POP3 server Standard behaviour for the command: Print an error
     * message
     *
     * @return reference to the current state
     */
    AbstractState connect() {
        System.out
                .println("\t-ERR: connect() - wrong method call in this state");
        return this;
    }

    /**
     * Hand over the user name to the server Standard behaviour for the command:
     * Print an error message
     *
     * @param user user's name on the server
     * @return reference to the current state
     */
    AbstractState USER(String user) {
        System.out.println("\t-ERR: user() - wrong method call in this state");
        return this;
    }

    /**
     * Send password to server<br>Immediately follows the USER command Standard
     * behaviour for the command: Print an error message
     *
     * @param pass the user's password
     * @return reference to the current state
     */
    AbstractState PASS(String pass) {
        System.out.println("\t-ERR: pass() - wrong method call in this state");
        return this;
    }

    /**
     * End the current session Standard behaviour for the command: Print an
     * error message
     *
     * @return reference to the current state
     */
    AbstractState QUIT() {
        System.out.println("\t-ERR: quit() - wrong method call in this state");
        return this;
    }

    /**
     * Aquire mail box statistics Standard behaviour for the command: Print an
     * error message
     *
     * @return reference to the current state
     */
    AbstractState STAT() {
        System.out.println("\t-ERR: stat() - wrong method call in this state");
        return this;
    }

    /**
     * Mark the email with the given index as deleted Standard behaviour for the
     * command: Print an error message
     *
     * @param index of the email to delete
     * @return reference to the current state
     */
    AbstractState DELE(int index) {
        System.out.println("\t-ERR: dele() - wrong method call in this state");
        return this;
    }

    /**
     * List all stored emails Standard behaviour for the command: Print an error
     * message
     *
     * @return reference to the current state
     */
    AbstractState LIST() {
        System.out.println("\t-ERR: list() - wrong method call in this state");
        return this;
    }
}
