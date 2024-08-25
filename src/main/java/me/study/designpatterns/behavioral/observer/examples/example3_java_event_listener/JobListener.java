package me.study.designpatterns.behavioral.observer.examples.example3_java_event_listener;

import java.util.EventListener;

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
public interface JobListener extends EventListener {

    /**
     * Gets called when a new job is available
     *
     * @param jobEvent an EventObject
     */
    void updateJob(JobEvent jobEvent);
}
