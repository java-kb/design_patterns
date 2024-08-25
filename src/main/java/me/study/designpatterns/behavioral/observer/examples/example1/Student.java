package me.study.designpatterns.behavioral.observer.examples.example1;

public class Student implements JobObserver {
    private String name;

    public Student(String name) {
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
        if (randomnumber <= 8)
            answer = answer + " applies for the job";
        else
            answer = answer + " does not apply";
        System.out.println(answer);
    }
}