package com.zalinius.zje.math;

public class Interpolation {
	
	public static double linearInterpolant(double start, double end, double value) {
		return (value-start)/(end-start);
	}
	
	public static double linearInterpolation(double start, double end, double interpolant) {
		return (end - start)*interpolant + start;
	}
	
	/*
	 * Maps a value x from the value interval [valueStart, valueEnd] to the result interval [resultStart, resultEnd], in a linear fashion
	 */
	public static double linearMapping(double valueStart, double valueEnd, double value, double resultStart, double resultEnd) {
		return linearInterpolation(resultStart, resultEnd, linearInterpolant(valueStart, valueEnd, value));
	}

	public static double cosineInterpolation(double start, double end, double interpolant) {
		return (start-end)/2.0 * Math.cos(Math.PI * interpolant) + (start+end)/2.0;
	}
	
	public static double quadraticInterpolation(double interpolant, double start, double end) {
		return ((end - start) * Math.pow(interpolant, 2)) + start;
	}

}
