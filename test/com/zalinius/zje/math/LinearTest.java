package com.zalinius.zje.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zalinius.zje.math.Linear.Intersection;

public class LinearTest {

	@Test
	void areParallel_twoVerticalLines_true() {
		Linear l1 = new Linear(true, 0);
		Linear l2 = new Linear(true, 1);
		
		boolean result = Linear.areParallel(l1, l2);
		
		assertTrue(result);
	}
	
	@Test
	void areParallel_twoHorizontalLines_true() {
		Linear l1 = new Linear(0, 0);
		Linear l2 = new Linear(0, 1);
		
		boolean result = Linear.areParallel(l1, l2);
		
		assertTrue(result);
	}
	
	@Test
	void areParallel_twoDiagonalLines_true() {
		Linear l1 = new Linear(1, 0);
		Linear l2 = new Linear(1, 1);
		
		boolean result = Linear.areParallel(l1, l2);
		
		assertTrue(result);
	}
	
	@Test
	void hasIntersection_twoSeparateDiagonalLines_none() {
		Linear l1 = new Linear(1, 0);
		Linear l2 = new Linear(1, 1);
		
		Intersection result = Linear.hasIntersection(l1, l2);
		
		assertEquals(Intersection.NONE, result);
	}
	
	@Test
	void hasIntersection_twoPerpendicularLines_one() {
		Linear l1 = new Linear(0, 0);
		Linear l2 = new Linear(true, 0);
		
		Intersection result = Linear.hasIntersection(l1, l2);
		
		assertEquals(Intersection.ONE, result);
	}
	
	
	
	
	
	@Test
	void hasIntersection_twoStackedDiagonalLines_infinite() {
		Linear l1 = new Linear(1, 0);
		Linear l2 = new Linear(1, 0);
		
		Intersection result = Linear.hasIntersection(l1, l2);
		
		assertEquals(Intersection.INFINITE, result);
	}
	
	@Test
	void hasIntersection_twoStackedHorizontalLines_infinite() {
		Linear l1 = new Linear(0, 0);
		Linear l2 = new Linear(0, 0);
		
		Intersection result = Linear.hasIntersection(l1, l2);
		
		assertEquals(Intersection.INFINITE, result);
	}
	
	@Test
	void hasIntersection_twoStackedVerticalLines_infinite() {
		Linear l1 = new Linear(true, 0);
		Linear l2 = new Linear(true, 0);
		
		Intersection result = Linear.hasIntersection(l1, l2);
		
		assertEquals(Intersection.INFINITE, result);
	}
	
	@Test
	void xIntercept_twoPerpendicularLines_0() {
		Linear l1 = new Linear(0, 0);
		Linear l2 = new Linear(true, 0);
		
		double result = Linear.xIntercept(l1, l2);
		
		assertEquals(0, result);
	}
	
	@Test
	void xIntercept_twodistantPerpendicularLines_2() {
		Linear l1 = new Linear(0, 5);
		Linear l2 = new Linear(true, 2);
		
		double result = Linear.xIntercept(l1, l2);
		
		assertEquals(2, result);
	}

}
