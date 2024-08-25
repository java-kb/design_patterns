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
public interface SortStrategy {

    /**
     * All sorting strategies need to do a kind of sort
     *
     * @param numbers the array of numbers to sort
     */
    void sort(int[] numbers);
}
