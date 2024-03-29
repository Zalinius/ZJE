package com.zalinius.zje.math;

import com.zalinius.zje.physics.Point;

public class ZMath {
	private ZMath() {}

	/**
	 * Constrains a value between two bounds (inclusive).
	 * The bounds need not be ordered.
	 * @return The value if it is within the two values, else the closest bound to the value.
	 */
	public static double clamp(double value, double bound1, double bound2){
		double clampedValue = value;
		double leftBound = bound1;
		double rightBound = bound2;

		if(bound2 < bound1) {
			leftBound = bound2;
			rightBound = bound1;
		}

		if(value < leftBound) {
			clampedValue = leftBound;
		}
		else if(value > rightBound) {
			clampedValue = rightBound;
		}

		return clampedValue;
	}

	/**
	 * Constrains a value between two values (inclusive).
	 * The bounds need not be ordered.
	 * @return The value if it is within the two values, else the closest bound to the value.
	 */
	public static int clamp(int value, int leftBound, int rightBound) {
		return (int)clamp((double)value, leftBound, rightBound);
	}

	/**
	 * Constrains a value to be outside of two bounds (inclusive).
	 * The bounds need not be ordered.
	 * @return The value if it is outside the two values, else the closest bound to the value.
	 */
	public static double xClamp(double value, double bound1, double bound2) {
		double clampedValue = value;
		double leftBound = bound1;
		double rightBound = bound2;

		if(bound2 < bound1) {
			leftBound = bound2;
			rightBound = bound1;
		}

		if(value > leftBound && value < rightBound) {
			double midPoint = (leftBound + rightBound) / 2.0;

			if(value <= midPoint) {
				clampedValue = leftBound;
			}
			else { // then value > midPoint
				clampedValue = rightBound;
			}

		}
		return clampedValue;
	}

	/**
	 * Constrains a value to be outside of two bounds (inclusive).
	 * The bounds need not be ordered.
	 * @return The value if it is within the two values, else the closest bound to the value.
	 */
	public static int xClamp(int value, int bound1, int bound2) {
		return (int) xClamp((double)value, bound1, bound2);
	}


	public static Point clamp(Point point, Point bound1, Point bound2) {
		double clampedX = clamp(point.x, bound1.x, bound2.x);
		double clampedY = clamp(point.y, bound1.y, bound2.y);

		return new Point(clampedX, clampedY);
	}


	public static boolean isBetween(double left, double right, double x) {
		if(left == right) {
			return x == left;
		}
		else if(right < left) {
			return x <= left && x >= right;
		}
		else {
			return x >= left && x <= right;
		}
	}

	public static boolean isStrictlyBetween(double left, double right, double x) {
		if(left == right) {
			return false;
		}
		else if(right < left) {
			return x < left && x > right;
		}
		else {
			return x > left && x < right;
		}
	}
}
