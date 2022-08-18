package com.zalinius.zje.graphics;

import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;

import com.zalinius.zje.math.Geometry;
import com.zalinius.zje.physics.Point;

public class ShapeFactory {
	private ShapeFactory() {}
	
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
	
	public static Arc2D.Double centeredCircularArc(Point center, double radius, double startingAngle, double extent) {
		return centeredCircularArc(center, radius, startingAngle, extent, Arc2D.OPEN);
	}
	public static Arc2D.Double centeredCircularArc(Point center, double radius, double startingAngle, double extent, int type) {
		Rectangle2D.Double boundingBox = centeredSquare(center, 2*radius);
		return new Arc2D.Double(boundingBox, Math.toDegrees(startingAngle), Math.toDegrees(extent), type);
	}

	public static Path2D.Double regularPolygon(Point center, int sides, double radius){
		return polygon(Geometry.regularPolygon(center, sides, radius));
	}
	public static Path2D.Double polygon(List<Point> points) {
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
	
}
