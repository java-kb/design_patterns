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
public class SelectionSortStrategy implements SortStrategy {

    /**
     * This is a simple selection sort algorithm
     *
     * @param numbers the array to sort
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
        return "Selection Sort";
    }
}
