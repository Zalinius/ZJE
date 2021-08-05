package com.zalinius.zje.math;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zalinius.zje.physics.Point;

public class Geometry {
	private Geometry() {}
	
	public static Shape makePolygonShape(List<Point> points) {
		Path2D.Double path = new Path2D.Double();
		
		Iterator<Point> it = points.iterator();
		Point firstPoint = it.next();
		path.moveTo(firstPoint.x, firstPoint.y);
		while (it.hasNext()) {
			Point point = it.next();
			path.lineTo(point.x, point.y);
		}
		path.closePath();
		
		return path;
	}

	public static List<Point> regularPolygon(Point center, int sides, double radius) {
		List<Point> points = new ArrayList<>();
		sides = Math.max(sides, 3);
		
		for(int i = 0; i != sides; ++i) {
			double angle = (2*Math.PI) * ((i+.5) / sides);
			double x = radius * Math.cos(angle) + center.x;
			double y = radius * Math.sin(angle) + center.y;
			
			points.add(new Point(x, y));
		}
		
		return points;
	}
}
