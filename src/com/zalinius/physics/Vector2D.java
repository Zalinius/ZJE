package com.zalinius.physics;

public class Vector2D {
	public final double x;
	public final double y;

	public Vector2D() {
		this(0,0);
	}

	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(Point2D tail, Point2D tip) {
		this(tip.x - tail.x, tip.y - tail.y);
	}
	
	/**
	 * Creates a new vector, pointing at tip.
	 * @param tip The tip of the vector
	 */
	public Vector2D(Point2D tip) {
		this(tip.x, tip.y);
	}
	
	public static Vector2D zeroVector() {
		return new Vector2D();
	}
	
	/**
	 * Scales a vector by moving its end point, but not its start point.
	 * @param k The scaling scalar
	 * @return A new scaled vector
	 */
	public Vector2D scale(double k) {
		return new Vector2D(k * x, k * y);
	}

	public boolean isZeroVector() {
		return x == 0.0 & y == 0.0;
	}
	
	public boolean isUnitVector() {
		return length() == 1.0;
	}
	
	public double length() {
		return Math.sqrt(x*x + y*y);
	}
	
	public UnitVector normalize() {
		return new UnitVector(this);
	}

	/**
	 * Adds the v2 to v1
	 * @param v1
	 * @param v2
	 * @return A new vector, which starts at this vectors source
	 */
	public Vector2D add(Vector2D other) {
		return new Vector2D(x + other.x, y + other.y);
	}

	public static double dotProduct(Vector2D v1, Vector2D v2) {		
		return(v1.x * v2.x + v1.y * v2.y);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		else if(getClass() != obj.getClass()) {
			return false;
		}
		else {
			Vector2D otherVector = (Vector2D) obj;
			return x == otherVector.x && y == otherVector.y;
		}
	}
	
	public double angle() {
		if(x == 0 && y == 0) {
			return Double.NaN;
		}
		else if(x != 0 && y == 0) {
			if(x > 0)
				return 0.0;
			else
				return 180.0;
		}
		else if(x == 0 && y !=0) {
			if(y > 0)
				return 90.0;
			else
				return 270.0;
		}
		else {
			double theta = Math.atan(y/x) / Math.PI * 180.0;
			if(x > 0 && y > 0) {
				return theta;
			}
			else if(x < 0 && y > 0) {
				return theta + 180;
			}
			else if(x < 0 && y < 0) {
				return theta + 180;
			}
			else {
				return theta + 360;
			}
		}
	}
	
	@Override
	public String toString() {
			return "<" + x + ", " + y + ">";
	}
}