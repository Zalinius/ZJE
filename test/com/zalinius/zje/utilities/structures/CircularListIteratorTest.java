package com.zalinius.zje.utilities.structures;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Iterator;
import java.util.ListIterator;

import org.junit.jupiter.api.Test;

public class CircularListIteratorTest {


	@Test
	public void next_onIteratorOfFiveElementCircularList_canBeCalled20TimesWithoutThrowing() {
		CircularList<Integer> cl = new CircularList<>();
		cl.add(0);
		cl.add(1);
		cl.add(2);
		cl.add(3);
		cl.add(4);

		Iterator<Integer> it = cl.iterator();

		assertDoesNotThrow(() -> {
			for(int i = 0; i != 20; ++i) {
				it.next();
			}
		});
	}

	@Test
	public void previoud_onListIteratorOfFiveElementCircularList_canBeCalled20TimesWithoutThrowing() {
		CircularList<Integer> cl = new CircularList<>();
		cl.add(0);
		cl.add(1);
		cl.add(2);
		cl.add(3);
		cl.add(4);

		ListIterator<Integer> it = cl.listIterator();
		
		assertDoesNotThrow(() -> {
			for(int i = 0; i != 20; ++i) {
				it.previous();
			}
		});
	}

}
