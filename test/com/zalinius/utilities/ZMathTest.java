package com.zalinius.utilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ZMathTest {

	@Test
	void doubleClamp_inside_isUnchanged() {
		double value = 5.2;
		double left = 4.5;
		double right = 6.2;
		
		double result = ZMath.clamp(value, left, right);
				
		assertEquals(value, result);	}
	
	@Test
	void doubleClamp_onBound_isUnchanged() {
		double value = 3.2;
		double left = value;
		double right = 6.2;
		
		double result = ZMath.clamp(value, left, right);
				
		assertEquals(value, result);	}
	
	@Test
	void doubleClamp_outside_isClamped() {
		double value = 3.2;
		double left = 4.5;
		double right = 6.2;
		
		double result = ZMath.clamp(value, left, right);
				
		assertEquals(left, result);
	}
	
	@Test
	void intClamp_inside_isUnchanged() {
		int value = 5;
		int left = 4;
		int right = 6;
		
		int result = ZMath.clamp(value, left, right);
				
		assertEquals(value, result);	}
	
	@Test
	void intClamp_onLowerBound_isUnchanged() {
		int value = 3;
		int left = value;
		int right = 6;
		
		int result = ZMath.clamp(value, left, right);
				
		assertEquals(value, result);
	}
	
	@Test
	void intClamp_onUpperBound_isUnchanged() {
		int value = 6;
		int left = 3;
		int right = value;
		
		int result = ZMath.clamp(value, left, right);
				
		assertEquals(value, result);
	}
	
	@Test
	void intClamp_outside_isClamped() {
		int value = 3;
		int left = 4;
		int right = 6;
		
		int result = ZMath.clamp(value, left, right);
				
		assertEquals(left, result);
	}
	
	@Test
	void doubleXClamp_inside_pushedLeft() {
		double value = 5.2;
		double left = 4.5;
		double right = 6.2;
		
		double result = ZMath.xClamp(value, left, right);
				
		assertEquals(left, result);	}
	
	@Test
	void doubleXClamp_onBound_isUnchanged() {
		double value = 3.2;
		double left = value;
		double right = 6.2;
		
		double result = ZMath.xClamp(value, left, right);
				
		assertEquals(value, result);	}
	
	@Test
	void doubleXClamp_outside_isUnchanged() {
		double value = 3.2;
		double left = 4.5;
		double right = 6.2;
		
		double result = ZMath.xClamp(value, left, right);
				
		assertEquals(value, result);
	}

}
