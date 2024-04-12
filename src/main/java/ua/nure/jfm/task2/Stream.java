package ua.nure.jfm.task2;

// A sequence of elements.
public interface Stream<T> {

	// Represents a function that accepts one argument and produces a result.	
	// X - the type of the input to the function;
	// Y - the type of the result of the function.
	interface Function<X, Y> {
		Y apply(X x);
	}

	// Represents an operation that accepts a single input argument 
	// and returns no result.
	// X - the type of the input to the operation. 
	interface Action<X> {
		void perform(X x);
	}

	// Intermediate operations

	// Returns a stream consisting of the elements of this stream that match 
	// the given predicate.
	Stream<T> filter(Function<? super T, Boolean> predicate);

	// Returns a stream consisting of the results of applying the given function 
	// to the elements of this stream.
	<R> Stream<R> map(Function<? super T, ? extends R> function);

	// terminal operations

	// Returns the count of elements in this stream. 
	int count();

	// Performs an action for each element of this stream.	
	void forEach(Action<? super T> action);
	
}
