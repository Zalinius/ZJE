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
	
	public Vector2D(Point2D start, Point2D end) {
		y = end.y() - start.y();
		x = end.x() - start.x();
	}

	public double length() {
		return Math.sqrt(x*x + y*y);
	}

	public void scale(double k) {
		x *= k;
		y *= k;
	}

	public double angle() {
		double angle = 0;
		if(x == 0) {
			if(y == 0) {
				throw new ArithmeticException("0 vector has no angle");
			}
			else if(y > 0) {
				angle = 90;
			}
			else {
				angle = 270;
			}
		}
		else if(y == 0) {
			if(x > 0) {
				angle = 0;
			}
			else {
				angle = 180;
			}
		}
		else {
			angle = Math.atan(y/x) *360 / (2*Math.PI);

			if(x < 0 && y < 0) {
				angle += 180;
			}
			else if(x > 0 && y < 0) {
				angle += 360;
			}
			else if(x < 0 && y > 0) {
				angle += 180;
			}
			else if(x > 0 && y > 0) {
				// do nothing
			}
		}


		return angle;
	}

	public static double distance(Vector2D v1, Vector2D v2) {
		double y = v1.y - v2.y;
		double x = v1.x - v2.x;

		return Math.sqrt(x*x + y*y);
	}

	public static Vector2D add(Vector2D v1, Vector2D v2) {
		return new Vector2D(v1.x + v2.x, v1.y +v2.y);
	}

	public static Vector2D subtract(Vector2D v1, Vector2D v2) {
		return new Vector2D(v1.x - v2.x, v1.y - v2.y);
	}

	public static double dotProduct(Vector2D v1, Vector2D v2) {
		return v1.x * v2.x + v1.y * v2.y;
	}
}