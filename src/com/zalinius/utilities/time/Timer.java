package com.zalinius.utilities.time;

import com.zalinius.architecture.ILogical;

public interface Timer extends ILogical{
	public double timeLeft();
	public boolean isDone();
}
