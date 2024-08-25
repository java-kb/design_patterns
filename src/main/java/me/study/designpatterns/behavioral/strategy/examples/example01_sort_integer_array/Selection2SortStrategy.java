package me.study.designpatterns.behavioral.strategy.examples.example01_sort_integer_array;
/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Strategy"
 */
public class Selection2SortStrategy implements SortStrategy {

    /**
     * This is a simple selection sort algorithm
     *
     * @param numbers the array to sort
     */
    /**
     * Exercise: Change the behavior in a way that only the smalles number in
     * the remaining field is switched to the first position. Therefore, you'll
     * need some kind of marker to store the position of the smallest number so
     * far - if any. And finally, you'd need to check if there really has been a
     * smaller number and then swap it
     */
    @Override
    public void sort(int[] numbers) {
        for (var i = 0; i < numbers.length - 1; i++)
            for (var j = i + 1; j < numbers.length; j++)
                if (numbers[i] > numbers[j]) {
                    var temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
    }

    /**
     * For printing out the strategy's name
     *
     * @return the name
     */
    @Override
    public String toString() {
        return "Selection 2 Sort";
    }
}
