package com.zalinius.zje.math;

import java.util.ArrayList;
import java.util.List;

import com.zalinius.zje.physics.Point;

public class Geometry {
	private Geometry() {}
	

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
