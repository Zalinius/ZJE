package com.zalinius.zje.utilities.structures;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * An infinite 2D grid, which extends in every direction. At any point in the grid, a single object can be stored.
 * @author Zach
 *
 * @param <E> The type to be stored, on which there is no restriction..
 */
public class Grid<E> {
	Map<Coordinate, E> grid;

	public Grid() {
		grid = new HashMap<>();
	}
	
	/**
	 * Inserts an element into the grid, at (i,j)
	 * @param i 
	 * @param j
	 * @param element
	 * @return The element previously at (i,j), or null if the point was empty.
	 */
	public E put(int i, int j, E element) {
		E oldValue = grid.put(new Coordinate(i, j), element);
		return oldValue;
	}
	
	public int size() {
		return grid.size();
	}
	
	public E get(int i, int j) {
		return grid.get(new Coordinate(i, j));
	}
	
	
	public void clear() {
		grid.clear();
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}

	public Iterator<E> elements() {
		return grid.values().iterator();
	}
	
	public Iterator<Coordinate> points() {
		return grid.keySet().iterator();
	}
	
	public Iterator<Entry<Coordinate,E>> tiles(){
		return grid.entrySet().iterator();
	}
}
