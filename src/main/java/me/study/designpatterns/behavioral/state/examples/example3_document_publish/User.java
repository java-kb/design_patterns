package me.study.designpatterns.behavioral.state.examples.example3_document_publish;

public class User {
    String name;

    public User(String name, boolean isAdmin, boolean isAuther) {
        this.name = name;
        this.isAdmin = isAdmin;
        this.isAuther = isAuther;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isAuther() {
        return isAuther;
    }

    public void setAuther(boolean isAuther) {
        this.isAuther = isAuther;
    }

    boolean isAdmin;
    boolean isAuther;
}
