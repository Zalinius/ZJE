package com.zalinius.physics;

public class UnitVector extends Vector2D{

	public UnitVector(double x, double y) {
		this(new Vector2D(x, y));
	}
	
	public UnitVector(Vector2D v) {
		super(v.x / v.length(), v.y / v.length());
	}
	
	public static UnitVector up() {
		return new UnitVector(new Vector2D(0, 1));
	}
	
	public static UnitVector down() {
		return new UnitVector(new Vector2D(0, -1));
	}
	
	public static UnitVector left() {
		return new UnitVector(new Vector2D(-1, 0));
	}
	
	public static UnitVector right() {
		return new UnitVector(new Vector2D(1, 0));
	}

}
