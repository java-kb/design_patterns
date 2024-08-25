package me.study.designpatterns.behavioral.observer.examples.example2;
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
public class Student implements ApartmentObserver {

    /**
     * Name of the student
     */
    public final String name;

    /**
     * Constructor takes name of the student
     *
     * @param name of the student
     */
    public Student(String name) {
        this.name = name;
    }

    /**
     * Gets called when a new apartment is available
     *
     * @param market event source to get additional information from
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
