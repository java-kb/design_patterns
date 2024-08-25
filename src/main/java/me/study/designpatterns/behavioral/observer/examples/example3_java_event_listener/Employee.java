package me.study.designpatterns.behavioral.observer.examples.example3_java_event_listener;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Observer"
 */
public class Employee implements JobListener {

    /**
     * Name of employee
     */
    private final String name;

    /**
     * Gets the name of the employee to create
     *
     * @param name of the employee
     */
    Employee(String name) {
        this.name = name;
    }

    /**
     * Gets called when a new job offer is available
     *
     * @param jobEvent
     */
    @Override
    public void updateJob(JobEvent jobEvent) {
        var source = jobEvent.getSource().toString();
        System.out.println("Employee " + name + " gets noticed about a new job offer as " + jobEvent + " by " + source);
    }
}
