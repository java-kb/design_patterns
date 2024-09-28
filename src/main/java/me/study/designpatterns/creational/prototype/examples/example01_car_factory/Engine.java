package me.study.designpatterns.creational.prototype.examples.example01_car_factory;
import java.util.Objects;

/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Prototype"
 */
public class Engine {

    /**
     * ID of the block
     *
     */
    private String id;
    /**
     * The power
     */
    private int hp;
    /**
     * The cubic capacity
     */
    private double capacity;

    /**
     * Constructor: Get basic values
     *
     * @param id
     * @param hp
     * @param capacity
     */
    Engine(String id, int hp, double capacity) {
        this.id = id;
        this.hp = hp;
        this.capacity = capacity;
    }

    /**
     * Query capacity
     *
     * @return the capacity
     */
    public double getCapacity() {
        return capacity;
    }

    /**
     * Replace capacity (always with more capacity)
     *
     * @param capacity the new capacity
     */
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    /**
     * Query ID
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Set id
     *
     * @param id the new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Query engine power
     *
     * @return the power
     */
    public int getHp() {
        return hp;
    }

    /**
     * Set engine power
     *
     * @param hp the new power
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * A descriptive text of the engine
     *
     * @return text
     */
    @Override
    public String toString() {
        return id + " with " + hp + " HP and " + capacity + " liters capacity";
    }

    /**
     * Compare this engine with another object (which also should be an engine)
     *
     * @param obj the object to compare with
     *
     * @return true, if they are the same engine
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final var other = (Engine) obj;
        // Two possible tests on equality:
        // First, the equals method on the whole object
//        if (!Objects.equals(this.id, other.id))
//            return false;
        // alternatively: compare the
        if (!this.id.equals(other.id))
            return false;
        if (this.hp != other.hp)
            return false;
        return Double.doubleToLongBits(this.capacity) == Double.
                doubleToLongBits(other.capacity);
    }

    /**
     * This hashcode calculation is overriden - see annotation at the end of
     * chapter 17.1.1.1
     *
     * @return Hashcode of the object
     */
    @Override
    public int hashCode() {
        var hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + this.hp;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.capacity) ^ (Double.doubleToLongBits(this.capacity) >>> 32));
        return hash;
    }
}
