package me.study.designpatterns.behavioral.iterator.examples.example1;

public class MyArrayListTest {
    public static void main(String[] args) {
        MyArrayList<Integer> myArray = new MyArrayList<Integer>();
        myArray.add(1);
        myArray.add(2);
        myArray.add(3);
        myArray.add(4);
        myArray.add(5);
        myArray.add(6);
        for (int i = 0; i < myArray.size(); i++)
            System.out.println(myArray.get(i));

        System.out.println("\nDelete element with index 1\n");
        myArray.remove(1);

        for (int i = 0; i < myArray.size(); i++)
            System.out.println(myArray.get(i));

        System.out.println("\nDelete element with index 3\n");

        myArray.remove(3);

        for (int i = 0; i < myArray.size(); i++)
            System.out.println(myArray.get(i));


    }
}

class MyArrayList<E> {
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
}