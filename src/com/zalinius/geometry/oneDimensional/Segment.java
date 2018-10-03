package com.zalinius.geometry.oneDimensional;

import com.zalinius.physics.Point2D;
import com.zalinius.utilities.Linear;

public class Segment {
	private Point2D start, end;

	public Segment(Point2D start, Point2D end) {
		this.start = start;
		this.end = end;
	}
	
	public double length() {
		return Point2D.distance(start, end);
	}
	
	public Point2D start() {
		return start;
	}
	
	public Point2D end() {
		return end;
	}
	
	public Linear getFunction() {
		if(length() == 0.0) {
			throw new ArithmeticException("A point does not have linear function");
		}
		else if(start.x() == end.x()) { //vertical line
			return new Linear(true, start.x());
		}
		else {
			double a = (start.y() - end.y())/
					   (start.x() - end.x());
			double b = start.y() - start.x()*a;
			return new Linear(a, b);
		}
	}
	
	
	
}
