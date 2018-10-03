package com.zalinius.physics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Vector2DTest {
	@Test
	public void distance_rectangularTriangle3And4_5() {
		Vector2D v1 = new Vector2D(3, 0);
		Vector2D v2 = new Vector2D(0, 4);
		
		double distance = Vector2D.distance(v1, v2);
		
		assertEquals(5.0, distance);
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