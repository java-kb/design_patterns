package me.study.designpatterns.behavioral.state.examples.example3_document_publish;

public class Document {
    private DocumentState state = new DraftedState(this);

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public Document(User user) {
        this.user = user;
    }

    public void publish() {
        state.publish();
    }

    public void render() {
        state.render();
    }

    public void changeState(DocumentState newState) {
        state = newState;
    }

    public User getUser() {
        return user;
    }
/**
     * Print out the current state
     *
     * @return text
     */
    @Override
    public String toString() {
        return "The Document is " + state + ".";
    }
}
