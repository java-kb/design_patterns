package me.study.designpatterns.behavioral.observer.examples.example1;

import java.util.List;

public interface JobProvider {

    List<JobObserver> getObserverList();

    List<String> getJobList();

    void addJob(String job);

    void addObserver(JobObserver jobObserver);

    void removeObserver(JobObserver jobObserver);

}