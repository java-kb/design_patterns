The sample project Observer_01 demonstrates a first realization of the pattern. You have someone who wants to announce news, an event source, in the example a job market. This class is
able to register objects of the type JobObserver as interested parties, or observers; this type
is defined by the interface JobObserver, which prescribes the method newOffer().
```java
public interface JobObserver {
    void newOffer(String job);
}
```
The event source, i.e., the job market, defines three main methods: one to register
observers and one to deregister them. Also, a method to save a new job and inform all
observers about it. Saving jobs is not actually required in the sample implementation. But
if you want to add evaluations about the reported jobs in the job market, for example, you
will of course need it.
```java
public class JobMarket {
    private final List<JobObserver> observerList = new ArrayList<>();

    public List<JobObserver> getObserverList() {
        return observerList;
    }

    public List<String> getJobList() {
        return jobList;
    }

    private final List<String> jobList = new ArrayList<>();

    public void addJob(String job) {
        jobList.add(job);
        observerList.forEach(tempJobObserver -> {
            tempJobObserver.newOffer(job);
        });
    }

    public void addObserver(JobObserver jobObserver) {
        observerList.add(jobObserver);
    }

    public void removeObserver(JobObserver jobObserver) {
        observerList.remove(jobObserver);
    }
}
```
You can see in the code above that the information to all observers runs through the
forEach method. And for each tempJobObserver from this list, the newOffer() method
assigned via the lambda expression is then called. You can of course also use a common
for loop at this point:
for(JobObserver temJobObserver : observerList)
tempJobObserver.updateJob(job);
It provides the same functionality. The difference is that the for loop is an external iterator (which you write), while the forEach method is an internal iterator (which you only call
because it’s already built in). forEach also works with sets, queues, and maps, by the way.
The observers define what happens when the method newOffer() is called. The
parameter of this method is the new job. In the example, the method randomly decides
whether the observer applies for the job or not. Below you can see the abbreviated listing
for the Student class. In addition, you will also find the class Employee in the project;
this is to model employees who are looking for a part-time job. They, too, must implement
the JobObserver interface and thus their own version of the newOffer() method. In
the example, I only made a small difference: students apply for a new job with 80% probability, employees only with 50% probability. After all, they already have a job.
```java
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
```
The test program, which you will find in the project, creates two JobObservers and
tests which of the two is informed about a new job under which circumstances. The
expected console outputs can be found in the comments of the main method of the
test driver.