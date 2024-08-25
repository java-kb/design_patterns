package me.study.designpatterns.behavioral.command.examples.example01_book_trip.version3_command;

import java.time.LocalDate;

public class TravelAgency implements Provider {
    private final TripCommand tripCommand;

    TravelAgency(TripCommand tripCommand) {
        this.tripCommand = tripCommand;
    }

    @Override
    public void bookTrip(String destination,
            LocalDate from, LocalDate to) {
        tripCommand.book(destination, from, to);
    }
}