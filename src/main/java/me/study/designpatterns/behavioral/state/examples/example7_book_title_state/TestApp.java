package me.study.designpatterns.behavioral.state.examples.example7_book_title_state;

import java.util.*;
import java.util.function.Consumer;

import me.study.designpatterns.utils.ConsoleUtil;

public class TestApp {
    public static void main(String[] args) {
        Consumer<String> writeln = (line_in) -> System.out.println(line_in);

        ConsoleUtil.Title("BEGIN TESTING STATE PATTERN");
        writeln.accept("");

        Book book = new Book("Java for Cats", "Larry Truett");
        BookContext context = new BookContext(book);

        ConsoleUtil.Title("test 1 - show name");
        writeln.accept(context.getBookTitle());
        writeln.accept("");

        ConsoleUtil.Title("test 2 - show name");
        writeln.accept(context.getBookTitle());
        writeln.accept("");

        ConsoleUtil.Title("test 3 - show name");
        writeln.accept(context.getBookTitle());
        writeln.accept("");

        ConsoleUtil.Title("test 4 - show name");
        writeln.accept(context.getBookTitle());
        writeln.accept("");

        ConsoleUtil.Title("END TESTING STATE PATTERN");
    }
}

class BookContext {
    private Book book = null;
    private BookTitleState bookTitleState = null;

    public BookContext(Book book_in) {
        this.book = book_in;
        this.setTitleState(new BookTitleStateStars());
    }

    public String getBookTitle() {
        return this.bookTitleState.showTitle(this);
    }

    public Book getBook() {
        return this.book;
    }

    public void setTitleState(BookTitleState titleState_in) {
        this.bookTitleState = titleState_in;
    }
}

interface BookTitleState {
    String showTitle(BookContext context_in);
}

class BookTitleStateExclaim implements BookTitleState {
    private int titleCount = 0;

    public String showTitle(BookContext context_in) {
        String title = context_in.getBook().getTitle();
        this.titleCount++;
        context_in.setTitleState(new BookTitleStateStars());
        return title.replace(' ', '!');
    }
}

class BookTitleStateStars implements BookTitleState {
    private int titleCount = 0;

    public String showTitle(BookContext context_in) {
        String title = context_in.getBook().getTitle();
        this.titleCount++;
        if (1 < this.titleCount) {
            context_in.setTitleState(new BookTitleStateExclaim());
        }
        return title.replace(' ', '*');
    }
}

class Book {
    private String author;
    private String title;

    public Book(String title_in, String author_in) {
        this.author = author_in;
        this.title = title_in;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthorAndTitle() {
        return this.getTitle() + " by " + this.getAuthor();
    }
}
