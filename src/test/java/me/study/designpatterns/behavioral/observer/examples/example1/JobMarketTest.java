package me.study.designpatterns.behavioral.observer.examples.example1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JobMarketTest {
    @Test
    public void testAddJob() {
        Student s = new Student("student1");
        Employee e = new Employee("employe1");
        JobProvider jm = new JobMarket();
        jm.addObserver(s);
        jm.addObserver(e);
        jm.addJob("Softwer Deveo");
        assertEquals(2, jm.getObserverList().size());
        assertEquals(1, jm.getJobList().size());
    }

    @Test
    public void testJobProvider() {
        Student s = new Student("student1");
        Employee e = new Employee("employe1");

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
        assertEquals(2, jobMarket.getObserverList().size());
        assertEquals(1, jobMarket.getJobList().size());
        assertEquals(2, employmentAgency.getObserverList().size());
        assertEquals(1, employmentAgency.getJobList().size());

    }

}
