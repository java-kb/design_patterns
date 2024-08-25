package me.study.designpatterns.behavioral.state.examples.example2_pop3;

import me.study.designpatterns.behavioral.state.examples.example2_pop3.states.Pop3Session;

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
public class TestApp {

    /**
     * Create a session, establish a connection and test commands
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        // create session
        var session = new Pop3Session();
        // create TCP-Connection
        session.connect();
        session.user("Admin");
        // wrong password
        session.pass("test");
        // returns to START

        // command not allowed in this state
        session.list();

        // connect once again
        session.connect();
        session.user("Admin");
        session.pass("Admin");

        // allowed command
        session.list();
        session.stat();

        // command not allowed
        session.pass("Test");

        session.quit();
    }
}
