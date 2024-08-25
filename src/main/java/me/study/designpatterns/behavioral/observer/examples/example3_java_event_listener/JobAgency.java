package me.study.designpatterns.behavioral.observer.examples.example3_java_event_listener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
public class JobAgency {

    /**
     * The active listeners
     */
    private final List<JobListener> listener = new CopyOnWriteArrayList<>();

    /**
     * Creates a new JobEvent and provides it to all listeners
     *
     * @param newJob
     */
    public void addJob(String newJob) {
        var jobEvent = new JobEvent(this, newJob);
        listener.forEach((tempListener) -> {
            tempListener.updateJob(jobEvent);
        });
    }

    /**
     * Registers a new listener
     *
     * @param jobListener
     */
    public void addJobListener(JobListener jobListener) {
        listener.add(jobListener);
    }

    /**
     * Removes a listener from list
     *
     * @param jobListener
     */
    public void removeJobListener(JobListener jobListener) {
        listener.remove(jobListener);
    }

    /**
     * @return the name
     */
    @Override
    public String toString() {
        return "Job agency";
    }
}
