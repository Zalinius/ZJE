package com.zalinius.physics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Vector2DTest {
	@Test
	public void length_rectangularTriangle3And4_5() {
		Vector2D v1 = new Vector2D(3, 4);
		
		double length = v1.length();
		
		assertEquals(5.0, length);
	}
	

	@Test
	void originVector_aVector_becomesOriginVector() {
		Vector2D v = Point2D.subtract(new Point2D(1, 1), new Point2D(4, 7));
		
		
		Vector2D expected = new Vector2D(3, 6);
		assertEquals(v, expected);
	}
	
	@Test
	void angle_upVector_90Degrees() {
		Vector2D v = new Vector2D(0, 1);
		
		double angle = v.angle();
		
		assertEquals(90.0, angle);
	}

	@Test
	void angle_upRightVector_45Degrees() {
		Vector2D v = new Vector2D(1, 1);
		
		double angle = v.angle();
		
		assertEquals(45.0, angle);
	}
	
	@Test
	void angle_upLeftVector_135Degrees() {
		Vector2D v = new Vector2D(-1, 1);
		
		double angle = v.angle();
		
		assertEquals(135.0, angle);
	}
	
}