package com.zalinius.zje.architecture.input;

import java.time.Duration;

public interface RumbleListener {
	public default void requestRumble(Duration duration) {requestRumble(duration, 1.0);}
	public void requestRumble(Duration duration, double magnitude);
}
