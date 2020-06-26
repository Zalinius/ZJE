package com.zalinius.utilities.time;

public class PassiveTimer implements Timer{

	private double length;
	
	public PassiveTimer() {
		this(0.0);
	}
	
	public PassiveTimer(double length) {
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
