package com.zalinius.zje.math.random;

import com.zalinius.zje.physics.Point;

public class RandomVector {
	
	public static Point randomPointInCircle(double maxRadius) {
		return randomPointInCircle(new Point(), maxRadius);
	}
	public static Point randomPointInCircle(Point circleCenter, double maxRadius) {
		double radius = Math.random() * maxRadius;
		double angle = ZRand.randomAngle();
		
		Point pointAtOrigin = Point.CreatePolarPoint(radius, angle);
		return Point.add(circleCenter, pointAtOrigin);
	}
}
