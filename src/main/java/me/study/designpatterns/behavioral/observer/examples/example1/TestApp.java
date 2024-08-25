package me.study.designpatterns.behavioral.observer.examples.example1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Demo class. Everything comes together here.
 */
public class TestApp {
    public static void main(String[] args) throws IOException {
        Student s = new Student("student1");
        Employee e = new Employee("employe1");
        JobProvider jm = new JobMarket();
        jm.addObserver(s);
        jm.addObserver(e);
        jm.addJob("Softwer Deveo");

        var jobMarket = new JobMarket();
        var employmentAgency = new EmploymentAgency();
        var providers = new JobProvider[] {
                jobMarket, employmentAgency
        };
        // … abridged
        jobMarket.addObserver(s);
        jobMarket.addObserver(e);
        employmentAgency.addObserver(s);
        employmentAgency.addObserver(e);
        var job = "temp in theater";
        System.out.println("\nNew job: " + job);
        for (var tempProvider : providers)
            tempProvider.addJob(job);
    }
}