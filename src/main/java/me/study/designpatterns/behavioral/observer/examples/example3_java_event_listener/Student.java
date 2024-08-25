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
public class Student implements JobListener {

    /**
     * Name of student
     */
    private final String name;

    /**
     * Gets the name of the student to create
     *
     * @param name of new student
     */
    Student(String name) {
        this.name = name;
    }

    /**
     * Gets called when a new job is available
     *
     * @param jobEvent
     */
    @Override
    public void updateJob(JobEvent jobEvent) {
        var source = jobEvent.getSource().toString();
        System.out.println(source + " informs the student " + name + " about a new job opportunity as " + jobEvent + ".");
    }
}
