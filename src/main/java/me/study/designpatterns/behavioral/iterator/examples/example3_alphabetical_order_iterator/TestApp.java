package me.study.designpatterns.behavioral.iterator.examples.example3_alphabetical_order_iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TestApp {
    public static void main(String[] args) {
        var myList = new WordsCollection<String>();
        myList.add("First");
        myList.add("Second");
        myList.add("Third");

        System.out.println("Straight traversal:");
        var iterator = myList.getIterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());

        System.out.println("Reverse traversal:");
        iterator = myList.getReverseIterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
    }
}

class AlphabeticalOrderIterator<E> implements Iterator<E> {
    private WordsCollection<E> wordsCollection;
    private boolean reverse;
    private int position = 0;

    public AlphabeticalOrderIterator(WordsCollection<E> wordsCollection) {
        this.wordsCollection = wordsCollection;
    }

    public AlphabeticalOrderIterator(WordsCollection<E> wordsCollection, boolean reverse) {
        this.wordsCollection = wordsCollection;
        this.reverse = reverse;
        this.position = this.reverse ? wordsCollection.size() - 1 : 0;
    }

    @Override
    public boolean hasNext() {
        return this.reverse ? position >= 0
                : (position < wordsCollection.size()) && wordsCollection.get(position) != null;
    }

    public E next() {
        if (position >= wordsCollection.size() || wordsCollection.get(position) == null)
            throw new NoSuchElementException("No more data");
        @SuppressWarnings("unchecked")
        var value = (E) wordsCollection.get(position);
        this.position = this.position + (this.reverse ? -1 : 1);
        return value;
    }

}

class WordsCollection<E> {
    private int counter = 0;
    private Object[] elements = new Object[5];

    public void add(E e) {
        if (counter == elements.length) {
            var tempArray = new Object[counter + 5];
            System.arraycopy(elements, 0, tempArray, 0, counter);
            elements = tempArray;
        }
        elements[counter] = e;
        counter++;
    }

    public int size() {
        return counter;
    }

    public E get(int index) {
        return (E) elements[index];
    }

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

    public Iterator<E> getIterator() {
        return new AlphabeticalOrderIterator<E>(this);
    }

    public Iterator<E> getReverseIterator() {
        return new AlphabeticalOrderIterator<E>(this, true);
    }
}