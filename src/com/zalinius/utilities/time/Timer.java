package com.zalinius.utilities.time;

import com.zalinius.architecture.Logical;

public interface Timer extends Logical{
	public double timeLeft();
	public boolean isDone();
}
