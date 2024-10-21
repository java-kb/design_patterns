package me.study.designpatterns.creational.builder.examples.example06_build_html_page;

public class TestApp {
    public static void main(String[] args) {
        System.out.println("BEGIN TESTING BUILDER PATTERN");
        System.out.println("");

        HTMLPageBuilder pageBuilder = new HTMLPageBuilder();
        HTMLPageDirector pageDirector = new HTMLPageDirector(pageBuilder);
        pageDirector.buildPage();
        HTMLPage page = pageDirector.getPage();
        System.out.println(page.showPage());
        System.out.println("");

        System.out.println("END TESTING BUILDER PATTERN");
    }
}

abstract class AbstractPageBuilder {
    abstract HTMLPage getPage();

    abstract void setTitle(String string);

    abstract void setHeading(String string);

    abstract void setText(String string);

    abstract void formatPage();
}

abstract class AbstractPageDirector {
    protected AbstractPageBuilder builder = null;

    AbstractPageDirector(AbstractPageBuilder builder_in) {
        this.builder = builder_in;
    }

    abstract void buildPage();

    abstract HTMLPage getPage();
}

class HTMLPage {
    private String page = null;
    private String page_title = null;
    private String page_heading = null;
    private String page_text = null;

    HTMLPage() {
    }

    String showPage() {
        return this.page;
    }

    void setTitle(String title_in) {
        this.page_title = title_in;
    }

    void setHeading(String heading_in) {
        this.page_heading = heading_in;
    }

    void setText(String text_in) {
        this.page_text += text_in;
    }

    void formatPage() {
        this.page = "<html>";
        this.page += "<head><title>" + this.page_title + "</title></head>";
        this.page += "<body>";
        this.page += "<h1>" + this.page_heading + "</h1>";
        this.page += this.page_text;
        this.page += "</body>";
        this.page += "</html>";
    }
}

class HTMLPageBuilder extends AbstractPageBuilder {
    private HTMLPage page = null;

    HTMLPageBuilder() {
        this.page = new HTMLPage();
    }

    void setTitle(String title_in) {
        this.page.setTitle(title_in);
    }

    void setHeading(String heading_in) {
        this.page.setHeading(heading_in);
    }

    void setText(String text_in) {
        this.page.setText(text_in);
    }

    void formatPage() {
        this.page.formatPage();
    }

    HTMLPage getPage() {
        return this.page;
    }
}

class HTMLPageDirector extends AbstractPageDirector {

    HTMLPageDirector(AbstractPageBuilder builder_in) {
        super(builder_in);
        // TODO Auto-generated constructor stub
    }

    public void buildPage() {
        this.builder.setTitle("Testing the HTMLPage");
        this.builder.setHeading("Testing the HTMLPage");
        this.builder.setText("Testing, testing, testing!");
        this.builder.setText("Testing, testing, testing, or!");
        this.builder.setText("Testing, testing, testing, more!");
        this.builder.formatPage();
    }

    public HTMLPage getPage() {
        return this.builder.getPage();
    }
}
