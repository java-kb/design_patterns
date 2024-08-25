package me.study.designpatterns.behavioral.observer.examples.example1;

import java.util.ArrayList;
import java.util.List;

public class JobMarket implements JobProvider {
    private final List<JobObserver> observerList = new ArrayList<>();

    @Override
    public List<JobObserver> getObserverList() {
        return observerList;
    }

    @Override
    public List<String> getJobList() {
        return jobList;
    }

    private final List<String> jobList = new ArrayList<>();

    @Override
    public void addJob(String job) {
        jobList.add(job);
        observerList.forEach(tempJobObserver -> {
            tempJobObserver.newOffer(job);
        });
    }

    @Override
    public void addObserver(JobObserver jobObserver) {
        observerList.add(jobObserver);
    }

    @Override
    public void removeObserver(JobObserver jobObserver) {
        observerList.remove(jobObserver);
    }
}
