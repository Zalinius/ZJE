package com.zalinius.utilities;

public class Tuple<E> {
	private E first;
	private E second;
	
	public Tuple(E first, E second) {
		this.first = first;
		this.second = second;
	}
	
	public E first() {
		return first;
	}
	
	public E second() {
		return second;
	}

}
