package me.study.designpatterns.behavioral.command.examples.example01_book_trip.version3_command;

import java.time.LocalDate;

public interface Provider {
    void bookTrip(String destination, LocalDate from, LocalDate to);
}
