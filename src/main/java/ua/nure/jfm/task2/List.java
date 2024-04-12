package ua.nure.jfm.task2;

public interface List<T> extends Container<T> {
	
    // Inserts the specified element at the beginning.
    void addFirst(T element);

    // Removes the first element.
    void removeFirst();

    // Removes the last element.
    void removeLast();

    // Returns the first element.
    T getFirst();

    // Returns the last element.
    T getLast();

    // Returns the first occurrence of the specified element.
    // Returns null if no such element.
    // (use 'equals' method to check an occurrence)
    T search(T element);

    // Removes the first occurrence of the specified element.
    // Returns true if this list contained the specified element.
    // (use 'equals' method to check an occurrence)
    boolean remove(T element);

}
