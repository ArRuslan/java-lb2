package ua.nure.jfm.task2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl<T> implements Array<T> {

    private Object[] elements;
	private int nextIndex = 0;

	public ArrayImpl() {
		elements = new Object[16];
	}

	private void resize() {
		Object[] oldElements = elements;
		elements = new Object[(int)(elements.length * 1.25) + 1];
		System.arraycopy(oldElements, 0, elements, 0, oldElements.length);
	}
	
	@Override
	public void add(T element) {
		if(nextIndex >= elements.length) {
			resize();
		}
		elements[nextIndex++] = element;
	}

	@Override
	public void clear() {
		nextIndex = 0;
		elements = new Object[16];
	}

	@Override
	public int size() {
		return nextIndex;
	}

	@Override
	public T get(int index) {
		if(index >= nextIndex) {
			throw new NoSuchElementException();
		}

		return (T)elements[index];
	}

	@Override
	public int indexOf(T element) {
		for(int i = 0; i < size(); i++) {
			T elem = get(i);
			if(elem == null && element != null) {
				continue;
			}
			if((elem == null && element == null) || elem.equals(element)) {
				return i;
			}
		}

		return -1;
	}

	@Override
	public void remove(int index) {
		if(index >= nextIndex) {
			return;
		}

		elements[index] = null;
		System.arraycopy(elements, index+1, elements, index, size()-index-1);
		nextIndex--;
	}

	@Override
	public void set(int index, T element) {
		if(index >= nextIndex) {
			return;
		}
		elements[index] = element;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		for(int i = 0; i < size(); i++) {
			builder.append(get(i));
			if(i < size()-1)
				builder.append(", ");
		}
		builder.append("]");

		return builder.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new IteratorImpl();
	}

	private class IteratorImpl implements Iterator<T> {
		private int currentIndex = 0;

		@Override
		public boolean hasNext() {
			return currentIndex < nextIndex;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}

			return get(currentIndex++);
		}
		
		@Override
		public void remove() {
			if(currentIndex-1 < 0) {
				throw new IllegalStateException();
			}
			ArrayImpl.this.remove(currentIndex-1);
			currentIndex--;
		}

	}

	@Override
	public Stream<T> stream() {
		return new StreamImpl<>(this);
	}

    public static void main(String[] args) {
    	Array<String> array = new ArrayImpl<>();
		array.add("1");
		array.add("2");
		array.add("2");

		System.out.println(array.indexOf("2"));
	}

}
