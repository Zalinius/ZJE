package com.zalinius.physics.collisions;

import static org.junit.jupiter.api.Assertions.*;
import static com.zalinius.physics.collisions.Collision.*;

import org.junit.jupiter.api.Test;

import com.zalinius.geometry.Circle;
import com.zalinius.geometry.Square;
import com.zalinius.geometry.oneDimensional.Segment;
import com.zalinius.physics.Point2D;

public class CollisionTest {

	@Test
	void isOverlapping_twoFarAwayCircles_false() {
		Circle c1 = new Circle(new Point2D(), 1);
		Circle c2 = new Circle(new Point2D(10, 10), 1);
		
		boolean areOverlapping = Collision.isOverlapping(c1, c2);
		
		assertFalse(areOverlapping);
	}
	
	@Test
	void isOverlapping_twoConcentricIdenticalCircles_true() {
		Circle c1 = new Circle(new Point2D(), 1);
		Circle c2 = new Circle(new Point2D(), 1);

		boolean isOverlapping = isOverlapping(c1, c2);
		
		assertTrue(isOverlapping);
	}
	
	@Test
	void isOverlapping_twoConcentricDifferentCircles_true() {
		Circle c1 = new Circle(new Point2D(), 3);
		Circle c2 = new Circle(new Point2D(), 2);

		boolean isOverlapping = isOverlapping(c1, c2);
		
		assertTrue(isOverlapping);
	}
	
	@Test
	void isOverlapping_twoAdjacentCircles_true() {
		Circle c1 = new Circle(new Point2D(), 1);
		Circle c2 = new Circle(new Point2D(0, 2), 1);
		
		boolean result = isOverlapping(c1, c2);
		assertTrue(result);
	}
	
	@Test
	void isOverlapping_twoPartiallyOverlappingCircles_true() {
		Circle c1 = new Circle(new Point2D(), 1);
		Circle c2 = new Circle(new Point2D(0, 1.5), 1);
		
		boolean result = isOverlapping(c1, c2);
		assertTrue(result);
	}
	
	@Test
	void isOverlapping_twoFarAwaySquare_false() {
		Square s1 = new Square(new Point2D(), 1);
		Square s2 = new Square(new Point2D(10, 10), 1);

		boolean areOverlapping = Collision.isOverlapping(s1, s2);
		
		assertFalse(areOverlapping);
	}
	
	@Test
	void isOverlapping_horizontallyAlignedDistantSquares_false() {
		Square s1 = new Square(new Point2D(), 1);
		Square s2 = new Square(new Point2D(10, 0), 1);

		boolean areOverlapping = Collision.isOverlapping(s1, s2);		
		
		assertFalse(areOverlapping);
	}
	
	@Test
	void isOverlapping_verticallyAlignedDistantSquares_false() {
		Square s1 = new Square(new Point2D(), 1);
		Square s2 = new Square(new Point2D(0, 10), 1);

		boolean areOverlapping = Collision.isOverlapping(s1, s2);		
		
		assertFalse(areOverlapping);
	}
	
	@Test 
	void isOverlapping_adjacentSquares_true(){
		Square s1 = new Square(new Point2D(), 1);
		Square s2 = new Square(new Point2D(1, 0), 1);

		boolean areOverlapping = Collision.isOverlapping(s1, s2);		
		
		assertTrue(areOverlapping);
	}
	
	@Test
	void isOverlapping_verticallyCloseSquares_true() {
		Square s1 = new Square(new Point2D(), 1);
		Square s2 = new Square(new Point2D(0, .6), 1);

		boolean areOverlapping = Collision.isOverlapping(s1, s2);		
		
		assertTrue(areOverlapping);
	}
	
	@Test
	void isOverlapping_twoSeparateHorizontalParallelLines_false() {
		Segment s1 = new Segment(new Point2D(), new Point2D(1, 0));
		Segment s2 = new Segment(new Point2D(0, 1), new Point2D(1, 1));
		
		boolean result = isOverlapping(s1, s2);
		
		assertFalse(result);
	}
	
	@Test
	void isOverlapping_twoDistantAlignedHorizontalParallelLines_false() {
		Segment s1 = new Segment(new Point2D(0,0), new Point2D(1, 0));
		Segment s2 = new Segment(new Point2D(2, 0), new Point2D(3, 0));
		
		boolean result = isOverlapping(s1, s2);
		
		assertFalse(result);
	}
	
	@Test
	void isOverlapping_twoDistantAlignedVerticalParallelLines_false() {
		Segment s1 = new Segment(new Point2D(0, 0), new Point2D(0, 1));
		Segment s2 = new Segment(new Point2D(0, 2), new Point2D(0, 3));
		
		boolean result = isOverlapping(s1, s2);
		
		assertFalse(result);
	}
	
	@Test
	void isOverlapping_twoSeparateVerticalParallelLines_false() {
		Segment s1 = new Segment(new Point2D(), new Point2D(0, 1));
		Segment s2 = new Segment(new Point2D(1, 0), new Point2D(1, 1));
		
		boolean result = isOverlapping(s1, s2);
		
		assertFalse(result);
	}
	
	@Test
	void isOverlapping_twoSeparateDiagonalParallelLines_false() {
		Segment s1 = new Segment(new Point2D(), new Point2D(1, 1));
		Segment s2 = new Segment(new Point2D(1, 0), new Point2D(2, 1));
		
		boolean result = isOverlapping(s1, s2);
		
		assertFalse(result);
	}
	
	
	@Test
	void isOverlapping_twoCrossingLines_true() {
		Segment s1 = new Segment(new Point2D(), new Point2D(1, 1));
		Segment s2 = new Segment(new Point2D(1, 0), new Point2D(0, 1));
		
		boolean result = isOverlapping(s1, s2);
		
		assertTrue(result);
	}
	
	@Test
	void isOverlapping_twoFullyStackedDiagonalParallelLines_true() {
		Segment s1 = new Segment(new Point2D(), new Point2D(1, 1));
		Segment s2 = new Segment(new Point2D(), new Point2D(1, 1));
		
		boolean result = isOverlapping(s1, s2);
		
		assertTrue(result);
	}
	
	@Test
	void isOverlapping_twoFullyStackedVerticalParallelLines_true() {
		Segment s1 = new Segment(new Point2D(), new Point2D(1, 0));
		Segment s2 = new Segment(new Point2D(), new Point2D(1, 0));
		
		boolean result = isOverlapping(s1, s2);
		
		assertTrue(result);
	}
	
	@Test
	void isOverlapping_twoFullyStackedHorizontalParallelLines_true() {
		Segment s1 = new Segment(new Point2D(), new Point2D(0, 1));
		Segment s2 = new Segment(new Point2D(), new Point2D(0, 1));
		
		boolean result = isOverlapping(s1, s2);
		
		assertTrue(result);
	}
	
	@Test
	void isOverlapping_twoDistantPerpendicularSegments_false() {
		Segment s1 = new Segment(new Point2D(10, -1), new Point2D(10, 1));
		Segment s2 = new Segment(new Point2D(), new Point2D(1, 0));
		
		boolean result = isOverlapping(s1, s2);
		
		assertFalse(result);
	}
	
	@Test
	void isOverlapping_twoCrossedPerpendicularSegments_true() {
		Segment s1 = new Segment(new Point2D(0, -1), new Point2D(0, 1));
		Segment s2 = new Segment(new Point2D(-1, 0), new Point2D(1, 0));
		
		boolean result = isOverlapping(s1, s2);
		
		assertTrue(result);
	}
	
	@Test
	void isOverlapping_twoJustTouchingPerpendicularSegments_true() {
		Segment s1 = new Segment(new Point2D(0, 0), new Point2D(0, 1));
		Segment s2 = new Segment(new Point2D(0, 0), new Point2D(1, 0));
		
		boolean result = isOverlapping(s1, s2);
		
		assertTrue(result);
	}
	
	@Test
	void isOverlapping_twoJustTouchingParallelHorizontalSegments_true() {
		Segment s1 = new Segment(new Point2D(0, 0), new Point2D(0, 1));
		Segment s2 = new Segment(new Point2D(0, 1), new Point2D(0, 2));
		
		boolean result = isOverlapping(s1, s2);
		
		assertTrue(result);
	}
	
	@Test
	void isOverlapping_twoJustTouchingParallelVerticalSegments_true() {
		Segment s1 = new Segment(new Point2D(0, 0), new Point2D(1, 0));
		Segment s2 = new Segment(new Point2D(1, 0), new Point2D(2, 0));
		
		boolean result = isOverlapping(s1, s2);
		
		assertTrue(result);
	}
	
	@Test
	void isOverlapping_twoKindaRandomPerpendicularLines_false() {
		Segment s1 = new Segment(new Point2D(1, 0), new Point2D(1, 1));
		Segment s2 = new Segment(new Point2D(11, 1), new Point2D(10, 1));
		
		boolean result = isOverlapping(s1, s2);
		
		assertFalse(result);
	}
	
	@Test
	void isOverlapping_twoMoreKindaRandomPerpendicularLines_false() {
		Segment s1 = new Segment(new Point2D(0, 0), new Point2D(1, 0));
		Segment s2 = new Segment(new Point2D(0, 1.6), new Point2D(0, 0.6));
		
		boolean result = isOverlapping(s1, s2);
		
		assertFalse(result);
	}
	
	@Test
	void isOverlapping_twoMoreMoreKindaRandomPerpendicularLines_false() {
		Segment s1 = new Segment(new Point2D(0, 0), new Point2D(1, 0));
		Segment s2 = new Segment(new Point2D(0, 1.6), new Point2D(0, 0.6));
		
		boolean result = isOverlapping(s1, s2);
		
		assertFalse(result);
	}
}
