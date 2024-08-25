package me.study.designpatterns.behavioral.observer.examples.example2_concurrency_issue;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Olaf Musch
 *
 *         Design Patterns with Java
 *
 *         An Introduction, Springer Vieweg
 *
 *         chapter "Observer"
 */
public class ApartmentMarket {

    /**
     * List of observers
     */
    private final List<ApartmentObserver> observerList = new ArrayList<>();
    /**
     * Newest apartment offered
     */
    private Apartment apartment;

    /**
     * Gets an apartment offer and informs the observers
     *
     * @param apartment the new apartment
     */
    public void addApartment(Apartment apartment) {
        this.apartment = apartment;
        observerList.forEach((tempObserver) -> {
            tempObserver.updateFlat(this);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {

            }
        });
    }

    /**
     * Returns the details (in this case, the whole object) of an apartment
     *
     * @return the newest apartment object
     */
    public Apartment getDetail() {
        return apartment;
    }

    /**
     * Adds a new obsever to the list
     *
     * @param observer the observer to be added
     */
    public void addObserver(ApartmentObserver observer) {
        observerList.add(observer);
    }

    /**
     * Removes an observer from the list
     *
     * @param observer to be removed
     */
    public void removeObserver(ApartmentObserver observer) {
        observerList.remove(observer);
    }
}
