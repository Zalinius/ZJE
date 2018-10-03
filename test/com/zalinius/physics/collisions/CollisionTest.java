package com.zalinius.physics.collisions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.zalinius.geometry.Circle;
import com.zalinius.geometry.Square;
import com.zalinius.physics.Point2D;

public class CollisionTest {

	@Test
	void isOverlagping_twoFarAwayCircles_false() {
		Circle c1 = new Circle(new Point2D(), 1);
		Circle c2 = new Circle(new Point2D(10, 10), 1);
		
		boolean areOverlapping = Collision.isOverlapping(c1, c2);
		
		assertFalse(areOverlapping);
	}
	
	@Test
	void isOverlapping_twoFarAwaySquare_false() {
		Square s1 = new Square(new Point2D(), 1);
		Square s2 = new Square(new Point2D(10, 10), 1);

		boolean areOverlapping = Collision.isOverlapping(s1, s2);
		
		assertFalse(areOverlapping);
	}

}
