package me.study.designpatterns.behavioral.observer.examples.example3_java_event_listener;

import java.util.EventObject;

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
public final class JobEvent extends EventObject {

    /**
     * Name of job
     */
    private final String job;

    /**
     * Creates a new EventObject
     *
     * @param jobProvider
     * @param job
     */
    JobEvent(Object jobProvider, String job) {
        // Pushes the event source up to the constructor of EventObject
        super(jobProvider);
        this.job = job;
    }

    /**
     *
     * @return job description
     */
    @Override
    public String toString() {
        return job;
    }
}
