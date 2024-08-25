package me.study.designpatterns.behavioral.state.examples.example3_document_publish;

public class DraftedState extends DocumentState {

    DraftedState(Document doc) {
        super(doc);
    }

    @Override
    void publish() {
        if (this.document.getUser().isAdmin() || this.document.getUser().isAuther()) {
            this.document.changeState(new PublishedState(document));
        } else {
            this.document.changeState(new ModerationState(document));
        }
    }

    @Override
    void render() {
        if (this.document.getUser().isAdmin() || this.document.getUser().isAuther()) {
            this.document.changeState(new PublishedState(document));
            System.out.println("Document rendered");
        } else {
            System.out.println("You don't have right access to render the document");
        }
    }

}
