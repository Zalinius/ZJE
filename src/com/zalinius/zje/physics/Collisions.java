package com.zalinius.zje.physics;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Collisions {
	
	private Collisions() {}

	public static boolean intersection(Ellipse2D.Double circle, Line2D.Double segment) {
		Point center = new Point(circle.getCenterX(), circle.getCenterY());
		double radius = circle.width/2d;

		//transform to line at origin coordinates
		Point pointPrime = center.subtract(segment.x1, segment.y1);
		Vector linePrime = new Vector(segment.x1, segment.y1, segment.x2, segment.y2);

		double projectionMagnitude = linePrime.projectionMagnitude(pointPrime);

		Point closestPoint;

		if(projectionMagnitude >= 0 && projectionMagnitude <= linePrime.length()) {
			closestPoint = linePrime.normalize().scale(projectionMagnitude).add(segment.x1, segment.y1).toPoint();
		}
		else {
			if(segment.getP1().distance(center.x, center.y) < segment.getP2().distance(center.x, center.y)) {
				closestPoint = new Point(segment.getX1(), segment.getY1());
			}
			else {
				closestPoint = new Point(segment.getX2(), segment.getY2());
			}

		}
		return Point.distance(center, closestPoint) <= radius;
	}
	
	public static double distanceBetweenInfiniteLineAndCircle(Ellipse2D.Double circle, Line2D.Double infiniteLine) {
		Point center = new Point(circle.getCenterX(), circle.getCenterY());

		//transform to line at origin coordinates
		Point centerPrime = center.subtract(infiniteLine.x1, infiniteLine.y1);
		Vector linePrime = new Vector(infiniteLine.x1, infiniteLine.y1, infiniteLine.x2, infiniteLine.y2);

		double projectionMagnitude = linePrime.projectionMagnitude(centerPrime);

		Point closestPoint = linePrime.normalize().scale(projectionMagnitude).toPoint();
		return Point.distance(centerPrime, closestPoint);
	}

}
