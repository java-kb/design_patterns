package me.study.designpatterns.behavioral.observer.examples.example3_java_event_listener;

import me.study.designpatterns.utils.ConsoleUtil;

/**
 *
 * @author Olaf Musch
 *
 *         Design Patterns with Java
 *
 *         An Introduction, Springer Vieweg
 *
 *         chapter "Observer"
 */
public class TestApp {
    /*
     * The serializable class EventObject from the package java.util expects a
     * reference to the object that sends the event as a parameter in
     * the constructor. The getSource() method returns this source. The toString
     * method from the Object class is meaningfully overridden. The interface
     * EventListener from the same package does not prescribe any methods, but only
     * defines a role.
     * In the ListenerDemo sample project, there is a JobEvent class that extends
     * EventObject. It expects the source and a string object that describes the new
     * job as
     * parameters. Of course, you could also provide a flat here instead of the
     * string. The source
     * is passed on to the Super class, the job is stored in its own data field.
     */
    /**
     * Creates a job agency, two employees and two job offerings
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        var agency = new JobAgency();
        var john = new Employee("John Doe");
        var joe = new Student("Joe Average");
        agency.addJobListener(john);
        agency.addJobListener(joe);
        agency.addJob("waiter");
        agency.addJob("DJ");
    }
}
