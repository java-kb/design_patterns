package me.study.designpatterns.behavioral.command.examples.example01_book_trip.version3_command;

import java.time.LocalDate;

public class TripCommand {
    private final TourOperator operator;

    public TripCommand(TourOperator operator) {
        this.operator = operator;
    }

    public void book(String destination, LocalDate from,
            LocalDate to) {
        Trip trip = new Trip(destination, from, to);
        operator.execute(trip);
    }
}