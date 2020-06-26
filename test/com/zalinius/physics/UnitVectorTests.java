package com.zalinius.physics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UnitVectorTests {

	public UnitVectorTests() {
		// TODO Auto-generated constructor stub
	}
	

	@Test
	public void unitVector_length_is1() {
		UnitVector u = new UnitVector(22, 17);
		
		double expected = 1.0;
		double result = u.length();
		
		assertEquals(expected, result);
	}
	
	@Test
	public void unitVector_constructor_throwsWhenInitializedWithZeroVector() {
		Vector2D zeroVector = new Vector2D();
		assertThrows(ArithmeticException.class, () -> new UnitVector(zeroVector));
	}

	
	@Test
	public void unitVector_constructor_throwsWhenInitializedWithZeroComponents() {
		assertThrows(ArithmeticException.class, () -> new UnitVector(0, 0));
	}

}
