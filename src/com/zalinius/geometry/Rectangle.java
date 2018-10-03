package com.zalinius.geometry;

import com.zalinius.physics.Point2D;
import com.zalinius.physics.Vector2D;
import com.zalinius.utilities.ZMath;

public class Rectangle extends Shape{
	private Point2D position;
	private double width, height;
	 	
	public Rectangle(double width, double height) {
		this(new Point2D(), width, height);
	}
	
	public Rectangle(Point2D position, double width, double height) {
		this.position = position;
		this.width = width;
		this.height = height;
	}

	public double diagonal() {
		return Math.sqrt(width*width + height*height);
	}
	
	public double area() {
		return width*height;
	}
	
	public Point2D center() {
		return Point2D.add(position, new Vector2D(width/2, height/2));
	}

	@Override
	public boolean contains(Point2D p) {
		return ZMath.isBetween(position.x(), position.x() + width, p.x())
			&& ZMath.isBetween(position.y(), position.y() + width, p.y());
	}
}
