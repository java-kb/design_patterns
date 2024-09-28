package me.study.designpatterns.creational.prototype.examples.example04_clone_page_object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class TestApp {
    public static void main(String[] args) {
        Author author = new Author("John Smith");
        Page page = new Page("Tip of the day", "Keep calm and carry on.", author);

        page.addComment("Nice tip, thanks!");

        Page draft = page.clone();
        System.out.println("Dump of the clone. Note that the author is now referencing two objects.");
        System.out.println(draft);
    }
}

class Page {
    private String title;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Author getAuthor() {
        return author;
    }

    public List<String> getComments() {
        return comments;
    }

    public LocalDateTime getDate() {
        return date;
    }

    private String body;
    private Author author;
    private List<String> comments;
    private LocalDateTime date;

    public Page(String title, String body, Author author) {
        this.title = title;
        this.body = body;
        this.author = author;
        this.author.addToPage(this);
        this.comments = new ArrayList<>();
        this.date = LocalDateTime.now();
    }

    public void addComment(String comment) {
        this.comments.add(comment);
    }

    public Page clone() {
        Page clone = new Page("Copy of " + this.title, this.body, this.author);
        // this.author.addToPage(clone);
        clone.comments = new ArrayList<>();
        clone.date = LocalDateTime.now();
        return clone;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        builder.append(this.getClass().getName() + "\n");
        builder.append("  title='" + title + "\n");
        builder.append("  body='" + body + "\n");
        builder.append("  date='" + date + "\n");
        builder.append("  comments=\n");
        for (var comment : comments)
            builder.append(comment + "\n");

        builder.append("  author=" + author + "\n");

        return builder.toString();
    }
}

class Author {
    private String name;
    private List<Page> pages;

    public Author(String name) {
        this.name = name;
        this.pages = new ArrayList<>();
    }

    public void addToPage(Page page) {
        this.pages.add(page);
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        builder.append("  " + this.getClass().getName() + "\n");
        builder.append("    " + "name='" + name + "\n");

        builder.append("    pages=\n");
        for (var page : pages)
            builder.append("    " + page.getTitle() + "\n");
        return builder.toString();
    }
}
