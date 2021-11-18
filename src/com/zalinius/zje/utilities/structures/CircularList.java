package com.zalinius.zje.utilities.structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class CircularList<E> extends ArrayList<E>{
	
	private static final long serialVersionUID = -1486069477056861855L;

	@Override
	public Iterator<E> iterator() {
		return new CircularIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new CircularIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new CircularIterator(index);
	}
	
	public class CircularIterator implements ListIterator<E>{
		
        private int cursor;
        private Integer lastElementIndex;

		public CircularIterator() {
			this(0);
		}
		
		public CircularIterator(int index) {
			if(index < 0 || index >= size()) {
				throw new IllegalArgumentException();
			}
			cursor = index;
			lastElementIndex = null;
		}

		@Override
		public boolean hasNext() {
			return !isEmpty();
		}

		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			else {
				E element = get(cursor);
				lastElementIndex = cursor;
				incrementIndex();
				return element;
			}
		}

		@Override
		public boolean hasPrevious() {
			return !isEmpty();
		}

		@Override
		public E previous() {
			if(!hasPrevious()) {
				throw new NoSuchElementException();
			}
			else {
				decrementIndex();
				E element = get(cursor);
				lastElementIndex = cursor;
				return element;
			}
		}

		@Override
		public int nextIndex() {
			incrementIndex();
			int result = cursor;
			decrementIndex();
			return result;
		}

		@Override
		public int previousIndex() {
			decrementIndex();
			int result = cursor;
			incrementIndex();
			return result;
		}

		@Override
		public void remove() {
			if(lastElementIndex == null) {
				throw new IllegalStateException();
			}
			else {
	     		CircularList.this.remove(lastElementIndex);
	     		lastElementIndex = null;
			}
		}

		@Override
		public void set(E e) {
			if(lastElementIndex == null) {
				throw new IllegalStateException();
			}
			else {
	     		CircularList.this.set(lastElementIndex, e);
			}			
		}

		@Override
		public void add(E e) {
			if(lastElementIndex == null) {
				throw new IllegalStateException();
			}
			else {
	     		CircularList.this.add(lastElementIndex, e);
	     		lastElementIndex = null;
			}
			
		}
		
		
		private void incrementIndex() {
			cursor++;
			if(cursor >= size()) {
				cursor = 0;
			}
		}
		private void decrementIndex() {
			cursor--;			
			if(cursor < 0) {
				cursor = size() - 1;
			}
		}
		
	}

}
