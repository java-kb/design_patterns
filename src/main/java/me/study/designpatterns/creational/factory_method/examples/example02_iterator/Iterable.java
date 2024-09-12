package me.study.designpatterns.creational.factory_method.examples.example02_iterator;
/**
 *
 * @author Olaf Musch
 *
 * Design Patterns with Java
 *
 * An Introduction, Springer Vieweg
 *
 * chapter "Factory Method"
 */
public interface Iterable {

    /**
     * Specify the iterator method
     *
     * @return a new iterator
     */
    Iterator iterator();
}
