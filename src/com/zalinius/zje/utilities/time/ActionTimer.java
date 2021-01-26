package com.zalinius.zje.utilities.time;

public class ActionTimer implements Timer{

	private Runnable action;
	private double length;
	
	public ActionTimer(Runnable action, double length) {
		this.action = action;
		this.length = length;
	}

	@Override
	public void update(double delta) {
		length -= delta;
		
		if(length < 0.0) {
			length = 0.0;
		}
		
		if(length == 0.0) {
			action.run();
			GameClock.removeTimer(action);
		}
	}

	@Override
	public double timeLeft() {
		return length;
	}

	@Override
	public boolean isDone() {
		return length <= 0;
	}
	
	

}
