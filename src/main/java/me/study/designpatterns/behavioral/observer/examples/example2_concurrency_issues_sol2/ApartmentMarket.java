package me.study.designpatterns.behavioral.observer.examples.example2_concurrency_issues_sol2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
    // private final List<ApartmentObserver> observerList = new ArrayList<>();
    private final List<ApartmentObserver> observerList = new CopyOnWriteArrayList<>();
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
        List<ApartmentObserver> tempList;
        /*
         * If you do not want to copy by yourself, you can also use a thread-safe
         * CopyOnWriteArrayList
         * However, working with a CopyOnWriteArrayList is comparatively expensive,
         * because whenever a new element is to be inserted or an existing one deleted,
         * the complete
         * database is copied. In this respect, the same criticism applies as in the
         * Observer_07 project. However, there is no trivial solution to this problem,
         * and at least the implementation
         * in the library java.util.concurrent is very efficient; you don’t have to
         * worry
         * about the exact implementation yourself. In common practice, the best
         * solution is probably to resort to a CopyOnWriteArrayList.
         */
        this.apartment = apartment;
        for (var tempObserver : observerList) {
            tempObserver.updateFlat(this);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                //
            }
        }
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
