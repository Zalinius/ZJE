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
	private Map<Coordinate, E> gridData;

	public Grid() {
		gridData = new HashMap<>();
	}
	
	/**
	 * Inserts an element into the grid, at (i,j)
	 * @param i 
	 * @param j
	 * @param element
	 * @return The element previously at (i,j), or null if the point was empty.
	 */
	public E put(int i, int j, E element) {
		return gridData.put(new Coordinate(i, j), element);
	}
	
	public int size() {
		return gridData.size();
	}
	
	public E get(int i, int j) {
		return gridData.get(new Coordinate(i, j));
	}
	
	public E remove(int i, int j) {
		return gridData.remove(new Coordinate(i, j));		
	}
	
	public boolean pointIsOccupied(int i, int j) {
		Coordinate coord = new Coordinate(i, j);
		return gridData.containsKey(coord) && gridData.get(coord) != null;
	}
	
	public void clear() {
		gridData.clear();
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}

	public Iterator<E> elements() {
		return gridData.values().iterator();
	}
	
	public Iterator<Coordinate> points() {
		return gridData.keySet().iterator();
	}
	
	public Iterator<Entry<Coordinate,E>> tiles(){
		return gridData.entrySet().iterator();
	}
	
	
}
