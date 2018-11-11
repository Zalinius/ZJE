package com.zalinius.physics;

public class Vector2D {
	public final Point2D start;
	public final Point2D end;

	public Vector2D() {
		this(new Point2D(), new Point2D());
	}

	public Vector2D(double x, double y) {
		this(new Point2D(), new Point2D(x, y));
	}
	
	/**
	 * Creates a new origin-vector, ending at end.
	 * @param end The endpoint of the vector
	 */
	public Vector2D(Point2D end) {
		this(new Point2D(), end);
	}
	
	public Vector2D(Point2D start, Point2D end) {
		this.start = start;
		this.end = end;
	}

	public double length() {
		return Point2D.distance(start, end);
	}

	/**
	 * Scales a vector by moving its end point, but not its start point.
	 * @param k The scaling scalar
	 * @return A new scaled vector
	 */
	public Vector2D scale(double k) {
		Vector2D base = originVector();
		Vector2D scaledBase = new Vector2D(base.end.x * k, base.end.y * k);
		
		return new Vector2D(start, new Point2D(start.x + scaledBase.end.x, start.y + scaledBase.end.y));
	}

	/**
	 * @return An equivalent vector, but starting at the origin.
	 */
	public Vector2D originVector() {
		return new Vector2D(end.x - start.x, end.y - start.y);
	}

	public boolean isZeroVector() {
		return start.equals(end);
	}
	
	public boolean isUnitVector() {
		return length() == 1.0;
	}
	
	public boolean isOriginVector() {
		return start.equals(new Point2D());
	}

	/**
	 * Adds the v2 to v1
	 * @param v1
	 * @param v2
	 * @return A new vector, which starts at this vectors source
	 */
	public Vector2D add(Vector2D other) {
		Vector2D change = other.originVector();
		return new Vector2D(start, Point2D.add(end, change.end));
	}

	public static double dotProduct(Vector2D v1, Vector2D v2) {
		Point2D p1 = v1.originVector().end;
		Point2D p2 = v2.originVector().end;
		
		return(p1.x * p2.x + p1.y * p2.y);
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
			return start.equals(otherVector.start) && end.equals(otherVector.end);
		}
	}
	
	@Override
	public String toString() {
		if(isOriginVector()) {
			return "<" + end.x + ", " + end.y + ">";
		}
		else {
			return "<" + start.toString() + "; " + end.toString() + ">";
		}
	}
}