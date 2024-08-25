package me.study.designpatterns.behavioral.state.examples.example2_pop3.states;

/**
 * This is the context, where the state is managed and used
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "State"
 */
public class Pop3Session {

    /**
     * The state
     */
    private AbstractState state;

    /**
     * Immer im Start-Zustand beginnen Always begin in Start state
     */
    public Pop3Session() {
        state = new Start();
    }

    /**
     * Forward command to state object
     */
    public void connect() {
        state = state.connect();
    }

    /**
     * Forward command to state object
     *
     * @param user name of user
     */
    public void user(String user) {
        state = state.USER(user);
    }

    /**
     * Forward command to state object
     *
     * @param pass password
     */
    public void pass(String pass) {
        state = state.PASS(pass);
    }

    /**
     * Forward command to state object
     */
    public void quit() {
        state = state.QUIT();
    }

    /**
     * Forward command to state object
     */
    public void stat() {
        state = state.STAT();
    }

    /**
     * Forward command to state object
     *
     * @param index of email to delete
     */
    public void dele(int index) {
        state = state.DELE(index);
    }

    /**
     * Forward command to state object
     */
    public void list() {
        state = state.LIST();
    }
}
