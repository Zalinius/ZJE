package com.zalinius.zje.math;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zalinius.zje.physics.Point;

public class Geometry {
	private Geometry() {}
	
	public static Path2D.Double makePolygonShape(List<Point> points) {
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
	
	public static Rectangle2D.Double centeredSquare(Point center, double width){
		return centeredRectangle(center, width, width);
	}
	
	public static Rectangle2D.Double centeredRectangle(Point center, double width, double height){
		return new Rectangle2D.Double(center.x - width/2, center.y - height/2, width, height);
	}

	public static Ellipse2D.Double centeredCircle(Point center, double radius){
		return centeredEllipse(center, 2*radius, 2*radius);
	}

	public static Ellipse2D.Double centeredEllipse(Point center, double width, double height){
		return new Ellipse2D.Double(center.x - width/2, center.y - height/2, width, height);
	}
	

}
