package com.zalinius.zje.utilities.time;

import com.zalinius.zje.architecture.Logical;

public interface Timer extends Logical{
	public double timeLeft();
	public boolean isDone();
}
