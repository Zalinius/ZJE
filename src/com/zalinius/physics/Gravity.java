package com.zalinius.physics;

public class Gravity {
	public static final double GRAVITY = 9.81;
	
	public static Vector2D fall(double delta, Moveable object) {
		Vector2D v0 = object.velocity();
		return new Vector2D(v0.start, new Point2D(v0.end.x, v0.end.y + GRAVITY * delta));
	}
}
