package me.study.designpatterns.behavioral.memento.examples.example01_car.datamodel;

import me.study.designpatterns.behavioral.memento.examples.example01_car.memento.Memento;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Memento"
 */
public class Car {

    /**
     * Inner class with a separate representation of the outer class's state
     */
    private class CarMemento implements Memento {

        /**
         * Speed
         */
        private final int tempo = speed;

        /**
         * Fuel consumption
         */
        private final int thirst = currentFuelConsumption;
    }

    /**
     * The speed of the car, initially 0
     */
    private int speed = 0;

    /**
     * The fuel consumption, initially 0
     */
    private int currentFuelConsumption = 0;

    /**
     * Speed up quite a bit, recalculate fuel consumption
     */
    public void driveMuchFaster() {
        speed += 10;
        calculateFuelConsumption();
    }

    /**
     * Speed up slightly, recalculate fuel consumption
     */
    public void driveFaster() {
        speed++;
        calculateFuelConsumption();
    }

    /**
     * Slow down, recalculate fuel consumption
     */
    public void driveSlower() {
        if (speed > 0) {
            speed--;
            calculateFuelConsumption();
        }
    }

    /**
     * Calculate fuel consumption, a simple (and not always appropriate) method
     */
    private void calculateFuelConsumption() {
        var randomValue = (Math.random()) + 1.0;
        this.currentFuelConsumption = (int) (randomValue * speed);
    }

    /**
     * Print out properties
     *
     * @return text
     */
    @Override
    public String toString() {
        return "Car is driving with a speed of " + speed + " mph"
                + "\nCurrent fuel consumption: " + currentFuelConsumption + " liters on 100 km";
    }

    /**
     * Create a memento
     *
     * @return a new memento with the current state of the car
     */
    public Memento createMemento() {
        return new CarMemento();
    }

    /**
     * Set the state according to the values of a given memento
     *
     * @param memento the memento with the new properties
     */
    public void setMemento(Memento memento) {
        var myMemento = (CarMemento) memento;
        this.currentFuelConsumption = myMemento.thirst;
        this.speed = myMemento.tempo;
    }
}
