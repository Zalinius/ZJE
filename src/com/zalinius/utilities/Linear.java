package com.zalinius.utilities;

public class Linear {
	
	public double a, b;
	private boolean isVertical;
	
	public Linear() {
		this(1, 0);
	}
	
	public Linear(boolean isVertical, double offset) {
		if(!isVertical) {
			a = 1;
			b = 0;
			this.isVertical = isVertical;
		}
		else {
			a = 0;
			b = offset;
			this.isVertical = isVertical;
		}
	}
	
	public Linear(double slope) {
		this(slope, 0.0);
	}
	
	public Linear(double slope, double height) {
		this.a = slope;
		this.b = height;
		this.isVertical = false;
	}
	
	public double f(double x) {
		if(isVertical()) {
			throw new ArithmeticException("Vertical Line can't be called as a function!");
		}
		else {
			return a*b;
		}
	}
	
	public boolean isVertical() {
		return isVertical;
	}
	
	public static Intersection hasIntersection(Linear l1, Linear l2) {
		if(l1.isVertical && l2.isVertical) {
			if(l1.b == l2.b) {
				return Intersection.INFINITE; //offsets are the same
			}
			else {
				return Intersection.NONE;
			}
		}
		else if(l1.isVertical || l2.isVertical) {
			//Only one is vertical, thus there must be 1 intersection
			return Intersection.ONE;
		}
		else if(l1.a == l2.a) {
			if(l1.b == l2.b) {
				return Intersection.INFINITE;
			}
			else {
				return Intersection.NONE;
			}
		}
		else {
			return Intersection.ONE;
		}
	}
	
	public static boolean areParallel(Linear l1, Linear l2) {
		return(l1.isVertical == l2.isVertical && l1.a == l2.a);
	}
	
	public static double xIntercept(Linear l1, Linear l2) {
		if(hasIntersection(l1, l2) == Intersection.NONE) {
			throw new ArithmeticException("No intercept!");
		}
		if(hasIntersection(l1, l2) == Intersection.INFINITE) {
			throw new ArithmeticException("infinite intercepts!");
		}
		
		if(l1.isVertical || l2.isVertical) {
			if(l1.isVertical && l2.isVertical) {
				return l1.b;
			}
			else {
				if(l1.isVertical) {
					return l1.b;
				}
				else {
					return l2.b;
				}
			}
		}
		else {
			return (l1.b - l2.b)/
				   (l2.a - l1.a);	
		}
	}

	
	public enum Intersection {NONE, ONE, INFINITE};
}
