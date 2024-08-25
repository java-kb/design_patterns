package me.study.designpatterns.behavioral.iterator.examples.example1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedListWithIteratorTest {
    public static void main(String[] args) {
        var myList = new MyLinkedListWithIterator<String>();
        myList.add("String 1");
        myList.add("String 2");
        myList.add("String 3");
        myList.add("String 4");
        myList.add("String 5");
        myList.add("String 6");

        var iterator = myList.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());

        for (var item : myList)
            System.out.println(item);
        // Should throw an exception
        System.out.println(iterator.next());
    }
}

/*
 * You take a completely different approach if you store the individual elements
 * not in an
 * array, but in a chain. Each element knows its successor. It would also be
 * conceivable that
 * an element also knows a predecessor; I won’t go into this possibility
 * further – the project
 * would only become unnecessarily extensive without changing the underlying
 * principle.
 * 
 */
class MyLinkedListWithIterator<E>  implements Iterable<E>{
    private int counter = 0;
    private Node<E> header = null;

    /*
     *
     * Strings and any other objects you want to store in your list, I call
     * elements. They are not
     * stored in the collection, but in the instance of an inner class I call node.
     * The Node class
     * has two data felds that store the element you want to store and the
     * subsequent node object.
     * The collection can then restrict itself to referencing the frst object (in
     * the header
     * data feld).
     * 
     */
    private class Node<E> {
        private final E element;
        private Node<E> nextNode;

        Node(E element, Node<E> next) {
            this.element = element;
            this.nextNode = next;
        }
    }

    /*
     * Data is inserted into the collection by creating a new node object. This
     * object is refer-
     * enced by the header variable and displaces the object previously stored as
     * header.
     * The nextNode field of the new header object references the previous header.
     * And finally, the counter must be incremented. When you query the size of the
     * collection, the
     * counter is returned.
     */
    @SuppressWarnings("unchecked")
    public void add(E element) {
        header = new Node(element, header);
        counter++;
    }

    public int size() {
        return counter;
    }

    /*
     * To delete an element from the collection, go through the collection with a
     * while loop
     * and check whether the referenced element is the same as the element you are
     * looking for.
     * If so, pass the reference of the subsequent node object to the predecessor of
     * the node
     * object that references the element you are looking for. Then decrement the
     * counter. The
     * local variable previous references the predecessor of the node object whose
     * element is
     * currently being checked.
     * 
     */
    public boolean remove(E element) {
        Node<E> previous = null;
        var tempNode = header;
        while (tempNode != null) {
            if (equals(element, tempNode.element)) {
                if (previous == null)
                    header = tempNode.nextNode;
                else
                    previous.nextNode = tempNode.nextNode;

                counter--;
                return true;
            }
            previous = tempNode;
            tempNode = previous.nextNode;
        }
        return false;
    }

    /*
     * The get() method is intended to solve the same task as the get method of the
     * MyArray class. However, because the database is not index-based, you cannot
     * directly
     * query the xth element in the collection. You must go through the entire
     * collection until you
     * fnd the xth element.
     */
    public E get(int index) {
        // If the index is out of range, throw an exception
        if (index < 0 || index >= counter)
            throw new NoSuchElementException(index + " Size " + counter);
        // Start at the header
        var tempNode = header;
        // Step through the list
        for (var i = 0; i < index; i++)
            tempNode = tempNode.nextNode;
        // Return element at given position
        return tempNode.element;
    }

    /**
     * Compare two elements in the list
     *
     * @param o1 first element
     * @param o2 second element
     *
     * @return if elements are equal
     */
    private boolean equals(E o1, E o2) {
        if (o1 == null)
            return o2 == null;
        else
            return o1.equals(o2);
    }

    /*
     * The class MyList is internally structured differently.
     * The individual elements are not stored in an array, but linked. What
     * consequence does this
     * have for the iterator? In contrast to MyArray no counter is stored as
     * bookmark, but the
     * current element. Initially the current element is set to the header of the
     * list.
     * If you want to test whether a collection contains more elements, you must ask
     * whether
     * the current element of the iterator is non-null, in which case the hasNext()
     * method may
     * return true. The next() method returns the contents of the current element
     * and
     * advances the pointer to the next element one place.
     * 
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node current = header;

            @Override
            public boolean hasNext() {
                return (current != null);
            }

            @Override
            public E next() {
                if (current == null)
                    throw new NoSuchElementException("");
                @SuppressWarnings("unchecked")
                var value = (E) current.element;
                current = current.nextNode;
                return value;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

}