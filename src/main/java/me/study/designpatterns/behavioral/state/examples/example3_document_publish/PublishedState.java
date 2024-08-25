package me.study.designpatterns.behavioral.state.examples.example3_document_publish;

public class PublishedState extends DocumentState {

    PublishedState(Document doc) {
        super(doc);
    }

    @Override
    void publish() {
        System.out.println("Document already published");
    }

    @Override
    void render() {
        if (this.document.getUser().isAdmin() || this.document.getUser().isAuther()) {
            System.out.println("Document rendered");
        } else {
            System.out.println("You don't have right access to render the document");
        }
    }
}
