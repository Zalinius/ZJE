package com.zalinius.physics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.zalinius.physics.collisions.Collision;

import java.awt.geom.*;
import java.awt.*;

public class Vector2DTest {
	@Test
	public void distance_rectangularTriangle3And4_5() {
		Vector2D v1 = new Vector2D(3, 0);
		Vector2D v2 = new Vector2D(0, 4);
		
		double distance = Vector2D.distance(v1, v2);
		
		assertEquals(5.0, distance);
	}
	
	@Test
	public void meow() { //Checking
		Shape s1 = new Rectangle2D.Double();
		Shape s2 = new Rectangle2D.Double();
		
		assertThrows(RuntimeException.class, new Executable() {			
			@Override
			public void execute() throws Throwable {
				Collision.isOverlapping(s1, s2);
			}
		});
	}
}
