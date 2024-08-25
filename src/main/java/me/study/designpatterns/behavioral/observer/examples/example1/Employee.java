package me.study.designpatterns.behavioral.observer.examples.example1;

public class Employee implements JobObserver {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void newOffer(String job) {
        var randomnumber = (int) (Math.random() * 10);
        var answer = "Student " + name;
        if (randomnumber <= 5)
            answer = answer + " applies for the job";
        else
            answer = answer + " does not apply";
        System.out.println(answer);
    }
}
