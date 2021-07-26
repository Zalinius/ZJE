package com.zalinius.zje.utilities.structures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CircularListTests {

	@Test
	public void constructList_negativeOrZeroSize_throwsIndexOutOfBoundsException() {
		assertThrows(IndexOutOfBoundsException.class, () -> new CircularList<>(0));
	}
	
	@Test
	public void next_onSize5List_canBeCalled20TimesWithoutThrowing() {
		CircularList<Integer> cl = new CircularList<>(5);
		
		for(int i = 0; i != 20; ++i) {
			cl.next();
		}
	}

	@Test
	public void previous_onSize5List_canBeCalled20TimesWithoutThrowing() {
		CircularList<Integer> cl = new CircularList<>(5);
		
		for(int i = 0; i != 20; ++i) {
			cl.previous();
		}
	}
}
