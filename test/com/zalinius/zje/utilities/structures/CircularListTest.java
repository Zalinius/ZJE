package com.zalinius.zje.utilities.structures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class CircularListTest {

	@Test
	public void add_anObjectToTheList_addsTheObject() {
		CircularList<Integer> circularList = new CircularList<>();
		
		circularList.add(0);
		
		assertEquals(1, circularList.size());
		assertEquals(0, circularList.get(0));
	}


	@Test
	public void addAll_anExistingListToTheList_addsAllTheObject() {
		CircularList<Integer> circularList = new CircularList<>();
		
		circularList.addAll(List.of(0, 1, 2, 3, 4));
		
		assertEquals(5, circularList.size());
		assertEquals(0, circularList.get(0));
		assertEquals(1, circularList.get(1));
		assertEquals(2, circularList.get(2));
		assertEquals(3, circularList.get(3));
		assertEquals(4, circularList.get(4));
	}
	
	@Test
	public void isEmpty_aListWithNoObjects_returnsTrue() {
		CircularList<Integer> circularList = new CircularList<>();
				
		
		assertTrue(circularList.isEmpty());
	}

	@Test
	public void isEmpty_aListWithOneObject_returnsFalse() {
		CircularList<Integer> circularList = new CircularList<>();
		
		circularList.add(0);
		
		assertFalse(circularList.isEmpty());
	}


}
