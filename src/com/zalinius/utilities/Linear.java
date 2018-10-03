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
	
	public boolean isVertical() {
		return isVertical;
	}
	
	public static boolean hasIntersection(Linear l1, Linear l2) {
		if(l1.isVertical && l2.isVertical) {
			if(l1.b == l2.b) {
				return true; //offsets are the same
			}
			else {
				return false;
			}
		}
		else if(l1.a == l2.a) {
			return l1.b == l2.b;
		}
		else {
			return true;
		}
	}
	
	public static double xIntercept(Linear l1, Linear l2) {
		if(!hasIntersection(l1, l2)) {
			throw new ArithmeticException();
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

}
