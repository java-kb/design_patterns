package me.study.designpatterns.behavioral.state.examples.example3_document_publish;

import me.study.designpatterns.utils.ConsoleUtil;

public class TestApp {
    public static void main(String[] args) {
        User normalUser = new User("user", false, false);
        User adminUser = new User("admin", true, false);
        User autherUser = new User("auther", false, true);
        ConsoleUtil.Title("User Normal accesing Document");
        Document doc = new Document(normalUser);
        System.out.print(doc + "\n\t");
        doc.publish();
        doc.render();
        System.out.print(doc + "\n\t");
        ConsoleUtil.Title("User Auther accesing Document");
        System.out.print(doc + "\n\t");
        doc.setUser(autherUser);
        doc.publish();
        doc.render();
        System.out.print(doc + "\n\t");
        ConsoleUtil.Title("User Admin accesing Document");
        System.out.print(doc + "\n\t");
        doc.setUser(adminUser);
        doc.publish();
        doc.render();
        System.out.print(doc + "\n\t");
        // doc = new Document(autherUser);
        // doc.publish();
        // doc.render();
    }
}
