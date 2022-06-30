package com.zalinius.zje.math.random;

import com.zalinius.darzalcommon.random.RandomExtended;
import com.zalinius.zje.physics.Point;

public class RandomVector {
	private static RandomExtended rand = new RandomExtended();
	
	private RandomVector() {}
	
	public static Point randomPointInCircle(double maxRadius) {
		return randomPointInCircle(new Point(), maxRadius);
	}
	public static Point randomPointInCircle(Point circleCenter, double maxRadius) {
		double radius = Math.random() * maxRadius;
		double angle = rand.nextRadian();
		
		Point pointAtOrigin = Point.createPolarPoint(radius, angle);
		return Point.add(circleCenter, pointAtOrigin);
	}
}
