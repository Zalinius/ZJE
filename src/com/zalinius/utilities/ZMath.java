package com.zalinius.utilities;

public class ZMath {

    public static double clamp(double value, double leftBoundary, double rightBoundary){
        assert (leftBoundary <= rightBoundary);
        double clampedValue = value;
        
        if(value < leftBoundary)
        	clampedValue = leftBoundary;
        else if(value > rightBoundary)
        	clampedValue = rightBoundary;

        return clampedValue;
    }
    
    public static boolean isBetween(double left, double right, double x) {
    	return x > left && x < right;
    }
}
