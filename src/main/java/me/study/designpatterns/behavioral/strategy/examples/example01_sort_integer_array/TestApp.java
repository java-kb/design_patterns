package me.study.designpatterns.behavioral.strategy.examples.example01_sort_integer_array;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

/**
 *
 * @author Olaf Musch
 *
 *         Design Patterns with Java
 *
 *         An Introduction, Springer Vieweg
 *
 *         chapter "Strategy"
 */
public class TestApp {
    /**
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        testSorting();
    }

    /**
     *
     * Generate an array filled with random numbers, clone it multiple times and
     * sort it using the different strategies. Always measure the time of the
     * sorting and print out the results
     *
     */
    public static void testSorting() {
        // The array will contain 100.000 numbers
        var feldgroesse = 100000;
        // Those numbers will range from 0 to 999.999
        var wertebereich = 1000000;
        // Reference to the respective sort strategy
        SortStrategy sortStrategy;

        /**
         * Generate the array and clone it twice. Thus, all sort calls have the
         * same starting condition
         *
         */
        var numbers_1 = createArray(feldgroesse, wertebereich);
        var numbers_2 = numbers_1.clone();
        var numbers_3 = numbers_1.clone();
        /**
         * For the variant of the Selection Sort mentioned in the book, you'd
         * need a fourth array
         */
        // int[] numbers_4 = zahlen_1.clone();

        System.out.println("The arrays with the identical unsorted random numbers were generated");

        // Selection Sort
        sortStrategy = new SelectionSortStrategy();
        executeStrategy(sortStrategy, numbers_1);

        /*
         * Here you could choose and call the optimized Selection Sort strategy
         */
        // Selection 2 Sort
        // sortStrategy = new Selection2SortStrategy();
        // executeStrategy(sortStrategy, numbers_4);
        // Merge Sort
        sortStrategy = new MergeSortStrategy();
        executeStrategy(sortStrategy, numbers_2);

        // Quick Sort
        sortStrategy = new QuickSortStrategy();
        executeStrategy(sortStrategy, numbers_3);

    }

    /**
     * Encapsulating the execution of a sort strategy. Also includes timekeeping
     * and console output.
     *
     * @param s selected sorting strategy
     * @param z the array to sort
     */
    private static void executeStrategy(SortStrategy s, int[] z) {
        /**
         * First, some preparations
         */
        Instant istart;
        Instant iend;
        Long idifference;

        // Print out the upcoming strategy's name
        System.out.println("Starte " + s);
        // 1. Start clock
        istart = Instant.now();

        /*
         * Call the strategy
         */
        s.sort(z);

        /**
         * And do some upkeep
         */
        // 2. Stop clock
        iend = Instant.now();
        // Calculate time used by strategy and convert to Milliseconds
        var elapsed = Duration.between(istart, iend);
        idifference = elapsed.toMillis();
        // Print out result
        System.out.println("Dauer " + s + ": " + idifference + " Millisekunden");
    }

    /**
     * Fill an array with random numbers
     *
     * @param amount of random numbers to generate
     * @param range  of numbers (0 .. range)
     *
     * @return the array filled with random numbers
     */
    private static int[] createArray(int amount, int range) {
        var result = new int[amount];
        var generator = new Random();

        for (var i = 0; i < amount; i++)
            result[i] = generator.nextInt(range);

        return result;
    }

}
