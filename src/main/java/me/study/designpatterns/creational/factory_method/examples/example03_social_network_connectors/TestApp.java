package me.study.designpatterns.creational.factory_method.examples.example03_social_network_connectors;

public class TestApp {

    public static void main(String[] args) {
        /**
         * During the initialization phase, the app can decide which social network it
         * wants to work with, create an object of the proper subclass, and pass it to
         * the client code.
         */
        System.out.println("Testing Facebook Creator:");
        clientCode(new FacebookPoster("john_smith", "******"));
        System.out.println();

        System.out.println("Testing LinkedIn Creator:");
        clientCode(new LinkedInPoster("john_smith@example.com", "******"));
    }

    /**
     * The client code can work with any subclass of SocialNetworkPoster since it
     * doesn't depend on concrete classes.
     */
    public static void clientCode(SocialNetworkPoster creator) {
        creator.post("Hello world!");
        creator.post("I had a large hamburger this morning!");
    }
}

/**
 * Factory Method Design Pattern
 *
 * Intent: Provides an interface for creating objects in a superclass, but
 * allows subclasses to alter the type of objects that will be created.
 *
 * Example: In this example, the Factory Method pattern provides an interface
 * for creating social network connectors, which can be used to log in to the
 * network, create posts and potentially perform other activities—and all of
 * this without coupling the client code to specific classes of the particular
 * social network.
 */

/**
 * The Creator declares a factory method that can be used as a substitution for
 * the direct constructor calls of products, for instance:
 *
 * - Before: $p = new FacebookConnector;
 * - After: $p = $this->getSocialNetwork;
 *
 * This allows changing the type of the product being created by
 * SocialNetworkPoster's subclasses.
 */
abstract class SocialNetworkPoster {
    /**
     * When the factory method is used inside the Creator's business logic, the
     * subclasses may alter the logic indirectly by returning different types of
     * the connector from the factory method.
     */
    public void post(String content) {
        SocialNetworkConnector network = getSocialNetwork();
        network.logIn();
        network.createPost(content);
        network.logOut();
    }

    /**
     * The actual factory method. Note that it returns the abstract connector.
     * This lets subclasses return any concrete connectors without breaking the
     * superclass' contract.
     */
    public abstract SocialNetworkConnector getSocialNetwork();
}

/**
 * This Concrete Creator supports Facebook. Remember that this class also
 * inherits the 'post' method from the parent class. Concrete Creators are the
 * classes that the Client actually uses.
 */
class FacebookPoster extends SocialNetworkPoster {
    private String login;
    private String password;

    public FacebookPoster(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public SocialNetworkConnector getSocialNetwork() {
        return new FacebookConnector(login, password);
    }
}

/**
 * This Concrete Creator supports LinkedIn.
 */
class LinkedInPoster extends SocialNetworkPoster {
    private String email;
    private String password;

    public LinkedInPoster(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public SocialNetworkConnector getSocialNetwork() {
        return new LinkedInConnector(email, password);
    }
}

/**
 * The Product interface declares behaviors of various types of products.
 */
interface SocialNetworkConnector {
    void logIn();

    void logOut();

    void createPost(String content);
}

/**
 * This Concrete Product implements the Facebook API.
 */
class FacebookConnector implements SocialNetworkConnector {
    private String login;
    private String password;

    public FacebookConnector(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void logIn() {
        System.out.println("Send HTTP API request to log in user " + login + " with password " + password);
    }

    public void logOut() {
        System.out.println("Send HTTP API request to log out user " + login);
    }

    public void createPost(String content) {
        System.out.println("Send HTTP API requests to create a post in Facebook timeline.");
    }
}

/**
 * This Concrete Product implements the LinkedIn API.
 */
class LinkedInConnector implements SocialNetworkConnector {
    private String email;
    private String password;

    public LinkedInConnector(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void logIn() {
        System.out.println("Send HTTP API request to log in user " + email + " with password " + password);
    }

    public void logOut() {
        System.out.println("Send HTTP API request to log out user " + email);
    }

    public void createPost(String content) {
        System.out.println("Send HTTP API requests to create a post in LinkedIn timeline.");
    }
}
