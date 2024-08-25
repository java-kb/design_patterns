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
public interface ApartmentObserver {

    /**
     * Gets called, when a new apartment is to be offered to an observer
     *
     * @param apartmentMarket
     */
    void updateFlat(ApartmentMarket apartmentMarket);
}
