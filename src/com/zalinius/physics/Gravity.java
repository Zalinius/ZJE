package com.zalinius.physics;

public class Gravity {
	public static final double GRAVITY = 9.81;
	
	public static Vector fall(double delta, Moveable object) {
		Vector v0 = object.velocity();
		return new Vector(v0.start, new Point(v0.end.x, v0.end.y + GRAVITY * delta));
	}
}
