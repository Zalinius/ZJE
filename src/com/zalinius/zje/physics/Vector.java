package com.zalinius.zje.physics;

import java.awt.geom.Line2D;

public class Vector {
	public final double x;
	public final double y;

	public Vector() {
		this(0,0);
	}

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Creates a new vector pointing at Point.
	 * @param point The endpoint of the vector
	 */
	public Vector(Point point) {
		this(point.x, point.y);
	}
	
	public Vector(Vector vector) {
		this.x = vector.x;
		this.y = vector.y;
	}

	public Vector(double x1, double y1, double x2, double y2) {
		this.x = x2 - x1;
		this.y = y2 - y1;
	}
	
	public Vector(Point start, Point end) {
		this(start.x, start.y, end.x, end.y);
	}
	
	public Vector(Line2D.Double line) {
		this(line.x1, line.y1, line.x2, line.y2);
	}
	

	public double length() {
		return Math.sqrt(x*x + y*y);
	}
	
	
	public double absoluteAngle() {
		double angle = Math.atan2(y, x);
		if(angle < 0) {
			angle = 2*Math.PI + angle;
		}
		return angle;
	}
	
	/**
	 * @return the vector's angle in degrees
	 */
	public double absoluteAngleDegrees() {
		return Math.toDegrees(absoluteAngle());
	}

	/**
	 * Scales a vector by moving its end point, but not its start point.
	 * @param k The scaling scalar
	 * @return A new scaled vector
	 */
	public Vector scale(double k) {
		return new Vector(k*x, k*y);
	}

	public boolean isZeroVector() {
		return x == 0 && y == 0;
	}

	public boolean isNonZero() {
		return x != 0 || y != 0;
	}
	
	public boolean isUnitVector() {
		return length() == 1.0;
	}
	
	/**
	 * Adds the v2 to v1
	 * @return A new vector, which starts at this vectors source
	 */
	public Vector add(Vector other) {
		return new Vector(x + other.x, y + other.y);
	}
	public Vector add(double dx, double dy) {
		return new Vector(x + dx, y + dy);
	}
	public Vector subtract(Vector other) {
		return subtract(other.x, other.y);
	}
	public Vector subtract(double dx, double dy) {
		return new Vector(x - dx, y - dy);
	}

	public static double dotProduct(Vector v1, Vector v2) {
		return(v1.x * v2.x + v1.y * v2.y);
	}
	
	public UnitVector normalize() {
		return new UnitVector(this);
	}
	
	/**
	 * @return A vector pointing in the opposite direction as this one
	 */
	public Vector reflect() {
		return scale(-1);
	}
	
	/**
	 * @return A vector rotated a quarter turn in the clockwise direction
	 */
	public Vector perpendicularCW() {
		return new Vector(-y, x);
	}
	/**
	 * @return A vector rotated a quarter turn in the counter-clockwise direction
	 */
	public Vector perpendicularCCW() {
		return new Vector(y, -x);
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "<" + x + ", " + y + ">";
	}

	//Projects a point onto a vector
	public Point projection(Point point) {
		Vector pointVector = new Vector(point);
		Vector result = projection(pointVector);
		return new Point(result.x, result.y);
	}
	public Vector projection(Vector vector) {
		Vector normalizedTarget = this.normalize();
		double resultMagnitude = dotProduct(vector, normalizedTarget);
		return normalizedTarget.scale(resultMagnitude);		
	}

	public double projectionMagnitude(Point point) {
		Vector pointVector = new Vector(point);
		return projectionMagnitude(pointVector);
	}
	
	public double projectionMagnitude(Vector vector) {
		return dotProduct(vector, this.normalize());
	}
	
	public Vector reflection(Vector reflectee) {
		return this.projection(reflectee).subtract(this.rejection(reflectee));
	}

	public Vector rejection(Vector vector) {
		return vector.subtract(projection(vector));
	}
	public Vector rejection(Point point) {
		Vector pointVector = new Vector(point);
		return pointVector.subtract(projection(pointVector));
	}

	
	public Point toPoint() {
		return new Point(x, y);
	}
	
}