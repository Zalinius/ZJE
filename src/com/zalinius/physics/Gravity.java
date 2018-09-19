package com.zalinius.physics;

public class Gravity {
	public static final double GRAVITY = 9.81;
	
	public static void fall(double delta, Moveable object) {
		object.velocity().y += GRAVITY * delta;
	}
}
