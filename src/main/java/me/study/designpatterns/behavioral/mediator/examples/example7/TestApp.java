package me.study.designpatterns.behavioral.mediator.examples.example7;

import java.util.Locale;

import me.study.designpatterns.utils.ConsoleUtil;

public class TestApp {
    public static void main(String[] args) {
        writeln("BEGIN TESTING MEDIATOR PATTERN");
        writeln("");

        BookMediator mediator = new BookMediator("Gamma, Helm, Johnson, and Vlissides", "Design Patterns");

        BookAuthorColleague author = mediator.getAuthor();
        BookTitleColleague title = mediator.getTitle();

        ConsoleUtil.Title("Original Author and Title: ");
        writeln("author: " + author.getAuthor());
        writeln("title: " + title.getTitle());
        writeln("");

        author.setAuthorLowerCase();

        ConsoleUtil.Title("After Author set to Lower Case: ");
        writeln("author: " + author.getAuthor());
        writeln("title: " + title.getTitle());
        writeln("");

        title.setTitleUpperCase();

        ConsoleUtil.Title("After Title set to Upper Case: ");
        writeln("author: " + author.getAuthor());
        writeln("title: " + title.getTitle());
        writeln("");

        writeln("END TESTING MEDIATOR PATTERN");
    }

    public static void writeln(String line) {
        System.out.println(line);
    }
}

class BookMediator {
    private BookAuthorColleague authorObject;
    private BookTitleColleague titleObject;

    public BookMediator(String author, String title) {
        this.authorObject = new BookAuthorColleague(author, this);
        this.titleObject = new BookTitleColleague(title, this);
    }

    public BookAuthorColleague getAuthor() {
        return this.authorObject;
    }

    public BookTitleColleague getTitle() {
        return this.titleObject;
    }

    // when title or author change case, this makes sure the other
    // stays in sync
    public void change(BookColleague changingClassIn) {
        if (changingClassIn instanceof BookAuthorColleague) {
            if ("upper".equals(changingClassIn.getState())) {
                if (!"upper".equals(this.getTitle().getState())) {
                    this.getTitle().setTitleUpperCase();
                }
            } else if ("lower".equals(changingClassIn.getState())) {
                if (!"lower".equals(this.getTitle().getState())) {
                    this.getTitle().setTitleLowerCase();
                }
            }
        } else if (changingClassIn instanceof BookTitleColleague) {
            if ("upper".equals(changingClassIn.getState())) {
                if (!"upper".equals(this.getAuthor().getState())) {
                    this.getAuthor().setAuthorUpperCase();
                }
            } else if ("lower".equals(changingClassIn.getState())) {
                if (!"lower".equals(this.getAuthor().getState())) {
                    this.getAuthor().setAuthorLowerCase();
                }
            }
        }
    }
}

abstract class BookColleague {
    private BookMediator mediator;

    public BookColleague(BookMediator mediator_in) {
        this.mediator = mediator_in;
    }

    public BookMediator getMediator() {
        return this.mediator;
    }

    public void changed(BookColleague changingClassIn) {
        getMediator().change(changingClassIn);
    }

    public abstract String getState();
}

class BookAuthorColleague extends BookColleague {
    private String author;
    private String state;

    public BookAuthorColleague(String author_in, BookMediator mediator_in) {
        super(mediator_in);
        this.author = author_in;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author_in) {
        this.author = author_in;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state_in) {
        this.state = state_in;
    }

    public void setAuthorUpperCase() {
        this.setAuthor(this.getAuthor().toUpperCase(Locale.getDefault()));
        this.setState("upper");
        this.getMediator().change(this);
    }

    public void setAuthorLowerCase() {
        this.setAuthor(this.getAuthor().toLowerCase(Locale.getDefault()));
        this.setState("lower");
        this.getMediator().change(this);
    }
}

class BookTitleColleague extends BookColleague {
    private String title;
    private String state;

    public BookTitleColleague(String title_in, BookMediator mediator_in) {
        super(mediator_in);
        this.title = title_in;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title_in) {
        this.title = title_in;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state_in) {
        this.state = state_in;
    }

    public void setTitleUpperCase() {
        this.setTitle(this.getTitle().toUpperCase(Locale.getDefault()));
        this.setState("upper");
        this.getMediator().change(this);
    }

    public void setTitleLowerCase() {
        this.setTitle(this.getTitle().toLowerCase(Locale.getDefault()));
        this.setState("lower");
        this.getMediator().change(this);
    }
}
