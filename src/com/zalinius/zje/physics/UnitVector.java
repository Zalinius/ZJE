package com.zalinius.zje.physics;

public class UnitVector extends Vector {
	public UnitVector(double x, double y) {
		this(new Vector(x, y));
	}

	public UnitVector(Vector v) {
		super(v.x / v.length(), v.y / v.length());

		if(v.x == 0 && v.y == 0) {
			throw new ArithmeticException("A unit vector cannot be initialized from a zero vector: " + v.toString());
		}
	}
	
	@Override
	public boolean isUnitVector() {
		return true;
	}

	public static UnitVector up() {
		return new UnitVector(new Vector(0, 1));
	}

	public static UnitVector down() {
		return new UnitVector(new Vector(0, -1));
	}

	public static UnitVector left() {
		return new UnitVector(new Vector(-1, 0));
	}

	public static UnitVector right() {
		return new UnitVector(new Vector(1, 0));
	}

}
