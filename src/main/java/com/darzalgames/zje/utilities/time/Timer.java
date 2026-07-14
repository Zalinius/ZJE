package com.darzalgames.zje.utilities.time;

import com.darzalgames.zje.architecture.Logical;

public interface Timer extends Logical{
	public double timeLeft();
	public boolean isDone();
}
