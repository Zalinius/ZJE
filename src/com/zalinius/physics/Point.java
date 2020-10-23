package com.zalinius.physics;

/**
 * A class to represent a point in the 2D plane.
 * This class is IMMUTABLE.
 * @author Zach
 *
 */
public class Point2D {
	
	public final double x, y;

	public Point2D() {
		this(0, 0);
	}

	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point2D add(double dX, double dY) {
		return new Point2D(x + dX, y + dY);
	}
	
	public static Point2D add(Point2D p1, Point2D p2) {
		return new Point2D(p1.x + p2.x, p1.y +p2.y);
	}
	
	/**
	 * @param p
	 * @param v
	 * @return A new Point2D, which is p moved by an amount equivalent to v
	 */
	public static Point2D add(Point2D p, Vector2D v) {
		Point2D change = v.originVector().end;
		return Point2D.add(p, change);		
	}
	
	public static double distance(Point2D p1, Point2D p2) {
		double y = p1.y - p2.y;
		double x = p1.x - p2.x;
		
		return Math.sqrt(x*x + y*y);
	}

	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		else if(getClass() != obj.getClass()) {
			return false;
		}
		else {
			Point2D otherPoint = (Point2D) obj;
			return x == otherPoint.x && y == otherPoint.y;
		}
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}	
}
