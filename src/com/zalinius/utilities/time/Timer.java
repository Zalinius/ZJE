package com.zalinius.utilities.time;

public class Timer {

	private double length;
	
	public Timer() {
		this(0.0);
	}
	
	public Timer(double length) {
		this.length = length;
	}
	
	public void update(double delta) {
		length -= delta;
		
		if(length < 0.0) {
			length  = 0.0;
		}
	}
	
	public double timeLeft() {
		return length;
	}
	
	public boolean isDone() {
		return length <= 0.0;
	}

}
