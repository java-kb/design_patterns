package me.study.designpatterns.creational.builder.examples.example01_trip;

import java.time.LocalDate;

public class TestApp {
    /**
     * Create a Trip object via a builder and then set several other properties
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        var trip = Trip.builder(LocalDate.now(), LocalDate.now().plusDays(15), 15, 2)
                .minimumStars(3)
                .rating(5)
                .numberKids(0)
                .build();
        System.out.println(trip);
    }
}
