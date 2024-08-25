package me.study.designpatterns.behavioral.command.examples.example01_book_trip.version3_command;

public class TourOperator {
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
