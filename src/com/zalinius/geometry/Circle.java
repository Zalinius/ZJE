package com.zalinius.geometry;

import com.zalinius.physics.Point2D;

public class Circle extends Shape {
	private Point2D center;
	private double radius;
	
	public Circle(Point2D center, double radius) {
		this.center = center;
		this.radius = radius;	
	}
	
	public boolean contains(Point2D p) {
		return Point2D.distance(p, center) < radius;
	}

	public boolean contains(double x, double y) {
		return contains(new Point2D(x, y));
	}

	public Point2D center() {
		return center;
	}
	
	public double radius() {
		return radius;
	}

}
