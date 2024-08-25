package me.study.designpatterns.behavioral.command.examples.example01_book_trip.version3_command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TestApp {
    public static void main(String[] args) {
        TourOperator operatorABC = new TourOperator("ABC-Travel");
        TourOperator operator123 = new TourOperator("123-Vacations");

        TripCommand tripABC = new TripCommand(operatorABC);
        TripCommand trip123 = new TripCommand(operator123);

        Provider agency = new TravelAgency(tripABC);
        Provider branch = new Branch(trip123);

        LocalDate from, to;
        from = toDate("08/01/2023");
        to = toDate("08/15/2023");
        agency.bookTrip("Cairo", from, to);

        from = toDate("12/24/2023");
        to = toDate("01/15/2024");
        branch.bookTrip("Oslo", from, to);

        from = toDate("07/14/2023");
        to = toDate("07/15/2023");
        branch.bookTrip("Paris", from, to);

        from = toDate("10/02/2023");
        to = toDate("10/04/2023");
        agency.bookTrip("Berlin", from, to);
    }

    /**
     * Convert a text to a LocalDate
     *
     * @param date as text in format MM/dd/yyyy
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
