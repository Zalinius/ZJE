package com.zalinius.zje.physics;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Iterator;

/**
 * A class to represent a point in the 2D plane.
 * This class is IMMUTABLE.
 * @author Zalinius
 *
 */
public class Point {
	
	public final double x, y;

	public Point() {
		this(0, 0);
	}

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point add(double dX, double dY) {
		return new Point(x + dX, y + dY);
	}
	public Point add(Vector v) {
		return Point.add(this, v);
	}
	
	public static Point CreatePolarPoint(double radius, double angle) {
		return new Point(Math.cos(angle) * radius, Math.sin(angle) * radius);
	}
	
	public static Point add(Point p1, Point p2) {
		return new Point(p1.x + p2.x, p1.y +p2.y);
	}
	
	/**
	 * @param p
	 * @param v
	 * @return A new Point2D, which is p moved by an amount equivalent to v
	 */
	public static Point add(Point p, Vector v) {
		return p.add(v.x, v.y);		
	}
	
	public Point subtract(double dX, double dY) {
		return new Point(x - dX, y - dY);
	}

	
	public static double distance(Point p1, Point p2) {
		double y = p1.y - p2.y;
		double x = p1.x - p2.x;
		
		return Math.sqrt(x*x + y*y);
	}
	
	public static Point center(Collection<Point> points) {
		double x=0,y=0;
		for (Iterator<Point> it = points.iterator(); it.hasNext();) {
			Point point = it.next();
			x += point.x;
			y += point.y;
		}
		x/= points.size();
		y/= points.size();
		return new Point(x, y);
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}	
	
	
	public Point2D point2D() {
		return new Point2D.Double(x, y);
	}
	
	
	
}
