package com.zalinius.zje.physics;

import java.awt.Shape;

public interface Collideable {
	public Shape shape();
	public default boolean isColliding(Point point) {return shape().contains(point.point2D());}
}
