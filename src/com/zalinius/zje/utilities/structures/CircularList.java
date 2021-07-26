package com.zalinius.zje.utilities.structures;

import java.util.ArrayList;
import java.util.List;

public class CircularList<E>{

	private int maxSize;
	private int position;
	private List<E> content;
	
	public CircularList(int maxSize) {
		this.maxSize = maxSize;
		position = 0;
		if(maxSize < 1) {
			throw new IndexOutOfBoundsException();
		}
		this.content = new ArrayList<>(maxSize);
		for(int i = 0; i != maxSize; ++i) {
			this.content.add(null);
		}
	}
	
	public E current() {
		return content.get(position);
	}
	
	public int position() {
		return position;
	}

	/**
	 * Replaces first null slot with object, unless there is none.
	 * Does not change the position of the list
	 */
	public void add(E e) {
		boolean set = false;
		for(int i = 0; i < maxSize; ++i) {
			if(content.get(i) == null) {
				content.set(i, e);
				set = true;
				i = maxSize;
			}
		}
		
		if(!set) {
			throw new RuntimeException();
		}
	}


	public boolean hasNext() {
		return true;
	}


	public boolean hasPrevious() {
		return true;
	}


	public E next() {
		position = nextIndex();
		return current();
	}


	public int nextIndex() {
		int nextPosition = position + 1;
		nextPosition %= maxSize;
		
		return nextPosition;
	}


	public E previous() {
		position = previousIndex();
		return current();
	}


	public int previousIndex() {
		int previousPosition = position - 1;
		if(previousPosition < 0) {
			previousPosition += maxSize;
		}
		return previousPosition;
	}


	public E remove() {
		E removed = content.set(position, null);
		return removed;
	}

	public boolean contains(E e) {
		return content.contains(e);
	}

}
