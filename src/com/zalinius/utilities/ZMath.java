package com.zalinius.utilities;

import com.zalinius.physics.Point2D;

public class ZMath {

    public static double clamp(double value, double leftBoundary, double rightBoundary){
        double clampedValue = value;
        double realLeftBound = leftBoundary;
        double realRightBound = rightBoundary;
        
        if(rightBoundary < leftBoundary) {
        	realLeftBound = rightBoundary;
        	realRightBound = leftBoundary;
        }
        
        if(value < realLeftBound) {
        	clampedValue = realLeftBound;
        }
        else if(value > realRightBound) {
        	clampedValue = realRightBound;
        }

        return clampedValue;
    }
    
    public static Point2D clamp(Point2D point, Point2D leftbound, Point2D rightBound) {
    	double clampedX = clamp(point.x(), leftbound.x(), rightBound.x());
    	double clampedY = clamp(point.y(), leftbound.y(), rightBound.y());
    	
    	return new Point2D(clampedX, clampedY);
    }
    
    public static boolean isBetween(double left, double right, double x) {
    	if(left == right) {
    		return false;
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
