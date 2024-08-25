package me.study.designpatterns.behavioral.command.examples.example01_book_trip.version1;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

public class TestApp {
    public static void main(String[] args) {
        // Create a tour operator
        var operator = new TourOperator("ABC-Travel");

        // Create an agency for this operator
        var travelAgency = new TravelAgency(operator);

        // Two variables for the dates
        LocalDate from, to;

        // Prepare and book a trip
        from = LocalDate.of(2023, Month.NOVEMBER, 4);
        // Until the fifteenth of the same month
        to = from.withDayOfMonth(15);
        travelAgency.bookTrip("Washington D.C.", from, to);

        // Prepare and book a second trip
        from = toDate("12/30/2023");
        // Until the next upcoming tuesday
        to = from.with(nextOrSame(DayOfWeek.TUESDAY));
        travelAgency.bookTrip("Rom", from, to);

        // The next trip
        from = toDate("10/02/2023");
        // Duration: exactly two weeks
        to = from.plusWeeks(2);
        travelAgency.bookTrip("Peking", from, to);
    }

    /**
     * Transforms a text into a LocalDate type
     *
     * @param date as text with format MM/dd/yyyy
     *
     * @return date as java.time.LocalDate
     */
    private static LocalDate toDate(String date) {
        LocalDate tempDate;

        try {
            // tempDate = LocalDate.parse(datum, ofLocalizedDate(FormatStyle.MEDIUM));
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
    final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    // Depending on your locale settings, the MEDIUM-Format-Style would also work:
    // For this, you would need to import
    // java.time.format.FormatStyle
    // final DateTimeFormatter dtFormatter =
    // DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);

    /**
     * The constructor needs all the information
     *
     * @param destination of the trip
     * @param from        start date
     * @param to          end date
     */
    Trip(String destination, LocalDate from, LocalDate to) {
        this.destination = destination;
        // Two possible ways to adjust the date format
        // The date formats itself using the formatter
        this.from = from.format(dtFormatter);
        // The formatter formats the date
        this.to = dtFormatter.format(to);
    }

    /**
     * Print out the trip data in one row
     *
     * @return all the data as one string
     */
    @Override
    public String toString() {
        return "Trip to " + destination + " from " + from + " to " + to;
    }
}

class TourOperator {

    /**
     * Name of tour operator
     */
    private final String company;

    /**
     * Simple constructor
     *
     * @param company name of operator
     */
    TourOperator(String company) {
        this.company = company;
    }

    /**
     * The tasks to operate the whole trip
     *
     * @param trip the travel information
     */
    void execute(Trip trip) {
        System.out.println(company + " operates"
                + " the following voyage: " + trip);
    }

    /**
     * Return the company's name
     *
     * @return operator name
     */
    @Override
    public String toString() {
        return company;
    }
}

class TravelAgency {

    /**
     * In the agency, there's only one known tour operator
     */
    private final TourOperator operator;

    /**
     * Simple constructor
     *
     * @param operator the one responsible operator
     */
    TravelAgency(TourOperator operator) {
        this.operator = operator;
    }

    /**
     * Book a trip
     *
     * @param destination where to go
     * @param from        start date
     * @param to          return date
     */
    void bookTrip(String destination, LocalDate from, LocalDate to) {
        // Create a trip object with all the data
        var trip = new Trip(destination, from, to);
        // Let the operator execute the trip operations
        operator.execute(trip);
    }
}
