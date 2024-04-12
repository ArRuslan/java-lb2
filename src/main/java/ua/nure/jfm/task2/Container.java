package ua.nure.jfm.task2;

import java.util.Iterator;

public interface Container<T> extends Iterable<T> {

    // Appends the specified element to the end.
    void add(T element);

    // Removes all of the elements.
    void clear();

    // Returns the number of elements.
    int size();

    // Returns a string representation of this container.
    // See JUnit tests for details.
    String toString();

    // Returns an iterator over elements.
    // Iterator must implement the remove method.
    Iterator<T> iterator();
    
    // Returns a stream over elements.
    Stream<T> stream();

}
