package me.study.designpatterns.structural.flyweight.examples.example01_birthrates_flyweight;

import java.time.LocalDate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;

public class TestApp {
    /**
     * Specifies some kids and evaluates the factory
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        var factory = new Factory();
        var child_1 = factory.getChild("Jack", "Miles", LocalDate.now());
        var child_2 = factory.getChild("Peter", "Smith", LocalDate.now());
        var child_3 = factory.getChild("Jack", "Darby", LocalDate.now());
        var child_4 = factory.getChild("Frank", "Miller", LocalDate.now());
        var child_5 = factory.getChild("Peter", "Burke", LocalDate.now());
        var child_6 = factory.getChild("John", "Miles", LocalDate.now());
        factory.evaluate();
    }
}

/**
 *
 * @author Olaf Musch
 *
 *         Design Patterns with Java
 *
 *         An Introduction, Springer Vieweg
 *
 *         chapter "Flyweight"
 */
class Child {

    /**
     * A unique id
     */
    private final long id;
    /**
     * A forename
     */
    private final String forename;
    /**
     * A surname
     */
    private final String surename;
    /**
     * The date of birth
     */
    private final LocalDate dob;

    /**
     * Constructor. Gets the properties. Id is generated
     *
     * @param forename first name
     * @param surname  family name
     * @param dob      date of birth
     */
    public Child(String forename, String surname, LocalDate dob) {
        this.forename = forename;
        this.surename = surname;
        this.dob = dob;
        id = (long) ((Long.MAX_VALUE * Math.random())
                * (forename.hashCode()
                        + surname.hashCode()
                        + dob.hashCode()));
    }
}

/**
 *
 * @author Olaf Musch
 *
 *         Design Patterns with Java
 *
 *         An Introduction, Springer Vieweg
 *
 *         chapter "Flyweight"
 */
class Factory {

    /**
     * A list of all occuring forenames
     */
    private final HashSet<String> forenameSet = new HashSet<>();
    /**
     * A list of all occuring surenames
     */
    private final HashSet<String> surenameSet = new HashSet<>();
    /**
     * A list of all occuring dates of birth
     */
    private final HashSet<LocalDate> dobSet = new HashSet<>();

    /**
     * The factory doesn't store duplicate elements, but adds some, if necessary
     *
     * @param forename first name
     * @param surename family name
     * @param dob      date of birth
     *
     * @return the new child object
     */
    Child getChild(String forename, String surename, LocalDate dob) {
        // Add element to their respective sets, if not yet existing
        forenameSet.add(forename);
        surenameSet.add(surename);
        dobSet.add(dob);

        return new Child(forename, surename, dob);
    }

    /**
     * Prints out all stored name elements
     */
    void evaluate() {
        System.out.println("Number of stored first names: "
                + forenameSet.size());
        forenameSet.forEach(tempforename -> {
            System.out.println("\t- " + tempforename);
        });

        System.out.println("\nNumber of stored family names: "
                + surenameSet.size());
        surenameSet.forEach(tempsurname -> {
            System.out.println("\t- " + tempsurname);
        });

        System.out.println("\nNumber of stored birthdates: "
                + dobSet.size());
        dobSet.forEach(tempdob -> {
            System.out.println("\t- " + tempdob.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        });

    }
}
