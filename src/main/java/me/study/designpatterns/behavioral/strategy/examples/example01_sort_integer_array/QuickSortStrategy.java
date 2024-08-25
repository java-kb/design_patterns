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
public class QuickSortStrategy implements SortStrategy {

    /**
     * Gets the array and initiates the Quicksort algorithm
     *
     * @param numbers the array to sort
     */
    @Override
    public void sort(int[] numbers) {
        quicksort(numbers, 0, numbers.length - 1);
    }

    /**
     * The recursive Quicksort
     *
     * @param array the array to sort
     * @param pLeft left position
     * @param pRight right position
     */
    private void quicksort(int[] array, int pLeft, int pRight) {
        var left = pLeft;
        var right = pRight + 1;
        int pivot, temp;

        pivot = array[pLeft];

        do {
            do
                left++;
            while (left <= pRight && array[left] < pivot);

            do
                right--;
            while (array[right] > pivot);

            if (left < right) {
                temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        } while (left <= right);

        temp = array[pLeft];
        array[pLeft] = array[right];
        array[right] = temp;

        if (pLeft < right)
            quicksort(array, pLeft, right);

        if (left < pRight)
            quicksort(array, left, pRight);
    }

    /**
     * For printing out the strategy's name
     *
     * @return the name
     */
    @Override
    public String toString() {
        return "Quick Sort";
    }
}
