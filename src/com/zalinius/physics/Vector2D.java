package com.zalinius.physics;

public class Vector2D {
	public double x;
	public double y;
	
	public Vector2D() {
		this(0.0, 0.0);
	}
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public static double distance(Vector2D v1, Vector2D v2) {
		double y = v1.y - v2.y;
		double x = v1.x - v2.x;
		
		return Math.sqrt(x*x + y*y);
	}
}
