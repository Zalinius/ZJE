package com.zalinius.geometry;

import java.awt.*;
import java.awt.geom.*;

import com.zalinius.physics.Vector2D;

public class Circle implements Shape{
	private Ellipse2D circle;
	private double radius;
	
	public Circle(Vector2D position, double radius) {
		circle = new Ellipse2D.Double(position.x, position.y, radius * 2, radius * 2);
		this.radius = radius;
	}
	
	@Override
	public boolean contains(Point2D p) {
		return circle.contains(p);
	}

	@Override
	public boolean contains(Rectangle2D r) {
		return circle.contains(r);
	}

	@Override
	public boolean contains(double x, double y) {
		return circle.contains(x, y);
	}

	@Override
	public boolean contains(double x, double y, double w, double h) {
		return circle.contains(x, y, w, h);
	}

	@Override
	public Rectangle getBounds() {
		return circle.getBounds();
	}

	@Override
	public Rectangle2D getBounds2D() {
		return circle.getBounds2D();
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		return circle.getPathIterator(at);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return circle.getPathIterator(at, flatness);
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		return circle.intersects(r);
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		return circle.intersects(x, y, w, h);
	}
	
	public Vector2D center() {
		return new Vector2D(circle.getX(), circle.getY());
	}
	
	public double radius() {
		return radius;
	}

}
