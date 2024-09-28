package me.study.designpatterns.creational.prototype.examples.example01_car_factory;
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
public class TestApp {

    /**
     * Build the engine and create a car with it. Then clone the car and check.
     *
     * @param args are ignored
     *
     * @throws CloneNotSupportedException
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        var engine = new Engine("General Motors", 100, 1.6);
        var prototype = new Car(engine, Edition.TINY, 4);
        System.out.println("Prototype: " + prototype);
        var newCar = (Car) prototype.clone();
        System.out.println("New car: " + newCar + "\n");

        // Tests
        System.out.println("Test on referencial identity (==): " + (newCar == prototype));
        System.out.println("Class of prototype: " + prototype.getClass());
        System.out.println("Class of newCar: " + newCar.getClass());
        System.out.println("Test on equality (equals): " + newCar.equals(prototype) + "\n");

        // Change engine data
        engine = prototype.getEngine();
        engine.setId("Marshall Motors");
        System.out.println("Prototype: " + prototype);
        System.out.println("New Car: " + newCar);


        //
        var prototype1 = new CarCopyConstructor(engine, Edition.TINY, 4);
        var newCarCopyConstructor= (CarCopyConstructor) prototype1.clone();
        System.out.println("New car: " + newCarCopyConstructor + "\n");

        // Teste
        System.out.println("Test on referencial identity (==): " + (newCarCopyConstructor == prototype1));
        System.out.println("Class of prototype: " + prototype.getClass());
        System.out.println("Class of newCarCopyConstructor: " + newCarCopyConstructor.getClass());
        System.out.println("Test on equality (equals): " + newCarCopyConstructor.equals(prototype1) + "\n");

        // Change engine data
        engine = prototype1.getEngine();
        engine.setId("Marshall Motors");
        System.out.println("Prototyp: " + prototype1);
        System.out.println("Neues Auto: " + newCarCopyConstructor);
    }
}
