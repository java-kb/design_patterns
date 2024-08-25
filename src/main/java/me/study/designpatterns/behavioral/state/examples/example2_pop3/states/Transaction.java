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
public class Transaction extends AbstractState {

    /**
     * A log entry
     */
    Transaction() {
        System.out.println("\nCreating Transaction-State");
    }

    /**
     * On Quit, first execute an update of the session, then quit from there
     *
     * @return the Start state, which is returned from Update.QUIT()
     */
    @Override
    AbstractState QUIT() {
        System.out.println("\t>QUIT => will terminate now");
        var updateState = new Update();
        return updateState.QUIT();
    }

    /**
     * Output statistics
     *
     * @return keep state
     */
    @Override
    AbstractState STAT() {
        System.out.println("\t>STAT => 3 messages");
        return this;
    }

    /**
     * "Delete" a message
     *
     * @param index of the message
     * @return keep state
     */
    @Override
    AbstractState DELE(int index) {
        if (index > 0)
            System.out.println("\t>+OK: DELE => Will delete email no." + index);
        else
            System.out.println("\t>-ERR: DELE => Unknown index");
        return this;
    }

    /**
     * Print list of messages
     *
     * @return keep state
     */
    @Override
    AbstractState LIST() {
        System.out.println("\tList of messages: 1.) ... 2.) ... 3.) ");
        return this;
    }
}
