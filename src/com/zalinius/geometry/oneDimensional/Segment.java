package com.zalinius.geometry.oneDimensional;

import com.zalinius.geometry.Shape;
import com.zalinius.physics.Point2D;
import com.zalinius.utilities.Linear;

public class Segment extends Shape{
	public final Point2D start;
	public final Point2D end;

	public Segment(Point2D start, Point2D end) {
		this.start = start;
		this.end = end;
	}
	
	public double length() {
		return Point2D.distance(start, end);
	}
	
	public Linear getFunction() {
		if(length() == 0.0) {
			throw new ArithmeticException("A point does not have linear function");
		}
		else if(start.x == end.x) { //vertical line
			return new Linear(true, start.x);
		}
		else {
			double a = (start.y - end.y)/
					   (start.x - end.x);
			double b = start.y - start.x*a;
			return new Linear(a, b);
		}
	}

	@Override
	public Point2D center() {
		return new Point2D((start.x + end.x) / 2, (start.y + end.y)/2);
	}

	@Override
	public boolean contains(Point2D p) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented!");
	}

	@Override
	public String toString() {
		return "[" + start + " => " + end + "]";
	}
	
		
}
