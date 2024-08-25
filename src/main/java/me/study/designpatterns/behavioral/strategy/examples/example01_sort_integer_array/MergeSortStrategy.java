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
public class MergeSortStrategy implements SortStrategy {

    /**
     * A temporary copy of the array of numbers
     */
    private int[] numbers;

    /**
     * Gets the array and initiates the Mergesort algorithm
     *
     * @param numbers the array to sort
     */
    @Override
    public void sort(int[] numbers) {
        this.numbers = numbers;
        sort(0, numbers.length - 1);
    }

    /**
     * Recursive sorting with Mergesort algorithm. Always only sort a part of
     * the array
     *
     * @param left index of the first number in the array
     * @param right index of the last number in the array
     */
    private void sort(int left, int right) {
        if (left < right) {
            var center = (left + right) / 2;

            // Sort left part
            sort(left, center);
            // Sort right part
            sort(center + 1, right);
            // Merge the two sorted parts
            merge(left, center, right);
        }
    }

    /**
     * Merge two parts of an array and sort along the way
     *
     * @param left edge of the array
     * @param center of the array
     * @param right edge of the array
     */
    private void merge(int left, int center, int right) {
        // Supporting array
        var arr = new int[numbers.length];

        int i, j;

        for (i = left; i <= center; i++)
            arr[i] = numbers[i];

        for (j = center + 1; j <= right; j++)
            arr[right + center + 1 - j] = numbers[j];

        i = left;
        j = right;

        for (var k = left; k <= right; k++)
            if (arr[i] <= arr[j]) {
                numbers[k] = arr[i];
                i++;
            } else {
                numbers[k] = arr[j];
                j--;
            }
    }

    /**
     * For printing out the strategy's name
     *
     * @return the name
     */
    @Override
    public String toString() {
        return "Merge Sort";
    }
}
