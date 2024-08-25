package me.study.designpatterns.behavioral.iterator.examples.example1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayListWithIteratorTest {
    /**
     * Create a MyArray and fill it. Then use the iterator to print it out
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        var myArray = new MyArrayListWithIterator<String>();
        myArray.add("String 1");
        myArray.add("String 2");
        myArray.add("String 3");
        myArray.add("String 4");
        myArray.add("String 5");
        myArray.add("String 6");

        var iterator = myArray.iterator();

        while (iterator.hasNext()) {
            var temp = iterator.next();
            System.out.println(temp);
        }

        // Should throw an exception
        System.out.println(iterator.next());
    }
}

class MyArrayListWithIterator<E> {
    /*
     * The basis for our frst collection will be an array. The elements in this
     * array will be of
     * the general type Object. Initially, fve objects are to be referenced. To be
     * able to store dif-
     * ferent data types in a type-safe way, create the class generically
     */
    private int counter = 0;
    private Object[] elements = new Object[5];

    /*
     * To insert a new element, defne the add() method. It is passed an argument of
     * generic
     * type. This element is stored at the next free position in the array. The
     * counter data feld
     * stores this position. If fve elements are already stored and a sixth is to be
     * added, the data-
     * base must be expanded. Since an array cannot be enlarged, the only way left
     * is to redefne
     * the array. In general: If the next free position is equal to the number of
     * elements, the size
     * of the array must be increased by a certain value. In the example, the array
     * size is to be
     * increased by another fve elements.
     * 
     */
    public void add(E e) {
        if (counter == elements.length) {
            var tempArray = new Object[counter + 5];
            System.arraycopy(elements, 0, tempArray, 0, counter);
            elements = tempArray;
        }
        elements[counter] = e;
        counter++;
    }

    /*
     * The client may want to inquire how many items are stored in the collection.
     * For this, it
     * is suffcient that you return the position of the next free item.
     * 
     */
    public int size() {
        return counter;
    }

    /*
     * The collection only fulflls its purpose when the individual elements can be
     * returned.
     * To do this, you create the get() method, which expects an index as a
     * parameter that
     * describes the location of the element you are looking for in the database.
     * Before the return,
     * the stored value is cast to the generic type.
     * 
     */
    public E get(int index) {
        return (E) elements[index];
    }

    /*
     * If you want to delete an element, the counter must frst be decremented. The
     * element is
     * then deleted by moving the subsequent elements forward one place at a time.
     * However, in
     * order not to throw an “index out of bound” exception, checks on the range
     * between 0 and
     * counter are still required. And to avoid leaving the removed object at the
     * end of the feld
     * in memory, it must be overwritten with null.
     */
    public void remove(int index) {
        if ((index <= counter) && (counter > 0)
                && (index >= 0)) {
            if (index != counter)
                System.arraycopy(elements, index + 1,
                        elements, index,
                        elements.length - 1 - index);
            elements[counter--] = null;
        }
    }

    /*
     * The simplest form of an iterator is returned by the iterator() method in the
     * MyArray
     * class. The iterator internally stores the position at which the bookmark is
     * set. The next()
     * method returns the next element in each case, and the hasNext() method
     * returns true
     * if there are more elements
     */
    /**
     * Return an iterator for the MyArray
     *
     * @return the iterator
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            // At start (with an empty array), the position must be -1
            private int position = -1;

            /**
             * Check, if there's more elements in the collection
             *
             * @return if there's more
             */
            @Override
            public boolean hasNext() {
                return (position < size()) && elements[position + 1] != null;
            }

            /**
             * Get the next element of the array
             *
             * @return the type casted element
             */
            @Override
            public E next() {
                position++;
                if (position >= size() || elements[position] == null)
                    throw new NoSuchElementException("No more data");
                @SuppressWarnings("unchecked")
                var value = (E) elements[position];
                return value;
            }

            /**
             * Remove an element from the collection. Not implemented here. Just
             * throw an exception
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}