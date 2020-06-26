package com.zalinius.geometry;

import com.zalinius.physics.Point2D;

public abstract class Shape {

	public abstract Point2D center();
	public abstract boolean contains(Point2D p);

}
