package com.zalinius.physics;

public class Point2D {
	
	private double x, y;

	public Point2D() {
		this(0, 0);
	}

	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double x() {
		return x;
	}
	
	public void add(double dX, double dY) {
		x += dX;
		y += dY;
	}
	
	public double y() {
		return y;
	}
	
	public static Point2D add(Point2D p1, Point2D p2) {
		return new Point2D(p1.x + p2.x, p1.y +p2.y);
	}
	
	public static Point2D add(Point2D p, Vector2D v) {
		return new Point2D(p.x + v.x, p.y +v.y);
	}
	
	public static double distance(Point2D p1, Point2D p2) {
		double y = p1.y - p2.y;
		double x = p1.x - p2.x;
		
		return Math.sqrt(x*x + y*y);
	}

	public boolean equals(Point2D other) {
		return x == other.x && y == other.y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	
	
}
