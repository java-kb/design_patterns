package me.study.designpatterns.behavioral.state.examples.example3_document_publish;

public abstract class DocumentState {
    public final Document document;

    DocumentState(Document doc) {
        this.document = doc;
    }

    abstract void publish();
    abstract void render();
     /**
     * It's sufficient to use the class name of the specific state
     *
     * @return the name of the specific state
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
