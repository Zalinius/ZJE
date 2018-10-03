package com.zalinius.physics.collisions;

import com.zalinius.geometry.Circle;
import com.zalinius.geometry.Shape;
import com.zalinius.physics.Point2D;

public class Collision {

	public static boolean isOverlapping(Shape s1, Shape s2) {
		if(s1 instanceof Circle && s2 instanceof Circle) {
			Circle c1 = (Circle) s1;
			Circle c2 = (Circle) s2;
			return isOverlapping(c1, c2);
		}
		
		throw new RuntimeException("Overlapping of " + s1.getClass().toString() + " and " + s2.getClass().toString() + " has not been implemented yet");
	}
	
	private static boolean isOverlapping(Circle c1, Circle c2) {
		double distance = Point2D.distance(c1.center(), c2.center());
		double radii = c1.radius() + c2.radius();
		
		return distance < radii;
	}
}
