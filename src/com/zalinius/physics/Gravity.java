package com.zalinius.physics;

public class Gravity {
	public static final double GRAVITY = 9.81;
	
	public static Vector2D fall(double delta, Moveable object) {
		Vector2D v0 = object.velocity();
		return new Vector2D(v0.x, v0.y + GRAVITY * delta);
	}
}
