package com.zalinius.physics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Vector2DTest {
	@Test
	public void length_rectangularTriangle3And4_5() {
		Vector v1 = new Vector(3, 4);
		
		double length = v1.length();
		
		assertEquals(5.0, length);
	}
	
	@Test
	void originVector_aVectorAlreadyAtOrigin_theSame() {
		Vector v = new Vector(4, 7);
		
		Vector originV = v.originVector();
		
		assertEquals(v, originV);
	}
	
	@Test
	void originVector_aVector_becomesOriginVector() {
		Vector v = new Vector(new Point(1, 1), new Point(4, 7));
		
		Vector originV = v.originVector();
		
		Vector expected = new Vector(3, 6);
		assertEquals(originV, expected);
	}
	
	@Test
	void angle_upVector_90Degrees() {
		Vector v = new Vector(0, 1);
		
		double angle = v.angle();
		
		assertEquals(90.0, angle);
	}

	@Test
	void angle_upRightVector_45Degrees() {
		Vector v = new Vector(1, 1);
		
		double angle = v.angle();
		
		assertEquals(45.0, angle);
	}
	
	@Test
	void angle_upLeftVector_135Degrees() {
		Vector v = new Vector(-1, 1);
		
		double angle = v.angle();
		
		assertEquals(135.0, angle);
	}
	
	@Test
	void angle_leftVector_180Degrees() {
		Vector v = new Vector(-1, 0);
		
		double angle = v.angle();
		
		assertEquals(180.0, angle);
	}
	
	@Test
	void angle_downLeftVector_225Degrees() {
		Vector v = new Vector(-1, -1);
		
		double angle = v.angle();
		
		assertEquals(225.0, angle);
	}
	
	@Test
	void angle_downVector_270Degrees() {
		Vector v = new Vector(0, -1);
		
		double angle = v.angle();
		
		assertEquals(270.0, angle);
	}
	
	@Test
	void angle_downRightVector_315Degrees() {
		Vector v = new Vector(1, -1);
		
		double angle = v.angle();
		
		assertEquals(315.0, angle);
	}
	
	@Test
	void angle_rightVector_0Degrees() {
		Vector v = new Vector(1, 0);
		
		double angle = v.angle();
		
		assertEquals(0.0, angle);
	}
	
	@Test
	void angle_unitCircleVector_60Degrees() {
		Vector v = new Vector(0.5, Math.sqrt(3) / 2.0);
		
		double angle = v.angle();
		
		assertEquals(60, angle);
	}
	
}