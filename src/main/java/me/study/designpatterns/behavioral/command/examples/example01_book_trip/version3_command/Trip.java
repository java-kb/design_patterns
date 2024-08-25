package me.study.designpatterns.behavioral.command.examples.example01_book_trip.version3_command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Trip {
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
