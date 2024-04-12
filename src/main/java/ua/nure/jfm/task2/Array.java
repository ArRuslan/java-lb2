package ua.nure.jfm.task2;

public interface Array<T> extends Container<T> {

	// Returns the element at the specified position.
	T get(int index);

	// Returns the index of the first occurrence of the specified element,
	// or -1 if this array does not contain the element.
	// (use 'equals' method to check an occurrence)
	int indexOf(T element);

	// Removes the element at the specified position.
	void remove(int index);

	// Sets the element at the specified position.
	void set(int index, T element);

}
