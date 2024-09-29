package me.study.designpatterns.creational.builder.examples.example01_trip;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Olaf Musch
 *
 *         Design Patterns with Java
 *
 *         An Introduction, Springer Vieweg
 *
 *         chapter "Builder"
 */
public class Trip {

    /**
     * Inner class for building a trip
     */
    public static class Builder {

        /**
         * Starting date of the trip
         */
        private LocalDate startDate;
        /**
         * Final day of the trip
         */
        private LocalDate endDate;
        /**
         * Duration of the trip in days
         */
        private int duration;
        /**
         * Number of travelling people
         */
        private int numberTravellers;
        /**
         * Number of kids (NOT included in the number of travelling people)
         */
        private int numberKids = 0;
        /**
         * Number of stars, the hotel should
         */
        private int minimumStars = 0;
        /**
         * The minimum recommomendation rate, the hotel should have
         */
        private int minimumRecommendations = 0;
        /**
         * The total rating the hotel should have
         */
        private int rating = 0;
        /**
         * The minimum number of ratings, the hotel should have
         */
        private int minimumNumberRatings = 0;

        /**
         * Constructor. Just gets the basic properties
         *
         * @param startDate
         * @param endDate
         * @param duration
         * @param numberTravellers
         */
        public Builder(LocalDate startDate, LocalDate endDate, int duration,
                int numberTravellers) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.duration = duration;
            this.numberTravellers = numberTravellers;
        }

        /**
         * Set number of children
         *
         * @param numberKids
         *
         * @return a builder again
         */
        public Builder numberKids(int numberKids) {
            this.numberKids = numberKids;
            return this;
        }

        /**
         * Set number of stars required
         *
         * @param minimumStars
         *
         * @return a builder again
         */
        public Builder minimumStars(int minimumStars) {
            this.minimumStars = minimumStars;
            return this;
        }

        /**
         * Set recommendation rate
         *
         * @param minimumRecommendations
         *
         * @return a builder again
         */
        public Builder minimumRecommendations(int minimumRecommendations) {
            this.minimumRecommendations = minimumRecommendations;
            return this;
        }

        /**
         * Set average rating
         *
         * @param rating
         *
         * @return a builder again
         */
        public Builder rating(int rating) {
            this.rating = rating;
            return this;
        }

        /**
         * Set minimum number of ratings
         *
         * @param minimumNumberRatings
         *
         * @return a builder again
         */
        public Builder minimumNumberRatings(int minimumNumberRatings) {
            this.minimumNumberRatings = minimumNumberRatings;
            return this;
        }

        /**
         * Really calls the constructor for the trip
         *
         * @return the new Trip
         */
        public Trip build() {
            return new Trip(this);
        }
    }

    /**
     * Starting date of the trip
     */
    private final LocalDate startDate;
    /**
     * Final day of the trip
     */
    private final LocalDate endDate;
    /**
     * The starting date as text
     */
    public final String start;
    /**
     * The final date as text
     */
    public final String end;
    /**
     * Duration of the trip in days
     */
    public final int duration;
    /**
     * Number of travelling people
     */
    public final int numberTravellers;
    /**
     * Number of kids (NOT included in the number of travelling people)
     */
    public final int numberKids;
    /**
     * Number of stars, the hotel should
     */
    public final int minimumStars;
    /**
     * The minimum recommomendation rate, the hotel should have
     */
    public final int minimumRecommendations;
    /**
     * The total rating the hotel should have
     */
    public final int rating;
    /**
     * The minimum number of ratings, the hotel should have
     */
    public final int minimumNumberRatings;

    /**
     * Constructor. Gets everything out of the builder
     *
     * @param builder with every property of the trip
     */
    private Trip(Builder builder) {
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        var formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
        start = formatter.format(startDate);
        end = formatter.format(endDate);
        this.duration = builder.duration;
        this.numberTravellers = builder.numberTravellers;
        this.numberKids = builder.numberKids;
        this.minimumStars = builder.minimumStars;
        this.minimumRecommendations = builder.minimumRecommendations;
        this.rating = builder.rating;
        this.minimumNumberRatings = builder.minimumNumberRatings;
    }

    /**
     * Creates and returns a static Trip-Builder with some basic data. Eligible
     * to be called from static methods (e.g. main()...)
     *
     * @param startDate
     * @param endDate
     * @param duration
     * @param numberTravellers
     * @return the builder
     */
    public static Trip.Builder builder(LocalDate startDate, LocalDate endDate, int duration, int numberTravellers) {
        return new Trip.Builder(startDate, endDate, duration, numberTravellers);
    }

    /**
     * Shows the complete data set for the trip
     *
     * @return text
     */
    @Override
    public String toString() {
        return "Trip from " + startDate + " to " + endDate + ", duration " + duration + " days, for " + numberTravellers
                + " people with " + numberKids + " kids; "
                + "the hotel should have " + minimumStars + " stars, a recommendation rate of " + minimumRecommendations
                + " out of 100 guests, "
                + "and a total rating of " + rating + " with at least " + minimumNumberRatings + " ratings.";

    }
}
