package com.zalinius.zje.physics;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Collisions {

	public static boolean intersection(Ellipse2D.Double circle, Line2D.Double line) {
		Point center = new Point(circle.getCenterX(), circle.getCenterY());
		double radius = circle.width/2d;

		//transform to line at origin coordinates
		Point pointPrime = center.subtract(line.x1, line.y1);
		Vector linePrime = new Vector(line.x1, line.y1, line.x2, line.y2);

		double projectionMagnitude = linePrime.projectionMagnitude(pointPrime);

		Point closestPoint;

		if(projectionMagnitude >= 0 && projectionMagnitude <= linePrime.length()) {
			closestPoint = linePrime.normalize().scale(projectionMagnitude).add(line.x1, line.y1).toPoint();
		}
		else {
			if(line.getP1().distance(center.x, center.y) < line.getP2().distance(center.x, center.y)) {
				closestPoint = new Point(line.getX1(), line.getY1());
			}
			else {
				closestPoint = new Point(line.getX2(), line.getY2());
			}

		}
		return Point.distance(center, closestPoint) <= radius;
	}

}
