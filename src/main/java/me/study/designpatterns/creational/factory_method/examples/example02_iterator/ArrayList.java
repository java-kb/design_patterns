package me.study.designpatterns.creational.factory_method.examples.example02_iterator;
// TODO Add implementation form src/main/java/me/study/designpatterns/behavioral/iterator/examples/example1/MyArrayListWithIteratorTest.java
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
public class ArrayList implements Iterable {

    /**
     * Create an iterator and return it
     *
     * @return the new iterator
     */
    @Override
    public Iterator iterator() {
        var iterator = new MyIterator();
        return iterator;
    }
}
