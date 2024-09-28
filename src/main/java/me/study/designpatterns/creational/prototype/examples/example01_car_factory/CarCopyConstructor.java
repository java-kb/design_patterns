package me.study.designpatterns.creational.prototype.examples.example01_car_factory;

import java.util.Objects;

public class CarCopyConstructor  implements Cloneable {

    /**
     * The referenc to the engine
     */
    private Engine engine;
    /**
     * The description of the car's edition
     */
    private Edition edition;
    /**
     * Number of seats
     */
    private final int numberSeats;

    /**
     * Constrnuctor: Gets an engine and some additional values
     *
     * @param engine
     * @param edition
     * @param numberSeats
     */
    CarCopyConstructor(Engine engine, Edition edition, int numberSeats) {
        this.engine = engine;
        this.edition = edition;
        this.numberSeats = numberSeats;
    }

    /**
     * Copy-Konstruktor. Copies itself into a new car
     *
     * @param car the car to be copied
     */
    protected CarCopyConstructor(CarCopyConstructor car) {
        this.engine = new Engine("Bishop Motors", 80, 1.4);
        this.numberSeats = car.numberSeats;
        this.edition = car.edition;
    }

    /**
     * Clone a car simply by calling the superclasses' clone method
     *
     * @return the cloned object
     *
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return new CarCopyConstructor(this);
    }

    /**
     * Return the edition variant
     *
     * @return the model variant
     */
    public Edition getEdition() {
        return edition;
    }

    /**
     * Set the model variant
     *
     * @param edition the new variant
     */
    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    /**
     * Check the engine
     *
     *
     * @return the engine
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * Exchange the car's engine with a new one
     *
     * @param engine the new engine
     */
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    /**
     * The complete description of the car
     *
     * @return text
     */
    @Override
    public String toString() {
        return numberSeats + "-seater passenger car, Edition " + edition + ", Engine: " + engine;
    }

    /**
     * Compare car with another object
     *
     * @param obj to compare with
     *
     * @return if car is equal the other object
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final var other = (CarCopyConstructor) obj;
        if (!Objects.equals(this.engine, other.engine))
            return false;
        if (this.edition != other.edition)
            return false;
        return this.numberSeats == other.numberSeats;
    }

    /**
     * This hashcode calculation is overriden - see annotation at the end of
     * chapter 17.1.1.1
     *
     * @return hashcode of the object
     */
    @Override
    public int hashCode() {
        var hash = 7;
        hash = 37 * hash + Objects.hashCode(this.engine);
        hash = 37 * hash + Objects.hashCode(this.edition);
        hash = 37 * hash + this.numberSeats;
        return hash;
    }
}
