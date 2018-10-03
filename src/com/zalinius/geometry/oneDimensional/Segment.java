package com.zalinius.geometry.oneDimensional;

import com.zalinius.physics.Point2D;

public class Segment {
	private Point2D start, end;

	public Segment(Point2D start, Point2D end) {
		this.start = start;
		this.end = end;
	}
	
	public double length() {
		return Point2D.distance(start, end);
	}
	
	
	
}
