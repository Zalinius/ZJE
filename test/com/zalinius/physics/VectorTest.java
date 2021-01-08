package com.zalinius.physics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class VectorTest {
	@Test
	public void length_rectangularTriangle3And4_5() {
		Vector v1 = new Vector(3, 4);
		
		double length = v1.length();
		
		assertEquals(5.0, length);
	}
	
	@Test
	void angle_upVector_90Degrees() {
		Vector v = new Vector(0, 1);
		
		double angle = v.angleDegrees();
		
		assertEquals(90.0, angle);
	}

	@Test
	void angle_upRightVector_45Degrees() {
		Vector v = new Vector(1, 1);
		
		double angle = v.angleDegrees();
		
		assertEquals(45.0, angle);
	}
	
	@Test
	void angle_upLeftVector_135Degrees() {
		Vector v = new Vector(-1, 1);
		
		double angle = v.angleDegrees();
		
		assertEquals(135.0, angle);
	}
	
	@Test
	void angle_leftVector_180Degrees() {
		Vector v = new Vector(-1, 0);
		
		double angle = v.angleDegrees();
		
		assertEquals(180.0, angle);
	}
	
	@Test
	void angle_downLeftVector_225Degrees() {
		Vector v = new Vector(-1, -1);
		
		double angle = v.angleDegrees();
		
		assertEquals(225.0, angle);
	}
	
	@Test
	void angle_downVector_270Degrees() {
		Vector v = new Vector(0, -1);
		
		double angle = v.angleDegrees();
		
		assertEquals(270.0, angle);
	}
	
	@Test
	void angle_downRightVector_315Degrees() {
		Vector v = new Vector(1, -1);
		
		double angle = v.angleDegrees();
		
		assertEquals(315.0, angle);
	}
	
	@Test
	void angle_rightVector_0Degrees() {
		Vector v = new Vector(1, 0);
		
		double angle = v.angleDegrees();
		
		assertEquals(0.0, angle);
	}
	
	@Test
	void angle_unitCircleVector_60Degrees() {
		Vector v = new Vector(0.5, Math.sqrt(3) / 2.0);
		
		double angle = v.angleDegrees();
		
		assertEquals(60, angle);
	}	
	
	
	@Test
	void projectionMagnitude_perpendicularVectors_0() {
		Vector v1 = new Vector(0, 1);
		Vector v2 = new Vector(1, 0);
		
		double projection = v1.projectionMagnitude(v2);
		
		assertEquals(0, projection);
	}
	
	@Test
	void projectionMagnitude_parallelVectors_lengthOfProjectedVector() {
		Vector v1 = new Vector(1, 0);
		Vector v2 = new Vector(1, 0);
		Vector v3 = new Vector(4, 0);
		Vector v4 = new Vector(7, 9);
		
		double projection1 = v1.projectionMagnitude(v2);
		double projection2 = v3.projectionMagnitude(v4);
		
		assertEquals(1, projection1);
		assertEquals(7, projection2);
	}


	@Test
	void projection_parallelVectors_unchanged() {
		Vector v1 = new Vector(1, 0);
		Vector v2 = new Vector(2, 0);
		Vector v3 = new Vector(2, 4);
		Vector v4 = new Vector(4, 8);
		
		Vector projection1 = v1.projection(v2);
		Vector projection2 = v3.projection(v4);
		
		Vector expected1 = new Vector(v2);
		Vector expected2 = new Vector(v4);
		
		assertEquals(expected1, projection1);
		assertEquals(expected2, projection2);
	}	
	
	
	@Test
	void rejection_parallelVectors_zeroVector() {
		Vector v1 = new Vector(1, 0);
		Vector v2 = new Vector(2, 0);
		Vector v3 = new Vector(2, 4);
		Vector v4 = new Vector(4, 8);
		
		Vector rejection1 = v1.rejection(v2);
		Vector rejection2 = v3.rejection(v4);
		
		Vector zeroVector = new Vector();
		
		assertEquals(zeroVector, rejection1);
		assertEquals(zeroVector, rejection2);
	}	
	
	
	
}