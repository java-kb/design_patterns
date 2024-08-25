package me.study.designpatterns.behavioral.observer.examples.example2_concurrency_issues_sol2;
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
public class Employee implements ApartmentObserver {

    /**
     * Name of the employee
     */
    public final String name;

    /**
     * Constructor takes name of the employee
     *
     * @param name of the employee
     */
    public Employee(String name) {
        this.name = name;
    }

    /**
     * Gets a job offer and decides whether the employee applies for it or not
     *
     * @param market event source to request additional information from
     */
    @Override
    public void updateFlat(ApartmentMarket market) {
        var randomnumber = (int) (Math.random() * 10);
        var answer = name + " has no interest.";
        // First check, if there's interest in the apartment
        if (randomnumber <= 5) {
            // And then get the details
            var apartment = market.getDetail();
            if (randomnumber < 3)
                answer = name + " is interested in the apartment";
        }
        System.out.println(answer);
    }
}
