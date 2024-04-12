package ua.nure.jfm.task2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl<T> implements List<T> {

    private static class Node<T> {
		private final T obj;
		private Node<T> next;

		public Node(T obj) {
			this.obj = obj;
		}

		public Node(T obj, Node<T> next) {
			this.obj = obj;
			this.next = next;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> node) {
			next = node;
		}

		public T getValue() {
			return obj;
		}
    }

	private Node<T> head = null;

	@Override
	public void add(T element) {
		if(head == null) {
			head = new Node<>(element);
			return;
		}
		Node<T> newNode = new Node<>(element);

		Node<T> current = head;
		while(current.getNext() != null) {
			current = current.getNext();
		}

		current.setNext(newNode);
	}

	@Override
	public void clear() {
		head = null;
	}

	@Override
	public int size() {
		int size = 0;
		Node<T> current = head;
		while(current != null) {
			size++;
			current = current.getNext();
		}

		return size;
	}

	@Override
	public void addFirst(T element) {
		head = new Node<>(element, head);
	}

	@Override
	public void removeFirst() {
		if(head == null) {
			return;
		}
		head = head.getNext();
	}

	@Override
	public void removeLast() {
		if(head == null || head.getNext() == null) {
			head = null;
			return;
		}

		Node<T> current = head;
		while(current.getNext().getNext() != null) {
			current = current.getNext();
		}

		current.setNext(null);
	}

	@Override
	public T getFirst() {
		return head.getValue();
	}

	@Override
	public T getLast() {
		Node<T> current = head;
		while(current.getNext() != null) {
			current = current.getNext();
		}

		return current.getValue();
	}

	@Override
	public T search(T element) {
		Node<T> current = head;
		while(current != null) {
			if((current.getValue() == null && element == null) || (current.getValue() != null && current.getValue().equals(element))) {
				return current.getValue();
			}
			current = current.getNext();
		}

		return null;
	}

	@Override
	public boolean remove(T element) {
		if(head == null) {
			return false;
		}
		if((head.getValue() == null && element == null) || (head.getValue() != null && head.getValue().equals(element))) {
			head = head.getNext();
			return true;
		}

		Node<T> current = head;

		while(current.getNext() != null) {
			if (current.getNext().getValue().equals(element)) {
				current.setNext(current.getNext().getNext());
				return true;
			}
			current = current.getNext();
		}

		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		Node<T> current = head;

		while(current != null) {
			builder.append(current.getValue());
			if(current.getNext() != null)
				builder.append(", ");

			current = current.getNext();
		}
		builder.append("]");

		return builder.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new IteratorImpl();
	}

	private class IteratorImpl implements Iterator<T> {
		private Node<T> current = head;
		private Node<T> previous;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T val = current.getValue();
			if (previous != null) {
				previous.next = current.next;
			} else {
				head = current.next;
			}
			previous = current;
			current = current.next;

			return val;
		}

		@Override
		public void remove() {
			if (previous == null) {
				throw new IllegalStateException();
			}
			if (previous == head) {
				head = head.next;
			} else {
				previous.next = current;
			}
			previous = null;
		}

    }

	@Override
	public Stream<T> stream() {
		return new StreamImpl<>(this);
	}

	public static void main(String[] args) {
		
	}

}
