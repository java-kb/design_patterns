package me.study.designpatterns.behavioral.command.examples.example01_book_trip.version2;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class TestApp {
    /**
     * Test the interaction of a tour operator and a travel agency by booking a
     * few trips
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        // Create two tour operators
        TourOperator operator_1 = new TourOperator("ABC-Travel");
        TourOperator operator_2 = new TourOperator("123-Vacations");

        // Create two travel providers, an agency and a branch
        // and assign an operator to each one of them
        Provider agency = new TravelAgency(operator_1);
        Provider branch = new Branch(operator_2);

        LocalDate from, to;

        // Book a first trip
        from = toDate("08/01/2023");
        to = from.plusDays(10);
        agency.bookTrip("Cairo", from, to);

        // Book another trip
        from = toDate("12/24/2023");
        to = from.plus(2, ChronoUnit.WEEKS);
        branch.bookTrip("Oslo", from, to);

        // Book another trip
        from = toDate("07/14/2023");
        to = from.plus(Period.of(0, 1, 3));
        branch.bookTrip("Sydney", from, to);

        // Book another trip
        to = toDate("10/04/2023");
        from = to.minusDays(8);
        agency.bookTrip("Berlin", from, to);
    }

    /**
     * Reformats a text into a date
     *
     * @param date as text in the format MM/dd/yyyy
     *
     * @return date as java.time.LocalDate
     */
    private static LocalDate toDate(String date) {
        LocalDate tempDate;

        try {
            tempDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        } catch (DateTimeParseException ex) {
            tempDate = null;
            ex.printStackTrace();
        }
        return tempDate;
    }
}

class Trip {

    final String destination;
    final String from;
    final String to;
    final DateTimeFormatter dtFormatter = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
            .toFormatter();

    /**
     * The constructor needs all the details
     *
     * @param destination where to go
     * @param from        when to start
     * @param to          when to return
     */
    Trip(String destination, LocalDate from, LocalDate to) {
        this.destination = destination;
        this.from = dtFormatter.format(from);
        this.to = dtFormatter.format(to);
    }

    /**
     * For printing out the trip's details
     *
     * @return the details as a string
     */
    @Override
    public String toString() {
        return "Trip to " + destination + " from " + from + " to " + to;
    }
}

interface Provider {
    void bookTrip(String destination, LocalDate from, LocalDate to);
}

class Branch implements Provider {

    /**
     * There's only one known tour operator
     */
    private final TourOperator operator;

    /**
     * Simple constructor
     *
     * @param operator know to this agency
     */
    Branch(TourOperator operator) {
        this.operator = operator;
    }

    /**
     * Book a trip. This is the implementation of the method prescribed in the
     * Provider interfac
     *
     * @param destination of the trip
     * @param from        start date
     * @param to          return date
     */
    @Override
    public void bookTrip(String destination, LocalDate from, LocalDate to) {
        Trip trip = new Trip(destination, from, to);
        operator.execute(trip);
    }
}

class TravelAgency implements Provider {

    /**
     * There's one known tour operator at the travel agency
     */
    private final TourOperator operator;

    /**
     * Simple Constructor
     *
     * @param operator object of the tour operator
     */
    TravelAgency(TourOperator operator) {
        this.operator = operator;
    }

    /**
     * Book a trip, the implementation
     *
     * @param destination of the trip
     * @param from        start date
     * @param to          return date
     */
    @Override
    public void bookTrip(String destination, LocalDate from, LocalDate to) {
        Trip trip = new Trip(destination, from, to);
        operator.execute(trip);
    }
}

class TourOperator {

    /**
     * Name of the operator
     */
    private final String company;

    /**
     * Simple constructor
     *
     * @param company name of the operator
     */
    TourOperator(String company) {
        this.company = company;
    }

    /**
     * The necessary steps to operate the whole trip
     *
     * @param trip the trip object
     */
    void execute(Trip trip) {
        System.out.println(company + " operates: " + trip);
    }
}
