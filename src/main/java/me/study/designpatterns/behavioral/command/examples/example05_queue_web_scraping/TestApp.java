package me.study.designpatterns.behavioral.command.examples.example05_queue_web_scraping;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * The client code.
 */
public class TestApp {
    public static void main(String[] args) {
        Queue queue = Queue.get();

        if (queue.isEmpty()) {
            queue.add(new IMDBGenresScrapingCommand());
        }

        queue.work();
    }
}

/**
 * The Command interface declares the main execution method as well as several
 * helper methods for retrieving a command's metadata.
 */
interface Command {
    public void execute();

    public int getId();

    public int getStatus();

    public void setStatus(int status);
}

/**
 * The base web scraping Command defines the basic downloading infrastructure,
 * common to all concrete web scraping commands.
 */
abstract class WebScrapingCommand implements Command {
    int id;

    int status = 0;

    /**
     * @var String URL for scraping.
     */
    String url;

    public WebScrapingCommand(String url) {
        this.url = url;
    }

    public int getId() {
        return this.id;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getURL() {
        return this.url;
    }

    /**
     * Since the execution methods for all web scraping commands are very
     * similar, we can provide a default implementation and let subclasses
     * override them if needed.
     *
     * Psst! An observant reader may spot another behavioral pattern in action
     * here.
     */
    public void execute() {
        var html = this.download();
        this.parse(html);
        this.complete();
    }

    public String download() {
        String html = getPageHtml(this.getURL());
        System.out.println(String.format("WebScrapingCommand: Downloaded %s",url));

        return html;
    }

    private String getPageHtml(String site) {
        // Instantiating the StringBuffer class to hold the result
        StringBuffer sb = new StringBuffer();

          try {
              // Instantiating the URL class
              URL url = new URL(site);
              // Retrieving the contents of the specified page
              URLConnection urlcon=url.openConnection(); 
              urlcon.addRequestProperty("User-Agent", "A");
              Scanner sc = new Scanner(urlcon.getInputStream());
              while (sc.hasNext()) {
                  sb.append(sc.next());
                  // System.out.println(sc.next());
              }
          } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }

        // Retrieving the String from the String Buffer object
        return sb.toString();
    }

    abstract public void parse(String html);

    public void complete() {
        this.status = 1;
        Queue.get().completeCommand(this);
    }
}

/**
 * The Concrete Command for scraping the list of movie genres.
 */
class IMDBGenresScrapingCommand extends WebScrapingCommand {
    public IMDBGenresScrapingCommand() {
        super("https://www.imdb.com/feature/genre/");
    }

    /**
     * Extract all genres and their search URLs from the page:
     * https://www.imdb.com/feature/genre/
     */
    @Override
    public void parse(String html) {
        // preg_match_all("|href=\"(https://www.imdb.com/search/title\?genres=.*?)\"|",
        // html, matches);
        // System.out.println("IMDBGenresScrapingCommand: Discovered " .
        // count($matches[1]) . " genres.\n");

        // foreach ($matches[1] as genre) {
        // Queue::get()->add(new IMDBGenrePageScrapingCommand($genre));
        // }

        Pattern pattern = Pattern.compile("(/search/title\\?genres=.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(html);
        // using Matcher find(), group(), start() and end() methods
        while (matcher.find()) {
            System.out.println("Found the text \"" + matcher.group()
                    + "\" starting at " + matcher.start()
                    + " index and ending at index " + matcher.end());
            Queue.get().add(new IMDBGenrePageScrapingCommand(matcher.group(), 1));
        }
    }
}

/**
 * The Concrete Command for scraping the list of movies in a specific genre.
 */
class IMDBGenrePageScrapingCommand extends WebScrapingCommand {
    private int page = 1;

    public IMDBGenrePageScrapingCommand(String url, int page) {
        super(url);
        this.page = page;
    }

    public String getURL() {
        return this.url + "?page=" + this.page;
    }

    /**
     * Extract all movies from a page like this:
     * https://www.imdb.com/search/title?genres=sci-fi&explore=title_type,genres
     */
    public void parse(String html) {
        // preg_match_all("|href=\"(/title/.*?/)\?ref_=adv_li_tt\"|", html, matches);
        // System.out.println("IMDBGenrePageScrapingCommand: Discovered " .
        // count($matches[1]) . " movies.\n");

        // foreach ($matches[1] as moviePath) {
        // url = "https://www.imdb.com" . moviePath;
        // Queue::get()->add(new IMDBMovieScrapingCommand($url));
        // }
        Pattern pattern = Pattern.compile("href=\\\"(/title/.*?/)\\?ref_=adv_li_tt\\\"", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(html);
        // using Matcher find(), group(), start() and end() methods
        while (matcher.find()) {
            System.out.println("Found the text \"" + matcher.group()
                    + "\" starting at " + matcher.start()
                    + " index and ending at index " + matcher.end());
            Queue.get().add(new IMDBMovieScrapingCommand(matcher.group(), 1));
        }
        // Parse the next page URL.
        // if (preg_match("|Next &#187;</a>|", html)) {
        // Queue::get()->add(new IMDBGenrePageScrapingCommand( this.url, this.page +
        // 1));
        // }
    }
}

/**
 * The Concrete Command for scraping the movie details.
 */
class IMDBMovieScrapingCommand extends WebScrapingCommand {
    private int page = 1;

    public IMDBMovieScrapingCommand(String url, int page) {
        super(url);
        this.page = page;
    }

    /**
     * Get the movie info from a page like this:
     * https://www.imdb.com/title/tt4154756/
     */
    public void parse(String html) {
        // if (preg_match("|<h1 itemprop=\"name\" class=\"\">(.*?)</h1>|", html,
        // matches)) {
        // title = matches[1];
        // }
        // System.out.println("IMDBMovieScrapingCommand: Parsed movie title.\n");

        Pattern pattern = Pattern.compile("<h1 itemprop=\\\"name\\\" class=\\\"\\\">(.*?)</h1>",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(html);
        // using Matcher find(), group(), start() and end() methods
        while (matcher.find()) {
            System.out.println("Found the text \"" + matcher.group()
                    + "\" starting at " + matcher.start()
                    + " index and ending at index " + matcher.end());
            System.out.println("IMDBMovieScrapingCommand: Parsed movie title" + matcher.group());
        }
    }
}

/**
 * The Queue class acts as an Invoker. It stacks the command objects and
 * executes them one by one. If the script execution is suddenly terminated, the
 * queue and all its commands can easily be restored, and you won't need to
 * repeat all of the executed commands.
 *
 * Note that this is a very primitive implementation of the command queue, which
 * stores commands in a local SQLite database. There are dozens of robust queue
 * solution available for use in real apps.
 */
class Queue {
    /**
     * The list with the command history
     */
    private final List<Command> commandList = new ArrayList<>();
    static Queue instance;

    private Queue() {

    }

    /**
     * For our convenience, the Queue object is a Singleton.
     */
    public static Queue get() {

        if (instance == null) {
            instance = new Queue();
        }

        return instance;
    }

    public boolean isEmpty() {
        return commandList.stream().filter(c -> c.getStatus() == 0).count()==0;
    }

    public void add(Command command) {
        commandList.add(command);
    }

    public Command getCommand() {
        return commandList.stream().filter(c -> c.getStatus() == 0).findFirst().get();
    }

    public void completeCommand(Command command) {
        Command c = commandList.stream().filter(c1 -> c1.getId() == command.getId()).findFirst().get();
        c.setStatus(1);
    }

    public void work() {
        while (!this.isEmpty()) {
            Command command = this.getCommand();
            command.execute();
        }
    }

}
